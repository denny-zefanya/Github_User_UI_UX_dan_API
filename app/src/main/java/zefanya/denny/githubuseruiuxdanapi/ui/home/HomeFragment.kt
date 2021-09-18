package zefanya.denny.githubuseruiuxdanapi.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import zefanya.denny.githubuseruiuxdanapi.R
import zefanya.denny.githubuseruiuxdanapi.adapter.ListUsernameAdapter
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.StatusResponse
import zefanya.denny.githubuseruiuxdanapi.databinding.FragmentHomeBinding
import zefanya.denny.githubuseruiuxdanapi.util.ClickItemRvCallBack
import zefanya.denny.githubuseruiuxdanapi.util.ThemeHelper
import zefanya.denny.githubuseruiuxdanapi.viewmodel.ViewModelFactory

class HomeFragment : Fragment(), ClickItemRvCallBack {
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var listUserAdapter: ListUsernameAdapter
    private var searchFor: String? = null
    private var isSearchable = false
    private lateinit var themeHelper: ThemeHelper

    val timer = object : CountDownTimer(800, 100) {
        override fun onTick(millisUntilFinished: Long) = Unit

        override fun onFinish() {
            getListuserName(searchFor)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as HomeActivity).supportActionBar?.title =
                getString(R.string.list_user_github)
            themeHelper = ThemeHelper()
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
            viewBinding.etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) =
                    Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val searchText = s.toString().trim()
                    if (searchText == searchFor) return
                    searchFor = searchText

                    if (count == 0) {
                        listUserAdapter.setData(null)
                        isSearchable = false
                    } else if (count == 1)
                        timer.cancel()

                    if (isSearchable && searchFor != "" && searchFor != null)
                        getListuserName(searchFor)
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.toString() != "")
                        timer.start()
                }
            })
        }
    }

    fun getListuserName(keyword: String?) {
        if (keyword != "" && keyword != null) {
            homeViewModel.getListUserName(keyword).observe(viewLifecycleOwner, { listUser ->
                if (listUser != null) {
                    listUserAdapter.setData(null)
                    when (listUser.status) {
                        StatusResponse.SUCCESS -> {
                            viewBinding.progressBar.visibility = View.GONE
                            if (listUser.body!!.isNotEmpty())
                                listUserAdapter.setData(listUser.body)
                            else
                                Toast.makeText(
                                    context,
                                    "user yang anda cari tidak ditemukan",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                        StatusResponse.LOADING ->
                            viewBinding.progressBar.visibility = View.VISIBLE
                        StatusResponse.ERROR -> {
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
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_favourite ->
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_favouriteFragment)
            R.id.item_setting ->
                chooseThemeDialog()

        }
        return true
    }

    override fun onItemClick(username: String) {
        val toDetailUserFragment = HomeFragmentDirections.actionHomeFragmentToDetailUserFragment()
        toDetailUserFragment.username = username
        view?.findNavController()?.navigate(toDetailUserFragment)
    }

    private fun chooseThemeDialog() {
        themeHelper.chooseThemeDialog(requireContext())
    }
}