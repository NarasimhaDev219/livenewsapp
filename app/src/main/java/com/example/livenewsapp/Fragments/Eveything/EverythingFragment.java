package com.example.livenewsapp.Fragments.Eveything;

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

public class EverythingFragment extends Fragment {

    private List<Article> eArrayList ;
    private RecyclerView recyclererthing_id;
    private ProgressBar progressbar_id;
    private EverythingAdapter everythingAdapter;

    public EverythingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_everything, container, false);

        recyclererthing_id = view.findViewById(R.id.recyclererthing_id);
        progressbar_id = view.findViewById(R.id.progressbar_id);
        recyclererthing_id.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclererthing_id.setLayoutManager(manager);


        getEverythingUrlData();

        return view;
    }
    private void getEverythingUrlData() {

        try {
            RequestInterface requestInterface = ApiClient.retrofit.create(RequestInterface.class);
            Call<Everything> responceCall = requestInterface.getEveryThingData();
            responceCall.enqueue(new Callback<Everything>() {
                @Override
                public void onResponse(Call<Everything> call, Response<Everything> response) {
                    progressbar_id.setVisibility(View.VISIBLE);
                    System.out.println("=f===ghgdgfgas========" + call + ","+response);
                    eArrayList = new ArrayList<>();
                    eArrayList.clear();
                    Everything everything = response.body();
//

                    if (everything != null){
                        String results = String.valueOf(everything.getTotalResults());
                        String status  = everything.getStatus();
                        eArrayList = everything.getArticles();
                        for (int i=0;i<= eArrayList.size();i++){
                            everythingAdapter = new EverythingAdapter(getContext(),eArrayList);
                            everythingAdapter.notifyDataSetChanged();
                            recyclererthing_id.setAdapter(everythingAdapter);
                            progressbar_id.setVisibility(View.GONE);
                        }
                    }else{
                        progressbar_id.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                        Toast.makeText(getContext(),"Server Not Responding",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Everything> call, Throwable t) {
                    Toast.makeText(getContext(),"Responce Error",Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}