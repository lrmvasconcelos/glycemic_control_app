package com.glycemiccontrol.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.glycemiccontrol.service.RetrofitBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import androidx.lifecycle.MutableLiveData
import com.glycemiccontrol.models.Doctor


class LoginViewModel : ViewModel() {


    private val _usersMutableLiveData = MutableLiveData<List<Doctor>>()

    private val _errorMutableLiveData = MutableLiveData<Throwable>()

    private val _progressMutableLiveData = MutableLiveData<Boolean>()

    val userLiveData: LiveData<List<Doctor>>
        get() = _usersMutableLiveData

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
                _usersMutableLiveData.value = response
            }, { error ->
                _errorMutableLiveData.value = error
            })
    }

}
