package com.burakusluer.kotlinmarvel.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.burakusluer.kotlinmarvel.model.ModelMarvel

class ViewModelHero(application: Application) : BaseViewModel(application) {
    val hero: MutableLiveData<ModelMarvel> = MutableLiveData<ModelMarvel>()
    override fun onCleared() {
        super.onCleared()
    }
}