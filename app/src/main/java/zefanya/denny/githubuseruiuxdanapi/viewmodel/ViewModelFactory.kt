package zefanya.denny.githubuseruiuxdanapi.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zefanya.denny.githubuseruiuxdanapi.data.Repository
import zefanya.denny.githubuseruiuxdanapi.di.Injection
import zefanya.denny.githubuseruiuxdanapi.ui.detailuser.DetailUserViewModel
import zefanya.denny.githubuseruiuxdanapi.ui.favourite.FavouriteViewModel
import zefanya.denny.githubuseruiuxdanapi.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = INSTANCE ?: synchronized(this){
            ViewModelFactory(Injection.provideRepository(context))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(repository) as T
            }
           modelClass.isAssignableFrom(DetailUserViewModel::class.java)-> {
               DetailUserViewModel(repository) as T
           }
           modelClass.isAssignableFrom(FavouriteViewModel::class.java)-> {
               FavouriteViewModel(repository) as T
           }
           else -> throw Throwable("Unknown viewmodel class: ${modelClass.name}")
       }
    }
}