package romain.com.recycleme.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import romain.com.recycleme.R;
import romain.com.recycleme.fragment.HomeFragment;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private ArrayList<String> listIngredients;
    private HomeFragment fragment;

    public IngredientAdapter(ArrayList<String> listIngredients, HomeFragment fragment){
        this.listIngredients = listIngredients;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.activity_ingredient_row, viewGroup, false);
        return new IngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final String myIngredient = listIngredients.get(position);
        viewHolder.firstLetterIngredient.setText(String.valueOf(myIngredient.toUpperCase().charAt(0)));
        viewHolder.nameIngredient.setText(myIngredient);
        viewHolder.ingredientContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fragment.removeIngredient(position, fragment);
                Toast.makeText(fragment.getActivity(), "Ingrédient supprimé !", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listIngredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView firstLetterIngredient;
        private TextView nameIngredient;
        private LinearLayout ingredientContainer;

        ViewHolder(View itemView) {
            super(itemView);
            firstLetterIngredient = itemView.findViewById(R.id.ingredient_first_letter);
            nameIngredient = itemView.findViewById(R.id.ingredient_name);
            ingredientContainer = itemView.findViewById(R.id.ingredient_row_container);
        }
    }
}
