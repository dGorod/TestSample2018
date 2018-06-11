package ua.dgorod.sample.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import ua.dgorod.sample.R
import ua.dgorod.sample.model.RepoUiModel

/**
 * Created by dgorodnytskyi on 6/11/18.
 */
class DetailsActivity : AppCompatActivity() {

    companion object {
        private val EXTRA_REPO = "extra_repo"
        private val EXTRA_TRANSITION_NAME = "extra_transition_name"

        fun start(activity: AppCompatActivity, repo: RepoUiModel, transitionView: View) {
            val transitionName = ViewCompat.getTransitionName(transitionView)!!
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, transitionView, transitionName)

            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(EXTRA_REPO, repo)
            intent.putExtra(EXTRA_TRANSITION_NAME, transitionName)

            activity.startActivity(intent, options.toBundle())
        }
    }

    private lateinit var repo: RepoUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onContentChanged() {
        super.onContentChanged()

        intent.getStringExtra(EXTRA_TRANSITION_NAME)?.let { vAvatar.transitionName = it }

        repo = intent.getParcelableExtra(EXTRA_REPO)

        fillData()
        setListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fillData() {
        with(repo) {
            vName.text = name
            vAuthor.text = String.format(getString(R.string.author), owner.name)

            Picasso.with(this@DetailsActivity)
                    .load(owner.avatarUrl)
                    .error(R.drawable.ic_cloud_off_24dp)
                    .into(vAvatar, photoLoadingCallback)
        }
    }

    private fun setListeners() {
        vShare.setOnClickListener {
            Intent(Intent.ACTION_SEND).run {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.repo_url))
                putExtra(Intent.EXTRA_TEXT, repo.htmlUrl)
                startActivity(Intent.createChooser(this, getString(R.string.share_this)))
            }
        }
    }

    private val photoLoadingCallback = object: Callback {
        override fun onSuccess() { supportStartPostponedEnterTransition() }
        override fun onError() { supportStartPostponedEnterTransition() }
    }
}