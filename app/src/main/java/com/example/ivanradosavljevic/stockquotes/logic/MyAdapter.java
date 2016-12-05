package com.example.ivanradosavljevic.stockquotes.logic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ivanradosavljevic.stockquotes.R;
import com.example.ivanradosavljevic.stockquotes.domain.Symbol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Ivan Radosavljevic on 3.12.2016.
 */
public class MyAdapter extends BaseAdapter {

    List<Symbol> symbolList;
    Context myContext;

    public MyAdapter(List<Symbol> symbolList, Context myContext) {
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
            convertView = View.inflate(myContext, R.layout.activity_listview_relative, null);
            holder = new ViewHolder();
            holder.itemName = (TextView) convertView.findViewById(R.id.name);
            holder.itemChangePersent = (TextView) convertView.findViewById(R.id.change_percent);
            holder.itemLast = (TextView) convertView.findViewById(R.id.last);
            if (myContext.getResources().getConfiguration().orientation == 2) {
                holder.itemDateTime = (TextView) convertView.findViewById(R.id.date_time);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemName.setText(symbolList.get(i).getName());
        holder.itemChangePersent.setText(MyNumberFormatter.myFormatter(symbolList.get(i).getQuoteChangePercent(), 1)+ "%");
        if (myContext.getResources().getConfiguration().orientation == 2) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String date = dateFormat.format(symbolList.get(i).getDateTime());
            holder.itemDateTime.setText(date.toString());
        }
        holder.itemChangePersent.setTextColor(SetColor.set(Double.parseDouble(symbolList.get(i).getQuoteChangePercent())));
        holder.itemLast.setText(symbolList.get(i).getQuoteLast());
        return convertView;
    }

    static class ViewHolder {
        TextView itemName;
        TextView itemChangePersent;
        TextView itemLast;
        TextView itemDateTime;
    }
}
