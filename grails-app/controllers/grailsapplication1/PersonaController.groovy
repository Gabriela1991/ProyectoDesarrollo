package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.*
class PersonaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    private static Log log = LogFactory.getLog("bitacora."+PersonaController.class.getName())
    
    /**
     *
     * Me redirige a la seccion de mostrar todos los usuarios
     */
    def index() {
        redirect(action: "list", params: params)
    }


    /**
     *
     * Muestra por pantalla un listado de todas las personas registradas en la BD
     */
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [personaInstanceList: Persona.list(params), personaInstanceTotal: Persona.count()]
    }


    /**
     *
     *Crea un usuario nuevo
     */
    def create() { 
        if (session.persona) session.invalidate();
        [personaInstance: new Persona(params)]
    }

    
    /**
     *
     * Guarda los cambios realizados sobre un usuario, estos cambios se veran 
     * reflejados en el log
     */
    def save() {
        
        def personaInstance = new Persona(params)
       
        if (!personaInstance.save(flush: true)) {
            render(view: "create", model: [personaInstance: personaInstance])
            return
        }

        flash.message = message(code: 'default.created.cuentaCreada', args: ["El usuario ", personaInstance.correo])
        log.info "Se ha agregado un usuario a la base de datos con id: "+personaInstance.id    
        redirect(action: "inicio")
    }

    
    /**
    *
    *Muestra el detalle de un usuario en especifico, los datos que fueron ingresados
    *al momento de registrar un usuario en el aplicativo
    */
    def show(Long id) {
        def personaInstance
        if (session.persona)
        personaInstance= Persona.findById(session.persona.id)
        else personaInstance= Persona.get(id)
        if (!personaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "list")
            return
        }

        [personaInstance: personaInstance]
    }


    /**
     *
     * Envia a la opcion de editar un usuario seleccionado
     */
    def edit(Long id) {
        def personaInstance= Persona.findById(session.persona.id)
        if (!personaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "list")
            return
        }

        [personaInstance: personaInstance]
    }


    /**
     *
     * Permite realizar cambio sobre un usario, de ser exitosa la
     * modificacion, se linkeara a la seccion de mostrar los datos del usuario, de lo contrario
     * mostrara un msj de error indicando el fallo de la transaccion
     * Dichos cambios se veran reflejados en la bitacora de la aplicacion
     */
    def update(Long id, Long version) {
        def personaInstance = Persona.get(id)
        if (!personaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (personaInstance.version > version) {
                personaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'persona.label', default: 'Persona')] as Object[],
                          "Mientras usted editaba su perfil, otro usuario lo ha editado")
                render(view: "edit", model: [personaInstance: personaInstance])
                return
            }
        }

        personaInstance.properties = params
        personaInstance.clave=personaInstance.clave.encodeAsSHA()
        if (!personaInstance.save(flush: true)) {
            render(view: "edit", model: [personaInstance: personaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), personaInstance.id])
        log.info "Se ha editado el usuario con id: "+personaInstance.id    
        session.persona = personaInstance;
        redirect(action: "show", id: personaInstance.id)
    }
    

    /**
     *Comienza el inicio de sesion
     *
     */    
     def inicio = { 
         session.persona=null
     session.nota=null
    }
    
    
    /**
    *
    *Busca al usuario que quiere iniciar sesion en la BD
    */
    def ventanaInicio (){
        def persona = Persona.findById(session.persona.id)
    }
    

    /**
    *
    *Aqui comprobamos que la cuenta introducida por el usuario es la que se
    *encuentra almacenada en la BD y que le corresponde dicho password, de ser
    *asi inicia sesion tanto en el aplicativo como en dropbox
    */
    def inicioSesion = {
    
    def persona = Persona.findByCorreoAndClave(params['correo'],params.clave.encodeAsSHA())
        session.persona = persona
         if (persona){             
            log.info "El usuario con id: "+persona.id+" ha iniciado sesion en la aplicacion" 
            redirect (controller:'Persona', action:'ventanaInicio')
            
            //establece la conexion con dropbox
             Dropbox d=new Dropbox()

             String claves;
              if(session.persona.keysdropbox){
                  claves=d.auth(session.persona.keysdropbox);
                  claves=session.persona.keysdropbox;      
              }
              else
                claves=d.auth(session.persona.keysdropbox);

            if(claves!=null){  // Es porque la persona aun no tiene las claves de dropbox
                  session.persona.keysdropbox=claves                
                  session.persona.save(flush:true) 
                  log.info "Se ha otorgado las claves del dropbox al usuario con id: "+persona.id  
              } 
              else { // la persona ya posee las claves de acceso a dropbox
                  claves=session.persona.keysdropbox
                  try {
                     println("CLAVES 2 "+claves.split('/')[0].toString())
                  } catch (NullPointerException e){
                     log.info "El usuario con id: "+persona.id+" ha iniciado sesion sin acceso a internet"  
                     flash.message = message (code: 'default.not.conection');                
                  }
              }        
      }
       else{ //si no se encuentra almacenado en la BD regresa a la ventana de login
            flash.message = message(code: 'default.not.found.message', args: ["Correo o clave incorrectas, intente de nuevo"])
            redirect(controller:'persona',action:'inicio')
       }
    }
    
    
def desvincular = {
    def personaInstance=Persona.get(session.persona.id)
    
   
    if (personaInstance.keysdropbox!=null){
        
        personaInstance.keysdropbox=null
        personaInstance.merge()
        personaInstance.save(flush:true)
        session.persona=personaInstance
        session.persona.merge()
        flash.message= "Se ha cerrado la sesión en su cuenta de dropbox"
        redirect (controller:'Persona', action:'ventanaInicio')
        log.info "Se ha desinculado la cuenta de dropbox del usuario con id: "+personaInstance.id  
        }
        else {

            flash.message= "No ha iniciado sesión en Dropbox"
             redirect (controller:'Persona', action:'ventanaInicio')
        }
    
    }
}
