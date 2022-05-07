package org.wit.plannerapp.models
//
//import android.content.Context
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.google.gson.reflect.TypeToken
//import org.jetbrains.anko.AnkoLogger
////import org.wit.planner.helpers.*
//import java.util.*
//
//val JSON_FILE = "planners.json"
//val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
//val listType = object : TypeToken<java.util.ArrayList<ItemModel>>() {}.type
//
//fun generateRandomId(): Long {
//    return Random().nextLong()
//}
//
//class PlannerJSONStore : PlannerStore, AnkoLogger {
//
//    val context: Context
//    var planners = mutableListOf<ItemModel>()
//
//    constructor (context: Context) {
//        this.context = context
//        if (exists(context, JSON_FILE)) {
//            deserialize()
//        }
//    }
//
//    override fun findAll(): MutableList<ItemModel> {
//        return planners
//    }
//
//    override fun create(planner: ItemModel) {
//        planner.id = generateRandomId()
//        planners.add(planner)
//        serialize()
//    }
//
//
//    override fun update(planner: ItemModel) {
//        val plannersList = findAll() as ArrayList<ItemModel>
//        var foundPlanner: ItemModel? = plannersList.find { p -> p.id == planner.id }
//        if (foundPlanner != null) {
//            foundPlanner.title = planner.title
//            foundPlanner.description = planner.description
//        }
//        serialize()
//    }
//
//    override fun delete(planner: ItemModel) {
//        planners.remove(planner)
//        serialize()
//    }
//
//    override fun search(searchTerm: String): List<ItemModel> {
//        return planners.filter { planner -> planner.title.contains(searchTerm) }
//    }
//
//    private fun serialize() {
//        val jsonString = gsonBuilder.toJson(planners, listType)
//        write(context, JSON_FILE, jsonString)
//    }
//
//    private fun deserialize() {
//        val jsonString = read(context, JSON_FILE)
//        planners = Gson().fromJson(jsonString, listType)
//    }
//}