package com.example.mubsone.mubsone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
        View base;
        TextView uName;
        TextView sTitle;
        TextView rate;
        ImageButton img;

        ViewHolder(View base){
            this.base=base;
        }

        TextView getUsername(){
            if(uName==null){
                uName=(TextView)base.findViewById(R.id.username);
            }
            return uName;
        }

        TextView getSongTitle(){
            if(sTitle==null){
                sTitle=(TextView)base.findViewById(R.id.songTitleInNewsfeed);
            }
            return  sTitle;
            }

        TextView getRate(){
            if(rate==null){
                rate=(TextView)base.findViewById(R.id.numberOfRatesNewsfeed);
            }
            return rate;
        }

        ImageButton getUserImg(){
            if(img==null){
                img=(ImageButton)base.findViewById(R.id.profilePictureInNewsfeed);
            }
            return img;
        }

    }

    public View getView (int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        //Check if the View is null. If so it will create it
        if(convertView==null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.newsfeed_row_model,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.getUsername().setText(username[position]);
        holder.getSongTitle().setText(songTitle[position]);
        holder.getRate().setText(rating[position]);
        holder.getUserImg().setImageResource(userImages[position]);
        return convertView;
    }
}
