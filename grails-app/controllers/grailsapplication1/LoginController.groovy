package grailsapplication1

import org.springframework.dao.DataIntegrityViolationException

class LoginController {

    def index = {redirect(action:login,params:params)}
    
    def login={}
    
    def manejaLogin = {
        
        def persona = Persona.findByCuentaAndPassword(params.usuario,params.password)
        if(!persona){
            
            flash.message="Usuario no encontrado: \$(params.usuario), Verifique sus datos"
            redirect(action:'login')
        }else{
            session.persona = persona
            redirect(controller:'evento')
        }
    }
    
    def logout = {
        if(session.persona){
            session.persona = null
            redirect(action:'login')
        }
    }
}
