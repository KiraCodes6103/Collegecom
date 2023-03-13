package com.example.college_com.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.college_com.data.User
import com.example.college_com.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ViewModel(){
    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.loading())
    val register : Flow<Resource<FirebaseUser>> = _register


    fun createAccountWithEmailAndPassword(user: User, password:String){
        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener {
                it.user?.let {
                    _register.value = Resource.success(it)
                }
            }.addOnFailureListener{
                    _register.value = Resource.failure(it.message.toString())
            }
    }

}