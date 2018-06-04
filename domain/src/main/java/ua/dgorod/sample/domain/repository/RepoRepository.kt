package ua.dgorod.sample.domain.repository

import io.reactivex.Flowable
import io.reactivex.Maybe
import ua.dgorod.sample.domain.model.Repo

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
interface RepoRepository {

    fun getAll(page: Int): Flowable<List<Repo>>

    fun get(id: Long): Maybe<Repo>
}