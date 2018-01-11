package moshe.com.my_random_budget_trip.contract_impl

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import moshe.com.my_random_budget_trip.contracts.ILoginPresenter
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.dao.FirebaseDao
import moshe.com.my_random_budget_trip.model.User
import moshe.com.my_random_budget_trip.utils.ValidationUtils
import moshe.com.my_random_budget_trip.view.activities.LoginActivity

/**
 * Created by moshe on 09/01/2018.
 */
class LoginPresenterImpl (private val mLoginView: ILoginView) : ILoginPresenter {
    private val mCtx = mLoginView as LoginActivity

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
                FirebaseDao.Instance.login(mCtx, user ){
                    success ->
                    if (success) {
                        mLoginView.onSuccess()
                    } else {
                        TODO("handle failed register")

                    }
                    mLoginView.hideLoading()
                }
            }
        }
    }

    override fun loginWithGoogle(acct: GoogleSignInAccount) {
        mLoginView.showLoading()

        FirebaseDao.Instance.loginWithGoogle(mCtx, acct){
            success ->
            if (success) {
                mLoginView.onSuccess()
            } else {
                TODO("handle failed register")

            }
            mLoginView.hideLoading()
        }
    }
}