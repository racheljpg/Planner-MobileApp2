package org.wit.plannerapp.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.plannerapp.adapters.PlannerAdapter
import org.wit.plannerapp.adapters.PlannerItemListener
import org.wit.plannerapp.databinding.FragmentListBinding
import org.wit.plannerapp.models.ItemModel
import org.wit.plannerapp.ui.auth.LoggedInViewModel
import org.wit.plannerapp.utils.createLoader
import org.wit.plannerapp.utils.hideLoader
import org.wit.plannerapp.utils.showLoader

class ListFragment : Fragment(), PlannerItemListener {
    lateinit var loader : AlertDialog
    private val listViewModel: ListViewModel by activityViewModels()
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        loader = createLoader(requireActivity())
        val recyclerView: RecyclerView = binding.recyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        listViewModel.observableItemList.observe(viewLifecycleOwner, Observer {
                items ->
            render(items as ArrayList<ItemModel>)
            hideLoader(loader)
        })
        return root
    }

    private fun render(itemList: ArrayList<ItemModel>) {
        binding.recyclerView.adapter = PlannerAdapter(itemList,this)
        binding.recyclerView.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        showLoader(loader,"Downloading Items")
        loggedInViewModel.liveFirebaseUser.observe(viewLifecycleOwner, Observer { firebaseUser ->
            if (firebaseUser != null) {
                listViewModel.liveFirebaseUser.value = firebaseUser
                listViewModel.load()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    override fun onPlannerItemClick(item: ItemModel) {
        val action = ListFragmentDirections.actionNavListToItemListDetails(item.uid!!)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}