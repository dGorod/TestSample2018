package ua.dgorod.sample.data

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ua.dgorod.sample.data.api.dto.UserDto
import ua.dgorod.sample.data.mapper.UserDtoMapper

/**
 * Sample jUnit test. In this case for mapper.
 *
 * Note:
 * We instantiate DTO object through JSON serialization.
 * In our app DTO objects may be created only by Retrofit so to block their creating anywhere else
 * DTOs don't have constructors with parameters.
 *
 * Created by dgorodnytskyi on 10/4/18.
 */
@RunWith(JUnit4::class)
class UserDtoMapperTest {

    @Test
    fun testUserDtoMapper() {
        val json = """
            {
              "id": 999,
              "name": "tester",
              "avatarUrl": "http://something.com",
              "htmlUrl": "http://user.com"
            }
        """.trimIndent()

        val dto = Gson().fromJson(json, UserDto::class.java)

        val entity = UserDtoMapper().map(dto)

        with(dto) {
            assertEquals(id, entity.id)
            assertEquals(name, entity.name)
            assertEquals(avatarUrl, entity.avatarUrl)
            assertEquals(htmlUrl, entity.htmlUrl)
        }
    }
}