package org.wit.plannerapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Parcelize
//data class ItemModel (var id: Long = 0,
//                        var title: String = "",
//                        var description: String = ""
//) : Parcelable

@IgnoreExtraProperties
@Parcelize
data class ItemModel(
    var uid: String? = "",
    var title: String = "N/A",
    var description: String = "N/A",
    var profilepic: String = "",
    var email: String? = "joe@bloggs.com")
    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "title" to title,
            "description" to description,
            "profilepic" to profilepic,
            "email" to email
        )
    }
}