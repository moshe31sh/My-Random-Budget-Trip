package moshe.com.my_random_budget_trip.contract_impl

import com.google.firebase.auth.FirebaseAuth
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.contracts.IRegisterPresenter
import moshe.com.my_random_budget_trip.model.User
import moshe.com.my_random_budget_trip.utils.ValidationUtils
import moshe.com.my_random_budget_trip.view.activities.RegisterActivity

/**
 * Created by moshe on 10/01/2018.
 */
class RegisterPresenterImpl(private val mLoginView: ILoginView): IRegisterPresenter {

    private val mCtx = mLoginView as RegisterActivity
    private val mAuth = FirebaseAuth.getInstance()

    override fun callRegister(user: User) {
        mLoginView.showLoading()
        when {
            ValidationUtils.checkIfUserEmailValid(user.email) -> {
                mLoginView.showUserNameError()
                mLoginView.hideLoading()
            }
            ValidationUtils.checkIfUserPasswordValid(user.password) -> {
                mLoginView.showPasswordError()
                mLoginView.hideLoading()
            }
            else -> {
                mAuth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener(mCtx, { task ->
                    mLoginView.hideLoading()
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