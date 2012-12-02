package grailsapplication1

class Libreta {
String nombre
String tema
Persona persona
static belongsTo= Persona
// relationships
static hasMany = [notas: Nota]
    static constraints = {
    }
     static mapping = {
        notas cascade:"all,delete-orphan"
    }
    def String toString() {
        return "${nombre}"
    
      }
}
