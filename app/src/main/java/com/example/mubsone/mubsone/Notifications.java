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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mubsone.mubsone.Blur.BlurActionBarDrawerToggle;


public class Notifications extends ListActivity implements NavigationView.OnNavigationItemSelectedListener {
    String [] names = {"comment_icon", "description_icon", "viewer_icon", "share_icon","comment_icon", "description_icon", "viewer_icon", "share_icon","comment_icon", "description_icon", "viewer_icon", "share_icon","comment_icon", "description_icon", "viewer_icon", "share_icon","comment_icon", "description_icon", "viewer_icon", "share_icon","comment_icon", "description_icon", "viewer_icon", "share_icon","comment_icon", "description_icon", "viewer_icon", "share_icon"};
    int [] images = {R.mipmap.ic_new_comment, R.mipmap.ic_new_description, R.mipmap.ic_new_viewer, R.mipmap.ic_new_share,R.mipmap.ic_new_comment, R.mipmap.ic_new_description, R.mipmap.ic_new_viewer, R.mipmap.ic_new_share,R.mipmap.ic_new_comment, R.mipmap.ic_new_description, R.mipmap.ic_new_viewer, R.mipmap.ic_new_share,R.mipmap.ic_new_comment, R.mipmap.ic_new_description, R.mipmap.ic_new_viewer, R.mipmap.ic_new_share,R.mipmap.ic_new_comment, R.mipmap.ic_new_description, R.mipmap.ic_new_viewer, R.mipmap.ic_new_share,R.mipmap.ic_new_comment, R.mipmap.ic_new_description, R.mipmap.ic_new_viewer, R.mipmap.ic_new_share,R.mipmap.ic_new_comment, R.mipmap.ic_new_description, R.mipmap.ic_new_viewer, R.mipmap.ic_new_share};
    private TextView title_toolbar;
    private BlurActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOfNotifications);

        toolbar.setVisibility(View.VISIBLE);
        //arsing the font for the title toolbar
        title_toolbar=(TextView)toolbar.findViewById(R.id.toolbarTitleNotifications);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        title_toolbar.setTypeface(MyCustomFont);

        //Create and set up adapter
        NotificationListAdapter ntfListAdapter = new NotificationListAdapter(this, names, images);
        setListAdapter(ntfListAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_notifications);
        drawer.setScrimColor(Color.TRANSPARENT);
        mDrawerToggle = new BlurActionBarDrawerToggle(
                this,                    /* host Activity */
                drawer,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );

        mDrawerToggle.setRadius(25);
        mDrawerToggle.setDownScaleFactor(0.0f);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_notifications);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //The actionListener of the list. So for each notification it will have a different condition
    protected void onListItemClick(ListView l, View v, int position, long id){
        Toast.makeText(this, l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_notifications);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
