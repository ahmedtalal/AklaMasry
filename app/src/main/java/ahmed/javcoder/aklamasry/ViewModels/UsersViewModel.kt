package ahmed.javcoder.aklamasry.ViewModels

import ahmed.javcoder.aklamasry.Repositories.UserProfileRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsersViewModel : ViewModel() {

    private var mlData:MutableLiveData<HashMap<String , String>>?=null
    private var userRepository:UserProfileRepository? =null
    fun init(){
        userRepository = UserProfileRepository.getInstance()
        mlData = userRepository!!.getAllProducts()
    }
    fun getProducts():LiveData<HashMap<String , String>>?{
        return mlData
    }
}