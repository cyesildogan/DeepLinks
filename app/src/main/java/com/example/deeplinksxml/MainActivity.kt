package com.example.deeplinksxml

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeDynamicLinks()
    }

    fun subscribeDynamicLinks() {
        Firebase.dynamicLinks.getDynamicLink(intent).addOnSuccessListener(this) {pendingDynamicLinks->

            var deepLink: Uri? = null
            if(pendingDynamicLinks!=null){
                deepLink = pendingDynamicLinks.link
                val args = Bundle()
                args.putString("imageId", deepLink.toString())
                
            }
        }
    }
}