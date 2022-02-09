package org.wit.planner.activities

import PlannerAdapter
import PlannerItemListener
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_planner_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.planner.R
import org.wit.planner.main.MainApp
import org.wit.planner.models.PlannerModel
//JUNK for search view
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast

class PlannerListActivity : AppCompatActivity(), PlannerItemListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planner_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadItems()

        toolbar.title = title
        setSupportActionBar(toolbar)

        //code for search bar

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                /**
                if (list.contains(query)) {
                adapter.filter.filter(query)
                } else {
                Toast.makeText(this@PlannerListActivity, "No Match found", Toast.LENGTH_LONG).show()
                }
                return false
                } **/
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                showItems(app.planners.search(newText))
                return false
            }
        })

        //end of code for search bar
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<PlannerActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlannerItemClick(planner: PlannerModel) {
        startActivityForResult(intentFor<PlannerActivity>().putExtra("planner_edit", planner), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadItems()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadItems() {
        showItems(app.planners.findAll())
    }

    fun showItems (planners: List<PlannerModel>) {
        recyclerView.adapter = PlannerAdapter(planners, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}
