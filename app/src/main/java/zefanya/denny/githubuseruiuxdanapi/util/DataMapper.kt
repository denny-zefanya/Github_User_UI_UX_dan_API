package zefanya.denny.githubuseruiuxdanapi.util

import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ItemsItem

object DataMapper {
    fun mapUserEntitytoUserResponse(listUserEntity: List<FavouriteUserEntity>): List<ItemsItem> =
        listUserEntity.map {
                ItemsItem(login = it.username, type = it.type, id = it.idLogin)
        }
}