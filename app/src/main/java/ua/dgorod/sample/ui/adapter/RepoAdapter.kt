package ua.dgorod.sample.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import ua.dgorod.sample.model.RepoUiModel
import ua.dgorod.sample.ui.adapter.viewholder.RepoViewHolder

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
class RepoAdapter(
        private val clickListener: (View) -> Unit
) : PagedListAdapter<RepoUiModel, RepoViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoUiModel>() {
            override fun areItemsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoUiModel, newItem: RepoUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(parent, clickListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) } ?: holder.clear()
    }
}