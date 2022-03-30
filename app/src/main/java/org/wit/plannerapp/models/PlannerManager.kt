package org.wit.plannerapp.models

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

}