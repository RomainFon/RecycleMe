package romain.com.recycleme.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import io.realm.Realm;
import io.realm.RealmModel;
import romain.com.recycleme.MainActivity;
import romain.com.recycleme.R;
import romain.com.recycleme.adapter.RecetteAdapter;
import romain.com.recycleme.model.Recette;
import romain.com.recycleme.utils.ActivityUtils;

public class DetailRecette extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_recette, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Gson gson = new Gson();
        final Recette recette = gson.fromJson(this.getArguments().getString("recette"), Recette.class);

        TextView nameRecetteDetail = view.findViewById(R.id.name_recette_detail);
        nameRecetteDetail.setText(recette.getTitle());

        Button btnAddFavoris = view.findViewById(R.id.btn_add_favoris);
        btnAddFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecetteInDb(recette);
                Toast.makeText(DetailRecette.this.getContext(), "Recette ajouter au favoris !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addRecetteInDb(Recette recette){
        Realm mRealmInstance = Realm.getDefaultInstance();
        mRealmInstance.beginTransaction();
        try{
            mRealmInstance.copyToRealmOrUpdate(recette);
            mRealmInstance.commitTransaction();
        }catch(Exception e){
            String a = "";
        }

    }
}
