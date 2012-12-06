package grailsapplication1



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Dropbox)
class DropboxTests {

    Dropbox d;
    Persona p;
    
    @Before
    void init(){
        d=new Dropbox();
     p=new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:null,clave:"123456");
    
    }
    
    @Test
    void testSomething() {
     assert d.unit(p.keysdropbox)!=null :'Claves generadas para el usuario';
     
    }
    
    @After
    void teardown (){
        d=null;
        p=null;
    }
}
