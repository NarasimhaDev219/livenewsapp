package com.example.livenewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.livenewsapp.Adapters.TabViewPagerAdapter;
import com.example.livenewsapp.Fragments.Sources.SourceResponce;
import com.example.livenewsapp.Retrofit.ApiClient;
import com.example.livenewsapp.Retrofit.RequestInterface;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Head lines,every thing,Sources

    private TabLayout tabLayout_id;
    private ViewPager viewpager_id;
    private TabViewPagerAdapter adapter;
    private List<SourceResponce.SourceData> sArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // tabaddAdapter();
        initiate();

    }

    private void initiate() {
        if (isNetworkAvailable()) {
         //   network_check.setVisibility(View.GONE);
            tabaddAdapter();
        } else
        {
          //  network_check.setVisibility(View.VISIBLE);
           alertDialog(MainActivity.this);
        }
    }
    private void tabaddAdapter(){
        tabLayout_id = findViewById(R.id.tabLayout_id);
        viewpager_id = findViewById(R.id.viewpager_id);

        adapter = new TabViewPagerAdapter(getSupportFragmentManager());
        viewpager_id.setAdapter(adapter);

        tabLayout_id.setupWithViewPager(viewpager_id);
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    public void alertDialog(MainActivity view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("You're offline..");
        builder.setMessage("Data not Available on your Device");
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // tabaddAdapter();
                initiate();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
        builder.create().show();
    }

}