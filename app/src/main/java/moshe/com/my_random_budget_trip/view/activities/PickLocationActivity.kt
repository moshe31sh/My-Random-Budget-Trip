package moshe.com.my_random_budget_trip.view.activities


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.gms.common.api.Status
import moshe.com.my_random_budget_trip.R
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.Place
import kotlinx.android.synthetic.main.activity_pick_loaction.*
import moshe.com.my_random_budget_trip.model.City
import moshe.com.my_random_budget_trip.model.Country
import moshe.com.my_random_budget_trip.utils.RESULT_CODE_NEW_LOCATION


class PickLocationActivity : BaseActivity() {

    private lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    companion object {

        val EXTRA_COUNTRY_DATA = "country"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, PickLocationActivity::class.java)
            return intent
        }
    }

    override fun getContentView(): Int = R.layout.activity_pick_loaction

    private fun init() {

        val autocompleteFragment = fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place?) {
                if (place != null) {
                    val city = ArrayList<City>()
                    city.add(City(place.name.toString()))
                    country = Country(place.address.toString(), city)
                }
            }

            override fun onError(status: Status?) {
            }
        })

        ActivityPickLocationCloseBtn.setOnClickListener {
            val intent = Intent()
            if (country.cities.size > 0) {
                intent.putExtra(EXTRA_COUNTRY_DATA, country)
                if (country.cities.size > 0) {
                    setResult(RESULT_CODE_NEW_LOCATION, intent)
                } else {
                    setResult(Activity.RESULT_CANCELED, intent)
                }
                finish()
            }
        }
    }

        override fun onBackPressed() {
            super.onBackPressed()
            overridePendingTransition(0, 0)
        }
    }
