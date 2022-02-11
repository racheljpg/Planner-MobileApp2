package org.wit.planner.fragments

import PlannerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.planner.R
import org.wit.planner.databinding.FragmentPlannerListBinding
import org.wit.planner.main.MainApp

class PlannerListFragment : Fragment() {

    lateinit var app: MainApp
    private var _fragBinding: FragmentPlannerListBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        app = activity?.application as MainApp
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentPlannerListBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.listOfItems)

        fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        //No value passed for parameter listener??
        fragBinding.recyclerView.adapter = PlannerAdapter(app.plannerStore.findAll())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            PlannerListFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}