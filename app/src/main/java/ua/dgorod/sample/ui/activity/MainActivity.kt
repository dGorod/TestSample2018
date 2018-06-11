package ua.dgorod.sample.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.android.viewModel
import ua.dgorod.sample.R
import ua.dgorod.sample.domain.Const
import ua.dgorod.sample.extension.longToast
import ua.dgorod.sample.model.RepoUiModel
import ua.dgorod.sample.ui.adapter.RepoAdapter
import ua.dgorod.sample.util.EndlessRecyclerOnScrollListener
import ua.dgorod.sample.viewmodel.MainViewModel
import ua.dgorod.sample.viewmodel.Parcel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val layoutManager = GridLayoutManager(this, 2)

    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewModel) {
            getRepositories(0)
            emptyState.observe(this@MainActivity, emptyObserver)
            loadingStatus.observe(this@MainActivity, loadingObserver)
            reposList.observe(this@MainActivity, reposObserver)
        }
    }

    override fun onContentChanged() {
        super.onContentChanged()

        adapter = RepoAdapter(itemClickListener)

        vRepos.layoutManager = layoutManager
        vRepos.addOnScrollListener(reposScrollListener)
        vRepos.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.menu_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val emptyObserver = Observer<Boolean> { isEmpty ->
        vEmpty.visibility = if (isEmpty == true) View.VISIBLE else View.GONE
    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
        vProgress.visibility = if (isLoading == true) View.VISIBLE else View.INVISIBLE
    }

    private val reposObserver = Observer<Parcel<List<RepoUiModel>>> { data ->
        when (data?.status) {
            Parcel.Status.SUCCESS -> adapter.submitList(data.content)
            Parcel.Status.ERROR -> longToast(R.string.error_general)
        }
    }

    private val reposScrollListener = object : EndlessRecyclerOnScrollListener(
            layoutManager, Const.DEFAULT_PAGE_SIZE) {
        override fun onLoadMore(page: Int) {
            viewModel.getRepositories(page)
        }
    }

    private val itemClickListener: (View) -> Unit = {
        val index = vRepos.getChildAdapterPosition(it)
        val repoView = it.findViewById(R.id.vAvatar) as ImageView
        DetailsActivity.start(this, adapter.getElement(index), repoView)
    }
}
