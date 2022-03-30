package org.wit.plannerapp.ui.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.wit.plannerapp.R
import org.wit.plannerapp.databinding.FragmentAddBinding
import org.wit.plannerapp.models.ItemModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    lateinit var addViewModel : AddViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    fun addButtonListener(binding: FragmentAddBinding) {
        var planner = ItemModel()

        binding.btnAdd.setOnClickListener() {
            planner.title = binding.itemTitle.text.toString()
            planner.description = binding.itemDescription.text.toString()
            if (planner.title.isEmpty()) {
                //toast(R.string.enter_planner_title)
            } else {
                //if (edit) {
                    //app.planners.update(planner.copy())
                //} else {
                    addViewModel.addItem(planner.copy())
                    Log.i("Add Fragment", addViewModel.getItems())
                }
            //info("add Button Pressed: $itemTitle")
            //setResult(AppCompatActivity.RESULT_OK)
            //finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addViewModel =
            ViewModelProvider(this).get(AddViewModel::class.java)

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.itemTitle
        addViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        addButtonListener(binding)
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}