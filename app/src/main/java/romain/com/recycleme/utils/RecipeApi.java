package romain.com.recycleme.utils;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import romain.com.recycleme.model.MainRecette;

public interface RecipeApi {

    class Factory {
        public static RecipeApi create() {
            return RetrofitHelper.buildRetrofit().create(RecipeApi.class);
        }
    }

    @GET("/api/")
    Call<MainRecette> getRecipes(@Query("q") String nameRecette);

    @GET("/api/")
    Call<MainRecette> getRecipesWithIngredient(@Query("i") String nameRecette);
}