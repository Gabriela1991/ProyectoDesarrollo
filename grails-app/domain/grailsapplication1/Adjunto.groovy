package grailsapplication1

class Adjunto {
String nombre
String archivo
Nota nota

    static constraints = {
    }
    
    def String toString() {
        return "${archivo}"
    
      }
}
