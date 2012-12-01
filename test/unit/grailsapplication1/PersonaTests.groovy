package grailsapplication1



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Persona)
class PersonaTests {

    void testSomething() {
      Persona pprueba = new Persona(nombre:"juan",apellido:"apellido",correo:"correo",clave:"clave")
      assertEquals "juan", pprueba.nombre
    }
}
