package com.example.sideproject_04.SearchStudy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sideproject_04.R;

import java.util.ArrayList;
import java.util.List;
public class SearchStudy_MyAdapter extends RecyclerView.Adapter<SearchStudy_MyAdapter.MyHolder>  implements Filterable ,View.OnClickListener {

    Context context;
    List<SearchStudy_FruitsData> list = new ArrayList<>();
    List<SearchStudy_FruitsData> list1 = new ArrayList<>();

    public SearchStudy_MyAdapter(Context context, List<SearchStudy_FruitsData> list) {
        this.context = context;
        this.list = list;
        this.list1 = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.searchstudy_item_row,viewGroup,false);
        view.setId(i);
        view.setOnClickListener(this);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.setId(i);
        myHolder.linearlayout.setId(i);
//        myHolder.imageView.setImageResource(list.get(i).getImage());
        Glide.with(context)
                .load(list.get(i).getImage())
                .into(myHolder.imageView);
        myHolder.textView.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void onClick(View view) {
        Log.d("222", "onClick: "+view.getId());
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        LinearLayout linearlayout;
        ImageView imageView;
        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            linearlayout = itemView.findViewById(R.id.searchstudy_lst_ll);
            imageView = itemView.findViewById(R.id.searchstudy_lst_img_view);
            textView = itemView.findViewById(R.id.searchstudy_lst_text_view);
        }

        public void setId(int i) {
            itemView.setId(i);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()){
                    list = list1;
                }else{

                    List<SearchStudy_FruitsData> filterList = new ArrayList<>();

                    for (SearchStudy_FruitsData data : list1){

                        if (data.getName().toLowerCase().contains(charString)){
                            filterList.add(data);
                        }
                    }

                    list = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                list = (List<SearchStudy_FruitsData>) results.values;
                notifyDataSetChanged();
            }
        };

    }
}