package moshe.com.my_random_budget_trip.contracts

import moshe.com.my_random_budget_trip.model.User

/**
 * Created by moshe on 09/01/2018.
 */
interface ILoginPresenter {
    fun callLogin(user: User)
}