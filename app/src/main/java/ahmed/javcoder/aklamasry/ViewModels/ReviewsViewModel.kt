package ahmed.javcoder.aklamasry.ViewModels

import ahmed.javcoder.aklamasry.Pojo.reviews
import ahmed.javcoder.aklamasry.Repositories.ReviewsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReviewsViewModel :ViewModel() {

    private var mlData: MutableLiveData<ArrayList<reviews>>? = null
    private var repository: ReviewsRepository? = null

    fun init(id:String){
        repository = ReviewsRepository.getInstance()
        mlData  = repository!!.getAllReviews(id)
    }
    fun getReview(): LiveData<ArrayList<reviews>>?{
        return mlData
    }
}