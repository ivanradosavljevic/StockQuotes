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
import com.example.ivanradosavljevic.stockquotes.logic.MyAdapter;
import com.example.ivanradosavljevic.stockquotes.logic.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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
    Gson gson;
    MySharedPreference sharedPreference;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        symbolList = new ArrayList<>();
        gson = new Gson();
        lv = (ListView) findViewById(R.id.stock_list);
        sharedPreference = new MySharedPreference(getApplicationContext());
        getSymbolListFromSharedPreference();
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

    private void saveSymbolListToSharedpreference(List<Symbol> symbolList) {
        //convert ArrayList object to String by Gson
        String jsonScore = gson.toJson(symbolList);

        //save to shared preference
        sharedPreference.saveSymbolList(jsonScore);
    }

    private void getSymbolListFromSharedPreference() {
        //retrieve data from shared preference
        String jsonScore = sharedPreference.getSymbolList();
        Type type = new TypeToken<List<Symbol>>(){}.getType();
        symbolList = gson.fromJson(jsonScore, type);
        myAdapter = new MyAdapter(symbolList,getApplicationContext());
        lv.setAdapter(myAdapter);
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
                    MyParser myParser = new MyParser(server_response);
                    MainActivity.this.symbolList = myParser.getList();

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
            lv.setAdapter(new MyAdapter(getSymbolList(), getApplicationContext()));
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

    @Override
    protected void onStop() {
        super.onStop();
        saveSymbolListToSharedpreference(symbolList);
    }
}

