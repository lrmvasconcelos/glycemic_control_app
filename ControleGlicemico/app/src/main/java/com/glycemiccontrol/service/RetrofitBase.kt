package com.glycemiccontrol.service

import com.glycemiccontrol.BuildConfig
import com.glycemiccontrol.app.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

class RetrofitBase {


    companion object {

        init {
            initRetrofit()
        }

        private lateinit var interfaceRetrofit: RestApi
        private lateinit var retrofit: Retrofit

        fun getInterfaceRetrofit(): RestApi? {
            return interfaceRetrofit
        }

        private fun initRetrofit() {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(1, TimeUnit.HOURS)
            builder.connectTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(logInterceptor)
            }

            val gsonBuilder = GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)

            //        gsonBuilder.registerTypeAdapter(TypePayment.class, new TypePaymentDeserializer());
            val gson = gsonBuilder.create()
            val gsonConverterFactory = GsonConverterFactory.create(gson)

            //        addAuthorizationHeaders(builder);

            val urlBase = Constants.BASE_URL

            val httpClient = builder.build()

            retrofit = Retrofit.Builder()
                .baseUrl(urlBase)
                .client(httpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()

            interfaceRetrofit = retrofit.create(RestApi::class.java)
        }
    }
}