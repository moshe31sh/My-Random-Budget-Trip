package moshe.com.my_random_budget_trip.view.activities

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.contract_impl.RegisterPresenterImpl
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.contracts.IRegisterPresenter
import moshe.com.my_random_budget_trip.model.User

class RegisterActivity : BaseActivity(), ILoginView {

    private lateinit var mRegisterPresenter: IRegisterPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun getContentView(): Int = R.layout.activity_register

    private fun init(){
        mRegisterPresenter = RegisterPresenterImpl(this)
        registerCreateUserBtn.setOnClickListener {
            mRegisterPresenter.callRegister(User(registerEmailText.text.toString().trim(), registerPasswordText.text.toString().trim()))

        }
    }

    override fun hideLoading() {
        dismissLoadingDialog()
    }

    override fun showLoading() {
        showLoadingDialog()
    }
    override fun showUserNameError() {
        registerEmailText.error = "error"
    }

    override fun showPasswordError() {
        registerPasswordText.error = "error"
        registerPasswordText.text = null
    }
    override fun onSuccess() {
        finish()
    }
}
