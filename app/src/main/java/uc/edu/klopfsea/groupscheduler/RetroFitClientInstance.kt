package uc.edu.klopfsea.groupscheduler

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClientInstance {

    private var retrofit: Retrofit? = null
    private var BASE_URL = "https://api.zippopotam.us"

    val retrofitInstace: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
}