package hu.bme.aut.android.high_fighter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.high_fighter.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {

            val action = MenuFragmentDirections.actionMenuFragmentToListFragment()
            findNavController().navigate(action)
            /*val name = binding.etUsername.text.toString()
            if (name.isBlank()) {
                binding.etUsername.error = "Enter your username"
            } else {
                val action = LoginFragmentDirections.actionLoginSuccess(name)
                findNavController().navigate(action)
            }*/
        }
    }
}