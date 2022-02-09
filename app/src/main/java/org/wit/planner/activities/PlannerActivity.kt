package org.wit.planner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_planner.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.planner.R
import org.wit.planner.helpers.readImage
import org.wit.planner.helpers.readImageFromPath
import org.wit.planner.helpers.showImagePicker
import org.wit.planner.main.MainApp
import org.wit.planner.models.Location
import org.wit.planner.models.PlannerModel

class PlannerActivity : AppCompatActivity(), AnkoLogger {


    var planner = PlannerModel()
    lateinit var app: MainApp

    val IMAGE_REQUEST = 1

    val LOCATION_REQUEST = 2

    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planner)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("planner_edit")) {
            edit = true
            planner = intent.extras?.getParcelable<PlannerModel>("planner_edit")!!
            itemTitle.setText(planner.title)
            itemDescription.setText(planner.description)
            btnAdd.setText(R.string.save_planner)
            itemImage.setImageBitmap(readImageFromPath(this, planner.image))
            if (planner.image != null) {
                chooseImage.setText(R.string.change_planner_image)
            }
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        itemLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (planner.zoom != 0f) {
                location.lat =  planner.lat
                location.lng = planner.lng
                location.zoom = planner.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }

        btnAdd.setOnClickListener() {
            planner.title = itemTitle.text.toString()
            planner.description = itemDescription.text.toString()
            if (planner.title.isEmpty()) {
                toast(R.string.enter_planner_title)
            } else {
                if (edit) {
                    app.planners.update(planner.copy())
                } else {
                    app.planners.create(planner.copy())
                }
            }
            info("add Button Pressed: $itemTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_planner, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.planners.delete(planner)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    planner.image = data.getData().toString()
                    itemImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_planner_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    planner.lat = location.lat
                    planner.lng = location.lng
                    planner.zoom = location.zoom
                }
            }
        }
    }
}


