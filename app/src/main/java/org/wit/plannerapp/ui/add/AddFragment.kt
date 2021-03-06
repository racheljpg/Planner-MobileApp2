package org.wit.plannerapp.ui.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.wit.plannerapp.R
import org.wit.plannerapp.databinding.FragmentAddBinding
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.ui.auth.LoggedInViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    lateinit var addViewModel : AddViewModel
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    fun addButtonListener(binding: FragmentAddBinding) {
        var planner = ItemModel()

        binding.btnAdd.setOnClickListener() {
            val title = binding.itemTitle.text.toString()
            val description = binding.itemDescription.text.toString()
            if (planner.title.isEmpty()) {
                //toast(R.string.enter_planner_title)
            } else {
                //if (edit) {
                    //app.planners.update(planner.copy())
                //} else {
                    addViewModel.addItem(loggedInViewModel.liveFirebaseUser,
                    ItemModel(title = title, description = description,
                        email = loggedInViewModel.liveFirebaseUser.value?.email!!)
                    )
                    //addViewModel.addItem(planner.copy())
                    //Log.i("Add Fragment", addViewModel.getItems())
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
        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        addViewModel.observableStatus.observe(viewLifecycleOwner, Observer {
                status -> status?.let { render(status) }
        })
        addButtonListener(binding)
        return root
    }

    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                }
            }
            false -> Toast.makeText(context,getString(R.string.item_error), Toast.LENGTH_LONG).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}