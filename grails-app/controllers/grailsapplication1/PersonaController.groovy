package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException

class PersonaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [personaInstanceList: Persona.list(params), personaInstanceTotal: Persona.count()]
    }

    def create() { 
        if (session.persona) session.invalidate();
        [personaInstance: new Persona(params)]
    }

    def save() {
        def personaInstance = new Persona(params)
        if (!personaInstance.save(flush: true)) {
            render(view: "create", model: [personaInstance: personaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: ["El usuario ", personaInstance.correo+" ha sido"])
        redirect(action: "inicio")
    }

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

    def edit(Long id) {
        def personaInstance= Persona.findById(session.persona.id)
        if (!personaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "list")
            return
        }

        [personaInstance: personaInstance]
    }

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
                          "Another user has updated this Persona while you were editing")
                render(view: "edit", model: [personaInstance: personaInstance])
                return
            }
        }

        personaInstance.properties = params

        if (!personaInstance.save(flush: true)) {
            render(view: "edit", model: [personaInstance: personaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), personaInstance.id])
        session.persona = personaInstance;
        redirect(action: "show", id: personaInstance.id)
    }

    def delete(Long id) {
        def personaInstance = Persona.get(id)
        if (!personaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "list")
            return
        }

        try {
            personaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "show", id: id)
        }
    }
    
    
     def inicio = { 
         session.persona=null
     
    }
    
    def ventanaInicio (){
        def persona = Persona.findById(session.persona.id)
  
    }
    
    //Aqui comprobamos que la cuenta introducida por el usuario se la que se
    //emcuentra almacenada en la BD y que le corresponde dicho password
    def inicioSesion = {
    def persona = Persona.findWhere(correo:params['correo'],
        clave:params['clave'])
        session.persona = persona
         if (persona){
            redirect (controller:'Persona', action:'ventanaInicio')
        //establece la conexion con dropbox
         Dropbox d=new Dropbox()
         println("verificar si ya se ha auth"+session.persona.keysdropbox)
         
         String claves;
          if(session.persona.keysdropbox){
              claves=d.auth(session.persona.keysdropbox);
            claves=session.persona.keysdropbox;      
          }
          else
            claves=d.auth(session.persona.keysdropbox);
            
          if(claves!=null){  // Es porque la persona aun no tiene las claves de dropbox
                session.persona.keysdropbox=claves
                 println("CLAVES 1 "+claves.split('/')[0].toString())
                session.persona.save(flush:true) 
            } 
            else { // la persona ya posee las claves de acceso a dropbox
                claves=session.persona.keysdropbox
                try {
                   println("CLAVES 2 "+claves.split('/')[0].toString())
                } catch (NullPointerException e){
                flash.message = message (code: 'default.not.conection');                
                }
            }
        
      }
       else{ //si no se encuentra almacenado en la BD regresa a la ventana de login
           flash.message = message(code: 'default.not.found.message', args: ["Correo o clave incorrectas, intente de nuevo"])
            redirect(controller:'persona',action:'inicio')
       }
    }
}
