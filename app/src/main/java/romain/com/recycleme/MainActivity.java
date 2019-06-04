package romain.com.recycleme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import romain.com.recycleme.fragment.FavorisFragment;
import romain.com.recycleme.fragment.HomeFragment;
import romain.com.recycleme.view.SwipableViewPager;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    public SwipableViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.activity_main_view_pager);
        tabLayout = findViewById(R.id.activity_main_tab_layout);

        tabLayout.addOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(this);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FavorisFragment());

        FragmentStatePagerAdapter adpater = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                switch(position){
                    case 0:
                        return "home";
                    case 1:
                        return "favoris";
                    default:
                        return "";
                }
            }
        };
        viewPager.setAdapter(adpater);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        this.viewPager.setPagingEnabled(true);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            for(Fragment subFragment : fragment.getChildFragmentManager().getFragments()){
                if(subFragment.getTag() != null && subFragment.getTag().equals(getString(R.string.menu_favoris))){
                    subFragment.getFragmentManager().popBackStack();
                }else if(subFragment.getTag() != null && subFragment.getTag().equals(getString(R.string.menu_home))){
                    subFragment.getFragmentManager().popBackStack();
                }
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
