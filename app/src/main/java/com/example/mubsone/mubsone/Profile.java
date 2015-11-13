package com.example.mubsone.mubsone;
import org.json.*;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mubsone.mubsone.Blur.BlurActionBarDrawerToggle;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemClickListener,
        HttpAsyncResponse {
    private TextView title_toolbar;
    private BlurActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        JWTManager jwtManager = new JWTManager(getApplicationContext());
        HttpRequestParams params = new HttpRequestParams("/accounts/profile/", "GET", null, jwtManager);
        HttpRequestTask request = new HttpRequestTask(params, this);
        request.execute();
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOfProfile);

        toolbar.setVisibility(View.VISIBLE);
        //arsing the font for the title toolbar
        title_toolbar=(TextView)toolbar.findViewById(R.id.toolbarTitleProfile);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        title_toolbar.setTypeface(MyCustomFont);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        drawer.setScrimColor(Color.TRANSPARENT);

        mDrawerToggle = new BlurActionBarDrawerToggle(
                this,                    /* host Activity */
                drawer,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );

        //mDrawerToggle.setRadius(25);
        //mDrawerToggle.setDownScaleFactor(0.0f);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profile);
        navigationView.setNavigationItemSelectedListener(this);

        GridView myGrid = (GridView)findViewById(R.id.gridViewProfile);
        myGrid.setAdapter(new ProfileGridAdapter(this));
        myGrid.setOnItemClickListener(this);


    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
        Toast.makeText(this, adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    public void httpRequestProcessFinish(String result)
    {
        Log.i("Res", result);
        String fansNumber   = null;
        String username     = null;
        String rating       = null;
        String videos       = null;
        String contests     = null;
        String name         = null;

        try
        {
            JSONObject jsonResponse = new JSONObject(result);

            fansNumber  = jsonResponse.getString("fans");
            username    = jsonResponse.getString("username");
            rating      = jsonResponse.getString("rating");
            videos      = jsonResponse.getString("videos");
            contests    = jsonResponse.getString("contests");
            name        = jsonResponse.getString("first_name") + jsonResponse.getString("last_name");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        TextView fansText       = (TextView) findViewById(R.id.fansText);
        TextView usernameText   = (TextView) findViewById(R.id.usernameTextProfile);
        TextView ratingsText    = (TextView) findViewById(R.id.ratesTextProfile);
        TextView videosText     = (TextView) findViewById(R.id.numberOfVideosText);
        TextView contestsText   = (TextView) findViewById(R.id.contestsWonText);
        TextView bio            = (TextView) findViewById(R.id.descriptionProfile);

        fansText.setText(fansNumber);
        usernameText.setText(username);
        ratingsText.setText(rating);
        videosText.setText(videos);
        contestsText.setText(contests);
        bio.setText(name);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
