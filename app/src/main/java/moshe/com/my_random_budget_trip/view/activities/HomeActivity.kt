package moshe.com.my_random_budget_trip.view.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.adapters.CountryAdapter
import moshe.com.my_random_budget_trip.contract_impl.HomeActivityPresenterImpl
import moshe.com.my_random_budget_trip.contracts.IHomeActivityPresenter
import moshe.com.my_random_budget_trip.contracts.IHomeActivityView
import moshe.com.my_random_budget_trip.model.City
import moshe.com.my_random_budget_trip.model.Country
import moshe.com.my_random_budget_trip.utils.RESULT_CODE_NEW_LOCATION

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, IHomeActivityView{

    private lateinit var mHomeActivityPresenter : IHomeActivityPresenter
    private lateinit var mLocation: ArrayList<Country>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        init()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }

    override fun getContentView(): Int = R.layout.activity_home

    private fun init(){
        mLocation = ArrayList()
        mHomeActivityPresenter = HomeActivityPresenterImpl(this)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        homeActivityAddNewCountryFab.setOnClickListener {
            gotoPickLocationActivity()
        }

        val adapter = CountryAdapter(mLocation, this)
        activityHomeRecycleView.layoutManager = LinearLayoutManager(this)
        activityHomeRecycleView.adapter = adapter

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_logout -> {
                mHomeActivityPresenter.doLogOut()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToLogin() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent, ActivityOptionsCompat.makeCustomAnimation(this, 0, 0).toBundle())
        finish()
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser == null) {
            goToLogin()
        }
    }

    private fun gotoPickLocationActivity(){
        intent =  PickLocationActivity.newIntent(this)
        startActivityForResult(intent, RESULT_CODE_NEW_LOCATION)
        overridePendingTransition(0,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == RESULT_CODE_NEW_LOCATION && resultCode == Activity.RESULT_OK ->{
                var country: Country = data!!.getParcelableExtra(PickLocationActivity.EXTRA_COUNTRY_DATA)
                mHomeActivityPresenter.arrangeLocationListByCountries(country, mLocation)

            }
        }
    }

    override fun updateList(location: ArrayList<Country>, indexToScroll: Int) {
        mLocation = location
        val adapter = CountryAdapter(mLocation, this)
        activityHomeRecycleView.adapter = adapter
        activityHomeRecycleView.scrollToPosition(indexToScroll)
        (activityHomeRecycleView.adapter as CountryAdapter).doExpand(indexToScroll)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        (activityHomeRecycleView.adapter as CountryAdapter).onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        (activityHomeRecycleView.adapter as CountryAdapter).onRestoreInstanceState(savedInstanceState)
    }
}
