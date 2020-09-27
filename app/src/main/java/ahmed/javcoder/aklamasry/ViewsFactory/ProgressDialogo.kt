package ahmed.javcoder.aklamasry.ViewsFactory

import android.app.ProgressDialog
import android.content.Context

@Suppress("DEPRECATION")
class ProgressDialogo {
    companion object {
        fun createProgressDialog(context: Context , message:String) : ProgressDialog{
            val dialog : ProgressDialog = ProgressDialog(context)
            dialog.setMessage(message)
            dialog.show()
            dialog.setCancelable(false)
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            return dialog
        }
    }
}