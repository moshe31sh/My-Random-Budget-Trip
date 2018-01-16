package moshe.com.my_random_budget_trip.adapters

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.model.City
import moshe.com.my_random_budget_trip.model.Country
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView


/**
 * Created by moshe on 16/01/2018.
 */
class CountryAdapter(groups: List<Country> ,private val mContext: Context) : ExpandableRecyclerViewAdapter<CountryAdapter.CountryViewHolder, CountryAdapter.CityViewHolder>(groups) {

    override fun onBindGroupViewHolder(holder: CountryViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        holder!!.setCountryName(group)
    }


    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): CountryViewHolder =
            CountryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.country_row, parent, false))

    override fun onBindChildViewHolder(holder: CityViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        val city = (group as Country).items[childIndex]
        holder!!.onBind(city)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): CityViewHolder =
            CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.city_row, parent, false))




    class CountryViewHolder(itemView: View?) : GroupViewHolder(itemView) {

        private var countryName : TextView = itemView!!.findViewById(R.id.countryRowTitle)
        private var arrow: ImageView = itemView!!.findViewById(R.id.countryRowArrow)

        fun setCountryName(country: ExpandableGroup<*>?){
            countryName.text = country!!.title
        }


        override fun expand() {
            animateExpand()
        }

        override fun collapse() {
            animateCollapse()
        }

        private fun animateExpand() {
            val rotate = RotateAnimation(360f, 180f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            arrow.animation = rotate
        }

        private fun animateCollapse() {
            val rotate = RotateAnimation(180f, 360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            arrow.animation = rotate
        }

    }

    class CityViewHolder(itemView: View?) : ChildViewHolder(itemView) {

        private val cityName: TextView = itemView!!.findViewById(R.id.cityRowName)

        fun onBind(city: City){
            cityName.text = city.name
        }

    }
}