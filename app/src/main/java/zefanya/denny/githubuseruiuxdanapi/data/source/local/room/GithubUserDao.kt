package zefanya.denny.githubuseruiuxdanapi.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity

@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFavourite(userFavourite: FavouriteUserEntity)

    @Query("select * from favouriteuserenties")
    fun getUserFavourite(): LiveData<List<FavouriteUserEntity>>

    @Query("select * from favouriteuserenties where username = :username")
    fun cekUserFavourite(username: String): LiveData<FavouriteUserEntity?>


    @Delete
    suspend fun deleteUserFavorite(userFavourite: FavouriteUserEntity)
}