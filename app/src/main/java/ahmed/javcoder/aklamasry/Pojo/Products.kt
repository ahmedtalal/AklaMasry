package ahmed.javcoder.aklamasry.Pojo

class Products {
    private var name:String?=""
    private var qauntity:String?=""
    private var way:String?= ""
    private var photo:String?= ""
    private var id:String?= ""

    constructor(name: String, qauntity: String, way: String, photo: String, id:String) {
        this.name = name
        this.qauntity = qauntity
        this.way = way
        this.photo = photo
        this.id = id
    }

    constructor(id: String?, name: String?, photo: String?) {
        this.id = id
        this.name = name
        this.photo = photo
    }

    constructor(){

    }



    fun setName(name:String){ this.name = name }
    fun getName(): String? { return this.name }

    fun setQauntity(qauntities: String){ this.qauntity = qauntities }
    fun getQauntity(): String? {return this.qauntity}

    fun setWay(way: String){this.way = way}
    fun getWay(): String? {return this.way}

    fun setPhoto(photo: String){this.photo = photo}
    fun getPhoto(): String? {return this.photo}

    fun setid(id: String){this.id = id}
    fun getid(): String? {return this.id}
}