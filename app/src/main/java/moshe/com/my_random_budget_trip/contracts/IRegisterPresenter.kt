package moshe.com.my_random_budget_trip.contracts

import moshe.com.my_random_budget_trip.model.User

/**
 * Created by moshe on 10/01/2018.
 */
interface IRegisterPresenter {
    fun callRegister(user: User)

}