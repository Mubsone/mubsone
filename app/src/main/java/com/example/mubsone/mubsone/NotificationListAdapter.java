package com.example.mubsone.mubsone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bsaraci on 10/16/2015.
 */
public class NotificationListAdapter extends ArrayAdapter<String> {
    Context c;
    String [] names;
    int [] images;
    LayoutInflater inflater;

    public NotificationListAdapter(Context context, String [] names, int [] images) {
        super(context, R.layout.notification_row_model,names);

        this.c=context;
        this.names=names;
        this.images=images;

    }

    //This inner class is holding our views
    public class ViewHolder{
        TextView nameTv;
        ImageView img;
    }

    public View getView (int position, View convertView, ViewGroup parent){

        //Check if the View is null. If so it will create it
        if(convertView==null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.notification_row_model,null);
        }
        else{
            ViewHolder holder = new ViewHolder();
            holder.nameTv=(TextView)convertView.findViewById(R.id.nameTv);
            holder.img=(ImageView)convertView.findViewById(R.id.imageView1);

            holder.nameTv.setText(names[position]);
            holder.img.setImageResource(images[position]);
        }

        return convertView;
    }
}
