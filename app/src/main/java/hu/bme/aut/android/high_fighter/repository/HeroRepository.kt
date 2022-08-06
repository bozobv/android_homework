package hu.bme.aut.android.high_fighter.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.high_fighter.R
import hu.bme.aut.android.high_fighter.model.HeroData

class HeroRepository {

    private val heroes = mutableListOf(
        HeroData(
            1,
            "4",
            "3",
            "4",
            "5",
            "Géza",
            "fegyvere egy van, de az iszonyú",
            R.drawable.hero
        ),
        HeroData(
            2,
            "4",
            "3",
            "4",
            "5",
            "Sanyi",
            "aki a szádba Sanyi",
            R.drawable.hero
        ),
        HeroData(
            3,
            "4",
            "6",
            "4",
            "8",
            "László",
            "szép a szeme",
            R.drawable.hero
        )
    )
    fun getAll() = MutableLiveData(heroes)

    fun getById(id: Long) = heroes.filter { it.id == id }.getOrNull(0).also { MutableLiveData(it) }

    fun add(hero: HeroData) {
        heroes.add(hero)
    }
}