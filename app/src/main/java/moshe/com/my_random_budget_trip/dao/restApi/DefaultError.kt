package moshe.com.my_random_budget_trip.dao.restApi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by moshe on 14/01/2018.
 */

class DefaultError() {

    @SerializedName("message")
    @Expose
    var message: String = ""
        private set

    @SerializedName("error")
    @Expose
    var error: Error? = null
        private set

    constructor(message: String, error: Error) : this() {
        this.message = message
        this.error = error
    }
}