package cn.ocookie;

import cn.ocookie.entity.ConstantData;
import cn.ocookie.entity.Posts;
import cn.ocookie.entity.Slug;
import cn.ocookie.entity.Token;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static cn.ocookie.entity.ConstantData.*;

/**
 * @author 几颗小饼干
 * @despcription The blog publish helper
 */
public class BlogHelper {

    public static final Gson gson = new Gson();
    public static Token token;

    public static void main(String[] args) {
        // write your code here
        final String slug = "机器人发布测试" + System.currentTimeMillis();
        final BlogHelper helper = new BlogHelper();

        final Posts posts = new Posts();
        Posts.Post post = new Posts.Post();
        post.setTitle(slug);
        post.setSlug(slug);
        post.setMarkdown("机器人发布测试,这是机器人的测试");
        post.setStatus("published");
        ArrayList<Posts.Post> posts1 = new ArrayList<Posts.Post>();
        posts1.add(post);
        posts.setPosts(posts1);

        helper.login("zhibt_com@163.com", "domybest1", new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> tokenCall, Response<Token> tokenResponse) {
                if (tokenResponse != null && tokenResponse.body() != null) {
                    token = tokenResponse.body();
                    helper.publishBlog(slug, posts);
                } else {
                    System.out.println(tokenResponse.raw());

                }
            }

            @Override
            public void onFailure(Call<Token> tokenCall, Throwable throwable) {
                throwable.printStackTrace();
                System.out.println("login fail");

            }
        });
    }

    public void login(String uid, String pwd, Callback<Token> callback) {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("grant_type", grant_type);
        hashMap.put("username", uid);
        hashMap.put("password", pwd);
        hashMap.put("client_id", client_id);
        hashMap.put("client_secret", client_secret);
        BlogApi.getService().getToken(hashMap).enqueue(callback);
    }

    public void publishBlog(String slug, final Posts posts) {
        BlogApi.getService().createSlug(slug).enqueue(new Callback<Slug>() {
            @Override
            public void onResponse(Call<Slug> slugCall, Response<Slug> slugResponse) {
                if (slugResponse != null && slugResponse.isSuccessful()) {
                    System.out.println("create slug success");
                    BlogApi.getService().publishBlog(new Gson().toJson(posts)).enqueue(new Callback<Posts>() {
                        @Override
                        public void onResponse(Call<Posts> postsCall, Response<Posts> postsResponse) {
                            // success
                            if (postsResponse != null && postsResponse.body() != null) {
                                System.out.println(gson.toJson(postsResponse.body()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Posts> postsCall, Throwable throwable) {
                            System.out.println("post fail");
                            throwable.printStackTrace();
                        }
                    });
                } else {
                    System.out.println(slugResponse.raw());
                }

            }

            @Override
            public void onFailure(Call<Slug> slugCall, Throwable throwable) {
                System.out.println("slug fail");

                throwable.printStackTrace();
            }
        });
    }

}
