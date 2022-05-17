package org.wit.plannerapp.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.models.PlannerManager

class AddViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    private val _text = MutableLiveData<String>().apply {
        value = "This is a test"
    }
    val text: LiveData<String> = _text

    fun addItem(firebaseUser: MutableLiveData<FirebaseUser>,
                item: ItemModel) {
        status.value = try {
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