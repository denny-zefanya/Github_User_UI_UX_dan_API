package zefanya.denny.githubuseruiuxdanapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import zefanya.denny.githubuseruiuxdanapi.adapter.ListUsernameAdapter
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.StatusResponse
import zefanya.denny.githubuseruiuxdanapi.databinding.FragmentFollowerBinding
import zefanya.denny.githubuseruiuxdanapi.util.ClickItemRvCallBack
import zefanya.denny.githubuseruiuxdanapi.viewmodel.ViewModelFactory


class FollowerFragment(private val username: String) : Fragment(), ClickItemRvCallBack {
    private lateinit var viewBind: FragmentFollowerBinding
    private lateinit var viewModel: DetailUserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBind = FragmentFollowerBinding.inflate(inflater, container, false)
        return viewBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listUserAdapter = ListUsernameAdapter(this)
        with(viewBind.rvListFollower) {
            layoutManager = LinearLayoutManager(context)
            adapter = listUserAdapter
            setHasFixedSize(true)
        }
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(
            DetailUserViewModel::class.java
        )
        viewModel.getListFollower(username).observe(viewLifecycleOwner, {
            if(it != null){
                when(it.status){
                    StatusResponse.SUCCESS->{
                        viewBind.pvFollower.visibility = View.GONE
                        listUserAdapter.setData(it.body)
                    }
                    StatusResponse.LOADING -> viewBind.pvFollower.visibility = View.VISIBLE
                    StatusResponse.ERROR ->{
                        viewBind.pvFollower.visibility = View.GONE
                        Toast.makeText(context, it.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    override fun onItemClick(username: String) {
        TODO("Not yet implemented")
    }

}