package ahmed.javcoder.aklamasry.ActionsModules

import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.UI.Activities.MainActivity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class SetFragment {

    companion object {
        fun loadFragment(fragment:Fragment , context: Context){
            val transaction: FragmentTransaction = (context as MainActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_id, fragment)
            transaction.commit()
        }
    }
}