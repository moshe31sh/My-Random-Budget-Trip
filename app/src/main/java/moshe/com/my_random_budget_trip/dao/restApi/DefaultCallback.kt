package moshe.com.my_random_budget_trip.dao.restApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by moshe on 14/01/2018.
 */
class DefaultCallback<T>(private val start: (T?) -> Unit, private val success: (T?) -> Unit, private val failure: (defaultError: DefaultError?) -> Unit) : Callback<T> {


    private fun onStart(callback: T?) = start(callback)

    private fun onSuccess(success: T?) = success(success)

    private fun onFailure(defaultError: DefaultError?) = failure(defaultError)

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body())
        } else {
            if (response.errorBody() != null) {
                val a = object : Annotation {}
                val converter = RestDao.getRetrofit().responseBodyConverter<DefaultError>(DefaultError::class.java, arrayOf(a))
                val error: DefaultError = converter.convert(response.errorBody())
                onFailure(error)
            }

        }
    }

    override fun onFailure(call: Call<T>?, failure: Throwable?) = onFailure(DefaultError())

    init {
        onStart(null)
    }
}