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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mubsone.mubsone.Blur.BlurActionBarDrawerToggle;

import java.util.HashMap;

public class CoverPage extends ListActivity implements NavigationView.OnNavigationItemSelectedListener,HttpAsyncResponse{
    private TextView title_toolbar;
    private BlurActionBarDrawerToggle mDrawerToggle;
    private User u;

    String [] username ={"jv21", "AndyMatrix", "brajansaraci", "mikelv92"};
    String [] songTitle ={"Grizzly Bear", "Hotline Bling", "I ran away", "Sex on fire"};
    String [] rating ={"5", "3.5", "4", "3"};
    int [] images = {R.mipmap.example_ic_user, R.mipmap.ic_new_user, R.mipmap.ic_new_user, R.mipmap.ic_new_user};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coverpage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOfCoverpage);

        toolbar.setVisibility(View.VISIBLE);
        //arsing the font for the title toolbar
        title_toolbar=(TextView)toolbar.findViewById(R.id.toolbarTitleCoverpage);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        title_toolbar.setTypeface(MyCustomFont);

        CoverpageListAdapter nwsfListAdapter = new CoverpageListAdapter(this, username, songTitle, rating, images);
        setListAdapter(nwsfListAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_newsfeed);
        drawer.setScrimColor(Color.TRANSPARENT);
        mDrawerToggle = new BlurActionBarDrawerToggle(
                this,                    /* host Activity */
                drawer,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );

        mDrawerToggle.setRadius(25);
        mDrawerToggle.setDownScaleFactor(0.0f);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_coverpage);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void logOut()
    {
        HttpRequestParams params = new HttpRequestParams("/accounts/logout/", "GET", null, null);

        HttpRequestTask request = new HttpRequestTask(params, this);
        request.execute();
    }

    public void httpRequestProcessFinish(String result)
    {
        return;
    }

    @Override
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
            logOut();
            Intent next_layout = new Intent(getApplicationContext(), LogIn.class);
            onPause();
            startActivity(next_layout);

        } else if (id == R.id.nav_marketplace) {
            Intent next_layout = new Intent(getApplicationContext(), Marketplace.class);
            onPause();
            startActivity(next_layout);
        }
        else if (id == R.id.nav_settings) {
            Intent next_layout = new Intent(getApplicationContext(), Settings.class);
            onPause();
            startActivity(next_layout);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_newsfeed);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
