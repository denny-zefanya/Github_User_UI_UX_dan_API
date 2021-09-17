package zefanya.denny.githubuseruiuxdanapi.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.network.ApiConfig
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ApiResponse
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.DetailUserResponse
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ItemsItem
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ListUserResponse

class RemoteDataSource private constructor() {
    companion object {
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = INSTANCE ?: synchronized(this) {
            RemoteDataSource().apply {
                INSTANCE = this
            }
        }
    }

    fun getListUser(keyword: String): LiveData<ApiResponse<List<ItemsItem>>> {
        val resultList = MutableLiveData<ApiResponse<List<ItemsItem>>>()
        resultList.value = ApiResponse.loading("", null)
        ApiConfig.provideApiService().getListUserName(keyword).enqueue(object :
            Callback<ListUserResponse> {
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                if (response.isSuccessful) {
                    val dataArray = response.body()!!.items
                    resultList.value = ApiResponse.success(dataArray)

                }
            }

            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                resultList.value = ApiResponse.error(t.message.toString(), ArrayList())
            }

        })

        return resultList
    }

    fun getDetailUser(username: String): LiveData<ApiResponse<DetailUserResponse?>>{
        var result = MutableLiveData<ApiResponse<DetailUserResponse?>>()

        ApiConfig.provideApiService().getDetailUsername(username).enqueue(object: Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    result.value = ApiResponse.success(data)
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                result.value = ApiResponse.error("Error", null)
            }

        })

        return result
    }

    fun getFollowerUser(username: String): LiveData<ApiResponse<List<ItemsItem>?>>{
        var resultList = MutableLiveData<ApiResponse<List<ItemsItem>?>>()
        resultList.value = ApiResponse.loading("kosong", null)
        ApiConfig.provideApiService().getFollower(username).enqueue(object: Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if(response.isSuccessful){
                    val dataArray = response.body()
                    if (dataArray!!.isNotEmpty())
                        resultList.value = ApiResponse.success(dataArray)
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                resultList.value = ApiResponse.error(t.message.toString(),null)
            }

        })
        return resultList
    }

    fun getFollowingUser(username: String): LiveData<ApiResponse<List<ItemsItem>?>>{
        var resultList = MutableLiveData<ApiResponse<List<ItemsItem>?>>()
        resultList.value = ApiResponse.loading("kosong", null)
        ApiConfig.provideApiService().getFollowing(username).enqueue(object: Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if(response.isSuccessful){
                    val dataArray = response.body()
                    if (dataArray!!.isNotEmpty())
                        resultList.value = ApiResponse.success(dataArray)

                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                resultList.value = ApiResponse.error(t.message.toString(),null)
            }

        })
        return resultList
    }
}