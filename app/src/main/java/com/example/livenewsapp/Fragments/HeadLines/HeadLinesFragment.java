package com.example.livenewsapp.Fragments.HeadLines;

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


public class HeadLinesFragment extends Fragment {

    private RecyclerView recyclerheadline_id;
    private ProgressBar progressbar_id;
    private List<Article> headLine_array;
    private HeadLinesAdapter headLinesAdapter;

    public HeadLinesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_head_lines, container, false);

        recyclerheadline_id = view.findViewById(R.id.recyclerheadline_id);
        progressbar_id = view.findViewById(R.id.progressbar_id);
        recyclerheadline_id.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerheadline_id.setLayoutManager(manager);
        System.out.println("=======asdjdjfjladjflsf+=============");
        getHeadLinesUrlData();
        return view;
    }
    private void getHeadLinesUrlData() {

        try {
            System.out.println("=======asdjdjfbsjladjflsf+=============");
            RequestInterface requestInterface = ApiClient.retrofit.create(RequestInterface.class);
            Call<HeadLines> responceCall = requestInterface.getHeadLinesData();
            responceCall.enqueue(new Callback<HeadLines>() {
                @Override
                public void onResponse(Call<HeadLines> call, Response<HeadLines> response) {
                    progressbar_id.setVisibility(View.VISIBLE);
                    System.out.println("=======asdjdjfbsjladjflsf+============="+response);
                    headLine_array = new ArrayList<>();
                    headLine_array.clear();
                    HeadLines headLines_responce = response.body();
//                    System.out.println("==sjdjfbasjdjbf==========="+status);

                    if (response != null){
                        String results = String.valueOf(headLines_responce.getTotalResults());
                        String status  = headLines_responce.getStatus();
                        headLine_array = headLines_responce.getArticles();
                        System.out.println("=======asdjdjfbsjladjflsf+============="+headLine_array);
                        for (int i=0;i<= headLine_array.size();i++){
                            headLinesAdapter = new HeadLinesAdapter(getContext(),headLine_array);
                            recyclerheadline_id.setAdapter(headLinesAdapter);
                          //  everythingAdapter = new EverythingAdapter(getContext(),eArrayList);
                          //  recyclererthing_id.setAdapter(everythingAdapter);
                            progressbar_id.setVisibility(View.GONE);
                        }
                    }

                }

                @Override
                public void onFailure(Call<HeadLines> call, Throwable t) {
                    Toast.makeText(getContext(),"Responce Error",Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}