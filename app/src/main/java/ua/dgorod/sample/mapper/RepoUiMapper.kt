package ua.dgorod.sample.mapper

import ua.dgorod.sample.domain.Mapper
import ua.dgorod.sample.domain.model.Repo
import ua.dgorod.sample.model.RepoUiModel
import ua.dgorod.sample.model.UserUiModel

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
class RepoUiMapper : Mapper<Repo, RepoUiModel> {

    override fun map(from: Repo): RepoUiModel {
        val user = with(from.owner) {
            UserUiModel(id, name, avatarUrl, htmlUrl)
        }

        return with(from) {
            RepoUiModel(id, name, fullName, desc, user, htmlUrl, isFork, language)
        }
    }
}