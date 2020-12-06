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
        View view = LayoutInflater.from(context).inflate(R.layout.headlines_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(5));
        Glide.with(context).load(hArray.get(position).getUrlToImage()).apply(requestOptions).into(holder.himage_id);

        holder.hauthor_id.setText(hArray.get(position).getAuthor());
        holder.hpublishedAt_id.setText(hArray.get(position).getPublishedAt());
        holder.htitle_id.setText(hArray.get(position).getTitle());
        holder.hdescription_id.setText(hArray.get(position).getDescription());
        holder.hcontent_id.setText(hArray.get(position).getContent());

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
       private ImageView himage_id;
       private TextView hauthor_id,hpublishedAt_id,htitle_id,hdescription_id,hcontent_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            himage_id = itemView.findViewById(R.id.himage_id);
            hauthor_id = itemView.findViewById(R.id.hauthor_id);
            hpublishedAt_id = itemView.findViewById(R.id.hpublishedAt_id);
            htitle_id = itemView.findViewById(R.id.htitle_id);
            hdescription_id = itemView.findViewById(R.id.hdescription_id);
            hcontent_id = itemView.findViewById(R.id.hcontent_id);


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
