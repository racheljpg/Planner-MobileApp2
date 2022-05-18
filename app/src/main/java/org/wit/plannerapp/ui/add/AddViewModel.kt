package org.wit.plannerapp.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import org.wit.plannerapp.firebase.FirebaseImageManager
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.models.PlannerManager

class AddViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status


    fun addItem(firebaseUser: MutableLiveData<FirebaseUser>,
                item: ItemModel) {
        status.value = try {
            item.profilepic = FirebaseImageManager.imageUri.value.toString()
            FirebaseDBManager.create(firebaseUser,item)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    //fun getItems() : String {
    //    return PlannerManager.findAll().toString()
    //}
}