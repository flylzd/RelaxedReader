package com.lemon.reader.ui.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lemon.reader.R;
import com.lemon.reader.base.BaseActivity;
import com.lemon.reader.base.BaseLazyFragment;
import com.lemon.reader.bean.NavigationEntity;
import com.lemon.reader.ui.adapter.NavigationAdapter;
import com.lemon.reader.ui.adapter.VPFragmentAdapter;
import com.lemon.reader.ui.fragment.ImagesContainerFragment;
import com.lemon.reader.ui.fragment.MusicsContainerFragment;
import com.lemon.reader.ui.fragment.NewsContainerFragment;
import com.lemon.reader.ui.fragment.VideosContainerFragment;
import com.lemon.reader.widget.XViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Bind(R.id.containerViewPager)
    XViewPager containerViewPager;
    private List<BaseLazyFragment> fragments;

    @Bind(R.id.listView)
    ListView listView;

    private NavigationAdapter adapter;
    private int mCurrentMenuCheckedPos = 0;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,getToolbar(),R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (null != adapter) {
                    setTitle(((NavigationEntity)adapter.getItem(mCurrentMenuCheckedPos)).name);
                }
            }
        };
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        String[] leftMenus = getResources().getStringArray(R.array.left_menu_navigation_list);
        int[] iconResIds = {R.drawable.ic_news, R.drawable.ic_picture, R.drawable.ic_video, R.drawable.ic_music};
        List<NavigationEntity> sourceList = new ArrayList<NavigationEntity>();
        int size = leftMenus.length;
        for (int i = 0; i < size; i++) {
            NavigationEntity bean = new NavigationEntity();
            bean.name = leftMenus[i];
            bean.iconResId = iconResIds[i];
            sourceList.add(bean);
        }
        adapter = new NavigationAdapter(MainActivity.this, sourceList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "position == " + position, Toast.LENGTH_SHORT).show();
            }
        });

        fragments = new ArrayList<BaseLazyFragment>();
        fragments.add(new NewsContainerFragment());
        fragments.add(new ImagesContainerFragment());
        fragments.add(new VideosContainerFragment());
        fragments.add(new MusicsContainerFragment());
        if (null != fragments && !fragments.isEmpty()) {
            containerViewPager.setEnableScroll(false);
            containerViewPager.setOffscreenPageLimit(fragments.size());
            containerViewPager.setAdapter(new VPFragmentAdapter(getSupportFragmentManager(), fragments));
        }





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
}
