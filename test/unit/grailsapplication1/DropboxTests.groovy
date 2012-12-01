package grailsapplication1



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Dropbox)
class DropboxTests {

    Dropbox d=new Dropbox();
    Persona p=new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:null,clave:"123456");
    
    void testSomething() {
     d.auth(p.keysdropbox);
     
    }
}
