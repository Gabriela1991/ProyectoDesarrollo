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
  
    /**
    *
    *Se declaran los objetos a utilizar durante la prueba
    */
    Persona p;
    Nota n;
    Libreta l;
    Etiqueta e1,e2;
  
    /**
    *
    *Inicializacion de los objetos a utilizar durante la prueba
    *Luego de crear cada uno de los objetos estos son almacenados en la BD
    */
    @Before
    void setUp() {
       
       p= new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"d27whkkkvvjpy01/1udnlslcu9iiuin",clave:"123456") 
       p.id=1;
       
       l=new Libreta (nombre:"Libreta Prueba Unitaria",tema: "libreta prueba unitaria")
       l.persona=p;
       
       
       p.addToLibretas(l);
       p.save(flush:true);
       n= new Nota(titulo: "Prueba Unitaria - Nota", texto: "Prueba", fecha:new Date().format("dd/MM/yyyy"), libreta: l)
      
       def controller = mockController(NotaController)
       
       e1= new Etiqueta(texto: "Etiqueta 1 prueba");
       e2= new Etiqueta(texto: "Etiqueta 2 prueba");
    
        
       n.etiquetas= new ArrayList <Etiqueta>();
       n.adjuntos= new ArrayList <Adjunto>();
       n.etiquetas.add(e1);
       n.etiquetas.add(e2);

       l.notas= new ArrayList <Nota>();
       l.notas.add(n); 
        
    }

    
    /**
    *Aqui se presentan dos casos de prueba
    *a) testBuscadorNotasCorrecto --> Busca en la BD todas aquellas notas que tengan 
    *la cadena de busqueda indicado en el campo params.campo, como lo indicado
    *en ese campo se encuentra contenido en una nota, dira que la busqueda ha 
    *sido exitosa
    *
    *b) testBuscadorNotasIncorrecto --> Busca en la BD todas aquellas notas que tengan 
    *la cadena de busqueda indicado en el campo params.campo, como lo indicado
    *en ese campo no se encuentra contenido en una nota, dira que la busqueda no ha 
    *devuelto ninguna nota con esa caracteristica solicitada
    */
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
    
    
    /**
    *
    *Al finalizar la prueba colocamos en null a todos los objetos utilizados
    */
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
