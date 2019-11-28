package com.malex.velo72.custom.views.BottomSliderView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.malex.velo72.R;
import com.malex.velo72.custom.adapters.PagerItem;
import com.malex.velo72.custom.adapters.ViewPagerAdapter;
import com.malex.velo72.fragments.BikeHireFragment;
import com.malex.velo72.fragments.BikeParkingFragment;
import com.malex.velo72.fragments.BikeRepairFragment;
import com.malex.velo72.fragments.BikeShopsFragment;
import com.malex.velo72.fragments.CategoriesFragment;
import com.malex.velo72.fragments.ProfileFragment;
import com.malex.velo72.fragments.MyRoutesFragment;
import com.malex.velo72.fragments.RoutesFragment;
import com.malex.velo72.fragments.TracksFragment;

import java.util.ArrayList;

public class MainMenuObject implements BottomSliderViewObject {

    private Context context;
    private LayoutInflater layoutInflater;
    private TabLayout mainMenuTabLayout;
    private ViewPager mainMenuViewPager;
    private ViewPagerAdapter adapter;
    private View mainMenuLayout;
    private ArrayList<PagerItem> pagerItems;
    private boolean adapterWasSet = false;


    public MainMenuObject(Context context, FragmentManager fragmentManager, LinearLayout view) {
        this.context = context;
        layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainMenuLayout = layoutInflater.inflate(R.layout.main_bottom_menu, view, false);

        mainMenuTabLayout = mainMenuLayout.findViewById(R.id.main_menu_tablayout);
        mainMenuViewPager = mainMenuLayout.findViewById(R.id.main_menu_viewpager);
        adapter = new ViewPagerAdapter(fragmentManager);
        pagerItems = new ArrayList<>();
    }



    public void setFragments(int viewPagerLevel)
    {
        adapter.removePagerItems();
        pagerItems.clear();
        switch (viewPagerLevel)
        {
            case 1:
                mainMenuTabLayout.setTabMode(TabLayout.MODE_FIXED);
                pagerItems.add(new PagerItem("МЕСТА", new CategoriesFragment()));
                pagerItems.add(new PagerItem("МОИ ПОЕЗДКИ", new MyRoutesFragment()));
                pagerItems.add(new PagerItem("ПРОФИЛЬ", new ProfileFragment()));
                break;
            case 2:
                mainMenuTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                pagerItems.add(new PagerItem("ПРОКАТ", new BikeHireFragment()));
                pagerItems.add(new PagerItem("МАРШРУТЫ", new RoutesFragment()));
                pagerItems.add(new PagerItem("ТРАССЫ", new TracksFragment()));
                pagerItems.add(new PagerItem("МАГАЗИНЫ", new BikeShopsFragment()));
                pagerItems.add(new PagerItem("РЕМОНТ", new BikeRepairFragment()));
                pagerItems.add(new PagerItem("ПАРКОВКИ", new BikeParkingFragment()));
                break;
            case 3:
                break;
            default:
                break;
        }
        adapterWasSet = false;
        adapter.setPagerItems(pagerItems);
        adapter.notifyDataSetChanged();
        mainMenuViewPager.setAdapter(adapter);
        mainMenuTabLayout.setupWithViewPager(mainMenuViewPager);
        setTabLayoutCustomTabs();
    }

    private void setTabLayoutCustomTabs()
    {
        for (int i = 0; i < pagerItems.size(); i++
        ) {
            View tabView = ((ViewGroup) mainMenuTabLayout.getChildAt(0)).getChildAt(i);
            tabView.requestLayout();
            View view = LayoutInflater.from(context).inflate(R.layout.selected_tab, null);
            TextView tv = view.findViewById(R.id.tabText);
            tv.setText(pagerItems.get(i).getTitle());
            if (i != 0)
            {
                tv.setTextColor(context.getResources().getColor(R.color.unselectedTabTextColor));
                tv.setBackground(context.getDrawable(R.drawable.tab_unselected));
            }
            mainMenuTabLayout.getTabAt(i).setCustomView(view);
        }
        adapterWasSet = true;
        mainMenuTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (adapterWasSet) {
                    View view = tab.getCustomView();
                    TextView tv = view.findViewById(R.id.tabText);
                    tv.setTextColor(MainMenuObject.this.context.getResources().getColor(R.color.selectedTabTextColor));
                    tv.setBackground(MainMenuObject.this.context.getDrawable(R.drawable.tab_selected));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (adapterWasSet) {
                    View view = tab.getCustomView();
                    TextView tv = view.findViewById(R.id.tabText);
                    tv.setTextColor(MainMenuObject.this.context.getResources().getColor(R.color.unselectedTabTextColor));
                    tv.setBackground(MainMenuObject.this.context.getDrawable(R.drawable.tab_unselected));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public View getView() {
        if(mainMenuLayout.getParent() != null) {
            ((ViewGroup)mainMenuLayout.getParent()).removeView(mainMenuLayout);
        }
        return mainMenuLayout;
    }
}


/*

 */