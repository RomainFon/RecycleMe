package romain.com.recycleme.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by romai on 26/03/2019.
 */

public class Recette extends RealmObject implements Comparable<Recette>{

    private String title;
    @PrimaryKey
    private String href;
    private String ingredients;
    private String thumbnail;
    private int pourcentage;

    public Recette() {
    }

    public Recette(String title, String href, String ingredients, String thumbnail) {
        this.title = title;
        this.href = href;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
        this.pourcentage = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }


    @Override
    public int compareTo(Recette o) {
        Integer pourcentage1 = o.getPourcentage();
        Integer pourcentage2 = this.getPourcentage();
        return pourcentage1.compareTo(pourcentage2);
    }
}
