package tech.thdev.app.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import tech.thdev.app.R;
import tech.thdev.app.adapter.pager.SectionsPagerAdapter;
import tech.thdev.app.view.main.presenter.MainContract;
import tech.thdev.app.view.main.presenter.MainPresenter;
import tech.thdev.base.view.BasePresenterActivity;

public class MainActivity extends BasePresenterActivity<MainContract.View, MainContract.Presenter> implements MainContract.View, ViewPager.OnPageChangeListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container)
    ViewPager viewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public MainContract.Presenter onCreatePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
        prevBottomNavigation = bottomNavigationView.getMenu().getItem(0);
        viewPager.setOffscreenPageLimit(3);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_one:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.action_two:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.action_three:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        getPresenter().setSectionPagerModel(mSectionsPagerAdapter);
        getPresenter().loadSectionPagerItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updatePager() {
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * BottomNavigation Prev
     */
    private MenuItem prevBottomNavigation;

    @Override
    public void onPageSelected(int position) {
        if (prevBottomNavigation != null) {
            prevBottomNavigation.setChecked(false);
        }
        prevBottomNavigation = bottomNavigationView.getMenu().getItem(position);
        prevBottomNavigation.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
