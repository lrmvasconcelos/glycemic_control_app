package com.glycemiccontrol.service

import com.glycemiccontrol.app.Constants
import com.glycemiccontrol.models.Pacients
import retrofit2.http.GET
import rx.Observable

interface RestApi {

    @GET(Constants.PACIENTES)
    fun getAllPacients(): Observable<List<Pacients>>
}