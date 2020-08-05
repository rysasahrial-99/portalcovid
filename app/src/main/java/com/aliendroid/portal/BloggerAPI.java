package com.aliendroid.portal;

import com.aliendroid.portal.model.PostList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerAPI {



    public static PostService postService = null;

    public static PostService getService()
    {
        if (postService == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(Pengaturan.ID_BLOGSPOT)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

            postService = retrofit.create(PostService.class);
        }
        return postService;

    }


    public interface PostService {
        @GET("?maxResults=500&key=" + Pengaturan.API_KEY_BLOGSPOT)
        Call<PostList> getPostList();
    }
}

