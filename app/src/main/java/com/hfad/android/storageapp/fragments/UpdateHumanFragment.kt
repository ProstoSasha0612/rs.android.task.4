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

    private var _binding: FragmentUpdateHumanBinding? = null
    private val binding
        get() = requireNotNull(_binding)
    private var id: Int? = null

    private val listViewModel: HumanListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(HumanListFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateHumanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Update person"
        //TODO add navigation

        id = arguments?.getInt(HUMAN_KEY_ID)
        val name = arguments?.getString(HUMAN_KEY_NAME)
        val surname = arguments?.getString(HUMAN_KEY_SURNAME)
        val age = arguments?.getInt(HUMAN_KEY_AGE)
        val gender = arguments?.getString(HUMAN_KEY_GENDER)

        binding.edittextName.setText(name)
        binding.editTextSurname.setText(surname)
        binding.editTextAge.setText(age.toString())
        if (gender == AddHumanFragment.MALE_GENDER) {
            binding.rbMale.isChecked = true
        } else {
            binding.rbFemale.isChecked = true
        }

        binding.btnAdd.setOnClickListener {
            if (updateHuman()) {
                parentFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, HumanListFragment())
                    .commit()
            } else {
                Snackbar.make(binding.root, "Please, fill in all the fields", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
            val updatedHuman = Human(id ?: 0, name, surname, age.toInt(), gender)
            listViewModel.update(updatedHuman)
            return true
        } else {
            Snackbar.make(binding.root, "Please, fill in all the fields", Snackbar.LENGTH_LONG)
                .show()
            return false
        }
    }

    companion object {
        fun newInstance(
            id: Int,
            name: String,
            secondName: String,
            age: Int,
            gender: String
        ): UpdateHumanFragment {
            val args = bundleOf(
                HUMAN_KEY_ID to id,
                HUMAN_KEY_NAME to name,
                HUMAN_KEY_SURNAME to secondName,
                HUMAN_KEY_AGE to age,
                HUMAN_KEY_GENDER to gender
            )

            val fragment = UpdateHumanFragment()
            fragment.arguments = args
            return fragment
        }

        const val HUMAN_KEY_NAME = "human_key_name"
        const val HUMAN_KEY_SURNAME = "human_key_surname"
        const val HUMAN_KEY_AGE = "human_key_age"
        const val HUMAN_KEY_GENDER = "human_key_gender"
        const val HUMAN_KEY_ID = "human_key_id"
    }
}