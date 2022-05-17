package org.wit.plannerapp.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import timber.log.Timber

object PlannerManager : PlannerStore {

    override fun findAll(itemsList: MutableLiveData<List<ItemModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, itemsList: MutableLiveData<List<ItemModel>>) {
        TODO("Not yet implemented")
    }

    override fun findById(userid: String, itemid: String, item: MutableLiveData<ItemModel>) {
        TODO("Not yet implemented")
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, item: ItemModel) {
        TODO("Not yet implemented")
    }

    override fun delete(userid: String, itemid: String) {
        TODO("Not yet implemented")
    }

    override fun update(userid: String, itemid: String, item: ItemModel) {
        TODO("Not yet implemented")
    }

    fun search(searchTerm: String): List<ItemModel> {
        TODO("Not yet implemented")
    }

}