package moshe.com.my_random_budget_trip.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_login.*
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.contract_impl.LoginPresenterImpl
import moshe.com.my_random_budget_trip.contracts.ILoginPresenter
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.model.User

class LoginActivity : BaseActivity(), ILoginView {

    private lateinit var mLoginPresenterImpl: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoginPresenterImpl = LoginPresenterImpl(this)

        init()
    }

    private fun init(){
        loginLoginBtn.setOnClickListener {
            mLoginPresenterImpl.callLogin(User(loginEmailTxt.text.toString().trim(), loginPasswordText.text.toString().trim()))
        }

        loginCreateUserBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent,ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_slide_in_left,  R.anim.anim_slide_out_left).toBundle())
        }

    }

    override fun getContentView(): Int = R.layout.activity_login


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
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent,ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_slide_in_left,  R.anim.anim_slide_out_left).toBundle())
        finish()
    }
}
