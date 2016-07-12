package com.example.shreekrishna.movierating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<String> mArrayListNames;
    ArrayAdapter mAdapter;
    ImageView poster;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  mListView= (ListView) findViewById(R.id.lvmovies);
      //  mArrayListNames =new ArrayList<String>();
       // poster= (ImageView) findViewById(R.id.ivposter);
        mRecyclerView=(RecyclerView)findViewById(R.id.moviesList);



        //why there was a need of layout manager? what is role of manager in setting CustomAdaptor?
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);






        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.omdbapi.com/?s=Batman&page=2", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Gson gson = new Gson();

                String jsonresponse = new String(responseBody); // why resopnseBody?

                Response response = gson.fromJson(jsonresponse,Response.class);
                // i created variable of Response class which accepts Response Object i.e java Object
                // so with the help of gson i converted json pojo to java object
                // but why i used responseBody?

                ArrayList<Response.SearchBean> list = (ArrayList<Response.SearchBean>) response.getSearch();
                //Doubt what we did in this line?

              //  for (Response.SearchBean s : list)
              //  {
              //      mArrayListNames.add(s.getTitle());
                    // here i loaded mArraylistNames with Movies name ie adding string elements inside ArrayList
              //  }

              //  mAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, mArrayListNames);
                // intansiated Arrayadptor with movie names i.e loaded adaptor with movie names

               // mListView.setAdapter(mAdapter);

               // Picasso.with(MainActivity.this).load(list.get(0).getPoster()).into(poster);
                //in above line , i said in MainActivity load batman poster into the image  view
                //load(inside list get 0 th location and of it get poster state) this will give back
                // url of batman poster like this you can call for 1st 2nd and so onn

                CustomAdaptor customAdaptor = new CustomAdaptor(MainActivity.this,list);
                mRecyclerView.setAdapter(customAdaptor);










            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }
}
