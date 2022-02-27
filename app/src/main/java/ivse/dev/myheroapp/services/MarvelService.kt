package ivse.dev.myheroapp.services

import ivse.dev.myheroapp.model.CharacterDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarvelService @Inject constructor(var api: MarvelApiClient) {

    suspend fun getCharactersNameStartWith(limit: Int, nameStartsWith: String):CharacterDataWrapper{
        return withContext(Dispatchers.IO){
            val response = api.getCharactersNameStartWith(limit, 0, nameStartsWith)
            response.body()!!
        }
    }

    suspend fun getCharacterByName(name: String): CharacterDataWrapper{
        return withContext(Dispatchers.IO){
            val response = api.getCharacterByName(name)
            response.body()!!
        }
    }

    suspend fun getCharacterById(characterId: Int): CharacterDataWrapper{
        return withContext(Dispatchers.IO){
            val response = api.getCharacterById(characterId)
            response.body()!!
        }
    }
}