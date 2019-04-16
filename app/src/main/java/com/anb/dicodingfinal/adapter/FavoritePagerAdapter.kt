package com.anb.dicodingfinal.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.anb.dicodingfinal.feature.favoritematch.FavoriteMatchFragment
import com.anb.dicodingfinal.feature.favoriteteam.FavoriteTeamFragment

class FavoritePagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> return FavoriteMatchFragment.newInstance()
            1 -> return FavoriteTeamFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Match"
            1 -> "Team"
            else -> null
        }

    }

}