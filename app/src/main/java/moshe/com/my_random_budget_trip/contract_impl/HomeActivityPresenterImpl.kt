package moshe.com.my_random_budget_trip.contract_impl

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import moshe.com.my_random_budget_trip.contracts.IHomeActivityPresenter
import moshe.com.my_random_budget_trip.contracts.IHomeActivityView
import moshe.com.my_random_budget_trip.view.activities.HomeActivity

/**
 * Created by moshe on 10/01/2018.
 */
class HomeActivityPresenterImpl(private val mHomeActivityView: IHomeActivityView): IHomeActivityPresenter {

    private val mCtx = mHomeActivityView as HomeActivity
    private val mAuth = FirebaseAuth.getInstance()

    override fun doLogOut() {
        mAuth.signOut()
        mCtx.goToLogin()
    }
}