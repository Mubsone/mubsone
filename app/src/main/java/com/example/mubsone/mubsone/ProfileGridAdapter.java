package com.example.mubsone.mubsone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by bsaraci on 10/22/2015.
 */
public class ProfileGridAdapter extends BaseAdapter {
    ArrayList<gridElement> list;
    Context context;

    ProfileGridAdapter(Context context){
        list=new ArrayList<gridElement>();
        int [] images = {R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon,R.drawable.description_icon};
        this.context=context;
        for (int i = 0; i<images.length; i++ ){
            gridElement element = new gridElement(images[i]);
            list.add(element);
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.profile_grid_element,parent,false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        holder.img.setImageResource(list.get(position).imageId);
        return row;
    }

    class gridElement {
        int imageId;

        gridElement(int imageId){
            this.imageId=imageId;
        }
    }

    class ViewHolder {
        ImageView img;

        ViewHolder(View v){
            img = (ImageView)v.findViewById(R.id.gridImageInProfile);
        }
    }
}
