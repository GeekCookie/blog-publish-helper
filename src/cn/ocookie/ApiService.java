package cn.ocookie;

import cn.ocookie.entity.Posts;
import cn.ocookie.entity.Slug;
import cn.ocookie.entity.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.*;

import java.util.HashMap;

/**
 * Created by boris on 16/10/16.
 */
public interface ApiService {

    @POST("/ghost/api/v0.1/authentication/token/")
    @FormUrlEncoded
    Call<Token> getToken(@FieldMap HashMap<String, Object> map);

    @GET("/ghost/api/v0.1/slugs/post/{slug}")
    Call<Slug> createSlug(@Path("slug") String slug);

    @POST("/ghost/api/v0.1/posts/?include=tags")
    Call<Posts> publishBlog(@Body Posts posts);

}
