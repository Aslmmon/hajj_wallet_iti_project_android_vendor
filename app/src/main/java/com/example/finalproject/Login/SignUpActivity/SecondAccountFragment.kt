package com.example.finalproject.Login.SignUpActivity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.finalproject.R
import com.google.android.gms.maps.model.LatLng
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_second_account.*
import kotlinx.android.synthetic.main.fragment_second_account.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SecondAccountFragment : Fragment(), BlockingStep {
    val GET_LOCATION_REQUEST = 1
    var Latitude: String? = null
    var Longtitude: String? = null
    var LatLng:LatLng?= null

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {

    }

    override fun onSelected() {

    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
        val parentActivity = (activity as SignUpActivity)
        parentActivity.userSignUp.nationality= nationality.text.toString()
        parentActivity.userSignUp.category= category.text.toString()
        parentActivity.userSignUp.crn= crn.text.toString()
        parentActivity.userSignUp.code= LatLng.toString()
//        code.setText(LatLng!!.latitude.toString())

        parentActivity.userSignUp.password1= password1.text.toString()
        parentActivity.userSignUp.password2 = password2.text.toString()
        parentActivity.userSignUp.store_name = store.text.toString()
        parentActivity.userSignUp.location= "Point(${LatLng!!.latitude} ${LatLng!!.longitude})"
        Toast.makeText(activity,"Your location is ${parentActivity.userSignUp.location}",Toast.LENGTH_SHORT).show()

        callback!!.complete()


    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
    }

    override fun verifyStep(): VerificationError?
    {
        return null
    }

    override fun onError(error: VerificationError) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view: View = inflater!!.inflate(R.layout.fragment_second_account, container, false)
        view.getLocation.setOnClickListener {
            var intent = Intent(activity, MapActivity::class.java)
            startActivityForResult(intent,GET_LOCATION_REQUEST)
            Log.d("tap", "Selected")
        }

        // Return the fragment view/layout
        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            GET_LOCATION_REQUEST -> {
                if(data!=null){
                    var bundle = data.extras
                    Log.i("Location","Your Location in SecondACtivityFragment is ${data.data}")
                    var latitdue =  bundle.getString("Latitude")
                    var longtitude = data.getStringExtra("Longtitude")
                    LatLng = data.getParcelableExtra("LatLng")
                    Log.i("Location","Your Location in SecondACtivityFragment is ${latitdue} adnd ${longtitude}")
                    Log.i("Location","Your Location in SecondACtivityFragment is ${LatLng}")
                    Log.i("show me Location ","Your Location in SecondACtivityFragment is ${LatLng!!.latitude} adnd ${LatLng!!.longitude} ")


                }

            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
