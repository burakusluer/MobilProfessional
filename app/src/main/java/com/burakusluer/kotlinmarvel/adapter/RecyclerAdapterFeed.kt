package com.burakusluer.kotlinmarvel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.burakusluer.kotlinmarvel.R
import com.burakusluer.kotlinmarvel.databinding.FeedRecyclerLayoutBinding
import com.burakusluer.kotlinmarvel.model.ModelMarvel
import com.burakusluer.kotlinmarvel.view.fragments.FragmentFeedDirections
import kotlinx.android.synthetic.main.feed_recycler_layout.view.*

class RecyclerAdapterFeed(private var heroes: ArrayList<ModelMarvel> = arrayListOf()) :
    RecyclerView.Adapter<RecyclerAdapterFeed.RecyclerViewHolderFeed>() {

    class RecyclerViewHolderFeed(var View: FeedRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(View.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderFeed {
        val view = DataBindingUtil.inflate<FeedRecyclerLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.feed_recycler_layout,
            parent,
            false
        )
        return RecyclerViewHolderFeed(view)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderFeed, position: Int) {
        holder.View.country = heroes[position]
        holder.View.listener = this@RecyclerAdapterFeed
    }

    fun setHeroes(list: List<ModelMarvel>) {
        heroes.clear()
        heroes.addAll(list)
        notifyDataSetChanged()
    }

    fun onLayoutClick(view: View) {
        Navigation.findNavController(view)
            .navigate(
                FragmentFeedDirections.actionFragmentFeedToFragmentHero(
                    view.uuidHolder.text.toString().toInt()
                )
            )
    }
}