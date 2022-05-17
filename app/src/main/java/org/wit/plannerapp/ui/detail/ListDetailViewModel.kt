package org.wit.plannerapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.models.PlannerManager

class ListDetailViewModel : ViewModel() {

    private val item = MutableLiveData<ItemModel>()

    val observableItem: LiveData<ItemModel>
        get() = item

    fun getItem(id: Long) {
        item.value = PlannerManager.findById(id)
    }

}