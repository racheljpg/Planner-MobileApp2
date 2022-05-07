package org.wit.plannerapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.card_planner.view.*
import org.wit.plannerapp.R
import org.wit.plannerapp.databinding.CardItemBinding
import org.wit.plannerapp.models.ItemModel

interface PlannerItemListener {
    fun onPlannerItemClick(planner : ItemModel)
}

class PlannerAdapter constructor(private var planners: List<ItemModel>,
                                 private val listener: PlannerItemListener) : RecyclerView.Adapter<PlannerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val planner = planners[holder.adapterPosition]
        holder.bind(planner, listener)
    }

    override fun getItemCount(): Int = planners.size

    class MainHolder constructor(val binding : CardItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(planner: ItemModel,  listener : PlannerItemListener) {
            //binding.itemTitle.text = planner.title
            //could add icon on card instead
            //itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, planner.image))
            //binding.setOnClickListener { listener.onPlannerItemClick(planner) }
        }
    }
}