package cn.ocookie;

import cn.ocookie.entity.Posts;
import cn.ocookie.entity.Slug;
import cn.ocookie.entity.Token;
import retrofit2.Callback;
import retrofit2.http.*;

import java.util.HashMap;

/**
 * Created by boris on 16/10/16.
 */
public interface ApiService {

    @POST("/ghost/api/v0.1/authentication/token")
    @FormUrlEncoded
    void getToken(@QueryMap HashMap map, Callback<Token> tokenCallback);

    @GET("/ghost/api/v0.1/slugs/post/{slug}")
    void createSlug(@Path("slug") String slug, Callback<Slug> slugCallback);

    @POST("/ghost/api/v0.1/posts/?include=tags")
    void publishBlog(@Body String body, Callback<Posts> postsCallback);

}
