package com.example.rogerio.campominado.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rogerio.campominado.menu.Menu_Item;
import com.example.rogerio.campominado.R;

import java.util.List;

/**
 * Created by ROGERIO on 01/09/2017.
 */

public class MenuAdapter extends BaseAdapter {

    private Context context;
    private List<Menu_Item> itens;

    public MenuAdapter(Context context, List<Menu_Item> itens) {
        this.context = context;
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Menu_Item menuItem = itens.get(position);

        View linha = LayoutInflater.from(context).inflate(R.layout.menu_line,null);

        ImageView img = (ImageView) linha.findViewById(R.id.menuIcon);
        TextView txt = (TextView) linha.findViewById(R.id.menuName);
        txt.setText(menuItem.getNome());

        //Image
        String uri = "@drawable/"+ menuItem.getIcon();  // where myresource (without the extension) is the file

        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        img.setImageDrawable(res);

        return linha;
    }
}
