package moshe.com.my_random_budget_trip.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by moshe on 09/01/2018.
 */
class User() : Parcelable {

    var email: String = ""
        private set
    var password: String =" "
        private set



    constructor(email: String, password: String) : this() {
        this.email = email
        this.password = password
    }


    constructor(parcel: Parcel) : this() {
        email = parcel.readString()
        password = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}