package grailsapplication1


class Nota {
 static belongsTo = Libreta
 String titulo;
 String texto;
 private String fecha_creacion;
 Libreta libreta;
 List etiquetas = new ArrayList()
 //Etiqueta etiqueta;  
 //  
 // relaciones
 
  static hasMany = [etiquetas: Etiqueta,adjuntos: Adjunto]
 
    static mapping = {
        etiquetas cascade:"all,delete-orphan"
    }
    
    static constraints = {
    }
    
     def getEtiquetasList() {
        return LazyList.decorate(
              etiquetas,
              FactoryUtils.instantiateFactory(Etiqueta.class))
    }
    
      def String toString() {
        return "${titulo}, ${texto}"
    
      }
}

