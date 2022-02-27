package ivse.dev.myheroapp.services

import ivse.dev.myheroapp.model.CharacterDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelService() {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharactersNameStartWith(limit: Int, nameStartsWith: String):CharacterDataWrapper{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MarvelApiClient::class.java)
                .getCharactersNameStartWith(limit, 0, nameStartsWith)
            response.body()!!
        }
    }

    suspend fun getCharacterByName(name: String): CharacterDataWrapper{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MarvelApiClient::class.java)
                .getCharacterByName(name)
            response.body()!!
        }
    }

    suspend fun getCharacterById(characterId: Int): CharacterDataWrapper{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(MarvelApiClient::class.java)
                .getCharacterById(characterId)
            response.body()!!
        }
    }
}