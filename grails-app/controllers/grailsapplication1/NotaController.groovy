package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.*
class NotaController {

    /**
    *
    *Inicializacion de variables que se usaran en todo el contralador
    */
    private static ultimoidlibreta=null;
    private static numero;
    private static ArrayList <Nota> listaBuscar= new ArrayList <Nota>();
    private static Log log = LogFactory.getLog("bitacora."+NotaController.class.getName())
    static allowedMethods = [save: "POST", update: "POST", delete: "POST", create: "GET"]
    
    
    /**
     *
     * Me redirige a la seccion de mostrar todos las notas
     */
    def index() {
        redirect(action: "list", params: params)
    }
    
    
    
    def etiquetas = {new ArrayList() as grails.converters.JSON 
        [Etiqueta: etiquetas];
    }  
    
   
    /**
     *
     * Muestra por pantalla un listado de todas las notas pertenecientes
     * a un usuario en especifico, aqui tambien se encuntra incluido la paginacion
     * para las notas
     */
    def list(Integer max, Long id) {  
        def persona= Persona.findById(session.persona.id)  
        def libreta;
        if (!ultimoidlibreta && id){
         libreta= Libreta.findById (id)
         ultimoidlibreta=id;
         numero=0;
        }
        else{ 
            libreta= Libreta.findById (ultimoidlibreta)
            numero=params.offset;
        }   
        def ls = Nota.executeQuery("from Nota a where a.libreta.id = ?",[ultimoidlibreta],[max: 10, offset: numero])
        def totalCount = Nota.executeQuery("from Nota a where a.libreta.id= ?",[ultimoidlibreta]).size()
        if (persona.libretas.contains(libreta)){
            [libretaInstance: ls, notaInstanceTotal: totalCount]   
        }
 }


    /**
    *
    *Realiza la busqueda de notas por una etiqueta en particular
    */
    def search(){
        def Etiqueta = Nota.executeQuery("SELECT distinct b.texto FROM Etiqueta b")
        [ Etiqueta:Etiqueta]
        
    }

    
    /**
     *
     *Crea una nota nueva para un usuario
     */
    def create() {
        def nota= new Nota();
        def persona= Persona.findById(session.persona.id);
        ArrayList <Etiqueta> etiq= new ArrayList <Etiqueta>()
        //nota.fecha= new Date().format("dd/MM/yyyy");
        if (persona.libretas.notas.etiquetas){
            for (int i=0; i<persona.libretas.notas.size(); i++){
                Iterator j= persona.libretas.notas[i].iterator()
                while(j.hasNext()){
                    Object elemento= j.next();
                        for (int k=0; k<elemento.etiquetas.size(); k++){
                            Iterator h= elemento.etiquetas[k].iterator()                          
                                while(h.hasNext()){
                                    Object elemento2= h.next();
                                    if (elemento2) etiq.add(elemento2)
                                }
                        }
                }
            } 
        
        }
        log.info "Se ha empezado a crear una nota "
        [notaInstance: nota, libretasInstance: persona.libretas, sesion: session.persona, etiquetasInstance: etiq] 
    }
    
    
    /**
     *
     *Crea una nota nueva para un usuario
     */
    def create1() {
        def nota= new Nota();
        def persona= Persona.findById(session.persona.id);
        ArrayList <Etiqueta> etiq= new ArrayList <Etiqueta>()
        //nota.fecha= new Date().format("dd/MM/yyyy");
        if (persona.libretas.notas.etiquetas){
            for (int i=0; i<persona.libretas.notas.size(); i++){
                Iterator j= persona.libretas.notas[i].iterator()
                while(j.hasNext()){
                    Object elemento= j.next();
                        for (int k=0; k<elemento.etiquetas.size(); k++){
                            Iterator h= elemento.etiquetas[k].iterator()                          
                                while(h.hasNext()){
                                    Object elemento2= h.next();
                                    if (elemento2) etiq.add(elemento2)
                                }
                        }
                }
            } 
        
        }
        log.info "Se ha empezado a crear una nota "
        [notaInstance: nota, libretasInstance: persona.libretas, sesion: session.persona, etiquetasInstance: etiq] 
    }

    
    /**
     *
     * Guarda los cambios realizados sobre una nota
     * Estos cambios se veran refejados en la bitacora del sistema
     */
    def save() { 

        
        def params2= params.clone(); //los parametros del html los clono para poder modificarlos

        def numero = params.size();
        def x=0;
       

        while (numero >=8){  //aca le borro los parametros etiquetas[x] para poder insertar la nota
            params.remove('etiquetas['+x+']');
            params.remove('etiqueta2');
            params2.remove('etiqueta2');
            params.remove();
            numero= numero-1;
            x++;
        }
      
        def notaInstance = new Nota(params) //creo la nueva nota
        notaInstance.fecha=new Date().format("dd/MM/yyyy");
        numero= params2.size();

        x=0;
       
        while (numero >= 8){
            if (params2.getAt('etiquetas['+x+']')){
                notaInstance.addToEtiquetas([texto:params2.getAt('etiquetas['+x+']')]); //y aqui se le añaden los hijos a esa nota 
                numero=numero-1;
            }
            x++;
        }
        if (!notaInstance.save(flush: true)) { 
            flash.message="Debe rellenar todos los campos de la nota";
            redirect (action:"create", controller:"nota")
           
            //render(view: "create", model: [notaInstance: notaInstance])
            return
        }

        flash.message = "Su nota se ha creado correctamente"
        //message(code: 'default.created.message', args: [message(code: 'nota.label', default: 'Nota'), notaInstance.id])
        session.nota= notaInstance
        log.info "Se ha agregado una nota a la base de datos, con identificador: "+notaInstance.id
        redirect(action: "show1", params:[id: notaInstance.id, sesion:session.persona])        
    }


