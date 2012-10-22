package grailsapplication1

class User {

    static constraints = {
        login(blank:false, unique:true)
        password(blank:false, password:true)
        role(inList:["admin","user"])
    }
    
    String login
    String password
    String role="user"
    
    String toString(){
        return "\$(login)"
    }
}
