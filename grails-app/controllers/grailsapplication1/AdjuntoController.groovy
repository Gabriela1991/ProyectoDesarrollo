package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import org.apache.commons.io.FileUtils
import java.io.File
import com.dropbox.client2.DropboxAPI.Entry;

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
       //OJO COLOCAR ID DE LA NOTA
        def adjuntos=Adjunto.executeQuery("select cast(archivo as string)from Adjunto where nota_id=21");
        def adjuntoInstanceList = []
       
        if( adjuntos.toList() ){
            adjuntos.each(){ archivo->
               
                adjuntoInstanceList.add( archivo )
            }
        }
        [ adjuntoInstanceList: adjuntoInstanceList ]

    } 

    def delete = {
        def Dropbox d=new Dropbox();
        String claves=session.persona.keysdropbox;
        def filename = params.id.replace('###', '.')    
        d.eliminarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
        def adjuntoInstance=Adjunto.findByArchivo(filename)
        adjuntoInstance.delete();
        flash.message = "El archivo ' ${filename}' ha sido eliminado" 
        redirect( action:list )
    }
    
    def download = {
        def Dropbox d=new Dropbox();
        String claves=session.persona.keysdropbox;
        def filename = params.id.replace('###', '.')
        String busqueda=d.buscarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
        redirect (url:busqueda)        
    }

    def upload = {
        def Dropbox d=new Dropbox();
        String claves=session.persona.keysdropbox;
        def f = request.getFile('fileUpload')
        String fileNameToCreate
        if(!f.empty) {
            fileNameToCreate =  f.getOriginalFilename();
            File file 
            file = new File(fileNameToCreate);
            FileUtils.writeByteArrayToFile(file, f.getBytes());

            def personaInstance=session.persona
           
           f.transferTo( file )								             			     	
            def nombreArchivo=d.subirArchivo(file,claves.split('/')[0].toString(),claves.split('/')[1].toString())
            flash.message = 'Tu archivo ha sido adjuntado'
            def adjuntoInstance=new Adjunto(params)
            adjuntoInstance.archivo=nombreArchivo
           
            // OJO CAMBIAR NOMBRE DE ARCHIVO E ID DE LA NOTA
            
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
