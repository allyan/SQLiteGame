package com.example.allyan.sqlitegame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Allyan on 28/04/2017.
 */

public class GamersAdapter extends ArrayAdapter<GamerInfo> {

    private Context context;
    private ArrayList<GamerInfo> gamers;

    public GamersAdapter(Context context, ArrayList<GamerInfo> gamers) {
        super(context, R.layout.list_item, gamers);
        this.context = context;
        this.gamers = gamers;
    }

    private class ViewHolder {
        TextView nameTV;
        TextView timeTV;
        TextView clicksTV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.list_item_name);
            viewHolder.timeTV = (TextView) convertView.findViewById(R.id.list_item_time);
            viewHolder.clicksTV = (TextView) convertView.findViewById(R.id.list_item_clicks);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameTV.setText(gamers.get(position).getName());
        viewHolder.timeTV.setText(gamers.get(position).getTime());
        viewHolder.clicksTV.setText(gamers.get(position).getClickes() + "");
        return convertView;
    }
}
