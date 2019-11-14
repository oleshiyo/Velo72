package com.malex.velo72.custom.views.BottomSliderView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.malex.velo72.R;
import com.malex.velo72.custom.MainMenuViewPager;
import com.malex.velo72.custom.adapters.ViewPagerAdapter;
import com.malex.velo72.fragments.BikeHireFragment;
import com.malex.velo72.fragments.CategoriesFragment;
import com.malex.velo72.fragments.ProfileFragment;
import com.malex.velo72.fragments.RoutesFragment;

public class MainMenuObject implements BottomSliderViewObject {

    private Context context;
    private LayoutInflater layoutInflater;
    private TabLayout mainMenuTabLayout;
    private MainMenuViewPager mainMenuViewPager;

    private CategoriesFragment categoriesFragment;
    private RoutesFragment routesFragment;
    private ProfileFragment profileFragment;

    private BikeHireFragment bikeHireFragment;

    private View mainMenuLayout;


    public MainMenuObject(Context context, FragmentManager fragmentManager, LinearLayout view, int viewPagerLevel) {
        this.context = context;
        layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainMenuLayout = layoutInflater.inflate(R.layout.main_bottom_menu, view, false);

        mainMenuTabLayout = mainMenuLayout.findViewById(R.id.main_menu_tablayout);
        mainMenuViewPager = mainMenuLayout.findViewById(R.id.main_menu_viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.removeAllFragments();

        switch (viewPagerLevel)
        {
            case 1:
                if (categoriesFragment == null) {
                    categoriesFragment = new CategoriesFragment();
                    routesFragment = new RoutesFragment();
                    profileFragment = new ProfileFragment();


                adapter.addFragment(categoriesFragment, "МЕСТА");
                adapter.addFragment(routesFragment, "МОИ ПОЕЗДКИ");
                adapter.addFragment(profileFragment, "ПРОФИЛЬ");
                break;
            case 2:
                if (bikeHireFragment == null) {
                    bikeHireFragment = new BikeHireFragment();
                }

                adapter.addFragment(bikeHireFragment, "ПРОКАТ");
                break;
            case 3:
                break;

                default:
                    break;
        }


        mainMenuViewPager.setAdapter(adapter);
        mainMenuTabLayout.setupWithViewPager(mainMenuViewPager);
    }

    @Override
    public View getShortDescriptionView() {
        if(mainMenuLayout.getParent() != null) {
            ((ViewGroup)mainMenuLayout.getParent()).removeView(mainMenuLayout);
        }
        return mainMenuLayout;
    }

    @Override
    public int getViewHeight() {
        mainMenuViewPager.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return mainMenuViewPager.getMeasuredHeight();
    }

    @Override
    public View getLongDescriptionView() {
        return null;
    }
}