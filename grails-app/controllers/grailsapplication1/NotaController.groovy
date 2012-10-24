package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException

class NotaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    
    def etiquetas = {new ArrayList() as grails.converters.JSON 
            [Etiqueta: etiquetas];
    } 
    
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [notaInstanceList: Nota.list(params), notaInstanceTotal: Nota.count()]
    }
    
    def listar () {
     //   def objeto= Nota.executeQuery("select MAX (id) from Nota");
        def notaInstance = Nota.find("from Nota where id= (Select MAX(id) from Nota)");
        def notafinal= new Nota();
        notafinal=notaInstance;
        def listaetiquetas = notafinal.etiquetas.findAll();
    //    def listaetiquetas = notafinal.etiquetas;
     //   println();
        

      //  def query = Nota.where{ id == max(id) }
      //  def fran= query.find();
      [etiquetas: listaetiquetas]
      //  [notas: notaInstance]
    }

    def create() {
        [notaInstance: new Nota(params)]
    }

    def save() {
     //   println (params);
        params.etiquetas= new ArrayList();
        EtiquetaController ec= new EtiquetaController();
       // ec.create2(params.etiquetas)
      //  params.etiquetas[0];
       // println (params.etiquetas[0]);
        def notaInstance = new Nota(params)
        if (!notaInstance.save(flush: true)) {
            render(view: "create", model: [notaInstance: notaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'nota.label', default: 'Nota'), notaInstance.id])
        redirect(action: "show", id: notaInstance.id)
    }

        
    def listetiqqueta (){
        def etiquetaInstance = new Etiqueta (params)
        def notaInstance = new Nota ().executeQuery("select max id from nota");
        notaInstance.getEtiquetas().println();
        
    }
    
    
    def show(Long id) {
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        [notaInstance: notaInstance]
    }

    def edit(Long id) {
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        [notaInstance: notaInstance]
    }

    def update(Long id, Long version) {
        
     /* def notaInstance = Nota.get( params.id )
    if(notaInstance) {
        if(params.version) {
            
        }
    notaInstance.properties = params
    def _toBeDeleted = notaInstance.etiquetas.findAll {it._deleted}
    if (_toBeDeleted) {
        notaInstance.etiquetas.removeAll(_toBeDeleted)
    }*/
            
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (notaInstance.version > version) {
                notaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'nota.label', default: 'Nota')] as Object[],
                          "Another user has updated this Nota while you were editing")
                render(view: "edit", model: [notaInstance: notaInstance])
                return
            }
        }

        notaInstance.properties = params

        if (!notaInstance.save(flush: true)) {
            render(view: "edit", model: [notaInstance: notaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'nota.label', default: 'Nota'), notaInstance.id])
        redirect(action: "show", id: notaInstance.id)
    }

    def delete(Long id) {
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        try {
            notaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "show", id: id)
        }
    }
}
