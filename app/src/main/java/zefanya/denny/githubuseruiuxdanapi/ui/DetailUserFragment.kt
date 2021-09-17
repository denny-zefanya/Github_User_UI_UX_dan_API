package zefanya.denny.githubuseruiuxdanapi.ui

import android.os.Bundle
import android.view.*
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import zefanya.denny.githubuseruiuxdanapi.R
import zefanya.denny.githubuseruiuxdanapi.adapter.SectionPagerAdapter
import zefanya.denny.githubuseruiuxdanapi.data.source.local.entity.FavouriteUserEntity
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ApiResponse
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.DetailUserResponse
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.StatusResponse
import zefanya.denny.githubuseruiuxdanapi.databinding.FragmentDetailUserBinding
import zefanya.denny.githubuseruiuxdanapi.viewmodel.ViewModelFactory

class DetailUserFragment : Fragment() {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.follower, R.string.following)
    }

    private lateinit var viewBinding: FragmentDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var sectionPagerAdapter: SectionPagerAdapter
    private lateinit var viewPager: ViewPager2
    private var userEntity: FavouriteUserEntity? = null
    private var isFav: Boolean = false
    private val detailUserObserver = Observer<ApiResponse<DetailUserResponse?>> {
        if (it != null) {
            when (it.status) {
                StatusResponse.SUCCESS -> {
                    with(viewBinding) {
                        userEntity =
                            FavouriteUserEntity(it.body?.login.toString(),it.body?.id!!, it.body?.type.toString())
                        tvName.text = it.body?.name
                        com.bumptech.glide.Glide.with(requireActivity())
                            .load("https://avatars.githubusercontent.com/u/" + it.body?.id)
                            .apply(
                                com.bumptech.glide.request.RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_baseline_error_24)
                            )
                            .into(ivAvatar)
                        tvLocation.text = it.body?.location
                        tvDataGithub.text = resources.getString(
                            R.string.data_github,
                            it.body?.followers,
                            it.body?.following,
                            it.body?.public_repos,
                            it.body?.public_gists
                        )
                    }

                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDetailUserBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val username = DetailUserFragmentArgs.fromBundle(
                arguments as Bundle
            ).username
            sectionPagerAdapter = SectionPagerAdapter(this, username)
            viewPager = viewBinding.viewPager
            viewPager.adapter = sectionPagerAdapter
            val tabs: TabLayout = viewBinding.tabs
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory).get(
                DetailUserViewModel::class.java
            )
            viewModel.getDetailUserName(username).observe(viewLifecycleOwner, detailUserObserver)
            viewModel.cekUserFavourite(username).observe(viewLifecycleOwner, {
                if (it != null) {
                    isFav = true
                    viewBinding.fabAddToFavorite.setImageResource(R.drawable.ic_favourite_fill)
                } else {
                    isFav = false
                    viewBinding.fabAddToFavorite.setImageResource((R.drawable.ic_favourite_border))
                }
            })

            viewBinding.fabAddToFavorite.setOnClickListener {
                if (isFav)
                    runBlocking { viewModel.deleteUserFavourite(userEntity!!) }
                else {
                    runBlocking {
                        launch { viewModel.insertUserFavourite(userEntity!!) }
                    }
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favourite_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view?.findNavController()?.navigate(R.id.action_detailUserFragment_to_favouriteFragment)
        return true
    }
}