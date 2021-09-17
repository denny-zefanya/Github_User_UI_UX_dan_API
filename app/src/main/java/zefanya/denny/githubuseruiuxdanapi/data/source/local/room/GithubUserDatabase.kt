package zefanya.denny.githubuseruiuxdanapi.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity

@Database(entities = [FavouriteUserEntity::class], version = 1, exportSchema = false)
abstract class GithubUserDatabase : RoomDatabase() {
    abstract fun githubuserDao(): GithubUserDao

    companion object {
        @Volatile
        private var instance: GithubUserDatabase? = null

        fun getInstance(context: Context): GithubUserDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                GithubUserDatabase::class.java,
                "githubUsername.db"
            ).build().apply {
                instance = this
            }
        }

    }
}