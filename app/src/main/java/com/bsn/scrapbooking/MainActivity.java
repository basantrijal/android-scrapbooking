package com.bsn.scrapbooking;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private String mListSelected;
    private Context context;
    private LinearLayout baseLL;
    private Menu menu;
    ActionBarDrawerToggle mActionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        baseLL = (LinearLayout) findViewById(R.id.imageList);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mListSelected = "NEPAL";

        toggleDrawer();
        menuClicked();
        new ViewLayout(this, baseLL, mListSelected);
    }

    private void menuClicked(){
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                baseLL.removeAllViews();
                if(id==1){
                    mListSelected = "AMERICA";
                }else if(id == 2){
                    mListSelected = "CANADA";
                }else{
                    mListSelected = "NEPAL";
                }
                new ViewLayout(context, baseLL, mListSelected);
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void toggleDrawer(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //Create Drawer Toggle
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.topmenu, menu);
        if(getCurrentLocale().getISO3Language().equals("eng")){
            this.menu.findItem(R.id.nepali).setVisible(true);
        }else{
            this.menu.findItem(R.id.english).setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    // handle menu activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();
        if (id == R.id.nepali) {
            this.menu.findItem(R.id.nepali).setVisible(false);
            Locale locale = new Locale("ne");
            setLocale(locale);
            this.menu.findItem(R.id.english).setVisible(true);
        }else if(id == R.id.english){
            this.menu.findItem(R.id.english).setVisible(false);
            Locale locale = new Locale("en");
            setLocale(locale);
            this.menu.findItem(R.id.nepali).setVisible(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return getResources().getConfiguration().getLocales().get(0);
        } else{
            return getResources().getConfiguration().locale;
        }
    }

    private void setLocale(Locale locale){
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.getApplicationContext().createConfigurationContext(configuration);
        } else {
            resources.updateConfiguration(configuration, null);
        }
        Intent refresh = new Intent(MainActivity.this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

}
