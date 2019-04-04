package com.glycemiccontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glycemiccontrol.models.Patient
import com.glycemiccontrol.service.RetrofitBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PatientViewModel : ViewModel() {

    private val _patientsMutableLiveData = MutableLiveData<List<Patient>>()

    private val _progressMutableLiveData = MutableLiveData<Boolean>()

    private val _errorMutableLiveData = MutableLiveData<Throwable>()

    val errorLiveData: LiveData<Throwable>
        get() = _errorMutableLiveData

    val patientLiveData: LiveData<List<Patient>>
        get() = _patientsMutableLiveData

    val progress: LiveData<Boolean> get() = _progressMutableLiveData

    fun getAllPacients(){
        RetrofitBase
            .getInterfaceRetrofit()!!
            .getAllPacients()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _patientsMutableLiveData.value = response
            }, { error ->
                _errorMutableLiveData.value = error
            })
    }

}