package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException

class PersonaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def inicio = { 
    }
    
    def ventanaInicio (){
        def persona = Persona.findWhere(correo:params['correo'],
        clave:params['clave'])
        session.persona = persona
         if (persona){
           [persona:persona]
           //establece la conexion con dropbox
        //    Dropbox x=new Dropbox();
         // x.main();
      }
       else //si no se encuentra almacenado en la BD regresa a la ventana de login
           redirect(controller:'persona',action:'inicio')
       //     println (persona.id)
       //     [idpersona:persona.id]
    }
    
    //Aqui comprobamos que la cuenta introducida por el usuario se la que se
    //emcuentra almacenada en la BD y que le corresponde dicho password
  /*  def inicioSesion = {
    def persona = Persona.findWhere(correo:params['correo'],
        clave:params['clave'])
        session.persona = persona
         if (persona){
            redirect (controller:'Persona', action:'ventanaInicio')
           // ventanaInicio(persona)
                      //establece la conexion con dropbox
        //    Dropbox x=new Dropbox();
         // x.main();
      }
       else //si no se encuentra almacenado en la BD regresa a la ventana de login
           redirect(controller:'persona',action:'inicio')
    }
*/    
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [personaInstanceList: Persona.list(params), personaInstanceTotal: Persona.count()]
    }

    def create() {
        [personaInstance: new Persona(params)]
    }

    def save() { //los parametros del html los clono para poder modificarlos
        def personaInstance = new Persona(params)
        if (!personaInstance.save(flush: true)) {
            render(view: "create", model: [personaInstance: personaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'persona.label', default: 'Persona'), personaInstance.id])
        redirect(action: "show", id: personaInstance.id)
    }

    def show(Long id) {
        def personaInstance = Persona.get(session.persona.id)
        if (!personaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), id])
            redirect(action: "list")
            return
        }

        [personaInstance: personaInstance]
    }

    def edit(Long id) {
        def personaInstance = Persona.get(session.persona.id)
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
}
