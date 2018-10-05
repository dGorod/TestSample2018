package ua.dgorod.sample.data

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declare
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import ua.dgorod.sample.data.api.ApiInterface
import ua.dgorod.sample.data.api.dto.ApiDto
import ua.dgorod.sample.data.api.dto.RepoDto
import ua.dgorod.sample.data.di.testDbModule
import ua.dgorod.sample.data.di.testReposModule
import ua.dgorod.sample.domain.model.Repo
import ua.dgorod.sample.domain.repository.RepoRepository

/**
 * Created by dgorodnytskyi on 10/4/18.
 */
@RunWith(AndroidJUnit4::class)
class RepoRepositoryTest : KoinTest {

    private val repoRepository: RepoRepository by inject()

    private val json = """
            {
              "total_count": 3224,
              "incomplete_results": false,
              "items": [
                {
                  "id": 51148780,
                  "name": "android-architecture",
                  "full_name": "googlesamples/android-architecture",
                  "owner": {
                    "id": 7378196,
                    "avatar_url": "https://avatars3.githubusercontent.com/u/7378196?v=4",
                    "html_url": "https://github.com/googlesamples"
                  },
                  "html_url": "https://github.com/googlesamples/android-architecture",
                  "description": "A collection of samples.",
                  "fork": false,
                  "created_at": "2016-02-05T13:42:07Z",
                  "stargazers_count": 29618,
                  "language": null
                },
                {
                  "id": 55076063,
                  "name": "Awesome-Hacking",
                  "full_name": "Hack-with-Github/Awesome-Hacking",
                  "owner": {
                    "id": 18143746,
                    "avatar_url": "https://avatars3.githubusercontent.com/u/18143746?v=4",
                    "html_url": "https://github.com/Hack-with-Github"
                  },
                  "html_url": "https://github.com/Hack-with-Github/Awesome-Hacking",
                  "description": "A collection of various awesome lists",
                  "fork": false,
                  "created_at": "2016-03-30T15:47:10Z",
                  "stargazers_count": 26136,
                  "language": null
                }
              ]
            }
        """.trimIndent()

    val type = object : TypeToken<ApiDto<RepoDto>>() {}.type
    private val testResponse = Gson().fromJson<ApiDto<RepoDto>>(json, type)

    @Before
    fun before() {
        val query = "android created:>2016-01-01 stars:>100"

        val mockedApi = mock(ApiInterface::class.java)
        `when`(mockedApi.getRepositories(query, page = 1)).thenReturn(Single.just(testResponse))

        declare { single { InstrumentationRegistry.getContext() } }
        declare { single { mockedApi } }
        StandAloneContext.loadKoinModules(testDbModule, testReposModule)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun testRepoRepository() {
        val subscriber = TestSubscriber<List<Repo>>()
        repoRepository.getAll(1).subscribe(subscriber)

        with(subscriber) {
            assertSubscribed()
            assertNoErrors()
            awaitCount(2) // first event is empty from clean Room, then with inserted data

            val results = values()
            assertEquals(2, results.size)

            val first = results[0][0]
            val expected = testResponse.items[0]

            assertTrue(first.id == expected.id && first.name == expected.name) // etc.
        }
    }
}