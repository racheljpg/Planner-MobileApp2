package org.wit.plannerapp.models

import timber.log.Timber

object PlannerManager : PlannerStore {

    var lastId = 0L

    internal fun getId(): Long {
        return lastId++
    }

    private val planners = ArrayList<ItemModel>()

    override fun findAll(): List<ItemModel> {
        return planners
    }

    override fun create(planner: ItemModel) {
      planner.id = getId()
        planners.add(planner)
    }

    override fun update(planner: ItemModel) {
        TODO("Not yet implemented")
    }

    override fun delete(planner: ItemModel) {
        TODO("Not yet implemented")
    }

    override fun search(searchTerm: String): List<ItemModel> {
        TODO("Not yet implemented")
    }

    //override fun findById(id:Long) : ItemModel? {
    override fun findById(id:Long) : ItemModel? {
        val foundItem: ItemModel? = planners.find { it.id == id }
        return foundItem
    }

    fun logAll() {
        Timber.v("** Item List **")
        planners.forEach { Timber.v("Item ${it}") }
    }

}