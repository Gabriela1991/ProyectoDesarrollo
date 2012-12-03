package grailsapplication1

class Libreta {
String nombre
String tema
Persona persona
static belongsTo= Persona
// relationships
static hasMany = [notas: Nota]
    static constraints = {
         nombre(blank:false, maxSize:30)
         tema(blank:false, maxSize:30)        
    }
     static mapping = {
        notas cascade:"all,delete-orphan"
    }
    def String toString() {
        return "${nombre}"
    
      }
}
