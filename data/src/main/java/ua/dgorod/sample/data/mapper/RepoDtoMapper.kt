package ua.dgorod.sample.data.mapper

import ua.dgorod.sample.data.api.dto.RepoDto
import ua.dgorod.sample.data.db.entity.RepoEntity
import ua.dgorod.sample.domain.Mapper

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
class RepoDtoMapper: Mapper<RepoDto, RepoEntity> {

    override fun map(from: RepoDto): RepoEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}