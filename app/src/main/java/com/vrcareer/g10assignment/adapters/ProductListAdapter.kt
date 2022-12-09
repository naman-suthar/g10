package com.vrcareer.g10assignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.vrcareer.g10assignment.R
import com.vrcareer.g10assignment.databinding.SingleProductListItemLayoutBinding
import com.vrcareer.g10assignment.model.Product
import org.w3c.dom.Text

class ProductListAdapter(val onClicked: (Product)->Unit): RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private val mProductList = ArrayList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = SingleProductListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currProduct = mProductList[position]
        holder.productName.text = currProduct.title.toString()
        holder.productPrice.text ="\u20B9 ${currProduct.price.toString()}"
        Glide.with(holder.productDP.context).load(currProduct.images?.get(0).toString()).into(holder.productDP)
        holder.productDesc.text = currProduct.description?.take(40).toString() + "..."
        holder.productCard.setOnClickListener {
            onClicked(currProduct)
        }
    }

    override fun getItemCount(): Int = mProductList.size

    inner class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        val productName = view.findViewById<TextView>(R.id.tv_product_name)
        val productDP = view.findViewById<ImageView>(R.id.img_product_display_photo)
        val productPrice = view.findViewById<TextView>(R.id.tv_product_price)
        val productCard = view.findViewById<MaterialCardView>(R.id.mc_product)
        val productDesc = view.findViewById<TextView>(R.id.tv_product_desc)
    }

    fun updateList(productList: List<Product>){
        mProductList.clear()
        mProductList.addAll(productList)
        notifyDataSetChanged()
    }
}