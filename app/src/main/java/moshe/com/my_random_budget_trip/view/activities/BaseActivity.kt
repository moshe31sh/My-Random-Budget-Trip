package moshe.com.my_random_budget_trip.view.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.view.dialogs.LoadingDialog

/**
 * Created by moshe on 09/01/2018.
 */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private lateinit var mLoadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        mLoadingDialog = LoadingDialog(this)

    }

    open fun getContentView() = 0


    protected fun showLoadingDialog(){
        if(!mLoadingDialog.isShowing) {
            mLoadingDialog.show()
        }

    }


    protected fun dismissLoadingDialog(){
        if(mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(!isTaskRoot) {
            overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right)
        }
    }
}