package com.burakusluer.kotlinmarvel.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.burakusluer.kotlinmarvel.R
import com.burakusluer.kotlinmarvel.databinding.FragmentHeroBinding
import com.burakusluer.kotlinmarvel.viewModel.ViewModelHero

class FragmentHero : Fragment() {

    private lateinit var dataBinding: FragmentHeroBinding
    private lateinit var viewModel: ViewModelHero

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_hero, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ViewModelHero::class.java)
        observetaion()
        arguments?.let { args ->
            viewModel.getHero(FragmentHeroArgs.fromBundle(args).uuid)
        }
    }

    private fun observetaion() {
        viewModel.hero.observe(viewLifecycleOwner, Observer {
            it?.let { hero ->
                dataBinding.hero = hero
            }
        })
    }

}