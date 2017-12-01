package com.example.rogerio.campominado;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by ROGERIO on 01/12/2017.
 */

public class GridAdapter extends BaseAdapter {

    private Context mContext;

    // references to our images
    private Integer[] mThumbIds;

    public GridAdapter(Context c, Integer[] list) {
        mContext = c;
        mThumbIds = list;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img = new ImageView(mContext);
        img.setImageResource(R.drawable.button);
        img.setAdjustViewBounds(true);

        return img;
    }


}
