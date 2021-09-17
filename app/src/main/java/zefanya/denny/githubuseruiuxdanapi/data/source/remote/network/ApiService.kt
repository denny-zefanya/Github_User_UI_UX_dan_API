package zefanya.denny.githubuseruiuxdanapi.data.source.remote.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.*

interface ApiService {
    @GET("search/users")
    fun getListUserName(@Query("q") keyword: String): Call<ListUserResponse>

    @GET("users/{username}")
    fun getDetailUsername(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollower(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}