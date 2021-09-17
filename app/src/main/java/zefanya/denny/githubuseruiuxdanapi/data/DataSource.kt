package zefanya.denny.githubuseruiuxdanapi.data

import androidx.lifecycle.LiveData
import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ItemsItem

interface DataSource {
    fun getListuserName(): LiveData<List<ItemsItem>>

    fun insertUserFavourite(favouriteUser: FavouriteUserEntity)

    fun deleteUserFavourite(favouriteUser: FavouriteUserEntity)

}