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
       
        p = new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:"mgbkdsd67qc14wl/q0hy12n2s5jwcsu",clave:"123456");
        libreta = new Libreta(titulo:"libreta 1",tema:"sdfsf");
        nota = new Nota(titulo:"prueba", texto:"sdfsfd",fecha:"05/12/12",libreta:libreta.id);
    
        a1 = new Adjunto(nombre:"Introduccion.txt", nota:nota.id);
        a2 = new Adjunto(nombre:"a2", nota:nota.id);
        a3 = new Adjunto(nombre:"a3", nota:nota.id);
        
        
        arch1 = new File("C:/Users/Angel/Desktop/ProyectoDesarrollo-master/web-app/images/Introduccion.txt");
        arch2 = new File("C:/Users/Angel/Desktop/ProyectoDesarrollo-master/web-app/images/koek9.jpg");
        arch3 = new File("C:/Users/Angel/Desktop/ProyectoDesarrollo-master/web-app/images/libreta.jpg");
        
        d = new Dropbox();
    }

    @Test
    void testNotaTresAdjuntos() {
        
        if(arch1 && arch2 && arch3){
           String a = d.auth(p.keysdropbox);
           String archivo;
       
               archivo = d.subirArchivo(arch1,p.keysdropbox.split('/')[0],p.keysdropbox.split('/')[1]);
               assertNotNull archivo
               a1.archivo = archivo;
               a1.nota = nota;
               nota.addToAdjuntos(a1);

                archivo = d.subirArchivo(arch2,p.keysdropbox.split('/')[0],p.keysdropbox.split('/')[1]);
                assertNotNull archivo
                a2.archivo = archivo;
                a2.nota = nota;
                nota.addToAdjuntos(a2);

                archivo = d.subirArchivo(arch3,p.keysdropbox.split('/')[0],p.keysdropbox.split('/')[1]);
                assertNotNull archivo;
                a3.archivo = archivo;
                a3.nota = nota;
                nota.addToAdjuntos(a3);

            
           System.out.println("nota: "+nota.getAdjuntos());
          
        }
        else{
            printf("No existe alguno de los archivos");
            assertNull arch1,arch2,arch3;
        }
    }   

    @After
     void tearDown() {
        // Tear down logic here
        p=null;
        a1=null;
        a2=null;
        a3=null;
        arch1=null;
        arch2=null;
        arch3=null;
        nota=null;
        libreta=null;
        d=null;
    }
}
