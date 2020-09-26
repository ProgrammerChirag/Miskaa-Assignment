package com.selflearn.assignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    List<RecyclerViewData> list;
    Context context;
    List<OfflineData> data;

    private static final String TAG = "CountryAdapter";

    public CountryAdapter(List<RecyclerViewData> list, Context context)
    {
        this.list = list;
        this.context = context;
        data = new ArrayList<>();


        manipulateAndInsert(list , data);
    }

    public CountryAdapter(Context context , List<OfflineData> data)
    {
        this.data = data;
        this.context = context;

    }


    private void manipulateAndInsert(List<RecyclerViewData> list, final List<OfflineData> data) {

        for (RecyclerViewData recyclerViewData : list)
        {
            OfflineData data1  = new OfflineData();

            data1.setName(recyclerViewData.getName());
            data1.setFlag(recyclerViewData.getFlag());
            data1.setCapital(recyclerViewData.getFlag());
            data1.setPopulation(Long.parseLong(recyclerViewData.getPopulation()));
            data1.setSubregion(recyclerViewData.getSubregion());
            data1.setRegion(recyclerViewData.getRegion());

            StringBuilder stringBuilder = new StringBuilder();

            Languages[] languages = recyclerViewData.getLanguages();
            for (Languages languages1 : languages) {
                stringBuilder.append("name: ").append(languages1.getName()).append("nativeName: ").append(languages1.getNativeName()).append("iso639_1: ").append(languages1.getIso639_1()).append("iso639_2: ").append(languages1.getIso639_2()).append("\n");
            }
            data1.setLanguages(String.valueOf(stringBuilder));
            StringBuilder stringBuilder1 = new StringBuilder();


            for (String str : recyclerViewData.getBorders()) {
                    stringBuilder1.append(str).append(" ");
            }

            data1.setBorders(String.valueOf(stringBuilder1));

            data.add(data1);

        }
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getDatabase(context.getApplicationContext()).dataDao().deleteAll();
            }
        });

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (OfflineData data1 : data)
                {
                    AppDatabase.getDatabase(context.getApplicationContext()).dataDao().insertAll(data1);
                }
            }
        });
    }


    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_recycler_view ,parent , false);

        return  new CountryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, final int position) {


        SvgLoader.pluck()
                .with(((Activity)(context)))
                .setPlaceHolder(R.drawable.download , R.drawable.download)
                .load(data.get(position).getFlag() , holder.flag);


        Log.d(TAG, "onBindViewHolder: " + data.get(position).getCapital());

//        Languages[] languages = list.get(position).getLanguages();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (Languages languages1 : languages) {
//            stringBuilder.append("name: ").append(languages1.getName()).append("nativeName: ").append(languages1.getNativeName()).append("iso639_1: ").append(languages1.getIso639_1()).append("iso639_2: ").append(languages1.getIso639_2()).append("\n");
//        }

        holder.languages.setText(String.format("Languages :\n%s", data.get(position).getLanguages()));

        holder.population.setText("Current population: "+data.get(position).getPopulation());
        holder.name.setText(data.get(position).getName());
        holder.region.setText("Region: "+data.get(position).getRegion());
        holder.capital.setText("Capital: "+data.get(position).getCapital());
        holder.subRegion.setText("SubRegion: "+data.get(position).getSubregion());


//        StringBuilder stringBuilder1  = new StringBuilder();
//
//        for (String str : list.get(position).getBorders())
//        {
//            stringBuilder1.append(str).append(" ");
//        }

        holder.border.setText("Borders: " + data.get(position).getBorders());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deleteOperation(data , data.get(position) , context);
                data.remove(data.get(position));
                notifyDataSetChanged();

                deleteOperation(data , data.get(position) , context);
            }
        });


    }

    private void deleteOperation(final List<OfflineData> data, OfflineData offlineData, final Context context) {
//        data.remove(offlineData);
//        data.notify();

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getDatabase(context.getApplicationContext()).dataDao().deleteAll();
            }
        });

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (OfflineData data1 : data)
                {
                    AppDatabase.getDatabase(context.getApplicationContext()).dataDao().insertAll(data1);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView name , capital , region, subRegion , population , border , languages;
        ImageView deleteBtn;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flag);
            name = itemView.findViewById(R.id.countryName);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subRegion = itemView.findViewById(R.id.subRegion);
            population  = itemView.findViewById(R.id.CountryPopulation);
            border = itemView.findViewById(R.id.borders);
            languages = itemView.findViewById(R.id.languages);

            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
