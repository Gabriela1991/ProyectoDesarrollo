package grailsapplication1

class Persona {

    String nombre
    String apellido
    String correo
    String cuenta
    String clave
  //  String cuentadropbox
   // String clavedropbox
  //  static hasMany=[libretas:Libreta]

    
    static constraints = {
        nombre(blank:false, maxSize:15)
        apellido(blank:false, maxSize:15)
        correo(blank:false, maxSize:45, unique:true)
        cuenta(blank:false, maxSize:45, unique:true)
        clave(blank:false, password:true, maxSize:15)
     //   cuentadropbox(blank:false, maxSize:45)
       // clavedropbox(blank:false, password:true, maxSize:15)
    }
    
    String toString()
        { "$correo" }

}