package moshe.com.my_random_budget_trip.utils

/**
 * Created by moshe on 09/01/2018.
 */

object ValidationUtils {

    fun checkIfUserEmailValid(email: String): Boolean = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun checkIfUserPasswordValid(password: String): Boolean = password.length != PASSWORD_LENGTH
}