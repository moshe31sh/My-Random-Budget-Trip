package moshe.com.my_random_budget_trip.view.activities

import android.os.Bundle
import moshe.com.my_random_budget_trip.R

class PickLocationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContentView(): Int = R.layout.activity_pick_loaction


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }
}
