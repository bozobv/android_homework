package hu.bme.aut.android.high_fighter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.high_fighter.model.HeroData
import hu.bme.aut.android.high_fighter.repository.EnemyRepository
import hu.bme.aut.android.high_fighter.repository.HeroRepository

class HeroListViewModel : ViewModel() {
    private val heroRepository : HeroRepository = HeroBrowserApplication.heroRepository
    val heroList: MutableLiveData<MutableList<HeroData>> = heroRepository.getAll()
    private val enemyRepository : EnemyRepository = HeroBrowserApplication.enemyRepository
    val enemyList: MutableLiveData<MutableList<HeroData>> = enemyRepository.getAll()

    private var buffs = arrayOf(0, 0, 0, 0)


    fun getHeroById(id: Long): MutableLiveData<HeroData> =
        HeroBrowserApplication.heroRepository.getById(id).let { MutableLiveData<HeroData>(it) }

    fun getEnemyByIs(id: Long) =
        HeroBrowserApplication.enemyRepository.getById(id).let { MutableLiveData<HeroData>(it) }

    fun getBuffs(): Array<Int> {
        return buffs
    }

    /*fun add(hero: HeroData) {
        heroRepository.add(hero)
    }*/
}