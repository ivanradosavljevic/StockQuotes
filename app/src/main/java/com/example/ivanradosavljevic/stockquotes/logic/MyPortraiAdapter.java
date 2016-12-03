package com.example.ivanradosavljevic.stockquotes.logic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ivanradosavljevic.stockquotes.R;
import com.example.ivanradosavljevic.stockquotes.domain.Symbol;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Ivan Radosavljevic on 3.12.2016.
 */
public class MyPortraiAdapter extends BaseAdapter {

    List<Symbol> symbolList;
    Context myContext;

    public MyPortraiAdapter(List<Symbol> symbolList, Context myContext) {
        this.symbolList = symbolList;
        this.myContext = myContext;
    }

    @Override
    public int getCount() {
        return symbolList.size();
    }

    @Override
    public Object getItem(int i) {
        return symbolList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(myContext, R.layout.activity_listview, null);
            holder = new ViewHolder();
            holder.itemName = (TextView) convertView.findViewById(R.id.name);
            holder.itemChangePersent = (TextView) convertView.findViewById(R.id.change_persnet);
            holder.itemLast = (TextView) convertView.findViewById(R.id.last);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemName.setText(symbolList.get(i).getName());
        holder.itemChangePersent.setText(Double.toString(symbolList.get(i).getQuoteChangePercent())+"%");
        if(symbolList.get(i).getQuoteChangePercent()<0){
            holder.itemChangePersent.setTextColor(Color.RED);
        } else {
            holder.itemChangePersent.setTextColor(Color.GREEN);
        }
        holder.itemLast.setText(Double.toString(symbolList.get(i).getQuoteLast()));
        return convertView;
    }

    static class ViewHolder {
        TextView itemName;
        TextView itemChangePersent;
        TextView itemLast;
    }
}
