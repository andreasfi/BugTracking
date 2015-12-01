package com.example.bugtracking.bugtracking;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BugActivity extends BaseActivity {

    //private ActionBar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    long projectid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug);

        // Adapter that handles the tabs/fragments as a menu
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // ID of the project

        Intent intent=getIntent();
        projectid = intent.getLongExtra("idProject", 1L);


        final Activity thisclass = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open add bug activity
                Intent intent = new Intent(thisclass, BugCrudActivity.class);
                intent.putExtra("action", "add");
                intent.putExtra("idpro", projectid);
                startActivity(intent);
            }
        });
    }

    public long getProjectid() {
        return projectid;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add the fragments to the Tab menu of BugActivity
        adapter.addFragment(new BugCurrentFragment(), getString(R.string.tab_current));
        adapter.addFragment(new BugSolvedFragment(), getString(R.string.tab_solved));
        adapter.addFragment(new BugOnholdFragment(), getString(R.string.tab_onhold));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}