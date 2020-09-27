package ahmed.javcoder.aklamasry.ViewModels

import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.Repositories.FavoriteRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel:ViewModel() {

    private var mlData:MutableLiveData<ArrayList<Products>>? = null
    private var repository:FavoriteRepository? = null
    fun init(){
        repository = FavoriteRepository.getInstance()
        mlData  = repository!!.getAllFavorites()
    }
    fun getFavorites():LiveData<ArrayList<Products>>?{
        return mlData
    }
}