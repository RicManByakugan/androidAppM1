package com.example.wm.controller.post;

import android.util.Log;

import com.example.wm.connexion.RetrofitClient;
import com.example.wm.model.Post;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControllerPost {

    public ControllerPost() {
    }

    public interface GetAllPostCallback {
        void onPostsReceived(List<Post> postList);
        void onError(String errorMessage);
    }
    public void GetAllPost(GetAllPostCallback callback) {
        Call<List<Post>> call = RetrofitClient.getApiService().getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> postList = response.body();
                    if (postList != null) {
                        callback.onPostsReceived(postList); // Pass the list of posts to the callback
                    } else {
                        callback.onError("DATA EMPTY");
                    }
                } else {
                    callback.onError("ERROR FETCH DATA");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }


    public interface GetPostCallBack{
        void onGetPostResult(String data);
    }
    public void GetPost(String _id, ControllerPost.GetPostCallBack callback) {
        Call<Post> call = RetrofitClient.getApiService().getOnePost(_id);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    Post postlist = response.body();
                    if (postlist != null){
                        String jsonResponse = new Gson().toJson(postlist);
                        Log.d("ONE POST", "JSON Response: " + jsonResponse);
                        callback.onGetPostResult(jsonResponse);
                    }else{
                        Log.d("ONE POST", "DATA EMPTY********************************************");
                    }
                }else{
                    Log.d("ONE POST", "ERROR FETCH DATA");
                }
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d("ONE POST", "ERROR CONNEXION");
            }
        });

    }
}
