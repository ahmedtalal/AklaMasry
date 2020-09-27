package ahmed.javcoder.aklamasry.UI.Fragments

import ahmed.javcoder.aklamasry.R
import ahmed.javcoder.aklamasry.UI.Activities.ShowMonacapatActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ApportunityFragment : Fragment() , View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_apportunity , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initialize
        init(view)
    }

    private fun init(view: View) {
         val eidOm : Button = view.findViewById(R.id.eidOm_btn_id)
         val eidLove: Button = view.findViewById(R.id.eidLove_btn_id)
         val ramadan : Button = view.findViewById(R.id.Ramadan_btn_id)
         val eidAdha: Button = view.findViewById(R.id.adha_btn_id)
         val eidMelad: Button = view.findViewById(R.id.melad_btn_id)
         val racSana: Button = view.findViewById(R.id.racSana_btn_id)
         val zawag : Button = view.findViewById(R.id.zawag_btn_id)

        eidOm.setOnClickListener(this)
        eidLove.setOnClickListener(this)
        eidAdha.setOnClickListener(this)
        racSana.setOnClickListener(this)
        eidMelad.setOnClickListener(this)
        ramadan.setOnClickListener(this)
        zawag.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.eidOm_btn_id -> switching("EdidOm")
            R.id.eidLove_btn_id ->switching("EidLove")
            R.id.adha_btn_id ->switching("EidAdha")
            R.id.Ramadan_btn_id ->switching("Ramadan")
            R.id.melad_btn_id ->switching("EidMelad")
            R.id.racSana_btn_id ->switching("RasCana")
            R.id.zawag_btn_id ->switching("zawag")
        }
    }

    private fun switching(s: String) {
        val intent = Intent(context , ShowMonacapatActivity::class.java)
        intent.putExtra("rootType" , s)
        context?.startActivity(intent)
        (context as Activity).finish()
    }


}