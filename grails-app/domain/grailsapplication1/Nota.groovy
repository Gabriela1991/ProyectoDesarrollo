package grailsapplication1


class Nota {
    static belongsTo = Libreta
 String titulo;
 String texto;
 private String fecha_creacion;
 Libreta libreta;
 //Etiqueta etiqueta;  
 //  
 // relaciones
  static hasMany = [etiquetas: Etiqueta,adjuntos: Adjunto]
 
    static constraints = {
    }
}

