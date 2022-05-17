package org.wit.plannerapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.models.PlannerManager
import timber.log.Timber

class ListDetailViewModel : ViewModel() {

    private val item = MutableLiveData<ItemModel>()

    var observableItem: LiveData<ItemModel>
        get() = item
        set(value) {item.value = value.value}

    fun getItem(userid:String, id: String) {
        try {
            FirebaseDBManager.findById(userid, id, item)
            Timber.i("Detail getItem() Success : ${
                item.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getItem() Error : $e.message")
        }
    }

    fun updateItem(userid:String, id: String,item: ItemModel) {
        try {
            FirebaseDBManager.update(userid, id, item)
            Timber.i("Detail update() Success : $item")
        }
        catch (e: java.lang.Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }

}