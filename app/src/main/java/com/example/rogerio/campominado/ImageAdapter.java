package com.example.rogerio.campominado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by ROGERIO on 01/12/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private Integer[] list;

    public ImageAdapter(Context c, Integer[] list) {
        context = c;
    }


    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position,View convertView, ViewGroup viewGroup) {
        ImageView img = new ImageView(context);
        img.setImageResource(list[position]);
        img.setAdjustViewBounds(true);

        return img;
    }




}
