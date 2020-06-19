package com.chullian.template.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chullian.template.databinding.LayoutItemBinding
import com.chullian.template.model.models.Item

class ItemRecyclerAdapter(private val interaction: ItemInteraction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem.itemId == newItem.itemId

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding =
            LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemVH(
            itemBinding,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemVH -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Item>) {
        differ.submitList(list)
    }

    class ItemVH
    constructor(
        private val itemBinding: LayoutItemBinding,
        private val interaction: ItemInteraction?
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Item) = with(itemBinding.root) {
            itemView.setOnClickListener {
                interaction.onItemSelected(adapterPosition, item)
            }
            itemBinding.itemName.text = item.itemName
            if (item.isLocked) {
                itemBinding.item.setBackgroundResource(R.color.colorLocked)
            } else itemBinding.item.setBackgroundResource(R.color.colorUnLocked)

        }
    }

    interface ItemInteraction {
        fun onItemSelected(position: Int, item: Item)
    }
}