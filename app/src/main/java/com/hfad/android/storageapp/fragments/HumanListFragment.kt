package com.hfad.android.storageapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.android.storageapp.HumanAdapter
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.SwipableItemTouchHelper
import com.hfad.android.storageapp.SwipeCallbacks
import com.hfad.android.storageapp.databinding.FragmentHumanListBinding
import com.hfad.android.storageapp.model.Human
import com.hfad.android.storageapp.viewmodel.HumanListFragmentViewModel


class HumanListFragment : Fragment(), SwipeCallbacks {

    private var _binding: FragmentHumanListBinding? = null
    private val binding
        get() = requireNotNull(_binding)
    private val humanAdapter = HumanAdapter()
    private val viewModel: HumanListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(HumanListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHumanListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.humanList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = humanAdapter
        }
        (activity as? AppCompatActivity)?.supportActionBar?.title = "StorageApp"
        //TODO add navigation
        val itemTouchHelper: SwipableItemTouchHelper =
            SwipableItemTouchHelper(
                this,
                ContextCompat.getColor(requireContext(), R.color.red),
                ContextCompat.getColor(requireContext(), R.color.orange)
            )

        viewModel.humansLiveData.observe(viewLifecycleOwner) {
            updateFullList(it)
        }

        //Checking settings from preferences
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        if (prefs.getBoolean(getString(R.string.alphabet_filter_settings_key), false)) {
            viewModel.getInAlphabetOrder().observe(viewLifecycleOwner) {
                updateFullList(it)
            }
        }

        binding.fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("Add Human Fragment")
                .replace(R.id.container, AddHumanFragment())
                .commit()
        }

        itemTouchHelper.touchHelper.attachToRecyclerView(binding.humanList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Swipable Callbacks
    override fun leftSwipe(human: Human?) {
        viewModel.delete(human)
    }

    override fun rightSwipe(human: Human?) {
        parentFragmentManager.beginTransaction()
            .addToBackStack("Add Human Fragment")
            .replace(
                R.id.container,
                UpdateHumanFragment.newInstance(
                    human?.id ?: -1,
                    human?.name ?: "Error",
                    human?.secondName ?: "Error",
                    human?.age ?: -1,
                    human?.gender ?: "Error"
                )
            )
            .commit()
    }


    private fun updateFullList(peoples: List<Human>) { //TODO add fun's that not submit All list, only one item(ec. when adding new Human)
        humanAdapter.submitList(peoples)
    }


//    private fun updateOneItem(human: Human){ //TODO make it later
//
//    }
}
