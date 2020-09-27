package ahmed.javcoder.aklamasry.Pojo

class UserModel {
    private var name:String? = null
    private var email:String? = null

    constructor(name: String, email: String) {
        this.name = name
        this.email = email
    }

    constructor(){

    }

    fun setName(name:String){ this.name = name }
    fun getName(): String? { return this.name }

    fun setEmail(email: String){ this.email = email }
    fun getEmail(): String? {return this.email}


}