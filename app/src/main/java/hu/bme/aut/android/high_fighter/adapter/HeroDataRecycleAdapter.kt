package hu.bme.aut.android.high_fighter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.high_fighter.databinding.HerodataListItemBinding
import hu.bme.aut.android.high_fighter.model.HeroData


class HeroDataRecycleAdapter : ListAdapter<HeroData, HeroDataRecycleAdapter.ViewHolder>(itemCallback) {
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

    private lateinit var binding: HerodataListItemBinding;
    private var heroList = emptyList<HeroData>()
    var itemClickListener: HeroItemClickListener? = null


    fun addItem(hero: HeroData) {
        heroList += hero
        submitList(heroList)
    }

    fun addAll(heroes: List<HeroData>) {
        heroList += heroes
        submitList(heroList)
    }

    fun deleteRow(position: Int) {
        heroList = heroList.filterIndexed { index, _ -> index != position }
        submitList(heroList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HerodataListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroList[position]

        holder.hero = hero
        holder.tvItemName.text = hero.name
        holder.tvItemHp.text = hero.hp
        holder.tvItemStrength.text = hero.strength
        holder.tvItemDex.text = hero.dex
        holder.tvItemDef.text =hero.def
        binding.heroItemImg.setImageResource(hero.img)
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = binding.tvName2
        val tvItemHp: TextView = binding.tvHP2
        val tvItemStrength: TextView = binding.tvStrength2
        val tvItemDex: TextView = binding.tvDex2
        val tvItemDef: TextView = binding.tvDef2

        var hero: HeroData? = null

        init {
            itemView.setOnClickListener {
                hero?.let { itemClickListener?.onItemClick(it) }
            }
        }
    }

    interface HeroItemClickListener {
        fun onItemClick(hd: HeroData)
    }
}
