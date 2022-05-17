package org.wit.plannerapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import org.wit.plannerapp.R
import org.wit.plannerapp.databinding.ItemListDetailsFragmentBinding
import org.wit.plannerapp.ui.auth.LoggedInViewModel
import org.wit.plannerapp.ui.list.ListViewModel
import timber.log.Timber

class ItemListDetails : Fragment() {

    private val args by navArgs<ItemListDetailsArgs>()

    private var _fragBinding: ItemListDetailsFragmentBinding? = null
    private val fragBinding get() = _fragBinding!!

    private val itemListViewModel : ListViewModel by activityViewModels()
    private lateinit var itemDetailViewModel: ItemListDetailsViewModel
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()

    companion object {
        fun newInstance() = ItemListDetails()
    }

    private lateinit var viewModel: ItemListDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = ItemListDetailsFragmentBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        itemDetailViewModel = ViewModelProvider(this).get(ItemListDetailsViewModel::class.java)
        itemDetailViewModel.observableItem.observe(viewLifecycleOwner, Observer { render() })


        return root
    }



    private fun render() {
        fragBinding.plannervm = itemDetailViewModel
        Timber.i("Retrofit fragBinding.plannervm == $fragBinding.plannervm")
    }

    override fun onResume() {
        super.onResume()
        itemDetailViewModel.getItem(loggedInViewModel.liveFirebaseUser.value?.uid!!, args.itemid)
    }



}