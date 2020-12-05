package com.example.livenewsapp.Fragments.Sources;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.livenewsapp.R;
import com.example.livenewsapp.Retrofit.ApiClient;
import com.example.livenewsapp.Retrofit.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesFragment extends Fragment {

    private List<SourceResponce.SourceData> sArrayList ;
    private SourcesAdapter sourcesAdapter;
    private RecyclerView recycler_id;
    private ProgressBar progressbar_id;

    public SourcesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);

        recycler_id = view.findViewById(R.id.recycler_id);
        progressbar_id = view.findViewById(R.id.progressbar_id);
        recycler_id.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recycler_id.setLayoutManager(manager);

        getUrlData();

        return view;
    }

    private void getUrlData() {
        try {
            RequestInterface requestInterface = ApiClient.retrofit.create(RequestInterface.class);
            Call<SourceResponce> responceCall = requestInterface.getSourceData();
            responceCall.enqueue(new Callback<SourceResponce>() {
                @Override
                public void onResponse(Call<SourceResponce> call, Response<SourceResponce> response) {

                    progressbar_id.setVisibility(View.VISIBLE);
                    sArrayList = new ArrayList<>();
                    sArrayList.clear();

                    SourceResponce sourceResponce = response.body();

                    if (response != null){
                        sArrayList = sourceResponce.getData();
                        for (int i=0;i<= sArrayList.size();i++){
                            sourcesAdapter = new SourcesAdapter(getContext(),sArrayList);
                            recycler_id.setAdapter(sourcesAdapter);
                            progressbar_id.setVisibility(View.GONE);

                        }
                    }
                }

                @Override
                public void onFailure(Call<SourceResponce> call, Throwable t) {
                    Toast.makeText(getContext(),"Responce Error",Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}