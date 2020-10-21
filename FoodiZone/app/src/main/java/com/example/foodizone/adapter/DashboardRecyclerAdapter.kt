package com.example.eatit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.foodizone.R
import com.example.foodizone.activity.DescriptionActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_dashboard_single_row.view.*
import model.Restaurant

class DashboardRecyclerAdapter(val context : Context, val itemList:ArrayList<Restaurant>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)

        return DashboardViewHolder(view)
    }



    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {

        val restaurant = itemList[position]
        holder.txtRestaurantName.text = restaurant.restaurantName
        holder.txtRating.text = restaurant.restaurantRating
        holder.txtPrice.text = restaurant.restaurantCost_For_One
        //holder.imgRestaurantImage.setImageResource(restaurant.restaurantImage)

        Picasso.get().load(restaurant.restaurantImage).error(R.drawable.default_restaurant_image)
            .into(holder.imgRestaurantImage)

        holder.rlContent.setOnClickListener{
            val intent= Intent(context, DescriptionActivity::class.java)
            intent.putExtra("restaurant_id",restaurant.restaurantId)
            intent.putExtra("restaurant_name",restaurant.restaurantName)
            context.startActivity(intent)
        }

    }

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //val txtRestuarantId :TextView = view.findViewById(R.id.txtRestaurantId)
        val txtRestaurantName: TextView = view.findViewById(R.id.txtRestaurantName)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val txtRating: TextView = view.findViewById(R.id.txtRating)
        val imgRestaurantImage: ImageView = view.findViewById(R.id.imgRestaurantImage)
       // val imgFav: ImageButton = view.findViewById(R.id.imgFav)
       val rlContent: RelativeLayout = view.findViewById(R.id.rlContent)





    }


}