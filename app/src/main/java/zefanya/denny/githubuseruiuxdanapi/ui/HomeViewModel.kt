package zefanya.denny.githubuseruiuxdanapi.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zefanya.denny.githubuseruiuxdanapi.data.Repository
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.network.ApiConfig
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ItemsItem
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ListUserResponse

class HomeViewModel(private val repository: Repository): ViewModel() {

    fun getListUserName(keyword: String) = repository.getListUserName(keyword)

}