    /**
     *
     *Selecciona el maximo ID de notas que se encuentra almacenado en la Base de 
     *Datos del sistema
     */
    def listetiqqueta (){
        def etiquetaInstance = new Etiqueta (params)
        def notaInstance = new Nota ().executeQuery("select max id from nota");
        notaInstance.getEtiquetas().println();
        
    }
    
    
    
    def show(Long id) {
        def persona= Persona.findById(session.persona.id)
        def notaInstance = Nota.get(id)
        def existeNota=false;
        session.nota=notaInstance
        
        session.nota.id=notaInstance.id
        for (int i=0; i< persona.libretas.notas.size(); i++){
            existeNota = persona.libretas.notas.get(i).contains(notaInstance)     
            if (existeNota) break
        }
        if (!existeNota){
            flash.message = message(code: 'default.not.found.message', args: ["Error: Lo sentimos la nota que busca no existe"])
            redirect (controller:"libreta",action:"list")
            return
        }else {
            log.info "El usuario "+persona.id+" esta consultando el detalle de la nota con id: "+notaInstance.id
            [notaInstance: notaInstance]
        }
    }

    
    /**
     *
     * Envia a la opcion de editar una nota seleccionada, si este se encuentra
     * almacenado en la lista de notas
     */
    def edit(Long id) {
        def persona= Persona.findById(session.persona.id);
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }
        session.nota= notaInstance
        session.nota.id=notaInstance.id
        log.info "Se ha empezado a modificar la nota con id: "+notaInstance.id
        [notaInstance: notaInstance, libretasInstance: persona.libretas, sesion: session.persona]
    }
    

    /**
     *
     * Permite realizar cambio sobre las notas seleccionadas, de ser exitosa la
     * modificacion, se linkeara a la seccion de mostrar notas, de lo contrario
     * mostrara un msj de error indicando el fallo de la transaccion
     * Estos cambios se refejaran en la bitacora del sistema
     */
    def update(Long id, Long version) {
       
        def notaInstance = Nota.get(id)
        params.remove('etiqueta2');
        def numero = params.size()
     

        def x=0;
        
        while (params.getAt('etiquetas['+x+']')){ //los parametros de la nota por defecto son 9 si tiene mas es porque hay alguna etiqueta agregada       
            
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
        session.nota= notaInstance
        session.nota.id=notaInstance.id
        flash.message = message(code: 'default.updated.message', args: [message(code: 'nota.label', default: 'Nota'), notaInstance.id])
        log.info "Se ha editado la nota con identificador: "+notaInstance.id
        redirect(action: "show", id: notaInstance.id)
    }

    
    /**
    *
    *Elimina una nota especifica que pertenece a un usuario, tanto de la BD como
    *del dropbox en el caso de que contenga adjuntos asociado, estos cambios se
    *reflejaran en la bitacora del sistema
    */
    def delete(Long id) {
        println("voy a eliminar adj d db 1")
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        try {
           
         
            def Dropbox d=new Dropbox();
            String claves=session.persona.keysdropbox;
       
             
            def adjuntos=Adjunto.executeQuery("select cast(archivo as string)from Adjunto where nota_id=:idnota",[idnota:id]);
          
            if (adjuntos!=null){
                if( adjuntos.toList() ){
                   
                    adjuntos.each(){ archivo->
            
                        d.eliminarArchivo(archivo,claves.split('/')[0].toString(),claves.split('/')[1].toString())
                    }
                }
              
            }
             
             
             notaInstance.delete(flush: true) 
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            log.info "Se ha eliminado la nota con identificador: "+notaInstance.id
            redirect(controller:'persona',action: 'ventanaInicio')
        }
        catch (DataIntegrityViolationException e) {
            log.info "La nota "+notaInstance+" no ha sido eliminada de la BD"
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "show", id: id)
        }
    }
    
    
    /**
    *
    *Realiza una busqueda rapida de las notas que se encuentran almacenadas en la
    *Bd y que pertenecen a un usuario en especifico.
    */
     def buscar () {
        def persona= Persona.findById(session.persona.id);
        def notas= persona.libretas.notas
        ArrayList<Nota> notasaux= new ArrayList <Nota>()
        if (params.campo){        
        for (int i=0; i<notas.size(); i++){
            Iterator k = notas[i].iterator();
            if (notas[i].size()>1) {
                for (int j=0; j<notas[i].size(); j++){
                    while (k.hasNext()) {    
                        Object elemento= k.next();
                        def bandera=0;
                        if (!elemento.texto.contains(params.campo) && !elemento.titulo.contains(params.campo) && !elemento.fecha.contains(params.campo)) {
                            for (int h=0; h<elemento.etiquetas.size(); h++){
                                if (elemento.etiquetas.get(h).texto.contains(params.campo)){
                                    bandera=1
                                    break
                                }
                                else bandera=0;               
                            }
                            if (bandera==1){
                                notasaux.add(elemento);    
                            }
                            bandera=0;
                            for (int h=0; h<elemento.adjuntos.size(); h++){
                                if (elemento.adjuntos.archivo.get(h).contains(params.campo)){
                                    bandera=1
                                    break;
                                } else bandera=0
                            } 
                            if (bandera==1 && !notasaux.contains(elemento)){
                                notasaux.add(elemento)
                            }
                        } else notasaux.add(elemento)
                    }                 
                }
            }
            else {
                while (k.hasNext()) {
                    Object elemento= k.next();
                    def bandera=0;
                    if (!elemento.texto.contains(params.campo) && !elemento.titulo.contains(params.campo) && !elemento.fecha.contains(params.campo)) {
                        for (int h=0; h<elemento.etiquetas.size(); h++){
                            if (elemento.etiquetas.get(h).texto.contains(params.campo)){
                                bandera=1
                                break
                            }
                            else bandera=0;               
                        }
                        if (bandera==1) notasaux.add(elemento);  
                        bandera=0;
                            for (int h=0; h<elemento.adjuntos.size(); h++){
                                if (elemento.adjuntos.archivo.get(h).contains(params.campo)){
                                    bandera=1
                                    break;
                                } else bandera=0
                            } 
                            if (bandera==1 && !notasaux.contains(elemento)){
                                notasaux.add(elemento)
                            }
                    } else notasaux.add(elemento)
                }         
            }
        }
        if (params.campo) listaBuscar=notasaux
        }
        
        if (!params.offset){
            if (notasaux.size()>=10){
                flash.message= "";
                log.info "Se ha encontrado la nota de acuerdo al criterio de busqueda: "+params.campo  
                [libretaInstance: notasaux.getAt(0..9), notasAuxTotal: notasaux.size()]
            }
            else if (notasaux.size()>0) {
                 flash.message= "";
                 log.info "Se ha encontrado la nota de acuerdo al criterio de busqueda: "+params.campo  
                 [libretaInstance: notasaux.getAt(0..notasaux.size()-1), notasAuxTotal: notasaux.size()]
            }
            else{
                flash.message = "No se encontro ninguna nota de acuerdo al criterio de busqueda"
                log.info "No se ha encontrado la nota de acuerdo al criterio de busqueda: "+params.campo  
                [libretaInstance: notasaux, notasAuxTotal: notasaux.size()]
            }
        }
       else{
           def cantidad = Integer.parseInt(params.offset)
           if (listaBuscar.size()-10<=cantidad){
                 flash.message= "";
                 log.info "Se ha encontrado la nota de acuerdo al criterio de busqueda: "+params.campo  
                 [libretaInstance: listaBuscar.getAt(cantidad..listaBuscar.size()-1), notasAuxTotal: listaBuscar.size()]
           }
            else{
                flash.message= "";
                log.info "Se ha encontrado la nota de acuerdo al criterio de busqueda: "+params.campo  
                [libretaInstance: listaBuscar.getAt(cantidad..cantidad+9), notasAuxTotal: listaBuscar.size()]
            }
               
       }

    }

    
    
    def show1(Long id) {
        def persona= Persona.findById(session.persona.id)
        def notaInstance = Nota.get(id)
        def existeNota=false;
        session.nota=notaInstance
        session.nota.id=notaInstance.id
        for (int i=0; i< persona.libretas.notas.size(); i++){
            existeNota = persona.libretas.notas.get(i).contains(notaInstance)     
            if (existeNota) break
        }
        if (!existeNota){
            log.info "No se ha encontrado la nota solicitada, ya que NO EXISTE"
            flash.message = message(code: 'default.not.found.message', args: ["Error: Lo sentimos la nota que busca no existe"])
            redirect (controller:"libreta",action:"list")
            return
        }else {
            [notaInstance: notaInstance]
        }
    }
}
