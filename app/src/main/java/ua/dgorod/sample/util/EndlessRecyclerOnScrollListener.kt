package ua.dgorod.sample.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * [RecyclerView] scroll listener with callback to load more data when reaching list end.
 *
 * @author Dmytro Gorodnytskyi
 * on 14-Dec-17.
 */
abstract class EndlessRecyclerOnScrollListener(
        private val layoutManager: LinearLayoutManager,
        private val pageSize: Int
) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true

    var visibleThreshold = 5

    abstract fun onLoadMore(page: Int)

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading && totalItemCount > previousTotal) {
            previousTotal = totalItemCount
            loading = false
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loading = true
            onLoadMore(totalItemCount / pageSize + 1)
        }
    }
}