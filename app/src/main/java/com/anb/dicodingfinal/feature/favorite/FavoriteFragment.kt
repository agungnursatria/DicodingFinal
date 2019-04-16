package com.anb.dicodingfinal.feature.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.anb.dicodingfinal.R
import com.anb.dicodingfinal.adapter.FavoritePagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(){

    companion object {
        fun newInstance() : FavoriteFragment{
            return FavoriteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setTabLayout()
    }

    private fun setTabLayout() {
        val mSectionsPagerAdapter = FavoritePagerAdapter(childFragmentManager)
        vp_fav.adapter = mSectionsPagerAdapter
        tl_fav.setupWithViewPager(vp_fav)
    }

}