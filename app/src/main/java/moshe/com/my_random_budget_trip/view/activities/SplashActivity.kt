package moshe.com.my_random_budget_trip.view.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityOptionsCompat
import com.google.firebase.auth.FirebaseAuth
import moshe.com.my_random_budget_trip.R
import moshe.com.my_random_budget_trip.utils.SPLASH_DISPLAY_LENGTH

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startSplash()
    }

    private fun startSplash(){
        val auth = FirebaseAuth.getInstance().currentUser

        Handler().postDelayed({

            val intent : Intent = if(auth == null) {
             Intent(applicationContext, LoginActivity::class.java)
            }else{
                Intent(applicationContext, HomeActivity::class.java)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent, ActivityOptionsCompat.makeCustomAnimation(this, 0, 0).toBundle())
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}
