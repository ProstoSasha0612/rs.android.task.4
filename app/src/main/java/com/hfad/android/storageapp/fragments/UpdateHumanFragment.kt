package com.hfad.android.storageapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.databinding.FragmentUpdateHumanBinding
import com.hfad.android.storageapp.model.Human


class UpdateHumanFragment : Fragment() {

    private lateinit var binding: FragmentUpdateHumanBinding
    private var human:Human? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateHumanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Add new person"
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true) //TODO add navigation

        human = arguments?.get(HUMAN_KEY) as? Human

        binding.btnAdd.setOnClickListener {
            parentFragmentManager.popBackStack()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, HumanListFragment())
                .commit()
        }
    }

    private fun updateHuman(human: Human) {

    }

    companion object {
        fun newInstance(human: Human): UpdateHumanFragment {
            val args = bundleOf(HUMAN_KEY to human)

            val fragment = UpdateHumanFragment()
            fragment.arguments = args
            return fragment
        }

        const val HUMAN_KEY = "human-key"
    }
}