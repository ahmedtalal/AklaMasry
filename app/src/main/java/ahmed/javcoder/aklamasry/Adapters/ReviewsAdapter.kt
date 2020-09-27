package ahmed.javcoder.aklamasry.Adapters

import ahmed.javcoder.aklamasry.Pojo.reviews
import ahmed.javcoder.aklamasry.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewsAdapter(private val list:ArrayList<reviews>, private var context: Context)
    : RecyclerView.Adapter<ReviewsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.review_list , parent , false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model:reviews = list.get(position)
        holder.name.text = model.getUserName().toString()
        holder.comment.text = model.getComment().toString()
        holder.rating.rating = model.getRating()!!.toFloat()
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name:TextView
        val comment:TextView
        val rating:RatingBar
        init {
            name = itemView.findViewById(R.id.person_name_id)
            comment = itemView.findViewById(R.id.review_title_id)
            rating = itemView.findViewById(R.id.rateReview_id)
        }
    }

}