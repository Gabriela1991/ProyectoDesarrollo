package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import org.apache.commons.io.FileUtils
import java.io.File

class AdjuntoController {

    //  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static allowedMethods = []
    
      
    def index() {
        redirect(action: "list", params: params)
    }

    

    def create() {
        [adjuntoInstance: new Adjunto(params)]
    }

    def save() {
        def adjuntoInstance = new Adjunto(params)
        if (!adjuntoInstance.save(flush: true)) {
            render(view: "create", model: [adjuntoInstance: adjuntoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), adjuntoInstance.id])
        redirect(action: "show", id: adjuntoInstance.id)
    }

    def show(Long id) {
        def adjuntoInstance = Adjunto.get(id)
        if (!adjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), id])
            redirect(action: "list")
            return
        }

        [adjuntoInstance: adjuntoInstance]
    }

    def edit(Long id) {
        def adjuntoInstance = Adjunto.get(id)
        if (!adjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), id])
            redirect(action: "list")
            return
        }

        [adjuntoInstance: adjuntoInstance]
    }

    def update(Long id, Long version) {
        def adjuntoInstance = Adjunto.get(id)
        if (!adjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (adjuntoInstance.version > version) {
                adjuntoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'adjunto.label', default: 'Adjunto')] as Object[],
                          "Another user has updated this Adjunto while you were editing")
                render(view: "edit", model: [adjuntoInstance: adjuntoInstance])
                return
            }
        }

        adjuntoInstance.properties = params

        if (!adjuntoInstance.save(flush: true)) {
            render(view: "edit", model: [adjuntoInstance: adjuntoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), adjuntoInstance.id])
        redirect(action: "show", id: adjuntoInstance.id)
    }

    def list = {
    
       
    
        def adjuntoInstanceList = []
        def f = new File( grailsApplication.config.images.location.toString() )
        if( f.exists() ){
            f.eachFile(){ file->
                if( !file.isDirectory() )
                adjuntoInstanceList.add( file.name )
            }
        }
        [ adjuntoInstanceList: adjuntoInstanceList ]
    } 

    def delete = {
        def Dropbox d=new Dropbox();
        String claves=session.persona.keysdropbox;
        def filename = params.id.replace('###', '.')
        println(params)
        println(filename)
        def file = new File( grailsApplication.config.images.location.toString() + File.separatorChar +   filename )
        d.eliminarArchivo(file.getName(),claves.split('/')[0].toString(),claves.split('/')[1].toString())
        file.delete()
       def adjuntoInstance=Adjunto.get(Adjunto.executeQuery("SELECT cast(Id as integer) FROM Adjunto where Archivo='"+filename+"'"))
        flash.message = "El archivo ' ${filename}' ha sido eliminado" 
        redirect( action:list )
    }

    def upload = {
        def Dropbox d=new Dropbox();
        String claves=session.persona.keysdropbox;
        def f = request.getFile('fileUpload')
       
        if(!f.empty) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile =(MultipartFile) multipartRequest.getFile("fileUpload");
            String fileNameToCreate = grailsApplication.config.images.location.toString() + File.separatorChar + f.getOriginalFilename();
            File file
		 
            file = new File(fileNameToCreate);
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
            file = new File( grailsApplication.config.images.location.toString() + File.separatorChar + f.getOriginalFilename() );
			
          
          
           println("verificar si ya se ha auth"+session.persona.keysdropbox)
            def personaInstance=session.persona
            if(session.persona.keysdropbox!=null){
                //personaInstance.keysdropbox=claves
                 println("CLAVES 3 "+session.persona.keysdropbox)
                //personaInstance.executeUpdate("update Persona set keysdropbox='"+claves+ "' where id="+personaInstance.id)
            }		
            
            new File( grailsApplication.config.images.location.toString() ).mkdirs()
            f.transferTo( file )								             			     	
            f.getOriginalFilename()	
            def nombreArchivo=d.subirArchivo(file,claves.split('/')[0].toString(),claves.split('/')[1].toString())
            flash.message = 'Tu archivo ha sido adjuntado'
            def adjuntoInstance=new Adjunto(params)
            adjuntoInstance.archivo=nombreArchivo
            adjuntoInstance.nombre='adj1'
            def notaInstance=Nota.get(21)
            adjuntoInstance.nota=notaInstance//
            adjuntoInstance.save(flush: true)
        }    
        else {
            flash.message = 'El archivo no puede ser vacío'
        }
        redirect( action:list)
    }
}
