package com.example.automatedsystem;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Units extends AppCompatActivity {

    String DB_URL = "jdbc:mysql://78.47.183.52:3306//attendance";
    String USER = "root";
    String  PASS = "Abc.123!";
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.units_layout);
        new AsyncFetch().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncFetch extends AsyncTask<String, String, String> {
        final ProgressDialog pdLoading = new ProgressDialog(Units.this);
        HttpURLConnection conn;
        Connection url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                url = DriverManager.getConnection(DB_URL, USER, PASS);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return e.toString();
            }
            /*try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

             */
            try {

                int response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            pdLoading.dismiss();
            List <UnitsList> unitsList = new ArrayList<>();

            //pdLoading.dismiss();
            try {

                JSONArray array = new JSONArray(result);

                for(int i = 0;i < array.length();i++){
                    JSONObject units = array.getJSONObject(i);

                    unitsList.add(new UnitsList(
                            units.getString("unitcode"),
                            units.getString("unitname"),
                            units.getString("lecturer")
                    //unitList.add(fishData)
                    ));
                }

                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                UnitsAdapter mAdapter = new UnitsAdapter(Units.this, unitsList);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Units.this));

            } catch (JSONException e) {
                Toast.makeText(Units.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}