package com.burakusluer.kotlinmarvel.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.burakusluer.kotlinmarvel.model.ModelMarvel
import com.burakusluer.kotlinmarvel.service.DataBaseMarvel
import com.burakusluer.kotlinmarvel.service.MarvelAPI
import com.burakusluer.kotlinmarvel.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ViewModelFeed(application: Application) : BaseViewModel(application) {
    val heroCollection: MutableLiveData<List<ModelMarvel>> = MutableLiveData<List<ModelMarvel>>()
    val marvelAPI = MarvelAPI()
    val sharedPreferences = CustomSharedPreferences.invoke(getApplication())
    val dataBaseMarvel = DataBaseMarvel.invoke(getApplication()).dao()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun refresh() {
        if (sharedPreferences != null && sharedPreferences.getLong("lastSuccessUpdate", 0L) == 0L) {
            //first time
            sharedPreferences.edit().putLong("lastSuccessUpdate", System.nanoTime()).apply()
            getDataFromAPI()
        } else if (sharedPreferences != null && (System.nanoTime() - sharedPreferences.getLong(
                "lastSuccessUpdate",
                0L
            )) / 60000000000 > 5
        ) {
            getDataFromAPI()
        } else {
            getFromDataBase()
        }
    }

    fun onSwipe() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        compositeDisposable.add(marvelAPI.api
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                object : DisposableSingleObserver<List<ModelMarvel>>() {
                    override fun onSuccess(t: List<ModelMarvel>) {
                        heroCollection.value = t
                        addDatabase()
                        sharedPreferences.edit().putLong("lastSuccessUpdate", System.nanoTime())
                            .apply()
                        Toast.makeText(getApplication(), "data from API", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Toast.makeText(getApplication(), e.localizedMessage, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            ))
    }

    fun getFromDataBase() {
        launch {
            heroCollection.value = dataBaseMarvel.getAll()
            Toast.makeText(getApplication(), "Data From DataBase", Toast.LENGTH_LONG).show()
        }

    }

    fun addDatabase() {
        launch {
            heroCollection.value?.let {
                dataBaseMarvel.deleteAll()
                val longList = dataBaseMarvel.insertALL(*it.toTypedArray())
                var i = 0
                while (i < longList.size) {
                    it[i].uuId = longList[i].toInt()
                    i++
                }
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}