package grailsapplication1

class Libreta {
String nombre
String tema

    static belongsTo= Persona
// relationships
//static hasMany = [notas: Nota]
    static constraints = {
    }
}
