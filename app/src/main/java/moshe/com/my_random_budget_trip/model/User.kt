package moshe.com.my_random_budget_trip.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by moshe on 09/01/2018.
 */
class User()  {

    var email: String = ""
        private set
    var password: String =" "
        private set

    lateinit var countries: ArrayList<City>


    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }

    constructor(email: String, password: String , countries: ArrayList<City> ) : this(email,password) {
        this.countries = countries
    }



}