package ua.dgorod.sample.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.android.viewModel
import ua.dgorod.sample.R
import ua.dgorod.sample.extension.longToast
import ua.dgorod.sample.model.RepoUiModel
import ua.dgorod.sample.ui.adapter.RepoAdapter
import ua.dgorod.sample.viewmodel.MainViewModel
import ua.dgorod.sample.viewmodel.Parcel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewModel) {
            getRepositories(1)
            emptyState.observe(this@MainActivity, emptyObserver)
            loadingStatus.observe(this@MainActivity, loadingObserver)
            reposList.observe(this@MainActivity, reposObserver)
        }
    }

    override fun onContentChanged() {
        super.onContentChanged()

        adapter = RepoAdapter(itemClickListener)

        vRepos.layoutManager = GridLayoutManager(this, 2)
        vRepos.adapter = adapter
    }

    private val emptyObserver = Observer<Boolean> { isEmpty ->
        vEmpty.visibility = if (isEmpty == true) View.VISIBLE else View.GONE
    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
        vProgress.visibility = if (isLoading == true) View.VISIBLE else View.INVISIBLE
    }

    private val reposObserver = Observer<Parcel<List<RepoUiModel>>> { data ->
        when (data?.status) {
            Parcel.Status.SUCCESS -> adapter.submitList(data.content as PagedList<RepoUiModel>)
            Parcel.Status.ERROR -> longToast(R.string.error_general)
        }
    }

    private val itemClickListener: (View) -> Unit = {
        val index = vRepos.getChildAdapterPosition(it)
        val repoView = it.findViewById(R.id.vAvatar) as ImageView
        //DetailsActivity.start(this, adapter.data.elementAt(index), photoView)
    }
}
