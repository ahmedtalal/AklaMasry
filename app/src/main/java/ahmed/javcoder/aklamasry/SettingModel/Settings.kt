package ahmed.javcoder.aklamasry.SettingModel

import ahmed.javcoder.aklamasry.BuildConfig
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

class Settings {

    companion object {
        fun shareApp(context: Context){
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, "اكله مصري")
                var shareMessage = "\nLet me recommend you this application\n\n"
                shareMessage =
                    """
                    ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                    
                    
                    """.trimIndent()
                intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    context.startActivity(Intent.createChooser(intent, "choose one"))
            } catch (e: Exception) {
                Log.i("exception", "shareApp: " + e.message)
            }
        }

        fun ratingApp(context: Context){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=ahmed.javcoder.aklamasry")
            Log.v(
                "Uri",
                "https://play.google.com/store/apps/details?id=ahmed.javcoder.aklamasry"
            )
            context.startActivity(intent)
        }

        fun aboutUs(context: Context , link4:String){
            val uriUrl = Uri.parse(link4)
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            //launchBrowser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //launchBrowser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launchBrowser)
        }

    }
}