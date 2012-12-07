package grailsapplication1

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import grails.test.ControllerUnitTestCase

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class NotaControllerTests extends ControllerUnitTestCase{
    Persona p;
    Nota n;
    Libreta l;
    
    @Before
    void setUp() {
       
       p= new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"qlhkg9e9m4s8gjj/xc93llpbixivaln",clave:"123456") 
       p.id=1;
       l=new Libreta (nombre:"Libreta prueba",tema: "tema libreta prueba")
       l.persona=p;
       n= new Nota(titulo: "Titulo nota", texto: "Texto nota", fecha:new Date().format("dd/MM/yyyy"), libreta: l)
       def controller = mockController(NotaController)
      // mockDomain ()
        // Setup logic here
    }

    @Test
    void testBuscadorNotas() {
        //mockDomain(Persona, [p])
       // p.findById(1);
        controller.getsession();
        params.submit="Ok";
        params.campo="a";
        params.action="buscar";
        params.controller="nota";  
        controller.buscar();
        
    }
    
    @After
     void tearDown() {
        // Tear down logic here
         
    }
}
