package org.wit.planner.models

interface PlannerStore {
    fun findAll(): List<PlannerModel>
    fun create(planner: PlannerModel)
    fun update(planner: PlannerModel)
    fun delete(planner: PlannerModel)
    fun search(searchTerm: String): List<PlannerModel>
}