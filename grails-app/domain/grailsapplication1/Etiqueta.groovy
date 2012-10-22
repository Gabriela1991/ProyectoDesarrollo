package grailsapplication1

class Etiqueta {
static belongsTo = [nota:Nota]
    String texto
 //Nota nota
 
    static constraints = {
    }
}
