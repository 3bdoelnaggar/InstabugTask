package com.elnaggar.instabugtask.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.elnaggar.instabugtask.databinding.FragmentMainBinding
import com.elnaggar.instabugtask.di.MainViewModelFactory
import com.elnaggar.instabugtask.network.Network

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var wordCountAdapter: WordsCountAdapter
    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(Network()))[MainViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            if (state != null) {
                render(state)
            }
        }
        wordCountAdapter = WordsCountAdapter()
        binding.wardsListRecyclerView.adapter = wordCountAdapter
        binding.wardsListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(state: MainState) {
        when (state) {
            is MainState.Error -> {
                binding.errorTextView.isVisible = true
                if (state.message != null) {
                    binding.errorTextView.text = state.message
                }
                binding.loadingProgressBar.isVisible = false
                binding.wardsListRecyclerView.isVisible = false
            }
            MainState.Loading -> {
                binding.errorTextView.isVisible = false
                binding.loadingProgressBar.isVisible = true
                binding.wardsListRecyclerView.isVisible = false
            }
            is MainState.Success -> {
                binding.errorTextView.isVisible = false
                binding.loadingProgressBar.isVisible = false
                binding.wardsListRecyclerView.isVisible = true
                wordCountAdapter.submitList(state.data)
            }
        }
    }


}