package com.selflearn.assignment;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="https://restcountries.eu/rest/v2/region/asia";
        RequestQueue requestQueue;

        requestQueue= Volley.newRequestQueue(this);

        loadDataFromUrl(url);

    }

    private void loadDataFromUrl(String url){

        if (isOnline()) {

            JsonArrayRequest arrayRequest = new JsonArrayRequest(url,

                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d("response_data", String.valueOf(response));
                            //setdatainviewlist(response);

                            setDataToList(response);


                            try {
                                JSONObject jsonObject = response.getJSONObject(0);
                                Log.d(TAG, "onResponse: " + jsonObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(arrayRequest);

        }else {
            Log.d(TAG, "loadDataFromUrl: " +"not online");

            // getting data offline from database....

            AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    List<OfflineData> users = AppDatabase.getDatabase(getApplicationContext()).dataDao().getAll();
                    for(OfflineData user : users) {
                        Log.d("User", user.getName());
                    }


                    // initializing recycler view
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this , RecyclerView.VERTICAL , false));
                    recyclerView.setHasFixedSize(true);

                    //making Adapter
                    CountryAdapter adapter = new CountryAdapter(MainActivity.this , users);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                }
            });

        }
        }

    private void setDataToList(JSONArray response) {
        try {

            List<RecyclerViewData> list = new ArrayList<>();
            List<OfflineData> list1 = new ArrayList<>();

            RecyclerViewData data ;
            OfflineData data1;
            for (int i = 0; i < response.length(); i++) {

                data = new RecyclerViewData();
                JSONObject object = response.getJSONObject(i);

                JSONArray array = object.getJSONArray("borders");
                String[] borders = new String[array.length()];
                for (int j=0 ; j < array.length() ; j ++)
                {
                 borders[j] = array.getString(j);
                }

                data.setBorders(borders);

                data.setName(object.getString("name"));
                data.setRegion(object.getString("region"));
                data.setCapital(object.getString("capital"));
                data.setSubregion(object.getString("subregion"));
                data.setPopulation(object.getString("population"));
                data.setFlag(object.getString("flag"));

                array = object.getJSONArray("languages");
                Languages[] languages = new Languages[array.length()];

                for (int j=0 ; j < array.length() ; j++)
                {
                    JSONObject jsonObject = array.getJSONObject(j);

                    Languages languages1 = new Languages();
                    languages1.setIso639_1(jsonObject.getString("iso639_1"));
                    languages1.setIso639_2(jsonObject.getString("iso639_2"));
                    languages1.setName(jsonObject.getString("name"));
                    languages1.setNativeName(jsonObject.getString("nativeName"));

                    Log.d(TAG, "setDataToList: " + jsonObject.getString("name"));

                    languages[j] = languages1;

                }

                data.setLanguages(languages);
                Log.d(TAG, "setDataToList: " + data.getCapital());
                list.add(data);

            }


            // initializing recycler view
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
            recyclerView.setHasFixedSize(true);

            //making Adapter

            CountryAdapter adapter = new CountryAdapter(list, MainActivity.this);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);


        }catch (Exception e)
        {
            Log.d(TAG, "onResponse: " + e.getMessage() + " "+e.getClass().getName());
        }
    }


    public boolean isOnline(){
//        Runtime runtime = Runtime.getRuntime();
//        try{
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//
//        }catch (IOException e)
//        {
//            Log.d(TAG, "isOnline: " + e.getClass().getName() + " " + e.getMessage());
//        } catch (InterruptedException e) {
//            Log.d(TAG, "isOnline: " + e.getClass().getName() + " " + e.getMessage());
//        }
//        return false;

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
