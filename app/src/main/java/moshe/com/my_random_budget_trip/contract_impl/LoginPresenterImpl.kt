package moshe.com.my_random_budget_trip.contract_impl

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import moshe.com.my_random_budget_trip.contracts.ILoginPresenter
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.model.User
import moshe.com.my_random_budget_trip.utils.ValidationUtils
import moshe.com.my_random_budget_trip.view.LoginActivity

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
            else ->
                mAuth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(mCtx, { task ->
                    if (task.isSuccessful) {

                    }else{

                    }
                })
        }
    }
}