package com.example
/*이미지들에 들어갈 리사이클러뷰 어댑터로, 데이터와 리스트 뷰 사이의 통신을 위한 다리 역할*/

import android.content.Context
import android.net.Uri
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.todaydrawings.R
import java.lang.reflect.Type
import java.text.FieldPosition

class MultiImageAdapter(private val items: ArrayList<Uri>, val context: Context) :
    RecyclerView.Adapter<MultiImageAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    /*홀더의 바인딩*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context).load(item)
            .override(500, 500)
            .into(holder.image)
    }

    /*홀더의 생성*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.multi_image_item, parent, false)
        return ViewHolder(inflatedView)
    }

    /**/
    class ViewHolder (v: View) :RecyclerView.ViewHolder(v) {
        private var view: View = v
        var image = v.findViewById<ImageView>(R.id.image)

        fun bind(listener: View.OnClickListener, item: String) {
            view.setOnClickListener(listener)

        }
    }
    }