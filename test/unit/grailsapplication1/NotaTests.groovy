package grailsapplication1



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Nota)
class NotaTests {
    Persona p;
    Libreta libreta;
    Nota nota;
    Adjunto a1,a3,a2;
    File arch1;
    Dropbox d;
    
 
    void init(){
       
        p = new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"qlhkg9e9m4s8gjj/xc93llpbixivaln",clave:"123456");
        libreta = new Libreta(titulo:"libreta 1",tema:"sdfsf");
        nota = new Nota(titulo:"prueba", texto:"sdfsfd",fecha:"05/12/12",libreta:libreta.id);
    
        a1 = new Adjunto(nombre:"a1", nota:nota.id);
        a2 = new Adjunto(nombre:"a2", nota:nota.id);
        a3 = new Adjunto(nombre:"a3", nota:nota.id);
        
        arch1 = new File("C:/Users/Gabriela/Documents/NetBeansProjects/ProyectoDesarrollo/web-app/images/Introduccion.txt");
        
        d = new Dropbox();
        
        
    }

    void testSomething() {
        
         p = new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"qlhkg9e9m4s8gjj/xc93llpbixivaln",clave:"123456");
        libreta = new Libreta(titulo:"libreta 1",tema:"sdfsf");
        nota = new Nota(titulo:"prueba", texto:"sdfsfd",fecha:"05/12/12",libreta:libreta.id);
    
        a1 = new Adjunto(nombre:"a1", nota:nota.id);
        a2 = new Adjunto(nombre:"a2", nota:nota.id);
        a3 = new Adjunto(nombre:"a3", nota:nota.id);
        
        //arch1 = new File("C:\\Users\\Gabriela\\Documents\\NetBeansProjects\\ProyectoDesarrollo\\web-app\\images\\Introduccion.txt");
        
        d = new Dropbox();
               
        
        assert d.subirArchivo(new File("C:\\Users\\Gabriela\\Documents\\NetBeansProjects\\ProyectoDesarrollo\\web-app\\images\\Introduccion.txt"),p.keysdropbox.split('/')[0],p.keysdropbox.split('/')[1]);
        
    }
}
