package moshe.com.my_random_budget_trip.contracts

/**
 * Created by moshe on 09/01/2018.
 */
interface ILoginView : IBaseViewAction {
    fun showUserNameError()
    fun showPasswordError()
    fun onSuccess()
}