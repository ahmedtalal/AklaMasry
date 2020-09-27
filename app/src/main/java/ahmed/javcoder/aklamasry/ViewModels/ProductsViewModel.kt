package ahmed.javcoder.aklamasry.ViewModels

import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.Repositories.ProductsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductsViewModel : ViewModel() {

    private var mlData:MutableLiveData<ArrayList<Products>>?=null
    private var productRepository:ProductsRepository? =null
    fun init(){
        productRepository = ProductsRepository.getInstance()
        mlData = productRepository!!.getAllProducts()
    }
    fun getProducts():LiveData<ArrayList<Products>>?{
        return mlData
    }
}