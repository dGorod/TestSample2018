package ua.dgorod.sample.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.dgorod.sample.data.api.dto.ApiDto
import ua.dgorod.sample.data.api.dto.RepoDto
import ua.dgorod.sample.domain.Const

interface ApiInterface {

    @GET("search/repositories")
    fun getRepositories(@Query("q") query: String,
                        @Query("sort") sorting: String = "stars",
                        @Query("page") page: Int = 1,
                        @Query("per_page") pageSize: Int = Const.DEFAULT_PAGE_SIZE
    ) : Single<ApiDto<RepoDto>>
}