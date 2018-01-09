package moshe.com.my_random_budget_trip

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by moshe on 09/01/2018.
 */

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
    }

    open fun getContentView() = 0

}