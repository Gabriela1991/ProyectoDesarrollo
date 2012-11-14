package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException

class LibretaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        def persona= Persona.findById(session.persona.id)
        params.max = Math.min(max ?: 10, 100)
        [libretaInstanceList: persona.libretas, libretaInstanceTotal: Libreta.count()]
    }

    def create() {
        // def persona= Persona.findById(session.persona.id);
        [libretaInstance: new Libreta(params)]
    }

    def save() {
        def persona= Persona.findById(session.persona.id);
        def libretaInstance = new Libreta(params)
        libretaInstance.persona= persona
        if (!libretaInstance.save(flush: true)) {
            render(view: "create", model: [libretaInstance: libretaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'libreta.label', default: 'Libreta'), libretaInstance.id])
        redirect(action: "show", id: libretaInstance.id)
    }

    def show(Long id) {
        def libretaInstance = Libreta.get(id)
        if (!libretaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
            return
        }

        [libretaInstance: libretaInstance]
    }

    def edit(Long id) {
        def libretaInstance = Libreta.get(id)
        if (!libretaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
            return
        }

        [libretaInstance: libretaInstance]
    }

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

        flash.message = message(code: 'default.updated.message', args: [message(code: 'libreta.label', default: 'Libreta'), libretaInstance.id])
        redirect(action: "show", id: libretaInstance.id)
    }

    def delete(Long id) {
        def libretaInstance = Libreta.get(id)
        if (!libretaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
            return
        }

        try {
            libretaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'libreta.label', default: 'Libreta'), id])
            redirect(action: "show", id: id)
        }
    }
}
