package moshe.com.my_random_budget_trip.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by moshe on 11/01/2018.
 */
class City(name: String) : Parcelable {
    var name = name
        private set

    constructor(source: Parcel) : this(
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<City> = object : Parcelable.Creator<City> {
            override fun createFromParcel(source: Parcel): City = City(source)
            override fun newArray(size: Int): Array<City?> = arrayOfNulls(size)
        }
    }
}