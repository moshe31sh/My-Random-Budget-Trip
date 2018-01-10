package moshe.com.my_random_budget_trip.contract_impl

import com.google.firebase.auth.FirebaseAuth
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.contracts.IRegisterPresenter
import moshe.com.my_random_budget_trip.model.User
import moshe.com.my_random_budget_trip.utils.ValidationUtils
import moshe.com.my_random_budget_trip.view.RegisterActivity

/**
 * Created by moshe on 10/01/2018.
 */
class RegisterPresenterImpl(private val mLoginView: ILoginView): IRegisterPresenter {

    private val mCtx = mLoginView as RegisterActivity
    private val mAuth = FirebaseAuth.getInstance()

    override fun callRegister(user: User) {
        when {
            ValidationUtils.checkIfUserEmailValid(user.email) -> {
                mLoginView.showUserNameError()
            }
            ValidationUtils.checkIfUserPasswordValid(user.password) -> {
                mLoginView.showPasswordError()
            }
            else -> {
            mAuth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener(mCtx, { task ->
                if (task.isSuccessful) {
                    mLoginView.onSuccess()
                }else{
                    TODO("handle failed register")
                }
            })
            }
        }
    }
}