package com.burakusluer.kotlinmarvel.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.burakusluer.kotlinmarvel.model.ModelMarvel
import com.burakusluer.kotlinmarvel.service.DataBaseMarvel
import kotlinx.coroutines.launch

class ViewModelHero(application: Application) : BaseViewModel(application) {
    val hero: MutableLiveData<ModelMarvel> = MutableLiveData<ModelMarvel>()
    val dataBase = DataBaseMarvel.invoke(application).dao()

    fun getHero(uuid: Int) {
        launch {
            hero.value = dataBase.getSingleUUID(uuid)
        }
    }

}