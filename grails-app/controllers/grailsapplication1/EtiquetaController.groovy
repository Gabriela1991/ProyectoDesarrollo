package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.*

class EtiquetaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    private static Log log = LogFactory.getLog("bitacora."+EtiquetaController.class.getName())
    
    /**
     *
     * Me redirige a la seccion de mostrar todos las etiquetas
     */
    def index() {
        redirect(action: "list", params: params)
    }

    
    /**
     *
     * Muestra por pantalla un listado de todas las etiquetas pertenecientes
     * a una nota en especifico
     */
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [etiquetaInstanceList: Etiqueta.list(params), etiquetaInstanceTotal: Etiqueta.count()]
    }

    
    /**
     *
     *Crea una etiqueta nueva para una nota
     */
    def create() {
        [etiquetaInstance: new Etiqueta(params)]
    }

    
    /**
     *
     * Guarda los cambios realizados sobre una etiqueta
     */
    def save() {
        def etiquetaInstance = new Etiqueta(params)
        if (!etiquetaInstance.save(flush: true)) {
            render(view: "create", model: [etiquetaInstance: etiquetaInstance])
            return
        }
        log.info "Se ha agregado una etiqueta a la base de datos con id:"+etiquetaInstance.id    
        flash.message = message(code: 'default.created.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), etiquetaInstance.id])
        redirect(action: "show", id: etiquetaInstance.id)
    }

    
    /**
    *
    *Muestra el detalle de una etiqueta en especifico
    */
    def show(Long id) {
        def etiquetaInstance = Etiqueta.get(id)
        if (!etiquetaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), id])
            redirect(action: "list")
            return
        }

        [etiquetaInstance: etiquetaInstance]
    }

    
    /**
     *
     * Envia a la opcion de editar una etiqueta seleccionada, si este se encuentra
     * almacenado en la lista de etiquetas
     */
    def edit(Long id) {
        def etiquetaInstance = Etiqueta.get(id)
        if (!etiquetaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), id])
            redirect(action: "list")
            return
        }

        [etiquetaInstance: etiquetaInstance]
    }

    
    /**
     *
     * Permite realizar cambio sobre las etiquetas seleccionadas, de ser exitosa la
     * modificacion, se linkeara a la seccion de mostrar etiquetas, de lo contrario
     * mostrara un msj de error indicando el fallo de la transaccion
     * Todas las modificaciones se veran reflejadas en la bitacora del sistema
     */
    def update(Long id, Long version) {
        
        println(params);
        def etiquetaInstance = Etiqueta.get(id)
        if (!etiquetaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (etiquetaInstance.version > version) {
                etiquetaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'etiqueta.label', default: 'Etiqueta')] as Object[],
                          "Another user has updated this Etiqueta while you were editing")
                render(view: "edit", model: [etiquetaInstance: etiquetaInstance])
                return
            }
        }

        etiquetaInstance.properties = params

        if (!etiquetaInstance.save(flush: true)) {
            render(view: "edit", model: [etiquetaInstance: etiquetaInstance])
            return
        }
        log.info "Se ha editado la etiqueta con id:"+etiquetaInstance.id    
        flash.message = message(code: 'default.updated.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), etiquetaInstance.id])
        redirect(action: "show", id: etiquetaInstance.id)
    }

    
    /**
    *
    *Elimina una etiqueta especifica que pertenece a una nota
    */
    def delete(Long id) {
        
        println (params);
        def etiquetaInstance = Etiqueta.get(id)
        if (!etiquetaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), id])
            redirect(action: "list")
            return
        }

        try {
            log.info "Se ha eliminado una etiqueta de la base de datos con id:"+etiquetaInstance.id    
            etiquetaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'etiqueta.label', default: 'Etiqueta'), id])
            redirect(action: "show", id: id)
        }
    }
    
    
    /**
    *
    *Muestra el conjunto de etiquetas que pertenecen a una nota en especifico
    */
    def getEtiqueta = {
        //Se la lista de estados
        def etiquetaList = notaInstance?.etiqueta
        //Se hace el render del emplate '_selectEstados.gsp' con la lista de estados obtenida.
        render(template: "selectEtiqueta", model: [etiquetaList:etiquetaList])
    }
    
    
    /**
    *
    *Busca todas las etiquetas petenecientes a una nota en especifico
    */
    def findEtiquetas = {
        def etiqueta = Etiqueta.get(params.etiqueta.id)
        render(template: 'etiquetaSelection', model:  [etiqueta: etiqueta])
    }
}
