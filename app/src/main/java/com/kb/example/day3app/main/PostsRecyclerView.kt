package com.kb.example.day3app.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kb.example.day3app.R
import com.kb.example.day3app.model.Post
import com.kb.example.day3app.views.PostViewHolder

/**
 * Created by Karol on 2017-09-20.
 */
class PostsRecyclerView : RecyclerView.Adapter<PostViewHolder>() {

    var posts: List<Post> = emptyList()

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost: Post = posts[position]
        holder.bindPostToView(currentPost)
    }

    override fun getItemCount(): Int = posts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostViewHolder {
        val postView: View = LayoutInflater.from(parent?.context).inflate(R.layout.post_item, parent, false)
        return PostViewHolder(postView)
    }

    fun updatePostList(postsUpdate: List<Post>) {
        if (postsUpdate != null || !postsUpdate.isEmpty()) {
            posts = postsUpdate
            notifyDataSetChanged()
        } else {
            posts = emptyList()
            notifyDataSetChanged()
        }
    }


}