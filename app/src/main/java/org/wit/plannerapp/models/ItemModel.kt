package org.wit.plannerapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel (var id: Long = 0,
                        var title: String = "",
                        var description: String = ""
) : Parcelable