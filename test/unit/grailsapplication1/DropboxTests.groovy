package grailsapplication1



import grails.test.mixin.*
import org.junit.*

/**
 * Esta prueba unitaria ha sido creada con la intencion de verificar que la conexion
 * con dropbox desde el aplicativo se ha realizado de manera exitosa
 */
@TestFor(Dropbox)
class DropboxTests {

    Dropbox d;
    Persona p;
    
    /**
    *
    *Inicializacion de los objetos a utilizar durante la prueba
    */
    @Before
    void init(){
        d=new Dropbox();
        p=new Persona(nombre:"juan",apellido:"perez",correo:"jperez@ucab.edu.ve",keysdropbox:null,clave:"123456");
    }
    
    
    /**
    *
    *Se considera que la aplicacion se ha conectado de manea exitosa, cuando
    *al usuario se le ha asignado la llave para ingresar a dropbox
    */
    @Test
    void testDirectorio() {
     assert d.unit(p.keysdropbox)!=null :'Claves generadas para el usuario';
     
    }
    
    /**
    *
    *Al finalizar la prueba colocamos en null a todos los objetos utilizados
    */
    @After
    void teardown (){
        d=null;
        p=null;
    }
}
