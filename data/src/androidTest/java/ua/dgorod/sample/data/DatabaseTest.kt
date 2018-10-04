package ua.dgorod.sample.data

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declare
import ua.dgorod.sample.data.db.MyDatabase
import ua.dgorod.sample.data.db.entity.RepoEntity
import ua.dgorod.sample.data.db.entity.RepoInfoEntity
import ua.dgorod.sample.data.db.entity.UserEntity
import ua.dgorod.sample.data.di.testDbModule
import java.util.*

/**
 * Test to check database I/O operations.
 *
 * Created by dgorodnytskyi on 10/4/18.
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest : KoinTest {

    private val db: MyDatabase by inject()

    @Before
    fun before() {
        declare { single { InstrumentationRegistry.getContext() } }
        StandAloneContext.loadKoinModules(testDbModule)
    }

    @After
    fun after() {
        db.close()
        StandAloneContext.stopKoin()
    }

    @Test
    fun test() {
        val testUser = UserEntity(999L, "tester", "http://someurl.com",
                "http://userurl.com")

        val testRepo = RepoEntity(1L, testUser.id, "test repo", "full test name",
                null, "http://repourl.com", false, "en", 1, Date())

        db.users().insert(testUser)
        db.repositories().insert(testRepo)

        db.repositories().getWithUser(testRepo.id)
                .test()
                .assertComplete()
                .assertValueCount(1)
                .assertValue { compareRepo(it, testRepo) && compareUser(it.user, testUser) }
    }

    private fun compareRepo(actual: RepoInfoEntity, expected: RepoEntity): Boolean {
        with(actual.repo) {
            return id == expected.id && name == expected.name && fullName == expected.fullName &&
                    desc == null && htmlUrl == expected.htmlUrl && fork == expected.fork &&
                    language == expected.language && stars == expected.stars &&
                    createdAt == expected.createdAt
        }
    }

    private fun compareUser(actual: UserEntity, expected: UserEntity): Boolean {
        with(actual) {
            return id == expected.id && name == expected.name && avatarUrl == expected.avatarUrl &&
                    htmlUrl == expected.htmlUrl
        }
    }
}