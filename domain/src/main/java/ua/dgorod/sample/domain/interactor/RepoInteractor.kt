package ua.dgorod.sample.domain.interactor

import io.reactivex.Flowable
import ua.dgorod.sample.domain.model.Repo
import ua.dgorod.sample.domain.repository.RepoRepository

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
class RepoInteractor(private val repo: RepoRepository) {

    fun getAll(page: Int): Flowable<List<Repo>> = repo.getAll(page)
}