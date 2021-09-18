package zefanya.denny.githubuseruiuxdanapi.ui.favourite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import zefanya.denny.githubuseruiuxdanapi.R
import zefanya.denny.githubuseruiuxdanapi.adapter.ListUsernameAdapter
import zefanya.denny.githubuseruiuxdanapi.databinding.FragmentFavouriteBinding
import zefanya.denny.githubuseruiuxdanapi.ui.home.HomeActivity
import zefanya.denny.githubuseruiuxdanapi.util.ClickItemRvCallBack
import zefanya.denny.githubuseruiuxdanapi.util.DataMapper
import zefanya.denny.githubuseruiuxdanapi.util.ThemeHelper
import zefanya.denny.githubuseruiuxdanapi.viewmodel.ViewModelFactory

class FavouriteFragment : Fragment(), ClickItemRvCallBack {
    private lateinit var viewBinding: FragmentFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFavouriteBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).supportActionBar?.title =getString(R.string.favourite_user)
        val listUserFavouriteAdapter = ListUsernameAdapter(this)
        viewBinding.rvListuserFavourite.layoutManager = LinearLayoutManager(context)
        viewBinding.rvListuserFavourite.setHasFixedSize(true)
        viewBinding.rvListuserFavourite.adapter = listUserFavouriteAdapter

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(FavouriteViewModel::class.java)
        viewModel.getListUserFavourite().observe(viewLifecycleOwner,{
            listUserFavouriteAdapter.setData(DataMapper.mapUserEntitytoUserResponse(it))
        })
    }

    override fun onItemClick(username: String) {
        val toDetailUserFragment = FavouriteFragmentDirections.actionFavouriteFragmentToDetailUserFragment()
        toDetailUserFragment.username = username
        view?.findNavController()?.navigate(toDetailUserFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.item_favourite)
        if(item != null)
            item.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val themeHelper = ThemeHelper()
        themeHelper.chooseThemeDialog(requireContext())
        return true
    }



}