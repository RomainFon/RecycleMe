package romain.com.recycleme.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import romain.com.recycleme.R;
import romain.com.recycleme.adapter.IngredientAdapter;
import romain.com.recycleme.adapter.RecetteAdapter;
import romain.com.recycleme.utils.ActivityUtils;

public class HomeFragment extends Fragment{

    private ArrayList<String> listIngredients = new ArrayList<>();
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnSearchRecette = view.findViewById(R.id.btn_search_recette);
        final EditText searchRecetteName = view.findViewById(R.id.search_recette_name);
        btnSearchRecette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = searchRecetteName.getText().toString();
                ListRecetteFragment fragment = new ListRecetteFragment();
                Bundle bundleFragment = new Bundle();
                bundleFragment.putString("name", getResources().getString(R.string.list_name_recette_name));
                bundleFragment.putString("search", search);
                fragment.setArguments(bundleFragment);
                ActivityUtils.addFragmentToActivity(HomeFragment.this, fragment, R.id.home_linear_layout, "favoris");
            }
        });

        Button btnSearchRecetteIngredient = view.findViewById(R.id.btn_search_recette_ingredient);
        btnSearchRecetteIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = TextUtils.join(",",listIngredients);
                ListRecetteFragment fragment = new ListRecetteFragment();
                Bundle bundleFragment = new Bundle();
                bundleFragment.putString("name", getResources().getString(R.string.list_name_recette_ing));
                bundleFragment.putString("search", search);
                fragment.setArguments(bundleFragment);
                ActivityUtils.addFragmentToActivity(HomeFragment.this, fragment, R.id.home_linear_layout, "favoris");
            }
        });


        Button btnAddIngredient = view.findViewById(R.id.btn_add_ingredient);
        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Ajouter un ingrédient");
                final EditText edittext = new EditText(view.getContext());
                alert.setView(edittext);
                alert.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String myIngredient = edittext.getText().toString();
                        listIngredients.add(myIngredient);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view_ingredients);
        recyclerView.setLayoutManager(new GridLayoutManager(HomeFragment.this.getContext(), 3));
        recyclerView.setAdapter(new IngredientAdapter(listIngredients, this));


    }

    public static void removeIngredient(int position, HomeFragment fragment){
        fragment.listIngredients.remove(position);
        fragment.recyclerView.getAdapter().notifyDataSetChanged();
    }
}