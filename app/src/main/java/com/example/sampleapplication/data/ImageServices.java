package com.example.sampleapplication.data;

import com.example.sampleapplication.modal.ImageSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageServices {
    @GET("?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&safe_search=1&text=kittens")
    Call<ImageSearchResult> searchImges(@Query("text") String imageSearchQuery);
}
