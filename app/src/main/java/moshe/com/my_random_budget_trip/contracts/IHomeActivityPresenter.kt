package moshe.com.my_random_budget_trip.contracts

import moshe.com.my_random_budget_trip.model.Country

/**
 * Created by moshe on 10/01/2018.
 */
interface IHomeActivityPresenter {
    fun doLogOut()
    fun arrangeLocationListByCountries(country: Country, locationList: ArrayList<Country>)
}