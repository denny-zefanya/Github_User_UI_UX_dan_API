package zefanya.denny.githubuseruiuxdanapi.ui.favourite

import androidx.lifecycle.ViewModel
import zefanya.denny.githubuseruiuxdanapi.data.Repository

class FavouriteViewModel(private val repository: Repository): ViewModel() {

    fun getListUserFavourite() = repository.getUserFavourite()
}