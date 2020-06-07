package com.burakusluer.kotlinmarvel.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.burakusluer.kotlinmarvel.model.ModelMarvel

class ViewModelFeed(application: Application) :BaseViewModel(application) {
    val heroCollection:MutableLiveData<List<ModelMarvel>> =MutableLiveData<List<ModelMarvel>>()
    
    override fun onCleared() {
        super.onCleared()
    }
}