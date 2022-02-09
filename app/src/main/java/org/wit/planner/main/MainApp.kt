package org.wit.planner.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.planner.models.PlannerJSONStore
import org.wit.planner.models.PlannerMemStore
import org.wit.planner.models.PlannerStore


class MainApp : Application(), AnkoLogger {

    lateinit var planners: PlannerStore

    override fun onCreate() {
        super.onCreate()
        //planners = PlannerMemStore()
        planners = PlannerJSONStore(applicationContext)
        info("Planner started")
    }
}