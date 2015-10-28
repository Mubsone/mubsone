package com.example.mubsone.mubsone;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Marketplace extends ListActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView title_toolbar;
    String [] itemNames = {"Microphone","Guitar","Camera","Audio Mixer","Microphone","Guitar","Camera","Audio Mixer","Microphone","Guitar","Camera","Audio Mixer"};
    int[] itemPhoto = {R.drawable.audio_input_microphone, R.drawable.fire_guitar, R.drawable.camera_unmount2, R.drawable.mixer, R.drawable.audio_input_microphone, R.drawable.fire_guitar, R.drawable.camera_unmount2, R.drawable.mixer, R.drawable.audio_input_microphone, R.drawable.fire_guitar, R.drawable.camera_unmount2, R.drawable.mixer};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_place);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOfMarketplace);
        toolbar.setVisibility(View.VISIBLE);
        //arsing the font for the title toolbar
        title_toolbar=(TextView)toolbar.findViewById(R.id.toolbarTitleMarketplace);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        title_toolbar.setTypeface(MyCustomFont);

        MarketplaceListAdapter mpfListAdapter = new MarketplaceListAdapter(this,itemNames, itemPhoto);
        setListAdapter(mpfListAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_marketplace);

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
            Intent next_layout = new Intent(getApplicationContext(), Newsfeed.class);
            onPause();
            startActivity(next_layout);

        } else if (id == R.id.nav_myprofil) {
            Intent next_layout = new Intent(getApplicationContext(), MyProfile.class);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_marketplace);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
