package com.example.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.R;
import com.example.weatherapp.model.City;

import java.util.ArrayList;

public class CityListViewAdapter extends ArrayAdapter<City> {
    private Context context;
    private ArrayList<City> listCities, filteredList;
    private int resource;
    public CityListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<City> listCities) {
        super(context, resource, listCities);
        this.context = context;
        this.resource = resource;
        this.listCities = listCities;
        this.filteredList = listCities;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        City city = filteredList.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tvName.setText(city.getName());

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String keyword = constraint.toString();
                if(!keyword.isEmpty()){
                    filteredList = new ArrayList<>();
                    for(City city : listCities){
                        if(city.getName().toLowerCase().contains(keyword.toLowerCase())){
                            filteredList.add(city);
                        }
                    }
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return filteredList.get(position).getId();
    }
    @Override
    public int getCount() {
        return filteredList.size();
    }

    class ViewHolder{
        TextView tvName;
        public ViewHolder(View view) {
            tvName = view.findViewById(R.id.tvName);
        }
    }
}
