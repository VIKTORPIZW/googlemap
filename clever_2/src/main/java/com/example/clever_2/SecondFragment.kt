package com.example.clever_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val title = view.findViewById<TextView>(R.id.txtTitleLarge)
        val description = view.findViewById<TextView>(R.id.txtDescriptionLarge)

        title.text = param1
        description.text = param2
        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val fragment:Fragment = this
            fragment.activity?.supportFragmentManager?.popBackStack()
        }
        val buttonExit: Button = view.findViewById(R.id.buttonExit)
        buttonExit.setOnClickListener {
            activity?.finish()

        }
        return view
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)


                }
            }
    }
}
