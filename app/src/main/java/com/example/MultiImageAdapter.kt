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
//items: 바인딩 될 데이터 객체 배열

    RecyclerView.Adapter<MultiImageAdapter.ViewHolder>() {

    //어댑터로 바인딩된 아이템 개수 반환
    override fun getItemCount(): Int = items.size

    /*데이터를 순서대로 바인딩 -- 포지션(인덱스)값을 활용 가능,
    * 현재는 모든 값 바인딩*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Glide.with(context)
            .load(item)
            .override(500, 500)
            .into(holder.image)
    }

    /*바인딩 당할 item xml 파일명 지정 --R.layout.multi_image_item */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.multi_image_item, parent, false)
        return ViewHolder(inflatedView)
    }

    /*데이터가 바인딩 당할 Item XML 내부의 elements 들*/
    inner class ViewHolder (v: View) :RecyclerView.ViewHolder(v) {
        private var view: View = v
        var image = v.findViewById<ImageView>(R.id.image)

        fun bind(listener: View.OnClickListener, item: String) {
            view.setOnClickListener(listener)

        }
    }
    }