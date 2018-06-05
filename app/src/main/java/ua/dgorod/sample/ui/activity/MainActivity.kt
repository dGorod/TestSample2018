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
    private val layoutManager = GridLayoutManager(this, 2)

    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewModel) {
            getRepositories(0, true)
            loadingStatus.observe(this@MainActivity, loadingObserver)
            reposList.observe(this@MainActivity, photosListObserver)
        }
    }

    override fun onContentChanged() {
        super.onContentChanged()

        adapter = RepoAdapter(itemClickListener)

        vRepos.layoutManager = layoutManager
        vRepos.adapter = adapter
    }

    private val loadingObserver = object : Observer<Boolean> {
        override fun onChanged(status: Boolean?) {
            vProgress.visibility = if (status == true) View.VISIBLE else View.INVISIBLE
        }
    }

    private val photosListObserver = object : Observer<Parcel<List<RepoUiModel>>> {
        override fun onChanged(data: Parcel<List<RepoUiModel>>?) {
            when (data?.status) {
                Parcel.Status.SUCCESS -> {
                    data.content?.let {
                        adapter.submitList(it as PagedList<RepoUiModel>)
                        adapter.notifyDataSetChanged() //TODO: optimize for proper notifications
                        vEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                    }
                }
                Parcel.Status.ERROR -> longToast(R.string.error_general)
            }
        }
    }

    private val itemClickListener: (View) -> Unit = {
        val index = vRepos.getChildAdapterPosition(it)
        val photoView = it.findViewById(R.id.vAvatar) as ImageView
        //DetailsActivity.start(this, adapter.data.elementAt(index), photoView)
    }
}
