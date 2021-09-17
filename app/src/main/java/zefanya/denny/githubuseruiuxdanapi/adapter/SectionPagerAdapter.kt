package zefanya.denny.githubuseruiuxdanapi.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import zefanya.denny.githubuseruiuxdanapi.ui.FollowerFragment
import zefanya.denny.githubuseruiuxdanapi.ui.FollowingFragment

class SectionPagerAdapter(fa: Fragment,val username: String) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FollowerFragment(username)
            1 -> FollowingFragment(username)
            else -> Fragment()
        }


}