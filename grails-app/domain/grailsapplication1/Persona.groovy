package grailsapplication1




class Persona {

    String nombre
    String apellido
    String correo
    String keysdropbox
    String clave
  
    static hasMany=[libretas:Libreta]

    
    static constraints = {
        nombre(blank:false, maxSize:15)
        apellido(blank:false, maxSize:15)
        correo(email:true, blank:false, maxSize:45, unique:true)
        keysdropbox(blank:true, maxSize:40, nullable:true)
        clave(blank:false, clave:true, maxSize:2000)
     
    }
    def beforeInsert = {
		clave = clave.encodeAsSHA()
		
	}
    
    String toString()
        { "$correo" }

    static mapping = {
        keysdropbox cascade:"all-delete-orphan"
      
    }
}