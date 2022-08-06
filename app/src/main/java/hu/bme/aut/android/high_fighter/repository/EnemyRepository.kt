package hu.bme.aut.android.high_fighter.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.high_fighter.R
import hu.bme.aut.android.high_fighter.model.HeroData

class EnemyRepository {

    private val enemmies = mutableListOf(
        HeroData(
            1,
            "4",
            "3",
            "4",
            "5",
            "Ede",
            "asdasdsadasdasdsadsad",
            R.drawable.enemy
        ),
        HeroData(
            2,
            "4",
            "3",
            "4",
            "5",
            "Károly",
            "kinek teste rákos",
            R.drawable.enemy
        ),
        HeroData(
            3,
            "4",
            "6",
            "4",
            "8",
            "Gergely",
            "hututututututututttutututtuututu",
            R.drawable.enemy
        ),
        HeroData(
            4,
            "4",
            "6",
            "4",
            "8",
            "Viktor",
            "türürürütütütüütütütütütütüütürürtü",
            R.drawable.enemy
        ),
        HeroData(
            5,
            "4",
            "6",
            "4",
            "8",
            "Olga",
            "pápápáppapappapapappapappapapap",
            R.drawable.enemy
        )

    )
    fun getAll() = MutableLiveData(enemmies)

    fun getById(id: Long) = enemmies.filter { it.id == id }.getOrNull(0).also { MutableLiveData(it) }

    fun add(enemy: HeroData) {
        enemmies.add(enemy)
    }

}