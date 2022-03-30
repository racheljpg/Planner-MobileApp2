package org.wit.plannerapp.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.models.PlannerManager

class AddViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is a test"
    }
    val text: LiveData<String> = _text

    fun addItem(item : ItemModel) {
        PlannerManager.create(item)
    }

    fun getItems() : String {
        return PlannerManager.findAll().toString()
    }
}