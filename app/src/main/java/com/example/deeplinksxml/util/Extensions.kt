package com.example.deeplinksxml.util

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

fun ImageView.loadUrl(url: Bitmap) {
    Glide.with(this).load(url).centerCrop().into(this)
}

fun ImageView.loadImageUrl(url : String){
    Glide.with(this).load(url).centerCrop().into(this)
}

fun MakeQRCode(url: String): Bitmap{
    val content = url
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE,512,512)
    val width = bitMatrix.width
    val height = bitMatrix.height
    val bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565)


    for (x in 0 until width){
        for (y in 0 until height){
            bitmap.setPixel(x,y,if (bitMatrix.get(x,y)) Color.BLACK else Color.WHITE)
        }
    }
    return bitmap
}