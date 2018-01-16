package moshe.com.my_random_budget_trip.model

import android.os.Parcel
import android.os.Parcelable
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

/**
 * Created by moshe on 11/01/2018.
 */
class Country : Parcelable, ExpandableGroup<City>{

     var name: String
        private set

     var cities: ArrayList<City>

    constructor(name: String, cities: ArrayList<City>): super(name, cities){
        this.name = name
        this.cities = cities

    }

    constructor(): super("", ArrayList<City>()){
        this.name = ""
        this.cities = ArrayList()
    }

    constructor(source: Parcel) : this(
            source.readString(),
            ArrayList<City>().apply { source.readList(this, City::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeList(cities)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Country> = object : Parcelable.Creator<Country> {
            override fun createFromParcel(source: Parcel): Country = Country(source)
            override fun newArray(size: Int): Array<Country?> = arrayOfNulls(size)
        }
    }
}