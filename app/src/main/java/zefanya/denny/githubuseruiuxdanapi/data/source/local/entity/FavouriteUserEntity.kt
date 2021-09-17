package zefanya.denny.githubuseruiuxdanapi.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="favouriteuserenties")
data class FavouriteUserEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    var username: String = "denny",
    @ColumnInfo(name="type")
    var type: String ="admin"
)