package hu.bme.aut.android.high_fighter.fragments

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
import hu.bme.aut.android.high_fighter.adapter.EnemyRecycleAdapter
import hu.bme.aut.android.high_fighter.databinding.FragmentEnemyListBinding
import hu.bme.aut.android.high_fighter.model.HeroData

class EnemyListFragment : EnemyRecycleAdapter.EnemyItemClickListener, Fragment() {
    private lateinit var binding: FragmentEnemyListBinding
    private lateinit var heroListViewModel: HeroListViewModel
    private lateinit var enemyRecycleAdapter: EnemyRecycleAdapter

    private fun setupRecyclerView(view: View) {
        enemyRecycleAdapter = EnemyRecycleAdapter()
        enemyRecycleAdapter.itemClickListener = this
        val rvAdList = view.findViewById<RecyclerView>(R.id.rvAdList)
        rvAdList.adapter = enemyRecycleAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val heroId = EnemyListFragmentArgs.fromBundle(requireArguments()).chosenId
        heroListViewModel = ViewModelProvider(this).get(HeroListViewModel::class.java)
        heroListViewModel.enemyList.observe(
            viewLifecycleOwner,
            Observer {
                enemyRecycleAdapter.addAll(it)

            }
        )

        heroListViewModel.getHeroById(heroId).observe(
            viewLifecycleOwner,
            Observer {
                binding.frHero.tvName2.setText(it.name)
                binding.frHero.tvHP2.setText(it.hp)
                binding.frHero.tvStrength2.setText(it.strength)
                binding.frHero.tvDef2.setText(it.def)
                binding.frHero.tvDex2.setText(it.dex)
                binding.frHero.heroItemImg.setImageResource(it.img)
            }
        )

        binding = FragmentEnemyListBinding.inflate(layoutInflater, container, false)
        setupRecyclerView(binding.root)
        /*binding.tvWelcome.text =
            "Üdvözlünk, ${ListFragmentArgs.fromBundle(requireArguments()).nameToShow}"*/
        return binding.root
    }

    override fun onItemClick(enemy: HeroData) {
        val action = EnemyListFragmentDirections.actionEnemyListFragmentToBattleFragment()
        findNavController().navigate(action)
    }
}