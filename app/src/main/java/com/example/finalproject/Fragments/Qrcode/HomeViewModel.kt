package com.example.finalproject.Fragments.Qrcode

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.finalproject.Model.QrCodeData.Data
import com.example.finalproject.R
import com.example.finalproject.Retrofit.service
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _getTokenID = MutableLiveData<Data>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val getTokenID: LiveData<Data>
        get() = _getTokenID


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getTokenFomResponse()
    }

    private fun getTokenFomResponse() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getDefferedText = service.retrofitService.getText()
            try {
                // this will run on a thread managed by Retrofit
                val Result = getDefferedText.await()
                Log.i("zamalek ", "success of ${Result.data.id}")
                if (Result != null) {
                    _getTokenID.value = Result.data
                }

            } catch (e: Exception) {
                Log.i("eror", "this is error ${e.message.toString()}")
            }
        }
    }


    @Throws(WriterException::class)
    fun TextToImageEncode(Value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.QR_CODE,
                    HomeFragment.QRcodeWidth, HomeFragment.QRcodeWidth, null
            )
        } catch (Illegalargumentexception: IllegalArgumentException) {
            return null
        }

        val bitMatrixWidth = bitMatrix.getWidth()
        val bitMatrixHeight = bitMatrix.getHeight()
        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix.get(x, y))
                    R.color.black
                else
                    R.color.white


            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }

}
