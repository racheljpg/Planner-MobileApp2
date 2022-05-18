import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.models.PlannerStore
import timber.log.Timber

object FirebaseDBManager : PlannerStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(itemList: MutableLiveData<List<ItemModel>>) {
        database.child("items")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Planner error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<ItemModel>()
                    val children = snapshot.children
                    children.forEach {
                        val item = it.getValue(ItemModel::class.java)
                        localList.add(item!!)
                    }
                    database.child("items")
                        .removeEventListener(this)

                    itemList.value = localList
                }
            })
    }

    override fun findAll(userid: String, itemList: MutableLiveData<List<ItemModel>>) {

        database.child("user-items").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Item error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<ItemModel>()
                    val children = snapshot.children
                    children.forEach {
                        val item = it.getValue(ItemModel::class.java)
                        localList.add(item!!)
                    }
                    database.child("user-items").child(userid)
                        .removeEventListener(this)

                    itemList.value = localList
                }
            })
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, item: ItemModel) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("items").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        item.uid = key
        val itemValues = item.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/items/$key"] = itemValues
        childAdd["/user-items/$uid/$key"] = itemValues

        database.updateChildren(childAdd)
    }

    override fun delete(userid: String, itemid: String) {

        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/items/$itemid"] = null
        childDelete["/user-items/$userid/$itemid"] = null

        database.updateChildren(childDelete)
    }

    override fun update(userid: String, itemid: String, item: ItemModel) {

        val itemValues = item.toMap()

        val childUpdate : MutableMap<String, Any?> = HashMap()
        childUpdate["items/$itemid"] = itemValues
        childUpdate["user-items/$userid/$itemid"] = itemValues

        database.updateChildren(childUpdate)
    }

    override fun findById(userid: String, itemid: String, item: MutableLiveData<ItemModel>) {

        database.child("user-items").child(userid)
            .child(itemid).get().addOnSuccessListener {
                item.value = it.getValue(ItemModel::class.java)
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }
    }


}