package com.example.mubsone.mubsone;

import android.content.Intent;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profile);
        navigationView.setNavigationItemSelectedListener(this);

        GridView myGrid = (GridView)findViewById(R.id.gridViewProfile);
        myGrid.setAdapter(new ProfileGridAdapter(this));
        myGrid.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
        Toast.makeText(this, adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            // Handle the camera action
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
            Intent next_layout = new Intent(getApplicationContext(), MarketPlace.class);
            onPause();
            startActivity(next_layout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
