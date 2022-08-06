package hu.bme.aut.android.high_fighter.fragments;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.high_fighter.HeroListViewModel
import hu.bme.aut.android.high_fighter.R
import hu.bme.aut.android.high_fighter.adapter.HeroDataRecycleAdapter
import hu.bme.aut.android.high_fighter.databinding.FragmentListBinding
import hu.bme.aut.android.high_fighter.model.HeroData

class ListFragment : HeroDataRecycleAdapter.HeroItemClickListener, Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var heroListViewModel: HeroListViewModel
    private lateinit var heroRecycleAdapter: HeroDataRecycleAdapter

    private fun setupRecyclerView(view: View) {
        heroRecycleAdapter = HeroDataRecycleAdapter()
        heroRecycleAdapter.itemClickListener = this
        val rvAdList = view.findViewById<RecyclerView>(R.id.rvAdList)
        rvAdList.adapter = heroRecycleAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        heroListViewModel = ViewModelProvider(this).get(HeroListViewModel::class.java)
        heroListViewModel.heroList.observe(
            viewLifecycleOwner,
            Observer { heroRecycleAdapter.addAll(it) })
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        setupRecyclerView(binding.root)
        /*binding.tvWelcome.text =
            "Üdvözlünk, ${ListFragmentArgs.fromBundle(requireArguments()).nameToShow}"*/
        return binding.root
    }

    override fun onItemClick(hd: HeroData) {
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(hd.id!!)
        findNavController().navigate(action)
    }
}
