package org.wit.plannerapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import org.wit.plannerapp.databinding.FragmentListdetailBinding

class ListDetailFragment : Fragment() {

    private lateinit var detailViewModel: ListDetailViewModel
    //private val args by navArgs<ListDetailFragmentArgs>()
    private var _fragBinding: FragmentListdetailBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentListdetailBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        detailViewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        detailViewModel.observableItem.observe(viewLifecycleOwner, Observer { render() })
        return root
    }

    private fun render() {
        fragBinding.editMessage.setText("A Message")
        fragBinding.plannervm = detailViewModel
    }

    override fun onResume() {
        super.onResume()
        //detailViewModel.getItem(args.itemid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}