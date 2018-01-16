package moshe.com.my_random_budget_trip.dao.restApi

import android.content.Context
import moshe.com.my_random_budget_trip.BuildConfig
import moshe.com.my_random_budget_trip.dao.FirebaseDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by moshe on 14/01/2018.
 */
object RestDao {
    private  var instance: IDao
    private var mOkHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    private  var mRetrofit : Retrofit

    init {
        mOkHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE})
        mRetrofit = Retrofit.Builder()
                .baseUrl("need to add URL")
                .client(mOkHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        instance = mRetrofit.create(IDao::class.java)
    }


    fun geInstance(url: String) : IDao {

        return instance
    }

    fun getRetrofit() : Retrofit = mRetrofit




}