package com.example.kotlin1lesson2.ui.fragments.characters.detail

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.common.extensions.setImage
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.databinding.FragmentDetailBinding
import com.example.kotlin1lesson2.ui.fragments.characters.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>(
    R.layout.fragment_detail) {

    override val binding by viewBinding(FragmentDetailBinding::bind)
    override val viewModel: CharactersViewModel by activityViewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun setupViews() {
        getData()
    }

    private fun getData() {
        viewModel.fetchCharacterID(args.id).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.e("loo", "olo")
                }
                is Resource.Error -> {
                    Log.e("tag", "Error Character ${it.message.toString()}")
                }
                is Resource.Success -> {
                    binding.tvtName.text = it.data?.name
                    it.data?.let { it1 -> binding.image.setImage(it1.image) }
                }
            }
        }
    }
}