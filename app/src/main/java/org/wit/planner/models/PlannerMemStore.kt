package org.wit.planner.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlannerMemStore : PlannerStore, AnkoLogger {

    val planners = ArrayList<PlannerModel>()

    override fun findAll(): List<PlannerModel> {
        return planners
    }

    override fun create(planner: PlannerModel) {
        planner.id = getId()
        planners.add(planner)
        logAll()
    }

    override fun update(planner: PlannerModel) {
        var foundPlanner: PlannerModel? = planners.find { p -> p.id == planner.id }
        if (foundPlanner != null) {
            foundPlanner.title = planner.title
            foundPlanner.description = planner.description
            foundPlanner.image = planner.image
            foundPlanner.lat = planner.lat
            foundPlanner.lng = planner.lng
            foundPlanner.zoom = planner.zoom
            logAll()
        }
    }

    override fun delete(planner: PlannerModel) {
        planners.remove(planner)
    }

    override fun search(searchTerm: String): List<PlannerModel> {
        return planners.filter { planner -> planner.title.contains(searchTerm) }
    }


    fun logAll() {
        planners.forEach { info("${it}") }
    }
}