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
    File arch1, arch2, arch3;
    Dropbox d;
    
    @Before
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

    @Test
    void testSomething() {
        
        p = new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"qlhkg9e9m4s8gjj/xc93llpbixivaln",clave:"123456");
        libreta = new Libreta(titulo:"libreta 1",tema:"sdfsf");
        nota = new Nota(titulo:"prueba", texto:"sdfsfd",fecha:"05/12/12",libreta:libreta.id);
    
        a1 = new Adjunto(nombre:"Introduccion.txt", nota:nota.id);
        a2 = new Adjunto(nombre:"a2", nota:nota.id);
        a3 = new Adjunto(nombre:"a3", nota:nota.id);
        
        
        arch1 = new File("C:\\Users\\Gabriela\\Documents\\NetBeansProjects\\ProyectoDesarrollo\\web-app\\images\\Introduccion.txt");
        arch2 = new File("C:\\Users\\Gabriela\\Documents\\NetBeansProjects\\ProyectoDesarrollo\\web-app\\images\\koek9.jpg");
        arch3 = new File("C:\\Users\\Gabriela\\Documents\\NetBeansProjects\\ProyectoDesarrollo\\web-app\\images\\libreta.jpg");
        
        d = new Dropbox();
               
        println(arch1);
        
        if(arch1){
           String a = d.auth(p.keysdropbox);
           String archivo = d.subirArchivo(arch1,p.keysdropbox.split('/')[0],p.keysdropbox.split('/')[1]);
           a1.archivo = archivo;
           a1.nota = nota;
           nota.addToAdjuntos(a1);
           archivo = d.subirArchivo(arch2,p.keysdropbox.split('/')[0],p.keysdropbox.split('/')[1]);
           a2.archivo = archivo;
           a2.nota = nota;
           nota.addToAdjuntos(a2);
           archivo = d.subirArchivo(arch3,p.keysdropbox.split('/')[0],p.keysdropbox.split('/')[1]);
           a3.archivo = archivo;
           a3.nota = nota;
           nota.addToAdjuntos(a3);
           System.out.println(" "+archivo+" nota: "+nota.getAdjuntos());
           assert nota.getAdjuntos().size() != 0,"agrego archivo";
        }
        else
            assert false;
    }
    
    
}
