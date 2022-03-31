package org.wit.plannerapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.card_planner.view.*
import org.wit.plannerapp.R
import org.wit.plannerapp.models.ItemModel

interface PlannerItemListener {
    fun onPlannerItemClick(planner : ItemModel)
}

class PlannerAdapter constructor(private var planners: List<ItemModel>,
                                 private val listener: PlannerItemListener) : RecyclerView.Adapter<PlannerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_planner, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val planner = planners[holder.adapterPosition]
        holder.bind(planner, listener)
    }

    override fun getItemCount(): Int = planners.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(planner: ItemModel,  listener : PlannerItemListener) {
            itemView.itemTitle.text = planner.title
            itemView.description.text = planner.description
            //itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, planner.image))
            itemView.setOnClickListener { listener.onPlannerItemClick(planner) }
        }
    }
}