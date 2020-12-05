package com.example.livenewsapp.Fragments.HeadLines;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.livenewsapp.R;

import java.util.List;

public class HeadLinesAdapter extends RecyclerView.Adapter<HeadLinesAdapter.ViewHolder> {

    private Context context;
    private List<Article> hArray;

    HeadLinesAdapter(Context context, List<Article> hArray){
        this.context = context;
        this.hArray = hArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.everything_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(5));
        Glide.with(context).load(hArray.get(position).getUrlToImage()).apply(requestOptions).into(holder.image_id);

        holder.author_id.setText(hArray.get(position).getAuthor());
        holder.publishedAt_id.setText(hArray.get(position).getPublishedAt());
        holder.title_id.setText(hArray.get(position).getTitle());
        holder.description_id.setText(hArray.get(position).getDescription());
        holder.content_id.setText(hArray.get(position).getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = hArray.get(position).getUrl();
                custome_webView(url);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private ImageView image_id;
       private TextView author_id,publishedAt_id,title_id,description_id,content_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_id = itemView.findViewById(R.id.image_id);
            author_id = itemView.findViewById(R.id.author_id);
            publishedAt_id = itemView.findViewById(R.id.publishedAt_id);
            title_id = itemView.findViewById(R.id.title_id);
            description_id = itemView.findViewById(R.id.description_id);
            content_id = itemView.findViewById(R.id.content_id);


        }
    }
    private void custome_webView(String url){
        Uri uri = Uri.parse(url);
        CustomTabsIntent.Builder intent_bulder = new CustomTabsIntent.Builder();
        intent_bulder.setShowTitle(true);
        intent_bulder.setToolbarColor(ContextCompat.getColor(context, R.color.blue));
        intent_bulder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.design_default_color_primary_dark));
        intent_bulder.setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        CustomTabsIntent customTabsIntent = intent_bulder.build();
        customTabsIntent.intent.setPackage("com.android.chrome");
        customTabsIntent.launchUrl(context, uri);

    }
}
