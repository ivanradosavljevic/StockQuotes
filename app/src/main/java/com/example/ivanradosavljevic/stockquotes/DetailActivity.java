package com.example.ivanradosavljevic.stockquotes;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ivanradosavljevic.stockquotes.domain.Symbol;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        changePersent = (TextView) findViewById(R.id.detail_change_persent);
        dateTime = (TextView) findViewById(R.id.detail_date_time);

        last.setText(Double.toString(symbol.getQuoteLast()).toString());
        change.setText(Double.toString(symbol.getQuoteChange()).toString());
        changePersent.setText(Double.toString(symbol.getQuoteChangePercent()).toString());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.");
        dateTime.setText(dateFormat.format(symbol.getDateTime()));
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
