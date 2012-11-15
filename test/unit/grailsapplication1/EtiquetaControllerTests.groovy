package grailsapplication1



import org.junit.*
import grails.test.mixin.*

@TestFor(EtiquetaController)
@Mock(Etiqueta)
class EtiquetaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/etiqueta/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.etiquetaInstanceList.size() == 0
        assert model.etiquetaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.etiquetaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.etiquetaInstance != null
        assert view == '/etiqueta/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/etiqueta/show/1'
        assert controller.flash.message != null
        assert Etiqueta.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/etiqueta/list'

        populateValidParams(params)
        def etiqueta = new Etiqueta(params)

        assert etiqueta.save() != null

        params.id = etiqueta.id

        def model = controller.show()

        assert model.etiquetaInstance == etiqueta
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/etiqueta/list'

        populateValidParams(params)
        def etiqueta = new Etiqueta(params)

        assert etiqueta.save() != null

        params.id = etiqueta.id

        def model = controller.edit()

        assert model.etiquetaInstance == etiqueta
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/etiqueta/list'

        response.reset()

        populateValidParams(params)
        def etiqueta = new Etiqueta(params)

        assert etiqueta.save() != null

        // test invalid parameters in update
        params.id = etiqueta.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/etiqueta/edit"
        assert model.etiquetaInstance != null

        etiqueta.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/etiqueta/show/$etiqueta.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        etiqueta.clearErrors()

        populateValidParams(params)
        params.id = etiqueta.id
        params.version = -1
        controller.update()

        assert view == "/etiqueta/edit"
        assert model.etiquetaInstance != null
        assert model.etiquetaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/etiqueta/list'

        response.reset()

        populateValidParams(params)
        def etiqueta = new Etiqueta(params)

        assert etiqueta.save() != null
        assert Etiqueta.count() == 1

        params.id = etiqueta.id

        controller.delete()

        assert Etiqueta.count() == 0
        assert Etiqueta.get(etiqueta.id) == null
        assert response.redirectedUrl == '/etiqueta/list'
    }
}
