package com.example.deeplinksxml.util
import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class QRCode () {
    val content = ""
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE,512,512)
    val width = bitMatrix.width
    val height = bitMatrix.height
    val bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)

    init {
        for (x in 0 until width){
            for (y in 0 until height){
                bitmap.setPixel(x,y,if (bitMatrix.get(x,y)) Color.BLACK else Color.WHITE)
            }
        }
    }
}