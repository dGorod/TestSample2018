package ua.dgorod.sample.data.mapper

import ua.dgorod.sample.data.api.dto.UserDto
import ua.dgorod.sample.data.db.entity.UserEntity
import ua.dgorod.sample.domain.Mapper

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
class UserDtoMapper: Mapper<UserDto, UserEntity> {

    override fun map(from: UserDto): UserEntity {
        return with(from) {
            UserEntity(id, name, avatarUrl, htmlUrl)
        }
    }
}