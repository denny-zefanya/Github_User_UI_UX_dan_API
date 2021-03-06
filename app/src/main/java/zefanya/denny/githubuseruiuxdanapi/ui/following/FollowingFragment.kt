package zefanya.denny.githubuseruiuxdanapi.ui.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import zefanya.denny.githubuseruiuxdanapi.adapter.ListUsernameAdapter
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.StatusResponse
import zefanya.denny.githubuseruiuxdanapi.databinding.FragmentFollowingBinding
import zefanya.denny.githubuseruiuxdanapi.ui.detailuser.DetailUserViewModel
import zefanya.denny.githubuseruiuxdanapi.util.ClickItemRvCallBack
import zefanya.denny.githubuseruiuxdanapi.viewmodel.ViewModelFactory


class FollowingFragment() : Fragment(), ClickItemRvCallBack {
    constructor(username: String) : this() {
        this.username = username
    }

    private lateinit var viewBinding: FragmentFollowingBinding
    private lateinit var viewModel: DetailUserViewModel
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listAdapter = ListUsernameAdapter(this)
        with(viewBinding.rvListFollowing) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setHasFixedSize(true)
        }
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(
            DetailUserViewModel::class.java
        )
        viewModel.getListFollowing(username).observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    StatusResponse.SUCCESS -> {
                        viewBinding.pbFollowing.visibility = View.GONE
                        listAdapter.setData(it.body)
                    }
                    StatusResponse.LOADING -> viewBinding.pbFollowing.visibility = View.VISIBLE
                    StatusResponse.ERROR -> {
                        viewBinding.pbFollowing.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onItemClick(username: String) {
        Toast.makeText(context, "$username clicked", Toast.LENGTH_SHORT).show()
    }

}