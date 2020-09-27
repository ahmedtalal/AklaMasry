package ahmed.javcoder.aklamasry.ViewsFactory

import android.content.Context
import android.widget.Toast

class ToastView {

    companion object {
        fun createToast(context: Context , message:String){
            Toast.makeText(context , message , Toast.LENGTH_LONG).show()
        }
    }
}