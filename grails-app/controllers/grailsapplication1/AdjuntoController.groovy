package grailsapplication1
import java.awt.Desktop;
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
      
    
    /**
     *
     * Me redirige a la seccion de mostrar todos los adjuntos
     */
    def index() {
        redirect(action: "list", params: params)
    }

    
    /**
     *
     *Crea un adjunto nuevo para una nota
     */
    def create() {
        [adjuntoInstance: new Adjunto(params)]
    }

    
    /**
     *
     * Guarda los cambios realizados sobre un adjunto
     */
    def save() {
        def adjuntoInstance = new Adjunto(params)
        if (!adjuntoInstance.save(flush: true)) {
            render(view: "create", model: [adjuntoInstance: adjuntoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), adjuntoInstance.id])
        redirect(action: "show", id: adjuntoInstance.id)
    }

    
    /**
     *
     *Muestra un conjunto de adjuntos pertenecientes a una nota en especifico
     */
    def show(Long id) {
        def adjuntoInstance = Adjunto.get(id)
        if (!adjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), id])
            redirect(action: "list")
            return
        }

        [adjuntoInstance: adjuntoInstance]
    }

    
     /**
     *
     * Envia a la opcion de editar un adjunto seleccionado, si este se encuentra
     * almacenado en la lista de adjuntos
     */
    def edit(Long id) {
        def adjuntoInstance = Adjunto.get(id)
        if (!adjuntoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adjunto.label', default: 'Adjunto'), id])
            redirect(action: "list")
            return
        }

        [adjuntoInstance: adjuntoInstance]
    }

    
    /**
     *
     * Permite realizar cambio sobre los adjuntos seleccionados, de ser exitosa la
     * modificacion, se linkeara a la seccion de mostrar adjuntos, de lo contrario
     * mostrara un msj de error indicando el fallo de la transaccion
     */
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

    
    /**
     *
     * Muestra por pantalla un listado de todos los adjuntos pertenecientes
     * a una nota en especifico
     */
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

    
    /**
     *
     * Elimina de una nota un archivo adjunto que se ha especificado con 
     * anterioridad
     */
    def delete = {
        
                def nota=Nota.get(session.nota.id)
        
        def   idnota=nota.id
                
                def Dropbox d=new Dropbox();
        def claves
        
        
         if(session.persona.keysdropbox){
            
            claves=session.persona.keysdropbox;
        }
        else{
            def persona = Persona.findById(session.persona.id);
            claves=d.auth(session.persona.keysdropbox);
            persona.keysdropbox=claves;
            persona.save(flush:true); 
            session.persona=persona
        }
                def filename = params.id.replace('###', '.')    
                
                int resp = d.eliminarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
                    
                    if (resp==0){
                        flash.message= "Parece haber un problema de conexion o el archivo no esta en la cuenta de dropbox asociada";
                        redirect( action:"list", id:idnota )
                    } else {                  
                       
                        def adjuntoInstance=Adjunto.findByArchivo(filename)
                         log.info "Se ha eliminado un adjunto de dropbox id:"+adjuntoInstance.id
                        adjuntoInstance.delete();
                        log.info "Se ha eliminado un adjunto de base de datos id:"+adjuntoInstance.id
                        flash.message = "El archivo ' ${filename}' ha sido eliminado" 

                        redirect( action:"list", id:idnota )
                    }
    }
    
    
    /**
     *
     * Vista de archivos adjuntos pertenecientes a una nota, al dropbox
     */
    def download = {
        
        def Dropbox d=new Dropbox();
        def claves
        
        def nota=Nota.get(session.nota.id)
        
        def   idnota=nota.id
        
         if(session.persona.keysdropbox){
            
            claves=session.persona.keysdropbox;
        }
        else{
            def persona = Persona.findById(session.persona.id);
            claves=d.auth(session.persona.keysdropbox);
            persona.keysdropbox=claves;
            persona.save(flush:true); 
            session.persona=persona
        }
        
        
     
        def filename = params.id.replace('###', '.')
        if (claves!=null){
        String busqueda=d.buscarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
        if (!busqueda){
            flash.message = "El archivo no se encuentra en la cuenta de dropbox asociada o existen problemas con su conexión"
           // flash.message = "Parece no tener conexion en este momento, no se puede ver su archivo; intente de nuevo mas tarde"
             redirect(controller:"Nota", action:"show", id:session.nota.id)
        } else {
            flash.message="";
                    Desktop.getDesktop().browse(new URL(busqueda).toURI())    

           redirect (controller: "nota",action:"show",id:session.nota.id)    
        }
        }
        else {
            flash.message = "No hay vinculo con dropbox"
             redirect(controller:"Nota", action:"show", id:idnota)
        }
        
    }
    
    
    /**
     *
     * Descarga un adjunto indicado desde dropbox
     */
    def descargar = {
        
         def Dropbox d=new Dropbox();
        def claves
        
        def nota=Nota.get(session.nota.id)
        
        def   idnota=nota.id
         if(session.persona.keysdropbox){
            
            claves=session.persona.keysdropbox;
        }
        else{
            def persona = Persona.findById(session.persona.id);
            claves=d.auth(session.persona.keysdropbox);
            persona.keysdropbox=claves;
            persona.save(flush:true); 
            session.persona=persona
        }
        def filename = params.id.replace('###', '.')
        String busqueda=d.descargarArchivo(filename,claves.split('/')[0].toString(),claves.split('/')[1].toString())
         if (!busqueda){
            flash.message = "Parece no tener conexion en este momento, no se puede descargar su archivo; intente de nuevo mas tarde"
            redirect(controller:"Nota", action:"show", id:session.nota.id)
        } else {
            flash.message="";
           
            Desktop.getDesktop().browse(new URL(busqueda).toURI())    

           redirect (controller: "nota",action:"show",id:idnota)    
        }        
    }

    
    /**
     *
     * Carga un archivo adjunto indicado al dropbox
     * Cada vez que se cargue un archivo, se reflejara en la bitacora
     * @params id Identificador de la persona (usuario) en la BD
     */
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
            session.persona=persona
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
