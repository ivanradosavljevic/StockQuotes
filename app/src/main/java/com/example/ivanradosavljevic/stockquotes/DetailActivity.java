package com.example.ivanradosavljevic.stockquotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ivanradosavljevic.stockquotes.domain.Symbol;
import com.example.ivanradosavljevic.stockquotes.logic.MyNumberFormatter;
import com.example.ivanradosavljevic.stockquotes.logic.SetColor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Ivan Radosavljevic on 3.12.2016.
 */
public class DetailActivity extends AppCompatActivity {
    TextView last;
    TextView change;
    TextView changePersent;
    TextView dateTime;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent intent = getIntent();
        Symbol symbol = (Symbol) intent.getSerializableExtra("clickedSymbol");
        actionBar = getSupportActionBar();
        actionBar.setTitle(symbol.getName().toString());
        actionBar.setDisplayHomeAsUpEnabled(true);

        last = (TextView) findViewById(R.id.detail_last);
        change = (TextView) findViewById(R.id.detail_change);
        changePersent = (TextView) findViewById(R.id.detail_change_percent);
        dateTime = (TextView) findViewById(R.id.detail_date_time);

        last.setText(MyNumberFormatter.myFormatter(symbol.getQuoteLast(),2).toString()+ " USD");
        last.setTextColor(SetColor.set(Double.parseDouble(symbol.getQuoteLast())));
        change.setText(MyNumberFormatter.myFormatter(symbol.getQuoteChange(),2).toString());
        change.setTextColor(SetColor.set(Double.parseDouble(symbol.getQuoteChange())));
        changePersent.setText(MyNumberFormatter.myFormatter(symbol.getQuoteChangePercent(),2).toString());
        changePersent.setTextColor(SetColor.set(Double.parseDouble(symbol.getQuoteChangePercent())));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.");
        dateTime.setText(dateFormat.format(symbol.getDateTime()));

        if(getResources().getConfiguration().orientation == 2){
            TextView open = (TextView) findViewById(R.id.detail_open);
            TextView high = (TextView) findViewById(R.id.detail_high);
            TextView low = (TextView) findViewById(R.id.detail_low);
            TextView volume = (TextView) findViewById(R.id.detail_voulme);
            TextView bid = (TextView) findViewById(R.id.detail_bid);
            TextView ask = (TextView) findViewById(R.id.detail_ask);

            open.setText(MyNumberFormatter.myFormatter(symbol.getQuoteOpen(),2).toString());
            high.setText(MyNumberFormatter.myFormatter(symbol.getQuoteHigh(),2).toString());
            low.setText(MyNumberFormatter.myFormatter(symbol.getQuoteLow(),2).toString());
            volume.setText(symbol.getQuoteVolume().toString());
            bid.setText(MyNumberFormatter.myFormatter(symbol.getQuoteBid(),2).toString());
            ask.setText(MyNumberFormatter.myFormatter(symbol.getQuoteAsk(),2).toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
