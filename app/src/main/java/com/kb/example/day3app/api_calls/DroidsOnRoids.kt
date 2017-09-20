package com.kb.example.day3app.api_calls

import com.kb.example.day3app.model.Post
import com.kb.example.day3app.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Karol on 2017-09-20.
 */
interface DroidsOnRoids {
    /*
    https://thedroidsonroids.com/wp-json/wp/v2/posts?search=bruno
    https://thedroidsonroids.com/wp-json/wp/v2/users/1001049
     */
    @GET("posts")
    fun getPosts(@Query("search") searchString: String): Observable<List<Post>>

    @GET("users/{id}")
    fun getUserData(@Path("id") userId: Int): Observable<User>

}