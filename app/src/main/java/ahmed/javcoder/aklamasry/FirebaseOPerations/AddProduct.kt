package ahmed.javcoder.aklamasry.FirebaseOPerations

import ahmed.javcoder.aklamasry.Pojo.EditTextModel
import ahmed.javcoder.aklamasry.Pojo.Products
import ahmed.javcoder.aklamasry.ViewsFactory.ToastView
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class AddProduct :AddingInterface {
    private var editTextModel:EditTextModel
    private var context:Context
    private val refrence :DatabaseReference = FirebaseDatabase.getInstance().reference
    private val fbs = FirebaseStorage.getInstance()
    private val storageReference = fbs.reference.child("ImagesUri")
    private var image:Uri
    private var dialog:ProgressDialog
    constructor(model: EditTextModel , context: Context , imageUri:Uri , dialog:ProgressDialog) {
        this.editTextModel = model
        this.context = context
        this.image = imageUri
        this.dialog = dialog
    }



    override fun checkViews(): Boolean {
        val name:String = editTextModel.getQuantityET().text.toString()
        val quantity:String = editTextModel.getQuantityET().text.toString()
        val way:String = editTextModel.getWayET().text.toString()

        if (name.equals("")){
            editTextModel.getNameET().error = "يجب عليك ادخال قيمه"
            editTextModel.getNameET().requestFocus()
            return false
        }
        if (quantity.equals("")){
            editTextModel.getQuantityET().error = "يجب عليك ادخال قيمه"
            editTextModel.getQuantityET().requestFocus()
            return false
        }
        if (way.equals("")){
            editTextModel.getWayET().error = "يجب عليك ادخال قيمه"
            editTextModel.getWayET().requestFocus()
            return false
        }
        return true
    }
    override fun addOPeration() {
        if (checkViews() == true){
            val rfs:StorageReference = storageReference.child(image.path!!)
            var uploadTask = rfs.putFile(image)
            val task: Task<Uri?> = uploadTask.continueWithTask { task:Task<UploadTask.TaskSnapshot?> ->
                    if (!task.isSuccessful) {
                       ToastView.createToast(context , task.exception?.message!!)
                    }
                    rfs.downloadUrl
                }.
                addOnCompleteListener { task:Task<Uri?> ->
                    val retrieveUriImage = task.result
                    val converImage = retrieveUriImage.toString()
                    saveData(converImage , context)
                }
        }
    }

    private fun saveData(converImage: String, context: Context) {
        val id :String = refrence.push().key.toString()
        val product = Products(
            editTextModel.getNameET().text.toString() ,
            editTextModel.getQuantityET().text.toString() ,
            editTextModel.getWayET().text.toString() ,
            converImage,
            id
        )
        refrence.child("Monacapat").child("zawag").child(id).setValue(product)
            .addOnCompleteListener { t:Task<Void?>->
                if (t.isSuccessful){
                    ToastView.createToast(context , "true")
                    dialog.dismiss()
                }else {
                    ToastView.createToast(context , "false")
                    dialog.dismiss()
                }
            }
    }


}