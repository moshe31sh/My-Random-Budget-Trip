package moshe.com.my_random_budget_trip.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import moshe.com.my_random_budget_trip.R

/**
 * Created by moshe on 09/01/2018.
 */

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
    }

    open fun getContentView() = 0


    protected fun showLoadingDialog(){

        TODO("build loading dialog")
    }


    protected fun dismissLoadingDialog(){
        TODO("build loading dialog")

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(!isTaskRoot) {
            overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right)
        }
    }
}