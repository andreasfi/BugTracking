package com.example.bugtracking.bugtracking;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.bugtracking.bugtracking.adapter.BugDataSource;
import com.example.bugtracking.bugtracking.adapter.CommentDataSource;
import com.example.bugtracking.bugtracking.object.Bug;
import com.example.bugtracking.bugtracking.object.Comment;

import java.util.ArrayList;
import java.util.List;

public class BugViewActivity extends BaseActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    public long idBug;
    private long idProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //test si l'utilisateur est connecté
       /* if(LoginActivity.CONNECTED==false){
            LoginActivity.MESSAGE_ERROR=true;
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }*/

        setContentView(R.layout.activity_bug_view);

        viewPager=(ViewPager) findViewById(R.id.viewpagerIssueView);
        setupViewPager(viewPager);


        tabLayout=(TabLayout) findViewById(R.id.tabsIssueView);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent=getIntent();
        idBug=intent.getLongExtra("BugId", 1L);
        idProject=intent.getLongExtra("idPro",1L);

        Log.d("IDissue","Id "+idBug);


    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter_IssueView adapter=new ViewPagerAdapter_IssueView(getSupportFragmentManager());

        //Add the fragment to the Tabmenu of IssueView
        adapter.addFragment(new BugViewFragment(), "Bug View");
        adapter.addFragment(new CommentFragment(),"Comments");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter_IssueView extends FragmentPagerAdapter{

        private final List<Fragment> issueFragmentList=new ArrayList<>();
        private final List<String> issueFragmentTitleList=new ArrayList<>();

        public ViewPagerAdapter_IssueView(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return issueFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return issueFragmentList.size();
        }

        public void addFragment (Fragment fragment, String title){
            issueFragmentList.add(fragment);
            issueFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return issueFragmentTitleList.get(position);
        }
    }
    public long getIdBug() {
        return idBug;
    }


    public long getIdProject() {
        return idProject;
    }
}
