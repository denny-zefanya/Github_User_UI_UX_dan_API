package zefanya.denny.githubuseruiuxdanapi.data.source.local

import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity
import zefanya.denny.githubuseruiuxdanapi.data.source.local.room.GithubUserDao

class LocalDataSource(private val githubuserDao: GithubUserDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(githubuserDao: GithubUserDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(githubuserDao).apply {
                INSTANCE = this
            }
    }

    suspend fun insertUserFavourite(user: FavouriteUserEntity) {
        githubuserDao.insertUserFavourite(user)
    }

    suspend fun deleteUserFavourite(user: FavouriteUserEntity) {
        githubuserDao.deleteUserFavorite(user)
    }

    fun getUserFavorite() = githubuserDao.getUserFavourite()

    fun cekUserFavourite(username: String) = githubuserDao.cekUserFavourite(username)

}