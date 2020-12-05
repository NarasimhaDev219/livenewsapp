package com.example.livenewsapp.Retrofit;

import com.example.livenewsapp.Fragments.Eveything.Everything;
import com.example.livenewsapp.Fragments.HeadLines.HeadLines;
import com.example.livenewsapp.Fragments.Sources.SourceResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface RequestInterface {

    @GET("sources?apiKey=c6b66ad2d4fd4cafb451ee8292e1ba25")
    @Streaming
    Call<SourceResponce> getSourceData();

    @GET("everything?q=bitcoin&apiKey=c6b66ad2d4fd4cafb451ee8292e1ba25")
    @Streaming
    Call<Everything> getEveryThingData();

    @GET("top-headlines?country=us&apiKey=c6b66ad2d4fd4cafb451ee8292e1ba25")
    @Streaming
    Call<HeadLines> getHeadLinesData();

}
