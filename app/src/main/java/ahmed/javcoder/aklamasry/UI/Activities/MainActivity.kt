package ahmed.javcoder.aklamasry.UI.Activities

import ahmed.javcoder.aklamasry.ActionsModules.SetFragment
import ahmed.javcoder.aklamasry.AuthModule.CheckCurrentUser
import ahmed.javcoder.aklamasry.FirebaseOPerations.UserProfile
import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.SettingModel.Settings
import ahmed.javcoder.aklamasry.UI.Fragments.ApportunityFragment
import ahmed.javcoder.aklamasry.UI.Fragments.FavoriteFragment
import ahmed.javcoder.aklamasry.UI.Fragments.HomeFragment
import ahmed.javcoder.aklamasry.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener ,BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        mainBinding.navigationViewId.itemIconTintList = null
        val toogle = ActionBarDrawerToggle(this , mainBinding.drawerId , mainBinding.toolbarId  , R.string.open , R.string.close)
        mainBinding.drawerId.addDrawerListener(toogle)
        toogle.syncState()

       init()

    }

    private fun init() {
        SetFragment.loadFragment(HomeFragment() , this)

        mainBinding.navigationViewId.setNavigationItemSelectedListener(this)
        mainBinding.bottomNavigationId.setOnNavigationItemSelectedListener(this)

        UserProfile.getUserPofile(mainBinding.navigationViewId , this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id :Int = item.itemId
        val fragment: Fragment
        when(id){
            R.id.share -> {
                Settings.shareApp(this)
            }
            R.id.Rate ->{
                Settings.ratingApp(this)
            }
            R.id.Aboutus -> {
                val url = "https://play.google.com/store/apps/developer?id=Ahmed+talal&hl=en"
                Settings.aboutUs(this , url)
            }
            R.id.logout -> {
                CheckCurrentUser.logOut(this)

            }
            R.id.all_id -> {
                mainBinding.toolbarId.title = "اكلات مصريه"
                fragment = HomeFragment()
                SetFragment.loadFragment(fragment , this)
            }
            R.id.monasapa -> {
                mainBinding.toolbarId.title = "المناسبات"
                fragment = ApportunityFragment()
                SetFragment.loadFragment(fragment , this)
            }
            R.id.favorite -> {
                mainBinding.toolbarId.title = "المفضله"
                fragment = FavoriteFragment()
                SetFragment.loadFragment(fragment , this)
            }
        }
        return true
    }


    override fun onBackPressed() {
        if (mainBinding.bottomNavigationId.selectedItemId == R.id.all_id) {
            super.onBackPressed()
            finish()
        } else {
            mainBinding.bottomNavigationId.setSelectedItemId(R.id.all_id)
        }
    }
}