package com.kb.example.day3app.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.textChanges
import com.kb.example.day3app.R
import com.kb.example.day3app.model.Post
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter
    private lateinit var recycler: PostsRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        recycler = PostsRecyclerView()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recycler
        onSetUpDataListener()
    }

    private fun onSetUpDataListener() {
        presenter?.onSubscribeToEditText(dataEditText.textChanges())
    }

    override fun updatePosts(posts: List<Post>) {
        recycler.updatePostList(posts)
    }
}
