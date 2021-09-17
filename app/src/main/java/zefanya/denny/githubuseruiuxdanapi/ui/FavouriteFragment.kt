package zefanya.denny.githubuseruiuxdanapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import zefanya.denny.githubuseruiuxdanapi.R
import zefanya.denny.githubuseruiuxdanapi.adapter.ListUsernameAdapter
import zefanya.denny.githubuseruiuxdanapi.databinding.FragmentFavouriteBinding
import zefanya.denny.githubuseruiuxdanapi.util.ClickItemRvCallBack
import zefanya.denny.githubuseruiuxdanapi.util.DataMaper
import zefanya.denny.githubuseruiuxdanapi.viewmodel.ViewModelFactory

class FavouriteFragment : Fragment(), ClickItemRvCallBack {
    private lateinit var viewBinding: FragmentFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFavouriteBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listUserFavouriteAdapter = ListUsernameAdapter(this)
        viewBinding.rvListuserFavourite.layoutManager = LinearLayoutManager(context)
        viewBinding.rvListuserFavourite.setHasFixedSize(true)
        viewBinding.rvListuserFavourite.adapter = listUserFavouriteAdapter

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(FavouriteViewModel::class.java)
        viewModel.getListUserFavourite().observe(viewLifecycleOwner,{
            listUserFavouriteAdapter.setData(DataMaper.mapUserEntitytoUserResponse(it))
        })
    }

    override fun onItemClick(username: String) {
        TODO("Not yet implemented")
    }


}