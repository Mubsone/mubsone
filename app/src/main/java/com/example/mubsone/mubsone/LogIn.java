package com.example.mubsone.mubsone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.HashMap;

public class LogIn extends AppCompatActivity implements HttpAsyncResponse {
    //declare all the TextViewer on the main page
/*
    private TextView [] id_text= new TextView [4];
    private EditText [] id_edit=new EditText [5];
    private String [] String_id_edit=new String [id_edit.length];
    private Button logIn;
    private User newUser;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //main for the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app);

/*
        change_fonts();
        press_the_button(false);  //error here //next time i need to solve
        check_interface();
*/

    }

    public void loginHomeButtonCallback(View view)
    {
        Intent intent = new Intent(this, CoverPage.class);

        EditText usernameEditText = (EditText) findViewById(R.id.usernameLoginPageEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordLoginPageEditText);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        logIn(username, password);

        startActivity(intent);
    }

    public void logIn(String username, String password)
    {
        HashMap<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("username", username);
        paramsMap.put("password", password);
        HttpRequestParams params = new HttpRequestParams("/accounts/login/", "POST", paramsMap);

        HttpRequestTask request = new HttpRequestTask(params, this);
        request.execute();
    }

    public void httpRequestProcessFinish(String result)
    {
        return;
    }

    //assign all the edit text for the sing in
/*
    public void sing_in_now(boolean x) {
        //?! assign easy
        if (x){
            id_text[2].setTextColor(getResources().getColor(R.color.white));
            id_text[1].setTextColor(getResources().getColor(R.color.white_log));
            id_edit [0]=(EditText)findViewById(R.id.loginUsernameHomeEditText);
            id_edit [1]=(EditText)findViewById(R.id.passwordHomeEditText);
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
        id_text [0] = (TextView) findViewById(R.id.mubsoneHomeHeader);
        id_text [1] = (TextView) findViewById(R.id.loginHomeTextView);
        id_text [2] = (TextView) findViewById(R.id.signupHomeButton);
        id_text [3] = (TextView) findViewById(R.id.text4);
        Typeface MyCustomFont = Typeface.createFromAsset(getAssets(),"fonts/dear_joe.ttf");
        for (int i=0; i<id_text.length; i++) {
            id_text[i].setTypeface(MyCustomFont);
        }
        logIn= (Button)findViewById(R.id.loginHomeButton);
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
                    String requestUrl = "http://10.0.2.2:8000/accounts/login/";
                    String username = (String) findViewById(R.id.loginUsernameHomeEditText).getTag();
                    String password = (String) findViewById(R.id.passwordHomeEditText).getTag();

                    try {
                        URL url = new URL(requestUrl);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        conn.setReadTimeout(10000);
                        conn.setConnectTimeout(15000);
                        conn.setRequestMethod("GET");
                        conn.setDoInput(true);
                        conn.setDoOutput(true);

                        String response = "";

                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(os, "UTF-8"));
                        HashMap<String, String> params = new HashMap<String, String>();

                        params.put("username", username);
                        params.put("password", password);

                        writer.write(getPostData(params));


                        writer.flush();
                        writer.close();
                        os.close();
                        int responseCode = conn.getResponseCode();

                        if (responseCode == HttpsURLConnection.HTTP_OK) {
                            String line;
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            while ((line = br.readLine()) != null) {
                                response += line;
                            }
                        } else {
                            response = "";
                        }
                        Log.i("Response", response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
    private String getPostData(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
*/
}
