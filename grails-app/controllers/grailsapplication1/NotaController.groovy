package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException

class NotaController {
//asjflafhsjfnasjf se ve?
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def index() {
        redirect(action: "list", params: params)
    }
    
    def etiquetas = {new ArrayList() as grails.converters.JSON  //este metodo realmente no hace nada.. solo probando
            [Etiqueta: etiquetas];
    }  
    
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [notaInstanceList: Nota.list(params), notaInstanceTotal: Nota.count()]
    }
    def search(){
        def Etiqueta = Nota.executeQuery("SELECT distinct b.texto FROM Etiqueta b")
        [ Etiqueta:Etiqueta]
        
    }
    def listar () {  //este metodo tampoco hace falta
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
                
        def params2= params.clone(); //los parametros del html los clono para poder modificarlos

        def numero = params.size();
        def x=0;

        while (numero-- >7){  //aca le borro los parametros etiquetas[x] para poder insertar la nota
            params.remove('etiquetas['+x+']');
            params.remove('etiqueta2');
            params2.remove('etiqueta2');
            x++;
        }

        def notaInstance = new Nota(params) //creo la nueva nota
        numero= params2.size();
        x=0;
      while (numero > 7){
         if (params2.getAt('etiquetas['+x+']')){
          notaInstance.addToEtiquetas([texto:params2.getAt('etiquetas['+x+']')]); //y aqui se le añaden los hijos a esa nota 
          numero=numero-1;
         }
          x++;
      }
        if (!notaInstance.save(flush: true)) { //aqui solo guardo en la bd y listo
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
       
       def notaInstance = Nota.get(id)
        params.remove('etiqueta2');
        def numero = params.size()
     

        def x=0;
        
        while (params.getAt('etiquetas['+x+']')){ //los parametros de la nota por defecto son 9 si tiene mas es porque hay alguna etiqueta agregada
        //  println (numero)
     
       //     def etiqueta= Etiqueta.find("from Etiqueta where Nota_Id=:id and id=:idetiq", [id:id,idetiq:params.getAt('etiquetas['+x+'].id').toLong()]); //aqui se obtiene la etiqueta asociada a la nota
   
            
            if (params.getAt('etiquetas['+x+']._deleted')=='true'){  //si el _deleted esta activado es que mande a borrar la etiqueta                      
              def  etiqueta= Etiqueta.find("from Etiqueta where Nota_Id=:id and id=:idetiq", [id:id,idetiq:params.getAt('etiquetas['+x+'].id').toLong()]);
                notaInstance.removeFromEtiquetas(etiqueta)
            } 
            else if (params.getAt('etiquetas['+x+']._deleted')=='false'){ //si esta en false es porque se mando a actualizar el texto de la etiqueta
               def etiqueta= Etiqueta.find("from Etiqueta where Nota_Id=:id and id=:idetiq", [id:id,idetiq:params.getAt('etiquetas['+x+'].id').toLong()]);
                etiqueta.setTexto(params.getAt('etiquetas['+x+']'));
                etiqueta.save(flush:true);
            } 
            else { //si nisiquiera aparece el atributo _deleted es que no hay etiquetas y acabo de agregar una nueva
                notaInstance.addToEtiquetas([texto:params.getAt('etiquetas['+x+']')]); 
            }
            
            params.remove('etiquetas['+x+']._deleted') //debo borrar estos 3 parametros para poder insertar la nota correctamente
                  params.remove('etiquetas['+x+']')
                  params.remove('etiquetas['+x+'].id') 
            x++;
            
        }     
     
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
