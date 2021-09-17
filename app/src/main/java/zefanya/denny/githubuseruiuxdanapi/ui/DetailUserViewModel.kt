package zefanya.denny.githubuseruiuxdanapi.ui

import androidx.lifecycle.ViewModel
import zefanya.denny.githubuseruiuxdanapi.data.Repository
import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity

class DetailUserViewModel(private val repository: Repository) : ViewModel() {

    fun getDetailUserName(username: String) = repository.getDetailUserName(username)

    fun getListFollower(username: String) = repository.getFollowerUser(username)

    fun getListFollowing(username: String) = repository.getFollowingUser(username)

    suspend fun insertUserFavourite(userEntity: FavouriteUserEntity) =
        repository.insertUserFavourite(userEntity)

    fun cekUserFavourite(username: String) = repository.cekUserFavourite(username)

    suspend fun deleteUserFavourite(userEntity: FavouriteUserEntity) =
        repository.deleteUserFavourite(userEntity)

}