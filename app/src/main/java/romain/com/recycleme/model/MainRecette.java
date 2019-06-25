package romain.com.recycleme.model;

import java.util.ArrayList;

public class MainRecette {
    private ArrayList<Recette> results;

    public MainRecette(ArrayList<Recette> results) {
        this.results = results;
    }

    public ArrayList<Recette> getResults() {
        return results;
    }

    public void setResults(ArrayList<Recette> results) {
        this.results = results;
    }
}
