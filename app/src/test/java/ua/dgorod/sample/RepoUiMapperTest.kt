package ua.dgorod.sample

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ua.dgorod.sample.domain.model.Repo
import ua.dgorod.sample.domain.model.User
import ua.dgorod.sample.mapper.RepoUiMapper
import java.util.*

/**
 * Created by dgorodnytskyi on 10/10/18.
 */
@RunWith(JUnit4::class)
class RepoUiMapperTest {

    @Test
    fun testRepoUiMapper() {
        val user = User(1L, "tester", null, "http://something.com")
        val repo = Repo(99L, "test repo", "repository", null, user,
                "http://repository.com", false, null, Date())

        val repoUi = RepoUiMapper().map(repo)

        with(repoUi) {
            assertEquals(repo.id, id)
            assertEquals(repo.name, name)

            assertNotNull(owner)
            assertEquals(user.id, owner.id)
            assertEquals(user.name, owner.name) // etc.
        }
    }
}