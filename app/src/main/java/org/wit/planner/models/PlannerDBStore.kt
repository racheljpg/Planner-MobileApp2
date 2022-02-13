package org.wit.planner.models

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class PlannerDBStore(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION), PlannerStore
{

    val planners = ArrayList<PlannerModel>()

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "plannerDB.db"
        private val TABLE_PLANNERS = "planners"

        val COLUMN_ID = "_id"
        val COLUMN_TITLE = "title"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_IMAGE = "image"
        val COLUMN_LAT = "lat"
        val COLUMN_LNG = "lng"
        val COLUMN_ZOOM = "zoom"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PLANNERS_TABLE = ("CREATE TABLE " +
                TABLE_PLANNERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_LAT + " DOUBLE," +
                COLUMN_LNG + " DOUBLE," +
                COLUMN_ZOOM + " FLOAT " +
                ")")
        db.execSQL(CREATE_PLANNERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLANNERS")
        onCreate(db)
    }

    override fun findAll(): List<PlannerModel> {
        val query = "SELECT * FROM $TABLE_PLANNERS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = Integer.parseInt(cursor.getString(0)).toLong()
                val title = cursor.getString(1)
                val description = cursor.getString(2)
                val image = cursor.getString(3)
                val lat = cursor.getDouble(4)
                val lng = cursor.getDouble(5)
                val zoom = cursor.getFloat(6)
                planners.add(PlannerModel(id, title = title, description = description, image = image, lat = lat, lng = lng, zoom = zoom))
                cursor.moveToNext()
            }
            cursor.close()
        }
        db.close()
        return planners
    }

    override fun create(planner: PlannerModel) {
        val values = ContentValues()
        values.put(COLUMN_TITLE, planner.title)
        values.put(COLUMN_DESCRIPTION, planner.description)
        values.put(COLUMN_IMAGE, planner.image)
        values.put(COLUMN_LAT, planner.lat)
        values.put(COLUMN_LNG, planner.lng)
        values.put(COLUMN_ZOOM, planner.zoom)

        val db = this.writableDatabase

        db.insert(TABLE_PLANNERS, null, values)
        db.close()
    }


    override fun update(planner: PlannerModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ID, planner.id)
        values.put(COLUMN_TITLE, planner.title)
        values.put(COLUMN_DESCRIPTION, planner.description)
        values.put(COLUMN_IMAGE, planner.image)
        values.put(COLUMN_LAT, planner.lat)
        values.put(COLUMN_LNG, planner.lng)
        values.put(COLUMN_ZOOM, planner.zoom)

        val updated = db.update(TABLE_PLANNERS, values,"_id="+ planner.id,null)
        db.close()

    }

    override fun delete(planner: PlannerModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ID, planner.id)

        val deleted = db.delete(TABLE_PLANNERS,"_id="+planner.id,null)
        db.close()
    }

    override fun search(searchTerm: String): List<PlannerModel> {
        return planners.filter { planner -> planner.title.contains(searchTerm) }
    }

}