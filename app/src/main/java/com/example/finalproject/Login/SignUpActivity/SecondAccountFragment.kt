package com.example.finalproject.Login.SignUpActivity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.finalproject.Login.SignUpActivity.Category.CategoryActivity
import com.example.finalproject.Login.SignUpActivity.Nationality.NationalityActivity

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
class SecondAccountFragment : Fragment(), BlockingStep, AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val text = p0!!.getItemAtPosition(p2)
    }

    val GET_LOCATION_REQUEST = 1
    val PICK_NATIONALITY_REQUEST = 5
    val PICK_CATEGORIES = 6
    var LatLng: LatLng? = null
    var nationality: String? = null
    var category: String? = null


    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
        callback!!.goToPrevStep()

    }

    override fun onSelected() {

    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {

        if (isUserDataValid()) {
            val parentActivity = (activity as SignUpActivity)

            parentActivity.userSignUp.nationality = myNationality.text.toString()
            parentActivity.userSignUp.category = myCategory.text.toString()
            parentActivity.userSignUp.crn = crn.text.toString()
            parentActivity.userSignUp.password1 = password1.text.toString()
            parentActivity.userSignUp.password2 = password2.text.toString()
            parentActivity.userSignUp.store_name = store.text.toString()
            parentActivity.userSignUp.location = "Point(${LatLng!!.longitude} ${LatLng!!.latitude})"
            Toast.makeText(activity, "Your location is ${parentActivity.userSignUp.location}", Toast.LENGTH_SHORT).show()

            callback!!.complete()
        } else {
            Toast.makeText(activity, "Complete Requested Fields", Toast.LENGTH_SHORT).show()
        }


    }

//    isFieldValid(context!!, category) &&

    private fun isUserDataValid(): Boolean {
        return isFieldValid(context!!, crn)
                && isFieldValid(context!!, password1) && isFieldValid(context!!, password2)
                && isFieldValid(context!!, store) && isPasswordValid(context!!, password1)
                && isPasswordValid(context!!, password2) && passwordsMatch(context!!, password1, password2)

    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onError(error: VerificationError) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view: View = inflater!!.inflate(R.layout.fragment_second_account, container, false)
        view.getLocation.setOnClickListener {
            var intent = Intent(activity, MapActivity::class.java)
            startActivityForResult(intent, GET_LOCATION_REQUEST)
            Log.d("tap", "Selected")
        }



        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PICK_CATEGORIES -> {
                if (data != null) {
                    category =
                            (data.getStringExtra("category"))
                    myCategory.text = category
                }
            }

            PICK_NATIONALITY_REQUEST -> {
                if (data != null) {
                    nationality =
                            (data.getStringExtra("nationality"))
                    myNationality.text = nationality
                }
            }
            GET_LOCATION_REQUEST -> {
                if (data != null) {
                    var bundle = data.extras
                    Log.i("Location", "Your Location in SecondACtivityFragment is ${data.data}")
                    var latitdue = bundle.getString("Latitude")
                    var longtitude = data.getStringExtra("Longtitude")
                    LatLng = data.getParcelableExtra("LatLng")
                    Log.i("Location", "Your Location in SecondACtivityFragment is ${latitdue} adnd ${longtitude}")
                    Log.i("Location", "Your Location in SecondACtivityFragment is ${LatLng}")
                    Log.i("show me Location ", "Your Location in SecondACtivityFragment is ${LatLng!!.latitude} adnd ${LatLng!!.longitude} ")
                }

            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }


    fun isFieldValid(context: Context, editText: EditText): Boolean {
        return if (editText.text.trim().toString().isEmpty()) {
            editText.requestFocus()
            false
        } else
            true
    }


    fun isEmailValid(context: Context, editText: EditText): Boolean {
        return if (android.util.Patterns.EMAIL_ADDRESS.matcher(editText.text.trim().toString()).matches())
            true
        else {
            editText.error = context.getString(R.string.not_valid_email)
            false
        }
    }

    fun isPasswordValid(context: Context, editText: EditText): Boolean {
        return !isNumericPassword(context, editText) && isPasswordLengthValid(context, editText)
    }

    private fun isNumericPassword(context: Context, editText: EditText): Boolean {
        val isNumericOnly = editText.text.trim().toString().toIntOrNull()
        return if (isNumericOnly != null) {
            editText.error = context.getString(R.string.password_is_numbers)
            editText.requestFocus()
            true
        } else
            false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        myNationality.setOnClickListener {

            startActivityForResult(
                    Intent(context, NationalityActivity::class.java),
                    PICK_NATIONALITY_REQUEST
            )
        }

        myCategory.setOnClickListener {
            startActivityForResult(
                    Intent(context, CategoryActivity::class.java),
                    PICK_CATEGORIES
            )
        }
    }

    fun passwordsMatch(context: Context, password1: EditText, password2: EditText): Boolean {
        if (password1.text.trim().toString() == password2.text.trim().toString())
            return true
        else {
            password2.error = context.getString(R.string.password_doesnt_match)
            return false
        }
    }

    private fun isPasswordLengthValid(context: Context, editText: EditText): Boolean {
        return if (editText.text.trim().toString().length > 7)
            true
        else {
            editText.error = context.getString(R.string.password_is_short)
            editText.requestFocus()
            false
        }
    }
}
