package com.anb.dicodingfinal.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.anb.dicodingfinal.feature.favorite.FavoriteFragment
import com.anb.dicodingfinal.feature.match.MatchFragment
import com.anb.dicodingfinal.feature.team.TeamFragment

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return MatchFragment.newInstance()
            1 -> return TeamFragment.newInstance()
            2 -> return FavoriteFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }
}
