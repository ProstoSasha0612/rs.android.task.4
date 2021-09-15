package com.hfad.android.storageapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.databinding.FragmentUpdateHumanBinding
import com.hfad.android.storageapp.model.Human
import com.hfad.android.storageapp.viewmodel.HumanListFragmentViewModel


class UpdateHumanFragment : Fragment() {

    private lateinit var binding: FragmentUpdateHumanBinding
    private var human: Human? = null
    private val listViewModel: HumanListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(HumanListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateHumanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Update person"
        //TODO add navigation

        human = arguments?.get(HUMAN_KEY) as? Human

        binding.btnAdd.setOnClickListener {
            if (updateHuman()) {
                parentFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, HumanListFragment())
                    .commit()
            } else{
                Snackbar.make(binding.root, "Please, fill in all the fields", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun updateHuman(): Boolean {

        val name = binding.edittextName.text.toString()
        val surname = binding.editTextSurname.text.toString()
        val age = binding.editTextAge.text.toString()
        val gender =
            if (binding.rbMale.isChecked) AddHumanFragment.MALE_GENDER else AddHumanFragment.FEMALE_GENDER

        if (name.isNotEmpty() && surname.isNotEmpty() &&
            age.isNotEmpty() && gender.isNotEmpty()
        ) {
            val updatedHuman = Human(human?.id ?: 0, name, surname, age.toInt(), gender)
            listViewModel.update(updatedHuman)
            return true
        } else {
            Snackbar.make(binding.root, "Please, fill in all the fields", Snackbar.LENGTH_LONG)
                .show()
            return false
        }
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