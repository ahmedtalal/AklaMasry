package ahmed.javcoder.aklamasry.Adapters

import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.UI.Activities.MainActivity
import ahmed.javcoder.aklamasry.UI.Activities.ShowProductActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private val list:ArrayList<Products> , private var context: Context)
    : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.products_list_view , parent , false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model:Products = list.get(position)
        holder.title.text = ("طريقه عمل " + model.getName())
        if (model.getPhoto() != null){
            Picasso.get()
                .load(model.getPhoto())
                .error(R.drawable.ic_dinner)
                .into(holder.image)
        }

        holder.moreBtn.setOnClickListener {
            val id:String? = model.getid()
          // your code here
            val intent = Intent(context , ShowProductActivity::class.java)
            intent.putExtra("id"  , id)
            context.startActivity(intent)
            (context as MainActivity).finish()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var title:TextView
        var image:ImageView
        var moreBtn:Button
        init {
            this.title = itemView.findViewById(R.id.title_id)
            this.image = itemView.findViewById(R.id.aklaImage_id)
            this.moreBtn = itemView.findViewById(R.id.moreBtn_id)
        }
    }

}