package com.kb.example.day3app.main

import android.util.Log
import com.kb.example.day3app.api_calls.DroidsOnRoids
import com.kb.example.day3app.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Karol on 2017-09-20.
 */

const val EVENT_TIMEOUT: Long = 500
const val CONNECT_TIMEOUT_SECONDS: Long = 30
const val LOG_PRESENTER = "presenter logging"
const val API_BASE_URL = "https://thedroidsonroids.com/wp-json/wp/v2/"

class MainPresenter(val view: MainView) {

    var service: DroidsOnRoids

    init {
        val client = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()
        service = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .build()
                .create(DroidsOnRoids::class.java)
    }

    fun onSubscribeToEditText(observable: Observable<CharSequence>) {
        observable.debounce(EVENT_TIMEOUT, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .switchMap {
                    if (it.isBlank()) Observable.empty() else makePostsApiCall(it.toString())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    Log.v(LOG_PRESENTER, it.toString())
                    view.updatePosts(it.map { Post(it.slug.replace("-", " "), it.authorID) })
                }
    }

    private fun makePostsApiCall(search: String): Observable<List<Post>> {
        return service.getPosts(search)
    }

}