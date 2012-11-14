package grailsapplication1


class Nota {
 static belongsTo = Libreta
 String titulo;
 String texto;
 String fecha;
 Libreta libreta;
 List etiquetas = new ArrayList()

 // relaciones
 
  static hasMany = [etiquetas: Etiqueta,adjuntos: Adjunto]
 
    static mapping = {
        etiquetas cascade:"all,delete-orphan"
    }
    
    static constraints = {
        texto nullabe: true;
        texto maxSize: 1000;
    }
    
     def getEtiquetasList() {
        return LazyList.decorate(
              etiquetas,
              FactoryUtils.instantiateFactory(Etiqueta.class))
    }
    
      def String toString() {
        return "${titulo}, ${texto}, ${fecha}"
    
      }
}

