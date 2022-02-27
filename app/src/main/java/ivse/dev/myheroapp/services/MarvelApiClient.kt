package ivse.dev.myheroapp.services

import ivse.dev.myheroapp.model.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiClient {
    @GET("v1/public/characters")
    suspend fun getCharactersNameStartWith(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0,
        @Query("nameStartsWith") nameStartsWith: String? = null
    ):Response<CharacterDataWrapper>

    @GET("v1/public/characters")
    suspend fun getCharacterByName(
        @Query("name") name: String? = null
    ):Response<CharacterDataWrapper>

    @GET("v1/public/characters")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int
    ):Response<CharacterDataWrapper>

}