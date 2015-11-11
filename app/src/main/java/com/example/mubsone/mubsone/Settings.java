package com.example.mubsone.mubsone;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mubsone.mubsone.Blur.BlurActionBarDrawerToggle;

import java.util.HashMap;

/**
 * Created by bsaraci on 11/7/2015.
 */
public class Settings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HttpAsyncResponse {
    private TextView title_toolbar;
    private BlurActionBarDrawerToggle mDrawerToggle;

    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarOfSettings);

        toolbar.setVisibility(View.VISIBLE);
        //arsing the font for the title toolbar
        title_toolbar=(TextView)toolbar.findViewById(R.id.toolbarTitleSettings);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        title_toolbar.setTypeface(MyCustomFont);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_settings);
        drawer.setScrimColor(Color.TRANSPARENT);

        mDrawerToggle = new BlurActionBarDrawerToggle(
                this,                    /* host Activity */
                drawer,                    /* DrawerLayout object */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );

        //mDrawerToggle.setRadius(25);
        //mDrawerToggle.setDownScaleFactor(0.0f);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_settings);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void updateDataCallback (View view) {
        EditText bioEditText = (EditText) findViewById(R.id.changeDescriptionText);
        EditText firstNameEditText = (EditText) findViewById(R.id.changeFirstNameText);
        EditText lastNameEditText = (EditText) findViewById(R.id.changeLastNameText);
        EditText usernameEditText = (EditText) findViewById(R.id.changeUsernameText);

        String bio = bioEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String username = usernameEditText.getText().toString();

        sendChangedData(bio, firstName, lastName, username);

        bioEditText.setText(null);
        firstNameEditText.setText(null);
        lastNameEditText.setText(null);
        usernameEditText.setText(null);

    }

    public void sendChangedData(String bio, String firstName, String lastName, String username) {

        HashMap<String, String> paramsMap = new HashMap<String, String>();

        if (bio != null) {
            paramsMap.put("biography", bio);
        } else {
            paramsMap.put("biography", null);
        }
        if (firstName != null) {
            paramsMap.put("first_name", firstName );
        } else {
            paramsMap.put("first_name", null );
        }
        if (lastName != null) {
            paramsMap.put("last_name", lastName);
        } else {
            paramsMap.put("last_name", null);
        }
        if (username != null) {
            paramsMap.put("username", username);
        } else {
            paramsMap.put("username", null);
        }


        JWTManager jwtManager = new JWTManager(getApplicationContext());
        HttpRequestParams params = new HttpRequestParams("/accounts/edit_profile/", "POST", paramsMap, jwtManager);

        HttpRequestTask request = new HttpRequestTask(params, this);
        request.execute();
    }

    public void httpRequestProcessFinish(String result)
    {
        return;
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

