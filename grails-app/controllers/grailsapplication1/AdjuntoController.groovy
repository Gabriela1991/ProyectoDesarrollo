package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import org.apache.commons.io.FileUtils
import java.io.File
import com.dropbox.client2.DropboxAPI.Entry;
import org.apache.commons.logging.*

class AdjuntoController {

    //  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static allowedMethods = []
    private static Log log = LogFactory.getLog("bitacora."+AdjuntoController.class.getName())
      
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

    def list (Long id) {
       //OJO COLOCAR ID DE LA NOTA
         def adjuntos=Adjunto.executeQuery("select cast(archivo as string)from Adjunto where nota_id=:idnota",[idnota:id]);
        def adjuntoInstanceList = []
       if (adjuntos!=null){
        if( adjuntos.toList() ){
            adjuntos.each(){ archivo->
               
                adjuntoInstanceList.add( archivo )
            }
        }
        [ adjuntoInstanceList: adjuntoInstanceList ]
       }
    } 

    def delete = {
        
                println("par"+params.nota+"  "+params.id)
                def idnota;
                if (session.nota.id==null)
                idnota=params.nota
                else
                idnota=session.nota.id
                def Dropbox d=new Dropbox();
                String claves=session.persona.keysdropbox;
                def filename = params.id.replace('###', '.')    
                println("filen"+filename)
                d.eliminarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
                log.info "Se ha eliminado un adjunto de dropbox"
                def adjuntoInstance=Adjunto.findByArchivo(filename)
                adjuntoInstance.delete();
                log.info "Se ha eliminado un adjunto de base de datos"
                flash.message = "El archivo ' ${filename}' ha sido eliminado" 

                redirect( action:"list", id:idnota )
    }
    
    def download = {
        println(params.nota)
        def Dropbox d=new Dropbox();
        String claves=session.persona.keysdropbox;
        def filename = params.id.replace('###', '.')
        String busqueda=d.buscarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
        redirect (url:busqueda)        
    }
    
    def descargar = {
        println(params.nota)
        def Dropbox d=new Dropbox();
        String claves=session.persona.keysdropbox;
        def filename = params.id.replace('###', '.')
        String busqueda=d.descargarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
        redirect (url:busqueda)        
    }

    def upload (Long id) {
        
        def Dropbox d=new Dropbox();
        String claves;
        
        def f = request.getFile('fileUpload')
        String fileNameToCreate
        
        if(session.persona.keysdropbox){
            claves=d.auth(session.persona.keysdropbox);
            claves=session.persona.keysdropbox;
        }
        else{
            def persona = Persona.findById(session.persona.id);
            claves=d.auth(session.persona.keysdropbox);
            persona.keysdropbox=claves;
            persona.save(flush:true); 
        }
            
        if(claves){
                if(!f.empty) {
                fileNameToCreate =  f.getOriginalFilename();
                File file 
                file = new File(fileNameToCreate);
                FileUtils.writeByteArrayToFile(file, f.getBytes());
                f.transferTo( file )
                def personaInstance=session.persona
                
                def nombreArchivo=d.subirArchivo(file,claves.split('/')[0].toString(),claves.split('/')[1].toString())

                file.delete()
                if (nombreArchivo){
                    flash.message = 'Tu archivo ha sido adjuntado'
                    def adjuntoInstance=new Adjunto(params)

                    adjuntoInstance.archivo=nombreArchivo
                    // OJO CAMBIAR NOMBRE DE ARCHIVO E ID DE LA NOTA

                    adjuntoInstance.nombre=session.nota.titulo
                    def notaInstance=Nota.get(session.nota.id)
                    adjuntoInstance.nota=notaInstance
                    adjuntoInstance.save(flush: true)
                    log.info "Se ha agregado un adjunto a la base de datos y a dropbox"
                } else flash.message= message (code: 'default.error.adjunto')
            }    
            else {
                flash.message = 'El archivo no puede ser vacío'
            }
        }else{
            flash.message= message (code: 'default.error.adjunto')
        }
        
        redirect( action:"list", id:session.nota.id)
    }
}
