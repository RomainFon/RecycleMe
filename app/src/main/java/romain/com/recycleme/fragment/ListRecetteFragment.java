package romain.com.recycleme.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import romain.com.recycleme.R;
import romain.com.recycleme.adapter.RecetteAdapter;
import romain.com.recycleme.model.MainRecette;
import romain.com.recycleme.model.Recette;
import romain.com.recycleme.utils.ActivityUtils;
import romain.com.recycleme.utils.RecipeApi;

public class ListRecetteFragment extends Fragment{

    private String listName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_recettes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listName = ListRecetteFragment.this.getArguments().getString("name");
        TextView textViewTitleRecetteList = view.findViewById(R.id.textViewTitleRecetteList);
        textViewTitleRecetteList.setText(listName);

        if(this.getArguments().getString("name").equals(getString(R.string.list_name_recette_name))) {
            RecipeApi.Factory.create().getRecipes(this.getArguments().getString("search")).enqueue(new Callback<MainRecette>() {
                @Override
                public void onResponse(Call<MainRecette> call, Response<MainRecette> response) {
                    ArrayList<Recette> listRecettes = response.body().getResults();
                    RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favoris);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListRecetteFragment.this.getContext()));
                    recyclerView.setAdapter(new RecetteAdapter(listRecettes, ListRecetteFragment.this));
                }

                @Override
                public void onFailure(Call<MainRecette> call, Throwable t) {
                }
            });
        }else if(this.getArguments().getString("name").equals(getString(R.string.list_name_recette_ing))){
            final String request = this.getArguments().getString("search");
            RecipeApi.Factory.create().getRecipesWithIngredient(this.getArguments().getString("search")).enqueue(new Callback<MainRecette>() {
                @Override
                public void onResponse(Call<MainRecette> call, Response<MainRecette> response) {
                    ArrayList<Recette> listRecettes = response.body().getResults();
                    for (Recette object: listRecettes) {
                        int pourcentage = 0;
                        List<String> ingredientsRecette = Arrays.asList(object.getIngredients().split("\\s*,\\s*"));
                        List<String> ingredientsSearch = Arrays.asList(request.split("\\s*,\\s*"));
                        pourcentage = Math.round(ingredientsSearch.size() * 100 / ingredientsRecette.size());
                        object.setPourcentage(pourcentage);
                    }
                    Collections.sort(listRecettes);
                    RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favoris);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ListRecetteFragment.this.getContext()));
                    recyclerView.setAdapter(new RecetteAdapter(listRecettes, ListRecetteFragment.this));
                }

                @Override
                public void onFailure(Call<MainRecette> call, Throwable t) {
                }
            });
        }else{
            RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favoris);
            recyclerView.setLayoutManager(new LinearLayoutManager(ListRecetteFragment.this.getContext()));
            recyclerView.setAdapter(new RecetteAdapter(getAllRecettesBdd(), ListRecetteFragment.this));
        }
    }

    private ArrayList<Recette> getAllRecettesBdd(){
        Realm mRealmInstance = Realm.getDefaultInstance();
        RealmQuery query = mRealmInstance.where(Recette.class);
        ArrayList<Recette> recettes = new ArrayList<Recette>(mRealmInstance.copyFromRealm(query.findAll()));
        return recettes;
    }
}
