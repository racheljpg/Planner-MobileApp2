import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.models.PlannerStore
import timber.log.Timber

object FirebaseDBManager : PlannerStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(itemList: MutableLiveData<List<ItemModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, itemList: MutableLiveData<List<ItemModel>>) {
        TODO("Not yet implemented")
    }

    override fun findById(userid: String, itemid: String, item: MutableLiveData<ItemModel>) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override fun update(userid: String, itemid: String, item: ItemModel) {
        TODO("Not yet implemented")
    }
}