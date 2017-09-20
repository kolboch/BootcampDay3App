package com.kb.example.day3app.views

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kb.example.day3app.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

/**
 * Created by Karol on 2017-09-20.
 */
class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindPostToView(post: Post) {
        itemView.postTitle.text = post.slug
    }
}