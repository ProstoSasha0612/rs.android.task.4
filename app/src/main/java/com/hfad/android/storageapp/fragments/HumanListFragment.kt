package com.hfad.android.storageapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.android.storageapp.HumanAdapter
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.databinding.FragmentHumanListBinding
import com.hfad.android.storageapp.model.Human
import com.hfad.android.storageapp.viewmodel.HumanListFragmentViewModel


class HumanListFragment : Fragment() {

    private lateinit var binding: FragmentHumanListBinding
    private val humanAdapter = HumanAdapter()
    private val viewModel: HumanListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(HumanListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHumanListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.humanList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = humanAdapter
        }
        (activity as? AppCompatActivity)?.supportActionBar?.title = "StorageApp"


        viewModel.humansLiveData.observe(viewLifecycleOwner) {
            updateFullList(it)
        }

        //Checking settings from preferences
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        if (prefs.getBoolean(getString(R.string.alphabet_filter_settings_key), false)) {
//            viewModel.getInAlphabetOrder()
//            val list = viewModel.humansLiveData.value?.size?:0
            val list = viewModel.getInAlphabetOrder()
            val a = 3
            binding.fab.setOnClickListener{

            }
//            updateFullList(list?: emptyList())
//            val list = viewModel.getInAlphabetOrder()
//            val list1 = viewModel.humansLiveData.value?: emptyList()
//            updateFullList(list1)
        }




        binding.fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack("Add Human Fragment")
                .replace(R.id.container, AddHumanFragment())
                .commit()
        }


    }


    private fun updateFullList(peoples: List<Human>) { //TODO add fun's that not submit All list, only one item(ec. when adding new Human)
        humanAdapter.submitList(peoples)
        humanAdapter.notifyDataSetChanged()
//        val adapter = HumanAdapter()
//        binding.humanList.adapter = adapter

    }

//    private fun updateOneItem(){ TODO make it later
//        humanAdapter.notifyItemChanged()
//    }
}