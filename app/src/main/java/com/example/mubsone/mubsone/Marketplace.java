package com.example.mubsone.mubsone;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mubsone.mubsone.Blur.BlurActionBarDrawerToggle;

public class Marketplace extends ListActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView title_toolbar;
    String [] itemNames = {"Microphone","Guitar","Camera","Audio Mixer","Microphone","Guitar","Camera","Audio Mixer","Microphone","Guitar","Camera","Audio Mixer"};
    int[] itemPhoto = {R.drawable.audio_input_microphone, R.drawable.fire_guitar, R.drawable.camera_unmount2, R.drawable.mixer, R.drawable.audio_input_microphone, R.drawable.fire_guitar, R.drawable.camera_unmount2, R.drawable.mixer, R.drawable.audio_input_microphone, R.drawable.fire_guitar, R.drawable.camera_unmount2, R.drawable.mixer};
    private BlurActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOfMarketplace);
        toolbar.setVisibility(View.VISIBLE);
        //arsing the font for the title toolbar
        title_toolbar=(TextView)toolbar.findViewById(R.id.toolbarTitleMarketplace);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        title_toolbar.setTypeface(MyCustomFont);

        MarketplaceListAdapter mpfListAdapter = new MarketplaceListAdapter(this,itemNames, itemPhoto);
        setListAdapter(mpfListAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_marketplace);
        drawer.setScrimColor(Color.TRANSPARENT);

        mDrawerToggle = new BlurActionBarDrawerToggle(
                this,                    /* host Activity */
                drawer,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );

        mDrawerToggle.setRadius(25);
        mDrawerToggle.setDownScaleFactor(0.0f);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_marketplace);
        navigationView.setNavigationItemSelectedListener(this);

    }

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

        } else if (id == R.id.nav_settings) {
            Intent next_layout = new Intent(getApplicationContext(), Settings.class);
            onPause();
            startActivity(next_layout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_marketplace);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

