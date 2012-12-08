package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.*


class LibretaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    private static Log log = LogFactory.getLog("bitacora."+LibretaController.class.getName())
    
    /**
     *
     * Me redirige a la seccion de mostrar todos las libretas
     */
    def index() {
        redirect(action: "list", params: params)
    }

    
    /**
     *
     * Muestra por pantalla un listado de todas las libretas pertenecientes
     * a un usuario en especifico
     */
    def list(Integer max) {
        def persona= Persona.findById(session.persona.id)
        params.max = Math.min(max ?: 10, 100)
        log.info "Se ha consultado la lista de libretas pertenecientes al usuario con id: "+persona.id
        [libretaInstanceList: persona.libretas, libretaInstanceTotal: Libreta.count()]
    }

    
    /**
     *
     *Crea una libreta nueva para un usuario
     */
    def create() {
        def persona= Persona.findById(session.persona.id);
        log.info "El usuario "+persona.id+" ha comenzado a crear una libreta"
        [libretaInstance: new Libreta(params)]
    }

    
    /**
     *
     * Guarda los cambios realizados sobre una libreta
     */
    def save() {
        def persona= Persona.findById(session.persona.id);
        def libretaInstance = new Libreta(params)
        libretaInstance.persona= persona
        if (!libretaInstance.save(flush: true)) {
            render(view: "create", model: [libretaInstance: libretaInstance])
            return
        }

        flash.message = "Su libreta ha sido creada correctamente"
        log.info "Se ha agregado una libreta a la base de datos con id: "+libretaInstance.id
        redirect(action: "show", id: libretaInstance.id)
    }
    
    
    /**
    *
    *Muestra el detalle de una libreta en especifico
    */
    def show(Long id) {
         NotaController.ultimoidlibreta=null;
         NotaController.numero=0;
         def persona= Persona.findById(session.persona.id)
         def libretaInstance = Libreta.get(id)
         if (persona.libretas.contains(Libreta.get(id))){
             log.info "El usuario "+persona.id+" esta consultando el detalle de la libreta con id: "+libretaInstance.id
             [libretaInstance: libretaInstance]
         }
         else {
             log.info "No se ha encontrado la libreta solicitada, ya que NO EXISTE"
             flash.message = message(code: 'default.not.found.message', args: ["Error: Lo sentimos la libreta solicitada no existe"])
             redirect(action: "list")
             return
         }
    }

    
    /**
     *
     * Envia a la opcion de editar una libreta seleccionada, si este se encuentra
     * almacenado en la lista de libretas
     */
    def edit(Long id) {
        println (id)
        def persona= Persona.findById(session.persona.id)
        def libretaInstance = Libreta.get(id)
        if (!libretaInstance) {
            log.info "No se ha encontrado la libreta"+libretaInstance.id+" para ser editada"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
            return
        }
        log.info "El usuario "+persona.id+" ha empezado a editar la libreta con id: "+libretaInstance.id
        [libretaInstance: libretaInstance]
    }

    
    /**
     *
     * Permite realizar cambio sobre las libretas seleccionadas, de ser exitosa la
     * modificacion, se linkeara a la seccion de mostrar libretas, de lo contrario
     * mostrara un msj de error indicando el fallo de la transaccion
     * Estos cambios se veran reflejados en la bitacora de la operacion
     * @params id Identificador del usuario en la aplicacion
     * @params version indica si la nota fue o no actualizada
     */
    def update(Long id, Long version) {
        def libretaInstance = Libreta.get(id)
        if (!libretaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (libretaInstance.version > version) {
                libretaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'libreta.label', default: 'Libreta')] as Object[],
                          "Another user has updated this Libreta while you were editing")
                render(view: "edit", model: [libretaInstance: libretaInstance])
                return
            }
        }

        libretaInstance.properties = params

        if (!libretaInstance.save(flush: true)) {
            render(view: "edit", model: [libretaInstance: libretaInstance])
            return
        }
        log.info "Se ha editado la libreta con id: "+libretaInstance.id
        flash.message = message(code: 'default.updated.message', args: [message(code: 'libreta.label', default: 'Libreta'), libretaInstance.id])
        redirect(action: "show", id: libretaInstance.id)
    }

    
    /**
    *
    *Elimina una libreta especifica que pertenece a un usuario
    */
    def delete(Long id) {
        def libretaInstance = Libreta.get(id)
        if (!libretaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
            return
        }

        try {
            log.info "Se ha eliminado la libreta con id: "+libretaInstance.id+" de la base de datos"
            libretaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            log.info "La libreta con id: "+libretaInstance.id+" no se ha podido eliminar de la Base de Datos"
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "show", id: id)
        }
    }
}
