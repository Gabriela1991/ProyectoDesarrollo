package grailsapplication1

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import grails.test.ControllerUnitTestCase
import grails.test.GrailsUnitTestCase
import grails.test.GrailsUnitTestCase

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(NotaController)
@Mock(Persona)
class NotaControllerTests{
    Persona p;
    Nota n;
    Libreta l;
    Etiqueta e1,e2;
  //  Adjunto a1;
    
    @Before
    void setUp() {
       
       p= new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"qlhkg9e9m4s8gjj/xc93llpbixivaln",clave:"123456") 
       p.id=1;
       
       l=new Libreta (nombre:"Libreta prueba",tema: "tema libreta prueba")
       l.persona=p;
       
       
       p.addToLibretas(l);
       p.save(flush:true);
       n= new Nota(titulo: "Titulo nota", texto: "Texto nota", fecha:new Date().format("dd/MM/yyyy"), libreta: l)
      
       def controller = mockController(NotaController)
       
       e1= new Etiqueta(texto: "Etiqueta 1 prueba");
       e2= new Etiqueta(texto: "Etiqueta 2 prueba");
    //   a1= new Adjunto(nombre:"Adjunto 1 prueha", nota:n.id); 
        
       n.etiquetas= new ArrayList <Etiqueta>();
       n.adjuntos= new ArrayList <Adjunto>();
       n.etiquetas.add(e1);
       n.etiquetas.add(e2);
//       n.adjuntos.add(a1);
       l.notas= new ArrayList <Nota>();
       l.notas.add(n); 
        
    }

    @Test
    void testBuscadorNotasCorrecto() {

        session.persona=p
        params.submit="Ok";
        params.campo="a";
        params.action="buscar";
        params.controller="nota";  
        controller.buscar();
        assert controller.flash.message == ""
    }

    void testBuscadorNotasIncorrecto() {

        session.persona=p
        params.submit="Ok";
        params.campo="w";
        params.action="buscar";
        params.controller="nota";  
        controller.buscar();
        assert controller.flash.message != ""
    }
    
    @After
     void tearDown() {
        // Tear down logic here
        p=null;
        e1=null;
        e2=null;
        n=null;
        l=null;
        
    }
}
