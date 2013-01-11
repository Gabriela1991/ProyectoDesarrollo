package grailsapplication1



import org.junit.*
import grails.test.mixin.*
import grails.converters.*
import grails.converters.deep.XML
@TestFor(PersonaController)
@Mock([Persona,Libreta,Nota,Etiqueta])
class PersonaControllerTests {

//    def populateValidParams(params) {
//        assert params != null
//        // TODO: Populate valid properties like...
//        //params["name"] = 'someValidName'
//    }
//
//    void testIndex() {
//        controller.index()
//        assert "/persona/list" == response.redirectedUrl
//    }
//
//    void testList() {
//
//        def model = controller.list()
//
//        assert model.personaInstanceList.size() == 0
//        assert model.personaInstanceTotal == 0
//    }
//
//    void testCreate() {
//        def model = controller.create()
//
//        assert model.personaInstance != null
//    }
//
//    void testSave() {
//        controller.save()
//
//        assert model.personaInstance != null
//        assert view == '/persona/create'
//
//        response.reset()
//
//        populateValidParams(params)
//        controller.save()
//
//        assert response.redirectedUrl == '/persona/show/1'
//        assert controller.flash.message != null
//        assert Persona.count() == 1
//    }
//
//    void testShow() {
//        controller.show()
//
//        assert flash.message != null
//        assert response.redirectedUrl == '/persona/list'
//
//        populateValidParams(params)
//        def persona = new Persona(params)
//
//        assert persona.save() != null
//
//        params.id = persona.id
//
//        def model = controller.show()
//
//        assert model.personaInstance == persona
//    }
//
//    void testEdit() {
//        controller.edit()
//
//        assert flash.message != null
//        assert response.redirectedUrl == '/persona/list'
//
//        populateValidParams(params)
//        def persona = new Persona(params)
//
//        assert persona.save() != null
//
//        params.id = persona.id
//
//        def model = controller.edit()
//
//        assert model.personaInstance == persona
//    }
//
//    void testUpdate() {
//        controller.update()
//
//        assert flash.message != null
//        assert response.redirectedUrl == '/persona/list'
//
//        response.reset()
//
//        populateValidParams(params)
//        def persona = new Persona(params)
//
//        assert persona.save() != null
//
//        // test invalid parameters in update
//        params.id = persona.id
//        //TODO: add invalid values to params object
//
//        controller.update()
//
//        assert view == "/persona/edit"
//        assert model.personaInstance != null
//
//        persona.clearErrors()
//
//        populateValidParams(params)
//        controller.update()
//
//        assert response.redirectedUrl == "/persona/show/$persona.id"
//        assert flash.message != null
//
//        //test outdated version number
//        response.reset()
//        persona.clearErrors()
//
//        populateValidParams(params)
//        params.id = persona.id
//        params.version = -1
//        controller.update()
//
//        assert view == "/persona/edit"
//        assert model.personaInstance != null
//        assert model.personaInstance.errors.getFieldError('version')
//        assert flash.message != null
//    }
//
//    void testDelete() {
//        controller.delete()
//        assert flash.message != null
//        assert response.redirectedUrl == '/persona/list'
//
//        response.reset()
//
//        populateValidParams(params)
//        def persona = new Persona(params)
//
//        assert persona.save() != null
//        assert Persona.count() == 1
//
//        params.id = persona.id
//
//        controller.delete()
//
//        assert Persona.count() == 0
//        assert Persona.get(persona.id) == null
//        assert response.redirectedUrl == '/persona/list'
//    }

    /**
    *
    *Se declaran los objetos a utilizar durante la prueba
    */
    Persona p;
    Nota n;
    Libreta l;
    Etiqueta e1,e2;
   Adjunto a1,a3,a2;
    /**
    *
    *Inicializacion de los objetos a utilizar durante la prueba
    *Luego de crear cada uno de los objetos estos son almacenados en la BD
    */
    @Before
    void setUp() {
       
       p= new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"d27whkkkvvjpy01/1udnlslcu9iiuin",clave:"123456") 
       p.id=1;
  
       l=new Libreta (nombre:"Libreta XML Prueba Unitaria",tema: "libreta prueba unitaria")
       l.persona=p;
       
       
       p.addToLibretas(l);
       p.save(flush:true);
       n= new Nota(titulo: "Prueba Unitaria XML- Nota", texto: "Prueba", fecha:new Date().format("dd/MM/yyyy"), libreta: l)
      
       def controller = mockController(PersonaController)
       
       e1= new Etiqueta(texto: "Tag 1 prueba");
       e2= new Etiqueta(texto: "Tag 2 prueba");
    
        
       n.etiquetas= new ArrayList <Etiqueta>();
       n.adjuntos= new ArrayList <Adjunto>();
       n.etiquetas.add(e1);
       n.etiquetas.add(e2);

        a1 = new Adjunto(nombre:"a1", nota:n.id);
        a2 = new Adjunto(nombre:"a2", nota:n.id);
        a3 = new Adjunto(nombre:"a3", nota:n.id);
        
       l.notas= new ArrayList <Nota>();
       l.notas.add(n); 
       l.save(flush:true)
       n.save(flush:true)
       e1.save(flush:true)
       e2.save(flush:true)
    }
    
     @Test
    void testExportarXMLCorrecto() {

        session.persona=p
       
       
        params.action="descargarXML";
        params.controller="persona";  
        
        assert controller.descargarXML();
    }
}
