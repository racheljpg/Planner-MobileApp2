package org.wit.plannerapp.models


interface PlannerStore {
    fun findAll(): List<ItemModel>
    fun create(planner: ItemModel)
    fun update(planner: ItemModel)
    fun delete(planner: ItemModel)
    fun search(searchTerm: String): List<ItemModel>
}