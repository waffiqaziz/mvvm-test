package com.waffiq.mvvmexample.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.waffiq.mvvmexample.data.utils.Mappers.toDomain
import com.waffiq.mvvmexample.databinding.FragmentListBinding
import com.waffiq.mvvmexample.presentation.adapter.ListAdapter
import com.waffiq.mvvmexample.presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

  private var _binding: FragmentListBinding? = null
  private val binding get() = _binding!!

  private val viewModel: ListViewModel by viewModels()
  private lateinit var adapter: ListAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.getList()

    // handle progress bar
    viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
      binding.progressBar.isVisible = isLoading
    }

    // setup adapter
    adapter = ListAdapter(requireContext())
    binding.rvItem.layoutManager =
      LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    binding.rvItem.itemAnimator = DefaultItemAnimator()
    binding.rvItem.adapter = adapter

    // set the list
    viewModel.listResponse.observe(viewLifecycleOwner) { data ->
      adapter.setList(data.map {
        it.toDomain()
      })
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}