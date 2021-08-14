package com.sungshin.recyclear.utils

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseUtil {
    val database = Firebase.database("https://recyclear-user-c81c3-default-rtdb.asia-southeast1.firebasedatabase.app/")
}