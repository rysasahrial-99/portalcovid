package com.aliendroid.portal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.aliendroid.covid17.R;
import com.aliendroid.portal.model.Item;


import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;



public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;

    public static ArrayList<Item> items;
    public static ArrayList<Item> mFilteredList;

    public PostAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
        this.mFilteredList = items;

    }

    @NotNull
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, final int position) {
        final Item item = mFilteredList.get(position);
        holder.postTitle.setText(item.getTitle());


        Document document = Jsoup.parse(item.getContent());
        holder.postDescription.setText(document.text());
        Elements elements = document.select("img");
        Glide.with(context).load(elements.get(0).attr("src")).into(holder.postImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
            return mFilteredList == null ? 0 : mFilteredList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postTitle;
        TextView postDescription;
        public Button favoriteImg;

        public PostViewHolder(View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.postImage);
            postTitle = itemView.findViewById(R.id.postTitle);
            postDescription = itemView.findViewById(R.id.postDescription);
            favoriteImg = (Button) itemView.findViewById(R.id.imageView5);


        }
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = items;
                } else {

                    ArrayList<Item> filteredList = new ArrayList<>();

                    for (Item androidVersion : mFilteredList) {

                        if (androidVersion.getTitle().toLowerCase().contains(charString)|| androidVersion.getContent().toLowerCase().contains(charString)|| androidVersion.getId().toLowerCase().contains(charString)) {

                           filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }




}
