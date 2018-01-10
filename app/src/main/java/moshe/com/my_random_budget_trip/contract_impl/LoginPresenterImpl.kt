package moshe.com.my_random_budget_trip.contract_impl

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import moshe.com.my_random_budget_trip.contracts.ILoginPresenter
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.model.User
import moshe.com.my_random_budget_trip.utils.ValidationUtils
import moshe.com.my_random_budget_trip.view.activities.LoginActivity

/**
 * Created by moshe on 09/01/2018.
 */
class LoginPresenterImpl (private val mLoginView: ILoginView) : ILoginPresenter {
    private val mCtx = mLoginView as LoginActivity
    private val mAuth = FirebaseAuth.getInstance()

    override fun callLogin(user: User) {
        when {
            ValidationUtils.checkIfUserEmailValid(user.email) -> {
                mLoginView.showUserNameError()
            }
            ValidationUtils.checkIfUserPasswordValid(user.password) -> {
                mLoginView.showPasswordError()
            }
            else -> {
                mLoginView.showLoading()
                mAuth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(mCtx, { task ->
                    if (task.isSuccessful) {
                        mLoginView.onSuccess()
                    } else {

                    }
                    mLoginView.hideLoading()
                })
            }
        }
    }

    override fun loginWithGoogle(acct: GoogleSignInAccount) {
        mLoginView.showLoading()
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential).addOnCompleteListener(mCtx, {task ->
            if (task.isSuccessful) {
                mLoginView.onSuccess()
            }else{

            }
            mLoginView.hideLoading()
        })
    }
}