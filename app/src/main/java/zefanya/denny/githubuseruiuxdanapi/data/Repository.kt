package zefanya.denny.githubuseruiuxdanapi.data

import zefanya.denny.githubuseruiuxdanapi.data.source.local.LocalDataSource
import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.RemoteDataSource

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    companion object {
        private var INSTANCE: Repository? = null

        fun getInstance(
            localDataSource: LocalDataSource,
            remoteDataSource: RemoteDataSource
        ): Repository = INSTANCE ?: synchronized(this) {
            Repository(localDataSource, remoteDataSource).apply {
                this
            }
        }
    }

    fun getListUserName(keyword: String) = remoteDataSource.getListUser(keyword)

    fun getDetailUserName(username: String) = remoteDataSource.getDetailUser(username)

    fun getFollowerUser(username: String) = remoteDataSource.getFollowerUser(username)

    fun getFollowingUser(username: String) = remoteDataSource.getFollowingUser(username)

    suspend fun insertUserFavourite(user: FavouriteUserEntity) = localDataSource.insertUserFavourite(user)

    suspend fun deleteUserFavourite(user: FavouriteUserEntity) = localDataSource.deleteUserFavourite(user)

    fun getUserFavourite() = localDataSource.getUserFavorite()

    fun cekUserFavourite(username: String) = localDataSource.cekUserFavourite(username)

}