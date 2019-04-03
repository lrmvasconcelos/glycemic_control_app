package com.glycemiccontrol.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.glycemiccontrol.service.RetrofitBase
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import androidx.lifecycle.MutableLiveData
import com.glycemiccontrol.models.Pacients


class UserTypeViewModel : ViewModel() {


    private val _usersMutableLiveData = MutableLiveData<List<Pacients>>()

    private val _errorMutableLiveData = MutableLiveData<Throwable>()

    private val _progressMutableLiveData = MutableLiveData<Boolean>()

    val userLiveData: LiveData<List<Pacients>>
        get() = _usersMutableLiveData

    val errorLiveData: LiveData<Throwable>
        get() = _errorMutableLiveData

    val progress: LiveData<Boolean> get() = _progressMutableLiveData


    fun getAllPacients() {

        RetrofitBase
            .getInterfaceRetrofit()!!
            .getAllPacients()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _usersMutableLiveData.value = response
            }, { error ->
                _errorMutableLiveData.value = error
            })
    }

}
