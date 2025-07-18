package com.waffiq.mvvmexample.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.waffiq.mvvmexample.databinding.FragmentSecondBinding
import com.waffiq.mvvmexample.domain.model.ResponseItemDomain
import com.waffiq.mvvmexample.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

  private var _binding: FragmentSecondBinding? = null
  private val binding get() = _binding!!

  private val viewModel: DetailViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentSecondBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args: DetailFragmentArgs by navArgs()
    val data = args.data
    setupData(data)
    showsData()
  }

  private fun setupData(data: ResponseItemDomain) {
    viewModel.getDetail(data.id)

    viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
      binding.progressBar.isVisible = isLoading
    }
  }

  private fun showsData() {
    viewModel.detailResponse.observe(viewLifecycleOwner) {
      binding.tvTitle.text = it.title
      binding.tvId.text = it.id.toString()
      binding.tvComplete.text = if (it.completed) "Completed" else "Not Completed"
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}