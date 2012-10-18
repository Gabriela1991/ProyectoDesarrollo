package grailsapplication1



import org.junit.*
import grails.test.mixin.*

@TestFor(LibretaController)
@Mock(Libreta)
class LibretaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/libreta/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.libretaInstanceList.size() == 0
        assert model.libretaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.libretaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.libretaInstance != null
        assert view == '/libreta/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/libreta/show/1'
        assert controller.flash.message != null
        assert Libreta.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/libreta/list'

        populateValidParams(params)
        def libreta = new Libreta(params)

        assert libreta.save() != null

        params.id = libreta.id

        def model = controller.show()

        assert model.libretaInstance == libreta
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/libreta/list'

        populateValidParams(params)
        def libreta = new Libreta(params)

        assert libreta.save() != null

        params.id = libreta.id

        def model = controller.edit()

        assert model.libretaInstance == libreta
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/libreta/list'

        response.reset()

        populateValidParams(params)
        def libreta = new Libreta(params)

        assert libreta.save() != null

        // test invalid parameters in update
        params.id = libreta.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/libreta/edit"
        assert model.libretaInstance != null

        libreta.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/libreta/show/$libreta.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        libreta.clearErrors()

        populateValidParams(params)
        params.id = libreta.id
        params.version = -1
        controller.update()

        assert view == "/libreta/edit"
        assert model.libretaInstance != null
        assert model.libretaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/libreta/list'

        response.reset()

        populateValidParams(params)
        def libreta = new Libreta(params)

        assert libreta.save() != null
        assert Libreta.count() == 1

        params.id = libreta.id

        controller.delete()

        assert Libreta.count() == 0
        assert Libreta.get(libreta.id) == null
        assert response.redirectedUrl == '/libreta/list'
    }
}
