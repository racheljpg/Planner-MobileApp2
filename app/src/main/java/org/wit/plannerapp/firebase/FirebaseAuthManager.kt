package org.wit.plannerapp.firebase

import android.app.Application
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import org.wit.plannerapp.R
import timber.log.Timber


class FirebaseAuthManager(application: Application) {

    private var application: Application? = null

    var firebaseAuth: FirebaseAuth? = null
    var liveFirebaseUser = MutableLiveData<FirebaseUser>()
    var loggedOut = MutableLiveData<Boolean>()
    var errorStatus = MutableLiveData<Boolean>()

    var googleSignInClient = MutableLiveData<GoogleSignInClient>()

    init {
        this.application = application
        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth!!.currentUser != null) {
            liveFirebaseUser.postValue(firebaseAuth!!.currentUser)
            loggedOut.postValue(false)
            errorStatus.postValue(false)
            /*FirebaseImageManager.
            checkStorageForExistingProfilePic(
                firebaseAuth!!.currentUser!!.uid)*/
        }
        //configureGoogleSignIn()

    }

    fun login(email: String?, password: String?) {
        firebaseAuth!!.signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(application!!.mainExecutor) { task ->
                if (task.isSuccessful) {
                    liveFirebaseUser.postValue(firebaseAuth!!.currentUser)
                    errorStatus.postValue(false)
                } else {
                    Timber.i("Login Failure: $task.exception!!.message")
                    errorStatus.postValue(true)
                }
            }
    }

    fun register(email: String?, password: String?) {
        firebaseAuth!!.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(application!!.mainExecutor) { task ->
                if (task.isSuccessful) {
                    liveFirebaseUser.postValue(firebaseAuth!!.currentUser)
                    errorStatus.postValue(false)
                } else {
                    Timber.i("Registration Failure: $task.exception!!.message")
                    errorStatus.postValue(true)
                }
            }
    }

//    private fun configureGoogleSignIn() {
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(application!!.getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        googleSignInClient.value = GoogleSignIn.getClient(application!!.applicationContext,gso)
//    }

    fun logOut() {

        firebaseAuth!!.signOut()
        Timber.i( "DonationX : firebaseAuth Signed out")
        //googleSignInClient.value!!.signOut()
        Timber.i( "DonationX : googleSignInClient Signed out")
        //FirebaseImageManager.imageUri = null!!
        loggedOut.postValue(true)
        errorStatus.postValue(false)
    }

}