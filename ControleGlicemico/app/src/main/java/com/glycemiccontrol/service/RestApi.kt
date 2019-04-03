package com.glycemiccontrol.service

import com.glycemiccontrol.app.Constants
import com.glycemiccontrol.models.Doctor
import retrofit2.http.GET
import rx.Observable

interface RestApi {

    @GET(Constants.MEDICOS)
    fun getAllDoctors(): Observable<List<Doctor>>
}