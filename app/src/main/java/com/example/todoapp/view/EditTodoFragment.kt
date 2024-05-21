package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCreateTodoBinding
import com.example.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment() {
    private lateinit var binding:FragmentCreateTodoBinding
    private lateinit var viewModel:DetailTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        binding.txtTitleHeading.text = "Edit Todo"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            binding.txtTitle.setText(it.title)
            binding.txtNotes.setText(it.notes)

            when(it.priority) {
                1 -> binding.radioLow.isChecked = true
                2 -> binding.radioMedium.isChecked = true
                else -> binding.radioHigh.isChecked = true
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

}