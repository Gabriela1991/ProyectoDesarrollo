package grailsapplication1



import org.junit.*
import grails.test.mixin.*
import grails.converters.*
import grails.converters.deep.XML
import org.springframework.mock.web.MockMultipartHttpServletRequest
import org.springframework.mock.web.MockMultipartHttpServletRequest
import org.springframework.mock.web.MockMultipartFile
import org.codehaus.groovy.grails.plugins.testing.GrailsMockMultipartFile
@TestFor(PersonaController)
@Mock([Persona,Libreta,Nota,Etiqueta])
class PersonaControllerTests {

    /**
     *
     *Se declaran los objetos a utilizar durante la prueba
     */
    Persona p;
    Nota n;
    Libreta l;
    Etiqueta e1,e2;
    Adjunto a1,a3,a2;
    File arch1;
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
        arch1 = new File("C:/Users/Eule/Downloads/datosPersona.xml");
        l.notas= new ArrayList <Nota>();
        l.notas.add(n); 
        l.save(flush:true)
        n.save(flush:true)
        e1.save(flush:true)
        e2.save(flush:true)
       
       

    }
    
    @Test 
    void testExportarXML() {

        session.persona=p
       
       
        params.action="descargarXML";
        params.controller="persona";  
        
        assert controller.descargarXML();
    }
    
     void testImportarXML() {

        session.persona=p
        params.controller="persona"; 
        params.action="readXML";
        final file = new GrailsMockMultipartFile ("archivo", "datosPersona.xml","multipart/form-data",arch1.getBytes())
        request.addFile(file)
        assert controller.readXML()
    }
}
