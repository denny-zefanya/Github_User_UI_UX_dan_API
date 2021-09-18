package zefanya.denny.githubuseruiuxdanapi.data.source.remote.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getListUserName(@Query("q") keyword: String): Call<ListUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUsername(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollower(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}