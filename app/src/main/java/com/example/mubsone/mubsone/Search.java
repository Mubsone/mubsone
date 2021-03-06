package com.example.mubsone.mubsone;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mubsone.mubsone.Blur.BlurActionBarDrawerToggle;
import com.example.mubsone.mubsone.SearchFragments.TabSearchFragment;


/**
 * Created by bsaraci on 10/26/2015.
 */
public class Search extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView title_toolbar;
    private BlurActionBarDrawerToggle mDrawerToggle;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOfSearch);
        toolbar.setVisibility(View.VISIBLE);
        //arsing the font for the title toolbar
        title_toolbar=(TextView)toolbar.findViewById(R.id.toolbarTitleSearch);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        title_toolbar.setTypeface(MyCustomFont);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search);
        drawer.setScrimColor(Color.TRANSPARENT);

        mDrawerToggle = new BlurActionBarDrawerToggle(
                this,                    /* host Activity */
                drawer,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );

        mDrawerToggle.setRadius(25);
        mDrawerToggle.setDownScaleFactor(0.0f);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_search);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabSearchFragment()).commit();

    }



    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_newsfeed);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            Intent next_layout = new Intent(getApplicationContext(), Search.class);
            onPause();
            startActivity(next_layout);

        } else if (id == R.id.nav_newsfeed) {
            Intent next_layout = new Intent(getApplicationContext(), CoverPage.class);
            onPause();
            startActivity(next_layout);

        } else if (id == R.id.nav_myprofil) {
            Intent next_layout = new Intent(getApplicationContext(), Profile.class);
            onPause();
            startActivity(next_layout);
        } else if (id == R.id.nav_notification) {
            Intent next_layout = new Intent(getApplicationContext(), Notifications.class);
            onPause();
            startActivity(next_layout);

        } else if (id == R.id.nav_logout) {
            Intent next_layout = new Intent(getApplicationContext(), LogIn.class);
            onPause();
            startActivity(next_layout);

        } else if (id == R.id.nav_marketplace) {
            Intent next_layout = new Intent(getApplicationContext(), Marketplace.class);
            onPause();
            startActivity(next_layout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_search);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}


