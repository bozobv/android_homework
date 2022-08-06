package hu.bme.aut.android.high_fighter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.high_fighter.HeroListViewModel
import hu.bme.aut.android.high_fighter.databinding.FragmentDetailsBinding
import hu.bme.aut.android.high_fighter.model.HeroData

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var heroListViewModel: HeroListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        val heroId = DetailsFragmentArgs.fromBundle(requireArguments()).heroId
        heroListViewModel = ViewModelProvider(this).get(HeroListViewModel::class.java)
        heroListViewModel.getHeroById(heroId).observe(
            viewLifecycleOwner,
            Observer { hero:HeroData ->
                binding.tvName2.text = hero?.name
                binding.tvHP2.text = hero?.hp
                binding.tvStrength2.text = hero?.strength
                binding.tvDef2.text = hero?.def
                binding.tvDex2.text = hero?.dex
                binding.tvDesc2.text = hero?.desc
                binding.adImg.setImageResource(hero.img)

            }
        )
        binding.btnChoose.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToEnemyListFragment(heroId!!)
            findNavController().navigate(action)
        }

        return binding.root
    }


}
