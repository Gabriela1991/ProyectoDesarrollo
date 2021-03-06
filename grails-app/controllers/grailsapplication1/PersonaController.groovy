package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.*
import grails.converters.*
import grails.converters.deep.XML
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

        if (params.clave!=personaInstance.clave)
        params.clave=params.clave.encodeAsSHA()
        
        personaInstance.properties = params

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
    *Dado un archivo XML subido por el usuario, se carga en la base de datos
    *los datos que se encuentran en el archivo, solo si cumplen con la siguientes 
    *condiciones:
    *1) Que el usuario exista y que hayan campos distintos, para modificar
    *2) Que el usuario exista y se deseen agrregar nuevas notas, archivos, etc..
    */
    def readXML() {
         def arch = request.getFile('archivo')
         def usuario =Persona.get(session.persona.id)  
         
        
        if(!arch.empty){
            def f =  arch.getOriginalFilename();
        
            def persona = new XmlSlurper().parse(new File("C:\\Users\\Eule\\Downloads\\"+f)) 
            
            Persona per= new Persona();

            persona.each({
               per.nombre= it.nombre
               per.apellido= it.apellido
               per.correo= it.correo
               per.clave= it.clave
               per.keysdropbox= it.keysdropbox
               
               def perso = Persona.findById(persona.@'id'.toInteger())
               session.perso = perso
               
               //Si existe la persona se compara campo por campo para ver si existen diferencias
               if(perso){
                   if(perso.correo != per.correo){
                       perso.correo = per.correo
                       log.info "Se ha modificado al usuario "+perso.id+" a traves de XML"
                   }
                   if(perso.nombre != per.nombre){
                       perso.nombre = per.nombre
                       log.info "Se ha modificado al usuario "+perso.id+" a traves de XML"
                   }
                   if(perso.apellido != per.apellido){
                       perso.apellido = per.apellido
                       log.info "Se ha modificado al usuario "+perso.id+" a traves de XML"
                   }
                   if(perso.clave != per.clave){
                       perso.clave = per.clave
                       log.info "Se ha modificado al usuario "+perso.id+" a traves de XML"
                   }
                   if(perso.keysdropbox != per.keysdropbox){
                       perso.keysdropbox = per.keysdropbox
                       log.info "Se ha modificado al usuario "+perso.id+" a traves de XML"
                   }
                   
                    persona.libretas.libreta.each ({
                           Libreta lib= new Libreta();
                           lib.tema= it.tema
                           lib.nombre= it.nombre
                           lib.persona= per
                           lib.notas= new ArrayList <Nota>();
                           per.addToLibretas(lib)
                           
                           def libre = Libreta.findById(it.@'id'.toInteger())
                           session.libre = libre
                           
                           if(libre){
                               if(libre.tema != lib.tema){
                                   libre.tema = lib.tema
                                   log.info "Se ha editado la libreta con id: "+libre.id+" a traves de XML"
                               }
                               if(libre.nombre != lib.nombre){
                                   libre.nombre = lib.nombre
                                   log.info "Se ha editado la libreta con id: "+libre.id+" a traves de XML"
                               }
                           }else{
                               libre = new Libreta()
                               libre.tema = lib.tema
                               libre.nombre = lib.nombre
                               libre.persona = perso
                               libre.save(flush:true);
                               log.info "Se ha agregado una libreta a la base de datos con id: "+libre.id+" a traves de XML"
                           }
                            
                           it.notas.nota.each ({
                                   Nota nota= new Nota();
                                   nota.texto= it.texto
                                   nota.titulo= it.titulo
                                   nota.fecha= it.fecha
                                   nota.libreta=lib
                                   nota.etiquetas= new ArrayList <Etiqueta>();
                                   lib.notas.add(nota)
                                   
                                    def x = Nota.findById(it.@'id'.toInteger())
                                   session.x = x
                                   
                                   if(x){
                                       if(x.texto != nota.texto){
                                           x.texto = nota.texto
                                           log.info "Se ha modificado la nota con id: ", x.id+" a traves de XML"
                                       }
                                       if(x.titulo != nota.titulo){
                                           x.titulo = nota.titulo
                                           log.info "Se ha modificado la nota con id: ", x.id+" a traves de XML"
                                       }
                                       if(x.fecha != nota.fecha){
                                           x.fecha = nota.fecha
                                           log.info "Se ha modificado la nota con id: ", x.id+" a traves de XML"
                                       }
                                   }else{
                                       x = new Nota()
                                       x.texto=nota.texto
                                       x.titulo = nota.titulo
                                       x.fecha = nota.fecha
                                       x.libreta=libre                                      
                                       x.save(flush:true) 
                                       if(x.id)
                                       log.info "Se ha agregado una nota a la base de datos con id: "+x.id+" a traves de XML"
                                   }
                                   
                                       it.etiquetas.etiqueta.each({
                                               Etiqueta et= new Etiqueta();
                                               et.texto= it.texto
                                               nota.etiquetas.add(et)
                                               
                                               def eti = Etiqueta.findById(it.@'id'.toInteger())
                                                session.eti = eti
                                                if(eti){
                                                    if(eti.texto != et.texto){
                                                        eti.texto = et.texto
                                                        log.info "Se ha modificado la etiqueta con id: "+eti.id+" a traves de XML"
                                                    }
                                                }else{
                                                    eti = new Etiqueta()
                                                    eti.texto = et.texto
                                                    x.addToEtiquetas(eti)
                                                    if (eti.id)
                                                    log.info "Se ha creado la etiqueta con id: ", eti.id+" a traves de XML"
                                                    eti.save(flush:true)
                                                }
                                       })
                                       it.adjuntos.adjunto.each({
                                               Adjunto adj= new Adjunto();
                                               adj.archivo= it.archivo
                                               adj.nombre=it.nombre
                                               nota.addToAdjuntos(adj)
                                               def ad = Adjunto.findById(it.@'id'.toInteger())
                                                session.ad = ad
                                                if(ad){
                                                    if(ad.archivo != adj.archivo){
                                                        ad.archivo = adj.archivo
                                                        log.info "Se ha modificado el adjunto con id: ", ad.id+" a traves de XML"
                                                    }
                                                    if(ad.nombre != adj.nombre){    
                                                    ad.nombre = adj.nombre
                                                    log.info "Se ha modificado el adjunto con id: ", ad.id+" a traves de XML"
                                                    }
                                                }else{
                                                    ad = new Adjunto()
                                                    ad.archivo = adj.archivo
                                                    ad.nombre = adj.nombre
                                                    x.addToAdjuntos(ad)
                                                    if (ad.id)
                                                    log.info "Se ha creado el adjunto con id: ", ad.id+" a traves de XML"
                                                    ad.save(flush:true)
                                                }
                                               adj.nota=nota
                                       })
                           })
                   })
               
               }else{ //De no existir la persona no se carga el xml y se registra en el log
                  log.error "El usuario con id: "+usuario.id+" ha intentado cargar un usuario no registrado en la Base de Datos"     
                  flash.message = 'El usuario que intentas importar no existe en la Base de Datos, importe un usuario valido'
                  redirect(controller: "Persona", action: "importarXML")
               }
            })
       
        }else{
            flash.message = 'El archivo no puede ser vacío'
            log.error "El usuario con id: "+usuario.id+" ha intentado importar un archivo XML vacio"
            redirect(controller: "Persona", action: "importarXML")
        }        
    }
    
    def importarXML(){}
    
    def descargarXML(){
        def personaInstance=Persona.get(session.persona.id)   
        
            //XML y creacion de la carpeta del usuario
            String usuario = personaInstance.correo
            String ruta = "C:\\administradorDeNotas\\"+usuario
            File folder = new File(ruta)
            folder.mkdirs()
            File archivoXML= new File(ruta+"\\datosPersona.xml")
      
            //establece que se usaran las asociaciones en el xml
            XML.use("deep")
            String s= personaInstance as XML           
            archivoXML.write(s)  
            
            //genera el link de descarga
            def file = new File(ruta+"\\datosPersona.xml"); //<-- you'll probably want to pass in the file name dynamically with the 'params' map    
            response.setContentType("application/excel")
            response.setHeader("Content-disposition", "attachment;filename=${file.getName()}")

            response.outputStream << file.newInputStream()
            
        log.info "El usuario con id "+personaInstance.id+" ha exportado su XML de configuracion"
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
