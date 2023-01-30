package edu.uw.yuanruizhang.fetchdisplay.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    val id: Int,
    val listId: Int,
    val name: String,
): Parcelable