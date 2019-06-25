package romain.com.recycleme.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import io.realm.Realm;
import romain.com.recycleme.R;
import romain.com.recycleme.fragment.DetailRecette;
import romain.com.recycleme.fragment.ListRecetteFragment;
import romain.com.recycleme.model.Recette;
import romain.com.recycleme.utils.ActivityUtils;

public class RecetteAdapter extends RecyclerView.Adapter<RecetteAdapter.ViewHolder> {

    private ArrayList<Recette> recettes;
    private ListRecetteFragment fragment;


    public RecetteAdapter(ArrayList<Recette> recettes, ListRecetteFragment fragment){
        this.recettes = recettes;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.activity_recette_row, viewGroup, false);
        return new RecetteAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Recette recette = recettes.get(position);
        String title = recette.getTitle();
        if(title.length() > 23){
            title = title.substring(0, 23).concat("...");
        }
        viewHolder.recetteName.setText(title);
        if(recette.getPourcentage() != 0){
            viewHolder.recettePourcentage.setText(String.valueOf(recette.getPourcentage()));
            viewHolder.recettePourcentage.setTextColor(Color.GREEN);
            if(recette.getPourcentage() < 75){
                viewHolder.recettePourcentage.setTextColor(Color.YELLOW);
            }
            if(recette.getPourcentage() < 50){
                viewHolder.recettePourcentage.setTextColor(Color.RED);
            }
        }

        viewHolder.recetteContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailRecette fragment = new DetailRecette();
                Bundle bundleFragment = new Bundle();
                Gson gson = new Gson();
                bundleFragment.putString("recette", gson.toJson(recette));
                fragment.setArguments(bundleFragment);
                ActivityUtils.addFragmentToActivity(RecetteAdapter.this.fragment, fragment, R.id.recettes_show_linearlayout, "favoris");
            }
        });
    }

    @Override
    public int getItemCount() {
        return recettes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView recetteName;
        private ImageView recetteImg;
        private LinearLayout recetteContainer;
        private TextView recettePourcentage;


        ViewHolder(View itemView) {
            super(itemView);
            recetteName = itemView.findViewById(R.id.recette_name);
            recetteContainer = itemView.findViewById(R.id.recette_row_container);
            recetteImg = itemView.findViewById(R.id.recette_row_image);
            recettePourcentage = itemView.findViewById(R.id.recette_pourcentage);
        }
    }

}
