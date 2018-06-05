package ua.dgorod.sample.ui.adapter.viewholder

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*
import ua.dgorod.sample.R
import ua.dgorod.sample.extension.inflate
import ua.dgorod.sample.model.RepoUiModel

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
class RepoViewHolder(
        parent: ViewGroup,
        clickListener: (View) -> Unit
): RecyclerView.ViewHolder(parent.inflate(R.layout.item_repo)) {

    init {
        itemView.setOnClickListener(clickListener)
    }

    fun bind(data: RepoUiModel) {
        with(itemView) {
            ViewCompat.setTransitionName(vAvatar, data.id.toString())

            vName.text = data.name
            vAuthor.text = data.owner.name

            Picasso.with(context)
                    .load(data.owner.avatarUrl)
                    .error(R.drawable.ic_cloud_off_24dp)
                    .into(vAvatar, photoLoadingCallback)
        }
    }

    fun clear() {
        with(itemView) {
            vName.text = "loading..."
            vAuthor.text = null
            vAvatar.setImageResource(R.drawable.ic_cloud_off_24dp)
        }
    }

    private val photoLoadingCallback = object: Callback {
        override fun onSuccess() {
            itemView.vAvatar.scaleType = ImageView.ScaleType.CENTER_CROP
            itemView.vAvatar.setPadding(0)
        }
        override fun onError() {
            itemView.vAvatar.scaleType = ImageView.ScaleType.FIT_CENTER
            itemView.vAvatar.setPadding(itemView.resources.getDimensionPixelSize(R.dimen.indent_l))
        }
    }
}