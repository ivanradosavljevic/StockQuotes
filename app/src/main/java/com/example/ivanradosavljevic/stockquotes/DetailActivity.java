package com.example.ivanradosavljevic.stockquotes;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ivanradosavljevic.stockquotes.domain.Symbol;

/**
 * Created by Ivan Radosavljevic on 3.12.2016.
 */
public class DetailActivity extends AppCompatActivity {
    TextView title;
    TextView last;
    TextView change;
    TextView changePersent;
   // ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent intent = getIntent();
        Symbol symbol = (Symbol) intent.getSerializableExtra("clickedSymbol");
        //actionBar.setTitle(symbol.getName().toString());
       // actionBar.setDisplayHomeAsUpEnabled(true);

        title = (TextView) findViewById(R.id.name);
        last = (TextView) findViewById(R.id.detail_last);
        change = (TextView) findViewById(R.id.change);
        changePersent = (TextView) findViewById(R.id.change_persnet_detail);

        title.setText(symbol.getName().toString());
        last.setText(Double.toString(symbol.getQuoteLast()).toString());
        change.setText(Double.toString(symbol.getQuoteChange()).toString());
        changePersent.setText(Double.toString(symbol.getQuoteChangePercent()).toString());
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
