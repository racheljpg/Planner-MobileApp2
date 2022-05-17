package org.wit.plannerapp.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

//
//interface PlannerStore {
//    fun findAll(): List<ItemModel>
//    fun create(planner: ItemModel)
//    fun update(planner: ItemModel)
//    fun delete(planner: ItemModel)
//    fun search(searchTerm: String): List<ItemModel>
//}

interface PlannerStore {
    fun findAll(itemsList:
                MutableLiveData<List<ItemModel>>
    )
    fun findAll(userid:String,
                itemsList: MutableLiveData<List<ItemModel>>)
    fun findById(userid:String, itemid: String,
                 item: MutableLiveData<ItemModel>)
    fun create(firebaseUser: MutableLiveData<FirebaseUser>, item: ItemModel)
    fun delete(userid:String, itemid: String)
    fun update(userid:String, itemid: String, item: ItemModel)
}