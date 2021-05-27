package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView imageView2;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView2=findViewById(R.id.imageView2);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ///////////////////////////////Retrofit Section/////////////////////////////////////////////////
        /*****************************************************************************************************/
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://recycleapi.epizy.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<List<Information>> call=apiInterface.Get_All_Users();

        call.enqueue(new Callback<List<Information>>() {
            @Override
            public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                Log.d("Output",response.body().get(0).getFirst_name());
            }

            @Override
            public void onFailure(Call<List<Information>> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
      /*****************************************************************************************************/
       imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, update.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}