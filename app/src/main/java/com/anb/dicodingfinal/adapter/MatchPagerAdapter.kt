package com.anb.dicodingfinal.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.anb.dicodingfinal.feature.listmatch.ListMatchFragment

class MatchPagerAdapter(fm: FragmentManager, val id: String): FragmentStatePagerAdapter(fm){

    var lastMatch = ListMatchFragment.newInstance(id, 0)
    var nextMatch = ListMatchFragment.newInstance(id, 1)

    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> return lastMatch
            1 -> return nextMatch
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            1 -> "Next Match"
            else -> null
        }

    }
}