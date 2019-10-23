package com.pessoadev.marvelapp.presentation.characters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class CharactersPageAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val fragmentTitle: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }
}