package grailsapplication1

class Person {

    String name;
    String email;
    
    static constraints = {
        name blank:false;
        email blank:false, unique:true;
    }
}
