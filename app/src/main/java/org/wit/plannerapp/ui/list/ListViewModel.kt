package org.wit.plannerapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import org.wit.plannerapp.models.ItemModel
import timber.log.Timber
import java.lang.Exception

class ListViewModel : ViewModel() {

    private val itemList =
        MutableLiveData<List<ItemModel>>()

    val observableItemList: LiveData<List<ItemModel>>
        get() = itemList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    var readOnly = MutableLiveData(false)

    init { load() }

    fun load() {
        try {
            readOnly.value = false
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,
                itemList)
            Timber.i("List Load Success : ${itemList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("List Load Error : $e.message")
        }
    }

    fun delete(userid: String, id: String) {
        try {
            FirebaseDBManager.delete(userid,id)
            Timber.i("List Delete Success")
        }
        catch (e: Exception) {
            Timber.i("List Delete Error : $e.message")
        }
    }

    fun loadAll() {
        try {
            readOnly.value = true
            FirebaseDBManager.findAll(itemList)
            Timber.i("Report LoadAll Success : ${itemList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report LoadAll Error : $e.message")
        }
    }

}