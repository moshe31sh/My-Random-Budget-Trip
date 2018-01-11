package moshe.com.my_random_budget_trip.dao

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import moshe.com.my_random_budget_trip.model.User

/**
 * Created by moshe on 11/01/2018.
 */
class FirebaseDao private constructor(){

    private val mAuth = FirebaseAuth.getInstance()

    private object Holder{
        val INSTANCE = FirebaseDao()
    }

    companion object {
        val Instance : FirebaseDao by lazy {
            Holder.INSTANCE
        }
    }

    fun login(activity: Activity, user: User, success: (Boolean) -> Unit ) {
        mAuth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(activity, { task->
            success(task.isSuccessful)
        })
    }


    fun loginWithGoogle(activity: Activity, acct: GoogleSignInAccount, success: (Boolean) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential).addOnCompleteListener(activity, {task ->
            success(task.isSuccessful)
        })
    }

    fun logout(success:() -> Unit ){
        mAuth.signOut()
        success()

    }

}