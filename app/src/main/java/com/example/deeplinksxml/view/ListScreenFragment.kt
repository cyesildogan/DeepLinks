package com.example.deeplinksxml.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.deeplinksxml.viewmodel.ImageViewModel
import com.example.deeplinksxml.R
import com.example.deeplinksxml.adapter.ImageAdapter
import com.example.deeplinksxml.databinding.ListScreenFragmentBinding
import com.example.deeplinksxml.model.Hit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListScreenFragment : Fragment(R.layout.list_screen_fragment) {

    private lateinit var fragmentBinding: ListScreenFragmentBinding
    private val imageViewModel : ImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateImages()
        fragmentBinding = ListScreenFragmentBinding.inflate(inflater)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ListScreenFragmentBinding.bind(view)
        fragmentBinding = binding
    }

    private fun updateImages(){
        viewLifecycleOwner.lifecycleScope.launch {
            imageViewModel.getImage()
            imageViewModel.imageState.collect{imageViewState->
                if (imageViewState.isLoading){

                }else if(imageViewState.isSuccess){
                    fragmentBinding.recyclerViewFavoritesList.visibility = View.VISIBLE
                    setRecyclerView(imageViewState.imageList!!.hits)
                }else {
                    fragmentBinding.recyclerViewFavoritesList.visibility = View.GONE
                    Toast.makeText(requireContext(),"Patladk",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun setRecyclerView(imageList : List<Hit>){
        fragmentBinding.recyclerViewFavoritesList.adapter = ImageAdapter(imageList,ImageAdapter.OnClickListener{
            findNavController().navigate(
                ListScreenFragmentDirections.actionListScreenFragmentToDetailScreenFragment(it.tags,it.largeImageURL)
            )
        })
    }
}