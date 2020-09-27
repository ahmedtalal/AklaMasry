package ahmed.javcoder.aklamasry.Pojo

import android.widget.EditText

class EditTextModel {
    private var nameEdittext:EditText
    private var quantityEdittext:EditText
    private var wayEdittext:EditText

    constructor(nameEdittext: EditText, quantityEdittext: EditText, wayEdittext: EditText) {
        this.nameEdittext = nameEdittext
        this.quantityEdittext = quantityEdittext
        this.wayEdittext = wayEdittext
    }

    fun setNameET(name:EditText){ this.nameEdittext = name }
    fun getNameET():EditText{return this.nameEdittext}

    fun setQuantityET(quantity:EditText){this.quantityEdittext = quantity}
    fun getQuantityET():EditText{return this.quantityEdittext}

    fun setWayET(way:EditText){this.wayEdittext = way}
    fun getWayET():EditText{return this.wayEdittext}

}