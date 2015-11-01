package com.example.mubsone.mubsone;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class LogIn extends AppCompatActivity {
    //declare all the TextViewer on the main page
    private TextView [] id_text= new TextView [4];
    private EditText [] id_edit=new EditText [5];
    private String [] String_id_edit=new String [id_edit.length];
    private Button logIn;
    private User newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //main for the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app);

        change_fonts();
        press_the_button(false);  //error here //next time i need to solve
        check_interface();

    }
    //assign all the edit text for the sing in
    public void sing_in_now(boolean x) {
        //?! assign easy
        if (x){
            id_text[2].setTextColor(getResources().getColor(R.color.white));
            id_text[1].setTextColor(getResources().getColor(R.color.white_log));
            id_edit [0]=(EditText)findViewById(R.id.edittext0);
            id_edit [1]=(EditText)findViewById(R.id.edittext1);
            id_edit [2]=(EditText)findViewById(R.id.edittext2);
            id_edit [3]=(EditText)findViewById(R.id.edittext3);
            id_edit [4]=(EditText)findViewById(R.id.edittext4);
            id_edit[2].setHint(getResources().getString(R.string.hint_fistname));
            id_edit[3].setHint(getResources().getString(R.string.hint_lastname));
            id_edit[4].setHint(getResources().getString(R.string.hint_email));
            for (int i=2; i<id_edit.length; i++){
                id_edit[i].setVisibility(View.VISIBLE);
            }
            logIn.setText(getResources().getString(R.string.sign_up));
        }else {
            id_text[1].setTextColor(getResources().getColor(R.color.white));
            id_text[2].setTextColor(getResources().getColor(R.color.white_log));
            for (int i=0; i<2; i++){
                id_edit[i].setVisibility(View.VISIBLE);
            }
            for (int i=2; i<id_edit.length; i++){
                id_edit[i].setVisibility(View.GONE);
            }
            logIn.setText(getResources().getString(R.string.log_in));
        }
    }
    public boolean check_interface() {
        boolean x = true;
        //for the log in text
        id_text [2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sing_in_now(true);
                press_the_button(true);
            }
        });
        //for the sign in text
        id_text [1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sing_in_now(false);
                press_the_button(false);
            }
        });
        return x;
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
        logIn= (Button)findViewById(R.id.button0);
    }
    //optimize  later :-//
    public void press_the_button(boolean x) {
        if (x) {
            logIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //start a new layout for you
                    //for (int i=0; i<id_edit.length; i++){
                        //String_id_edit [i]= id_edit[i].getText().toString();
                    //}
                    //just a test
                    //NewUser=new User(1, String_id_edit [0] , String_id_edit [1] ,  String_id_edit [2],   String_id_edit [3] , String_id_edit [4], 0 , 0," " ,false , false, false);

                    //Test of connect method (performPostCall)
                    String requestUrl = "http://10.0.2.2:8000/accounts/login";
                    String username = (String)findViewById(R.id.edittext0).getTag();
                    String password = (String)findViewById(R.id.edittext1).getTag();

                    HashMap<String, String> postDataParams = new HashMap<String, String>(2);
                    postDataParams.put("username",username);
                    postDataParams.put("password",password);

                    newUser.performPostCall(requestUrl,postDataParams);

                    Intent next_layout = new Intent(getApplicationContext(), Newsfeed.class);
                    onPause();
                    startActivity(next_layout);
                }
            });
        }else {
            //take the user things
            logIn.setOnClickListener(new View.OnClickListener() {
                //the servers information still here
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
}
