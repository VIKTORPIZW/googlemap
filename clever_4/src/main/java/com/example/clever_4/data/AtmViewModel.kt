package com.example.clever_4.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class AtmViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable= CompositeDisposable()
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchAtmList(atmApi: AtmApi?){
        atmApi?.let {
            compositeDisposable.add(atmApi.getATMList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{

                }))


        }
        
    }
}