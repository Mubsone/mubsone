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
public class MarketplaceListAdapter extends ArrayAdapter<String> {
    Context c;
    String [] itemNames;
    int [] itemPhotos;
    LayoutInflater inflater;

    public MarketplaceListAdapter(Context context, String [] itemNames, int []itemPhotos) {

        super(context, R.layout.marketplace_row_model,itemNames);

        this.c=context;
        this.itemNames=itemNames;
        this.itemPhotos=itemPhotos;

    }

    //This inner class is holding our views
    public class ViewHolder{
        View base;
        TextView name=null;
        ImageView img=null;

        ViewHolder(View base){
            this.base=base;
        }

        TextView getLabel(){
            if(name==null){
                name=(TextView)base.findViewById(R.id.itemNameMarketplace);
            }
            return name;
        }

        ImageView getImg(){
            if(img==null){
                img=(ImageView)base.findViewById(R.id.itemPhotoMarketplace);
            }
            return  img;
        }
    }

    public View getView (int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        //Check if the View is null. If so it will create it
        if(convertView==null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.marketplace_row_model,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.getLabel().setText(itemNames[position]);
        holder.getImg().setImageResource(itemPhotos[position]);
        return convertView;
    }
}
