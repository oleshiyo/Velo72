package com.malex.velo72.custom.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager mFragmentManager;
    private ArrayList<PagerItem> mPagerItems;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
        mPagerItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mPagerItems.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mPagerItems.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mPagerItems.get(position).getTitle();
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }

    public void hidePagerItems() {
        if (mPagerItems != null)
            for (int i = 0; i < mPagerItems.size(); i++) {
                mFragmentManager.beginTransaction().hide(mPagerItems.get(i).getFragment()).commit();
            }
    }

    public void showPagerItems(ArrayList<PagerItem> pagerItems) {
        mPagerItems = pagerItems;
        if (mPagerItems != null)
            for (int i = 0; i < mPagerItems.size(); i++) {
                if (mPagerItems.get(i).getFragment().isHidden())
                    mFragmentManager.beginTransaction().show(mPagerItems.get(i).getFragment()).commit();
            }
    }

    public void removePagerItems() {
        if (mPagerItems != null)
            for (int i = 0; i < mPagerItems.size(); i++) {
                mFragmentManager.beginTransaction().remove(mPagerItems.get(i).getFragment()).commit();
            }
    }

    public void setPagerItems(ArrayList<PagerItem> pagerItems) {
        mPagerItems = pagerItems;
    }
}
