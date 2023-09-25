package com.example.deeplinksxml.view


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.deeplinksxml.viewmodel.ImageViewModel
import com.example.deeplinksxml.R
import com.example.deeplinksxml.databinding.DetailScreenFragmentBinding
import com.example.deeplinksxml.util.MakeQRCode
import com.example.deeplinksxml.util.QRCode
import com.example.deeplinksxml.util.loadUrl
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenFragment(): Fragment(R.layout.detail_screen_fragment) {

    private lateinit var qrCode: QRCode
    private lateinit var fragmentBinding: DetailScreenFragmentBinding
    private val imageViewModel : ImageViewModel by viewModels()
    private val args : DetailScreenFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = DetailScreenFragmentBinding.inflate(inflater)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.tag.let {
            fragmentBinding.tags.text = it
        }
        args.url.let {
            val bitmap = MakeQRCode(makeDynamicLink(it).toString())
            fragmentBinding.imageView.loadUrl(bitmap)

        }
    }

   private fun makeDynamicLink(imageUrl: String): Uri {
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse("https://www.example.com/image_url?id=$imageUrl")
            domainUriPrefix = "https://deeplinksxml.page.link"
            androidParameters {

            }
        }

        val dynamicLinkUri = dynamicLink.uri
        return dynamicLinkUri
    }
}