package com.example.eatit.fragment

import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.eatit.adapter.DashboardRecyclerAdapter
import com.example.eatit.util.ConnectionManager
import com.example.foodizone.R
import model.Restaurant
import org.json.JSONException


/*
 * A simple [Fragment] subclass.
 */
class Dashboard_fragment : Fragment() {


    var restaurantInfoList = arrayListOf<Restaurant>()
    lateinit var recyclerView:RecyclerView

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar

    //lateinit var btnCheckInternet: Button


    /* val resList = arrayListOf(
        "Shimla ",
        "Jain",
        "TGK",
        "THE sara",
        "Ashoka ",
        "Arjunas",
        "Marwadi PLaza",
        "Atul bakery",
        "Arora bakers",
        "samarsingha"
        )*/



    lateinit var recyclerAdapter: DashboardRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView=view.findViewById(R.id.recyclerView)as RecyclerView
        progressLayout=view.findViewById(R.id.progressLayout)
        progressBar=view.findViewById(R.id.progressBar)
       // btnCheckInternet = view.findViewById(R.id.btnCheckInternet)
        /*btnCheckInternet.setOnClickListener{
            if(ConnectionManager().checkConnectivity(activity as Context)){
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success")
                dialog.setMessage("Internet Connection Found")
                dialog.setPositiveButton("ok"){text,listener ->
                    //Nothing
                }
                dialog.setNegativeButton("Cancel"){text,listener ->
                    //Nothing
                }
                dialog.create()
                dialog.show()

            }
            else{

                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet Connection is not Found")
                dialog.setPositiveButton("ok"){text,listener ->

                }
                dialog.setNegativeButton("Cancel"){text,listener ->

                }
                dialog.create()
                dialog.show()
            }
        }*/

        progressBar.visibility=View.VISIBLE
        layoutManager = LinearLayoutManager(activity)

        val queue = Volley.newRequestQueue(activity as Context)

        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"
        if(ConnectionManager().checkConnectivity(activity as Context)) {

            val jsonObjectRequest =
                object : JsonObjectRequest(Method.GET, url, null, Response.Listener {

                    try {
                        progressLayout.visibility=View.GONE

                    //println("Response is $it")
                    //val success = it.getBoolean("success")

                    val obj = it.getJSONObject("data")
                    val success = obj.getBoolean("success")
                    if (success) {
                        val data = obj.getJSONArray("data")
                        for (i in 0 until data.length()) {
                            val restaurantJsonObject = data.getJSONObject(i)
                            val resObject = Restaurant(
                                restaurantJsonObject.getString("id"),
                                restaurantJsonObject.getString("name"),
                                restaurantJsonObject.getString("rating"),
                                restaurantJsonObject.getString("cost_for_one"),
                                restaurantJsonObject.getString("image_url")
                            )
                            restaurantInfoList.add(resObject)
                            recyclerAdapter = DashboardRecyclerAdapter(
                                activity as Context,
                                restaurantInfoList
                            )

                            recyclerView.adapter = recyclerAdapter

                            recyclerView.layoutManager = layoutManager

                        }
                    } else {
                        Toast.makeText(context, "Some error occured!! ", Toast.LENGTH_LONG).show()
                    }
                }catch(e: JSONException){
                        e.printStackTrace()
                    }
                },
                    Response.ErrorListener {

                        Toast.makeText(activity as Context,"Volley error occurred",Toast.LENGTH_SHORT).show()

                    }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-type"] = "application/json"
                        headers["token"] = "cfe475a42a29ac"
                        return headers
                    }

                }
            queue.add(jsonObjectRequest)
        }
        else{
            val dialog=AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection not Found")
            dialog.setPositiveButton("Open Settings"){ text,listener->
                val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()

            }
            dialog.setNegativeButton("Exit"){ text,listener->

                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }
        return view
    }

}
