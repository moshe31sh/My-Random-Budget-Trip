package moshe.com.my_random_budget_trip.view

import android.os.Bundle
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.contract_impl.LoginPresenterImpl
import moshe.com.my_random_budget_trip.contracts.ILoginPresenter
import moshe.com.my_random_budget_trip.contracts.ILoginView

class LoginActivity : BaseActivity(), ILoginView {

    private lateinit var mLoginPresenterImpl: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoginPresenterImpl = LoginPresenterImpl(this)
    }

    override fun getContentView(): Int {
        return R.layout.activity_login
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUserNameError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPasswordError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
