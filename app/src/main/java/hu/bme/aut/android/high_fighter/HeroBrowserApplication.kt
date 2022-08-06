package hu.bme.aut.android.high_fighter

import android.app.Application
import hu.bme.aut.android.high_fighter.repository.EnemyRepository
import hu.bme.aut.android.high_fighter.repository.HeroRepository

class HeroBrowserApplication: Application() {

    companion object {
        lateinit var heroRepository: HeroRepository
            private set
        lateinit var enemyRepository: EnemyRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        heroRepository = HeroRepository()
        enemyRepository = EnemyRepository()
    }

}