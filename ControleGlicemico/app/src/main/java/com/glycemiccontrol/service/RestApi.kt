package com.glycemiccontrol.service

import com.glycemiccontrol.app.Constants
import com.glycemiccontrol.models.Doctor
import com.glycemiccontrol.models.GlycemicTest
import com.glycemiccontrol.models.Patient
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

interface RestApi {

    @GET(Constants.MEDICOS)
    fun getAllDoctors(): Observable<List<Doctor>>

    @GET(Constants.PACIENTES)
    fun getAllPacients(): Observable<List<Patient>>

    @POST(Constants.ADD_TEST)
    fun putExame(@Body request: GlycemicTest): Observable<Void>
}