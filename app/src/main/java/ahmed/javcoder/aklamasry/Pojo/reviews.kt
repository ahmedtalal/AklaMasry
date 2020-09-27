package ahmed.javcoder.aklamasry.Pojo



class reviews {
    private var userName:String? = null
    private var rating:Int? = null
    private var comment:String? = null

    constructor(userName: String, rating: Int, comment: String) {
        this.userName = userName
        this.rating = rating
        this.comment = comment
    }

    constructor(){

    }

    fun setUserName(name:String){ this.userName = name }
    fun getUserName(): String? { return this.userName }

    fun setRating(rating: Int){ this.rating = rating }
    fun getRating(): Int? {return this.rating}

    fun setComment(comment: String){this.comment = comment}
    fun getComment(): String? {return this.comment}


}