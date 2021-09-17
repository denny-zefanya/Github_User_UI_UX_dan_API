package zefanya.denny.githubuseruiuxdanapi.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import zefanya.denny.githubuseruiuxdanapi.R
import zefanya.denny.githubuseruiuxdanapi.adapter.ListUsernameAdapter
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.StatusResponse
import zefanya.denny.githubuseruiuxdanapi.databinding.FragmentHomeBinding
import zefanya.denny.githubuseruiuxdanapi.util.ClickItemRvCallBack
import zefanya.denny.githubuseruiuxdanapi.viewmodel.ViewModelFactory

class HomeFragment : Fragment(), ClickItemRvCallBack {
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var listUserAdapter: ListUsernameAdapter
    private var searchQuery: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        if (savedInstanceState != null)
            searchQuery = savedInstanceState.getString("searchQuery")
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listUserAdapter = ListUsernameAdapter(this)
        with(viewBinding.rvListuser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listUserAdapter

        }

        val factory = ViewModelFactory.getInstance(requireContext())
        homeViewModel = ViewModelProvider(
            this, factory
        ).get(HomeViewModel::class.java)

        getListuserName(searchQuery)
    }

    fun getListuserName(keyword: String?) {
        if (keyword != "" && keyword != null) {
            homeViewModel.getListUserName(keyword).observe(viewLifecycleOwner, { listUser ->
                if (listUser != null) {
                    listUserAdapter.setData(null)
                    when (listUser.status) {
                        StatusResponse.SUCCESS -> {
                            viewBinding.progressBar.visibility = View.GONE
                            if(listUser.body!!.isNotEmpty())
                                listUserAdapter.setData(listUser.body)
                            else
                                Toast.makeText(
                                    context,
                                    "user yang anda cari tidak ditemukan",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                        StatusResponse.LOADING->
                            viewBinding.progressBar.visibility = View.VISIBLE
                        StatusResponse.ERROR->{
                            viewBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, listUser.message, Toast.LENGTH_SHORT).show()

                        }

                    }
                }

            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchMenuItem = menu.findItem(R.id.search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.search)
        if(searchQuery != ""){
            searchMenuItem.expandActionView()
            searchView.setQuery(searchQuery, false)
            searchView.clearFocus()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchQuery = query
                getListuserName(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.menu.favourite_menu->{
                view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToFavouriteFragment())
            }

        }
        return true
    }

    override fun onItemClick(username: String) {
        val toDetailUserFragment =HomeFragmentDirections.actionHomeFragmentToDetailUserFragment()
        toDetailUserFragment.username = username
        view?.findNavController()?.navigate(toDetailUserFragment)
        //Toast.makeText(context, id!!.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("searchQuery", searchQuery)
        super.onSaveInstanceState(outState)
    }

}