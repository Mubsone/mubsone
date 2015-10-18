package com.example.mubsone.mubsone;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;


public class Notifications extends ListActivity {
    String [] names = {"comment_icon", "description_icon", "see_icon", "separator_icon", "share_icon", "user_icon", "viewer_icon"};
    int [] images = {R.drawable.comment_icon, R.drawable.description_icon, R.drawable.see_icon, R.drawable.separator_icon, R.drawable.share_icon, R.drawable.user_icon, R.drawable.viewer_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen aplication
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        //Create and set up adapter

        NotificationListAdapter ntfListAdapter = new NotificationListAdapter(this, names, images);
        setListAdapter(ntfListAdapter);

    }


    //The actionListener of the list. So for each notification it will have a different condition
    protected void onListItemClick(ListView l, View v, int position, long id){
        Toast.makeText(this, l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }
}
