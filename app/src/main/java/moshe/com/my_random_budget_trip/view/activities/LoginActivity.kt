package moshe.com.my_random_budget_trip.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.android.synthetic.main.activity_login.*
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.contract_impl.LoginPresenterImpl
import moshe.com.my_random_budget_trip.contracts.ILoginPresenter
import moshe.com.my_random_budget_trip.contracts.ILoginView
import moshe.com.my_random_budget_trip.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


class LoginActivity : BaseActivity(), ILoginView {

    private lateinit var mLoginPresenterImpl: ILoginPresenter
    private val RC_SIGN_IN  = 9001

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
            gotoRegister()
        }

        loginLoginGoogleBtn.setOnClickListener {
            googleLogin()
        }

    }

    override fun getContentView(): Int = R.layout.activity_login

    override fun hideLoading() {
        dismissLoadingDialog()
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun showUserNameError() {
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

    private fun gotoRegister(){
        val intent = Intent(this, RegisterActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent,ActivityOptionsCompat.makeCustomAnimation(this, R.anim.anim_slide_in_left,  R.anim.anim_slide_out_left).toBundle())
    }

    private fun googleLogin(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                mLoginPresenterImpl.loginWithGoogle(account)
            } catch (e: ApiException) {

            }

        }
    }
}