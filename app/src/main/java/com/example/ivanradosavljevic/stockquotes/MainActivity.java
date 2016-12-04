package com.example.ivanradosavljevic.stockquotes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ivanradosavljevic.stockquotes.domain.Symbol;
import com.example.ivanradosavljevic.stockquotes.logic.MyParser;
import com.example.ivanradosavljevic.stockquotes.logic.MyPortraiAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    List<Symbol> symbolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        symbolList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.stock_list);
        setsetRepeatingAsyncTask();
        
    }

    private void setsetRepeatingAsyncTask() {


        final android.os.Handler handler = new android.os.Handler();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            new Operation().execute("http://www.teletrader.com/downloads/symbols.xml");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 5*60*1000);

    }

    public List<Symbol> getSymbolList() {
        return symbolList;
    }

    private class Operation extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        String server_response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Please Wait");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                    //DOM PARSER!!!
                    MyParser myParser = new MyParser(server_response);
                    symbolList = myParser.getList();

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            for (int i = 0; i < symbolList.size(); i++) {
                Log.e("Quotes", Double.toString(symbolList.get(i).getQuoteChangePercent()));
            }
            lv.setAdapter(new MyPortraiAdapter(getSymbolList(), getApplicationContext()));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Symbol clickedSymbol = getSymbolList().get(i);
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("clickedSymbol", clickedSymbol);
                    startActivity(intent);
                }
            });

        }
    }

// Converting InputStream to String

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}

