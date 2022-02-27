package ivse.dev.myheroapp.ui.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ivse.dev.myheroapp.R
import ivse.dev.myheroapp.model.ComicList
import java.lang.Exception

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty())
        Picasso.get()
            .load(imageUrl)
            .error(R.drawable.ic_image_not_supported)
            .into(view, object: Callback {
                override fun onSuccess() {

                }
                override fun onError(e: Exception?) {
                    Log.e("Error", e?.message.toString())
                }
            })
}