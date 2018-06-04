package ua.dgorod.sample.data.mapper

import ua.dgorod.sample.data.db.entity.RepoInfoEntity
import ua.dgorod.sample.domain.Mapper
import ua.dgorod.sample.domain.model.Repo

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
class RepoEntityMapper: Mapper<RepoInfoEntity, Repo> {

    override fun map(from: RepoInfoEntity): Repo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}