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
        View base;
        TextView nameTv=null;
        ImageView img=null;

        ViewHolder(View base){
            this.base=base;
        }

        TextView getLabel(){
            if(nameTv==null){
                nameTv=(TextView)base.findViewById(R.id.textNotification);
            }
            return nameTv;
        }

        ImageView getImg(){
            if(img==null){
                img=(ImageView)base.findViewById(R.id.profilePictureInNotifications);
            }
            return  img;
        }
    }

    public View getView (int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        //Check if the View is null. If so it will create it
        if(convertView==null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.notification_row_model,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
            holder.getLabel().setText(names[position]);
            holder.getImg().setImageResource(images[position]);
        return convertView;
    }
}
