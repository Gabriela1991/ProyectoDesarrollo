package grailsapplication1

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class NotaControllerTests {

    void setUp() {
        
       
        // Setup logic here
    }

   

    void testSomething() {
        controller.buscar().params
        
    }
    
     void tearDown() {
        // Tear down logic here
         
    }
}
