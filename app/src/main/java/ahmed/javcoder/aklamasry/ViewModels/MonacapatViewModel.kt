package ahmed.javcoder.aklamasry.ViewModels

import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.Repositories.MonacapatRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonacapatViewModel : ViewModel() {

    private var mlData:MutableLiveData<ArrayList<Products>>?=null
    private var productRepository:MonacapatRepository? =null
    fun init(type:String){
        productRepository = MonacapatRepository.getInstance()
        mlData = productRepository!!.getAllMonacapat(type)
    }
    fun getMonacapat():LiveData<ArrayList<Products>>?{
        return mlData
    }
}