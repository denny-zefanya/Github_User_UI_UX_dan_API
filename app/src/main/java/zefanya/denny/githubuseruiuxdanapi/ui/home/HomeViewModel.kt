package zefanya.denny.githubuseruiuxdanapi.ui.home

import androidx.lifecycle.ViewModel
import zefanya.denny.githubuseruiuxdanapi.data.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun getListUserName(keyword: String) = repository.getListUserName(keyword)

}
