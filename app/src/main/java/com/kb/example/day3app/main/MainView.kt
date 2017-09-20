package com.kb.example.day3app.main

import com.kb.example.day3app.model.Post

/**
 * Created by Karol on 2017-09-20.
 */
interface MainView {

    fun updatePosts(posts: List<Post>)

}