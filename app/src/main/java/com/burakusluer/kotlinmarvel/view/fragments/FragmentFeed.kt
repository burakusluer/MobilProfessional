package com.burakusluer.kotlinmarvel.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.burakusluer.kotlinmarvel.R
import com.burakusluer.kotlinmarvel.adapter.RecyclerAdapterFeed
import com.burakusluer.kotlinmarvel.viewModel.ViewModelFeed
import kotlinx.android.synthetic.main.fragment_feed.*

class FragmentFeed : Fragment() {
    private lateinit var viewModel: ViewModelFeed
    private lateinit var recyclerAdapterFeed: RecyclerAdapterFeed

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FeedRecycler.layoutManager = LinearLayoutManager(this.context)
        recyclerAdapterFeed = RecyclerAdapterFeed()
        FeedRecycler.adapter = recyclerAdapterFeed
        viewModel = ViewModelProvider(requireActivity()).get(ViewModelFeed::class.java)
        observation()
        viewModel.refresh()
        FeedSwipeRefresh.setOnRefreshListener {
            viewModel.onSwipe()
            FeedSwipeRefresh.isRefreshing = false
        }
    }

    private fun observation() {
        viewModel.heroCollection.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerAdapterFeed.setHeroes(it)
            }
        })
    }

}