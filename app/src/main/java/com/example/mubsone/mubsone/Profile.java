package com.example.mubsone.mubsone;
import org.json.*;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mubsone.mubsone.Blur.BlurActionBarDrawerToggle;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemClickListener,
        HttpAsyncResponse {
    private BlurActionBarDrawerToggle mDrawerToggle;
    private TextView title_toolbar, fansText, usernameText, ratingsText, videosText, contestsText, bio;
    private ImageView number_video_imageview, contest_imageview, fans_imageview, star_imageview;
    private ImageButton profil_imageview;
    private Button become_a_fan_button;
    private Boolean butoon_fan_clicked;
    private GoogleApiClient client;

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
        title_toolbar = (TextView) toolbar.findViewById(R.id.toolbarTitleProfile);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(), "fonts/dear_joe.ttf");
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

        GridView myGrid = (GridView) findViewById(R.id.gridViewProfile);
        myGrid.setAdapter(new ProfileGridAdapter(this));
        myGrid.setOnItemClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(this, adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }

    public void httpRequestProcessFinish(String result) {
        Log.i("Res", result);
        String fansNumber = null;
        String username = null;
        String rating = null;
        String videos = null;
        String contests = null;
        String name = null;

        try {
            JSONObject jsonResponse = new JSONObject(result);

            fansNumber = jsonResponse.getString("fans");
            username = jsonResponse.getString("username");
            rating = jsonResponse.getString("rating");
            videos = jsonResponse.getString("videos");
            contests = jsonResponse.getString("contests");
            name = jsonResponse.getString("first_name") + jsonResponse.getString("last_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fansText = (TextView) findViewById(R.id.fansText);
        usernameText = (TextView) findViewById(R.id.usernameTextProfile);
        ratingsText = (TextView) findViewById(R.id.ratesTextProfile);
        videosText = (TextView) findViewById(R.id.numberOfVideosText);
        contestsText = (TextView) findViewById(R.id.contestsWonText);
        bio = (TextView) findViewById(R.id.descriptionProfile);

        fansText.setText("Andy");
        usernameText.setText(username);
        ratingsText.setText(rating);
        videosText.setText(videos);
        contestsText.setText(contests);
        bio.setText(name);


        //change the font for the page
        change_fonts();
        //show the text description under the icons
        show_text_description();
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

    public void change_fonts() {
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(), "fonts/dear_joe.ttf");
        fansText.setTypeface(MyCustomFont);
        usernameText.setTypeface(MyCustomFont);
        ratingsText.setTypeface(MyCustomFont);
        videosText.setTypeface(MyCustomFont);
        contestsText.setTypeface(MyCustomFont);
    }

    public void show_text_description() {
        number_video_imageview = (ImageView) findViewById(R.id.numberOfVideosIcon);
        contest_imageview = (ImageView) findViewById(R.id.contestsWonIcon);
        profil_imageview = (ImageButton) findViewById(R.id.profilePictureInProfile);
        fans_imageview = (ImageView) findViewById(R.id.fansIcon);
        star_imageview = (ImageView) findViewById(R.id.averageRatesIcon);
        become_a_fan_button = (Button) findViewById(R.id.btn_be_a_fan);
        number_video_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Test_Video", Toast.LENGTH_SHORT).show();
            }
        });
        contest_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Test_Contest", Toast.LENGTH_SHORT).show();
            }
        });
        profil_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Test_Profill", Toast.LENGTH_SHORT).show();
            }
        });
        fans_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Test_Fans", Toast.LENGTH_SHORT).show();
            }
        });
        star_imageview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Test_Stars", Toast.LENGTH_SHORT).show();
            }
        });
        //for now we will take false but for the future we need to put in the value into the server
        butoon_fan_clicked=false;
        become_a_fan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (butoon_fan_clicked){
                    become_a_fan_button.setText(getResources().getString(R.string.become_a_fan));
                    become_a_fan_button.setBackgroundResource(R.drawable.custom_button_become_a_fan);
                    Toast.makeText(Profile.this , "Test_You just Unsubscribe", Toast.LENGTH_SHORT).show();
                    butoon_fan_clicked=false;
                }else{
                    become_a_fan_button.setText(getResources().getString(R.string.you_are_a_fan));
                    become_a_fan_button.setBackgroundResource(R.drawable.custom_button_you_are_a_fan);
                    Toast.makeText(Profile.this , "Test_You just become a fan", Toast.LENGTH_SHORT).show();
                    butoon_fan_clicked=true;
                }

            }
        });

    }
}
