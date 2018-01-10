package moshe.com.my_random_budget_trip.contracts

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import moshe.com.my_random_budget_trip.model.User

/**
 * Created by moshe on 09/01/2018.
 */
interface ILoginPresenter {
    fun callLogin(user: User)
    fun loginWithGoogle(acct: GoogleSignInAccount)
}