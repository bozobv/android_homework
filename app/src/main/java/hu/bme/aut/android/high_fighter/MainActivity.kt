package hu.bme.aut.android.high_fighter

import android.os.Bundle
import hu.bme.aut.android.high_fighter.databinding.ActivityMainBinding

class MainActivity : BaseAuthActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try
        {
            this.supportActionBar!!.hide()
        }
        catch (e: NullPointerException) { }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}