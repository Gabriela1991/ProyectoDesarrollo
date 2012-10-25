package grailsapplication1

class Etiqueta {
    static belongsTo = [nota: Nota]
    
    String texto
  //  List notas= new ArrayList();
    
     boolean _deleted
    static transients = [ '_deleted' ]
    
    static constraints = {
    }
    
    def String toString (){
        return texto
    }
}
