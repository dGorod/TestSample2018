package ua.dgorod.sample.data.mapper

import ua.dgorod.sample.data.db.entity.RepoInfoEntity
import ua.dgorod.sample.domain.Mapper
import ua.dgorod.sample.domain.model.Repo
import ua.dgorod.sample.domain.model.User

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
class RepoEntityMapper: Mapper<RepoInfoEntity, Repo> {

    override fun map(from: RepoInfoEntity): Repo {
        val user = with(from.user) {
            User(id, name, avatarUrl, htmlUrl)
        }

        return with(from.repo) {
            Repo(id, name, fullName, desc, user, htmlUrl, fork, language, createdAt)
        }
    }
}