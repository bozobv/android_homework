package hu.bme.aut.android.high_fighter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.high_fighter.databinding.EnemyListItemBinding
import hu.bme.aut.android.high_fighter.model.HeroData

class EnemyRecycleAdapter: ListAdapter<HeroData, EnemyRecycleAdapter.ViewHolder2>(itemCallback) {
    companion object {
        object itemCallback : DiffUtil.ItemCallback<HeroData>() {
            override fun areItemsTheSame(oldItem: HeroData, newItem: HeroData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HeroData, newItem: HeroData): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var binding: EnemyListItemBinding;
    private var enemylist = emptyList<HeroData>()
    var itemClickListener: EnemyRecycleAdapter.EnemyItemClickListener? = null


    fun addItem(enemy: HeroData) {
        enemylist += enemy
        submitList(enemylist)
    }

    fun addAll(enemies: List<HeroData>) {
        enemylist += enemies
        submitList(enemylist)
    }

    fun deleteRow(position: Int) {
        enemylist = enemylist.filterIndexed { index, _ -> index != position }
        submitList(enemylist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        binding = EnemyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder2(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        val enemy = enemylist[position]

        /*holder.hero = enemy
        holder.tvItemName.text = hero.name
        holder.tvItemHp.text = hero.hp
        holder.tvItemStrength.text = hero.strength
        holder.tvItemDex.text = hero.dex
        holder.tvItemDef.text =hero.def
        binding.heroItemImg.setImageResource(hero.img)*/

        holder.enemy = enemy
        binding.ibEnemy.setImageResource((enemy.img))
    }



    inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val tvItemName: TextView = binding.tvName2
        val tvItemHp: TextView = binding.tvHP2
        val tvItemStrength: TextView = binding.tvStrength2
        val tvItemDex: TextView = binding.tvDex2
        val tvItemDef: TextView = binding.tvDef2*/

        var enemy: HeroData? = null

        init {
            itemView.setOnClickListener {
                enemy?.let { itemClickListener?.onItemClick(it) }
            }
        }
    }

    interface EnemyItemClickListener {
        fun onItemClick(enemy: HeroData)
    }
}