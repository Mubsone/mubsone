package com.example.mubsone.mubsone;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {
    //declare all the TextViewer on the main page
    private TextView [] id_text= new TextView [4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //main for the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app);

        //subprograms
        change_fonts();
        press_the_button();
        click_text();
    }
    public void click_text() {
        //for the log in text
        id_text [2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_text[2].setTextColor(getResources().getColor(R.color.white));
                id_text[1].setTextColor(getResources().getColor(R.color.white_log));
            }
        });
        //for the sign in text
        id_text [1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_text[1].setTextColor(getResources().getColor(R.color.white));
                id_text[2].setTextColor(getResources().getColor(R.color.white_log));
            }
        });
    }
    //optimize the setting here.
    public void change_fonts(){
        id_text [0] = (TextView) findViewById(R.id.text0);
        id_text [1] = (TextView) findViewById(R.id.text2);
        id_text [2] = (TextView) findViewById(R.id.text3);
        id_text [3] = (TextView) findViewById(R.id.text4);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        for (int i=0; i<id_text.length; i++) {
            id_text[i].setTypeface(MyCustomFont);
        }
    }
    //press the button to log in
    public void press_the_button() {
        Button log_in_now= (Button)findViewById(R.id.button0);
        log_in_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start a new layout for you
                Intent next_layout = new Intent(getApplicationContext(), Newsfeed.class);
                onPause();
                startActivity(next_layout);
            }
        });

    }
}
