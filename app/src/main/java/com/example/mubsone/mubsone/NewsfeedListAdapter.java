package com.example.mubsone.mubsone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bsaraci on 10/18/2015.
 */
public class NewsfeedListAdapter extends ArrayAdapter<String> {
    Context c;
    String [] username;
    String [] songTitle;
    String [] rating;
    int [] userImages;
    LayoutInflater inflater;

    public NewsfeedListAdapter(Context context, String [] username, String [] songTitle, String [] rating, int [] userImages) {
        super(context, R.layout.newsfeed_row_model, username);

        this.c=context;
        this.username=username;
        this.songTitle=songTitle;
        this.rating=rating;
        this.userImages=userImages;

    }

    public class ViewHolder{
        TextView uName;
        TextView sTitle;
        TextView rate;
        ImageView img;
    }

    public View getView (int position, View convertView, ViewGroup parent){

        //Check if the View is null. If so it will create it
        if(convertView==null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.newsfeed_row_model,null);
        }
        else{
            ViewHolder holder = new ViewHolder();
            holder.uName=(TextView)convertView.findViewById(R.id.username);
            holder.sTitle=(TextView)convertView.findViewById(R.id.songTitle);
            holder.rate=(TextView)convertView.findViewById(R.id.rates);
            holder.img=(ImageView)convertView.findViewById(R.id.userImageButton);

            holder.uName.setText(username[position]);
            holder.sTitle.setText(songTitle[position]);
            holder.rate.setText(rating[position]);
            holder.img.setImageResource(userImages[position]);
        }

        return convertView;
    }
}
