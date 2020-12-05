package com.example.livenewsapp.Fragments.Sources;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livenewsapp.R;

import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {

    public Context context;
    public List<SourceResponce.SourceData> data_array;


   public SourcesAdapter(Context context,List<SourceResponce.SourceData> data_array){

        this.context = context;
        this.data_array = data_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sourceitem_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name_id.setText(data_array.get(position).getName());
        holder.category_id.setText(data_array.get(position).getCategory()+",");
        holder.language_id.setText("Language : "+data_array.get(position).getLanguage());
        holder.country_id.setText(data_array.get(position).getCountry());
        holder.description_id.setText(data_array.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = data_array.get(position).getUrl();
               custome_webView(url);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout item_layout_id;
        TextView name_id,category_id,language_id,country_id,description_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_layout_id = itemView.findViewById(R.id.item_layout_id);
            name_id = itemView.findViewById(R.id.name_id);
            category_id = itemView.findViewById(R.id.category_id);
            language_id = itemView.findViewById(R.id.language_id);
            country_id = itemView.findViewById(R.id.country_id);
            description_id = itemView.findViewById(R.id.description_id);
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
