package com.example.deeplinksxml.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.deeplinksxml.viewmodel.DetailsViewModel
import com.example.deeplinksxml.R
import com.example.deeplinksxml.databinding.DeepLinksDetailsScreenBinding
import com.example.deeplinksxml.util.loadImageUrl
import kotlinx.coroutines.launch

class DeepLinksDetailsFragment : Fragment(R.layout.deep_links_details_screen) {

    private val detailsViewModel : DetailsViewModel by viewModels()
    private lateinit var fragmentBinding : DeepLinksDetailsScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DeepLinksDetailsScreenBinding.inflate(inflater)
        val args = arguments
        if (args!= null){
            val data = args.getString("imageId")
            if (data != null) {

                converturl(data)?.let { fragmentBinding.deeplinksIV.loadImageUrl(it) }
                updateImage(data)
            }
        }
        return fragmentBinding.root
    }

    private fun updateImage(id: String){
        viewLifecycleOwner.lifecycleScope.launch {
            detailsViewModel.getImageId(id)
            detailsViewModel.imageIdState.collect{imageViewState->
                if (imageViewState.isLoading){

                }else if(imageViewState.isSuccess){
                    fragmentBinding.deeplinksIV.loadImageUrl(imageViewState.imageId)
                }else{

                }

            }
        }
    }

    fun converturl(data : String): String?{
        val pattern = "id%3D([^&]+)".toRegex()
        val matchResult = pattern.find(data)
        return matchResult?.groupValues?.get(1)
    }


}