package grailsapplication1



import org.junit.*
import grails.test.mixin.*

@TestFor(LoginLogoutController)
@Mock(LoginLogout)
class LoginLogoutControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/loginLogout/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.loginLogoutInstanceList.size() == 0
        assert model.loginLogoutInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.loginLogoutInstance != null
    }

    void testSave() {
        controller.save()

        assert model.loginLogoutInstance != null
        assert view == '/loginLogout/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/loginLogout/show/1'
        assert controller.flash.message != null
        assert LoginLogout.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/loginLogout/list'

        populateValidParams(params)
        def loginLogout = new LoginLogout(params)

        assert loginLogout.save() != null

        params.id = loginLogout.id

        def model = controller.show()

        assert model.loginLogoutInstance == loginLogout
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/loginLogout/list'

        populateValidParams(params)
        def loginLogout = new LoginLogout(params)

        assert loginLogout.save() != null

        params.id = loginLogout.id

        def model = controller.edit()

        assert model.loginLogoutInstance == loginLogout
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/loginLogout/list'

        response.reset()

        populateValidParams(params)
        def loginLogout = new LoginLogout(params)

        assert loginLogout.save() != null

        // test invalid parameters in update
        params.id = loginLogout.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/loginLogout/edit"
        assert model.loginLogoutInstance != null

        loginLogout.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/loginLogout/show/$loginLogout.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        loginLogout.clearErrors()

        populateValidParams(params)
        params.id = loginLogout.id
        params.version = -1
        controller.update()

        assert view == "/loginLogout/edit"
        assert model.loginLogoutInstance != null
        assert model.loginLogoutInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/loginLogout/list'

        response.reset()

        populateValidParams(params)
        def loginLogout = new LoginLogout(params)

        assert loginLogout.save() != null
        assert LoginLogout.count() == 1

        params.id = loginLogout.id

        controller.delete()

        assert LoginLogout.count() == 0
        assert LoginLogout.get(loginLogout.id) == null
        assert response.redirectedUrl == '/loginLogout/list'
    }
}
