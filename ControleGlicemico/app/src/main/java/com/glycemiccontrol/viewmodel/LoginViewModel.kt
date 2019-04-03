package com.glycemiccontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.glycemiccontrol.service.RetrofitBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import androidx.lifecycle.MutableLiveData
import com.glycemiccontrol.models.Doctor
import com.glycemiccontrol.models.Pacient


class LoginViewModel : ViewModel() {


    private val _doctorsMutableLiveData = MutableLiveData<List<Doctor>>()

    private val _pacientsMutableLiveData = MutableLiveData<List<Pacient>>()

    private val _errorMutableLiveData = MutableLiveData<Throwable>()

    private val _progressMutableLiveData = MutableLiveData<Boolean>()

    val doctorsLiveData: LiveData<List<Doctor>>
        get() = _doctorsMutableLiveData

    val pacientLiveData: LiveData<List<Pacient>>
        get() = _pacientsMutableLiveData

    val errorLiveData: LiveData<Throwable>
        get() = _errorMutableLiveData

    val progress: LiveData<Boolean> get() = _progressMutableLiveData


    fun getAllDoctors() {

        RetrofitBase
            .getInterfaceRetrofit()!!
            .getAllDoctors()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _doctorsMutableLiveData.value = response
            }, { error ->
                _errorMutableLiveData.value = error
            })
    }

    fun getAllPacients(){
        RetrofitBase
            .getInterfaceRetrofit()!!
            .getAllPacients()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _pacientsMutableLiveData.value = response
            }, { error ->
                _errorMutableLiveData.value = error
            })
    }

}
