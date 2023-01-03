package space.lobanovi.taskapp.extenssion

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import space.lobanovi.taskapp.R

fun View.loadImage(url: String){
    Glide.with(this).load(url).placeholder(R.drawable.profiles).circleCrop().into(this as ImageView)
}
