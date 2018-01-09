package moshe.com.my_random_budget_trip.contract_impl

import moshe.com.my_random_budget_trip.contracts.ILoginPresenter
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.model.User
import moshe.com.my_random_budget_trip.view.LoginActivity

/**
 * Created by moshe on 09/01/2018.
 */
class LoginPresenterImpl (private val mLoginView: ILoginView) : ILoginPresenter {
    private val mCtx = mLoginView as LoginActivity


    override fun callLogin(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}