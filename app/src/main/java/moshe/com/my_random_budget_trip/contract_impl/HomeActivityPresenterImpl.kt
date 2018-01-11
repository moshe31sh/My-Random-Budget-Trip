package moshe.com.my_random_budget_trip.contract_impl
import moshe.com.my_random_budget_trip.contracts.IHomeActivityPresenter
import moshe.com.my_random_budget_trip.contracts.IHomeActivityView
import moshe.com.my_random_budget_trip.dao.FirebaseDao
import moshe.com.my_random_budget_trip.view.activities.HomeActivity

/**
 * Created by moshe on 10/01/2018.
 */
class HomeActivityPresenterImpl(private val mHomeActivityView: IHomeActivityView): IHomeActivityPresenter {

    private val mCtx = mHomeActivityView as HomeActivity

    override fun doLogOut() {
        FirebaseDao.Instance.logout {
            mCtx.goToLogin()
        }
    }
}