package grailsapplication1

class Persona {

    String nombre
    String apellido
    String correo
    String cuenta
    String clave
    String cuentadropbox
    String clavedropbox
    

    
    static constraints = {
        nombre(blank:false, maxSize:15)
        apellido(blank:false, maxSize:15)
        correo(blank:false, maxSize:45)
        cuenta(blank:false, maxSize:45)
        clave(blank:false, password:true, maxSize:15)
        cuentadropbox(blank:false, maxSize:45)
        clavedropbox(blank:false, password:true, maxSize:15)
    }
}