package ivse.dev.myheroapp.repository

import ivse.dev.myheroapp.model.CharacterDataWrapper
import ivse.dev.myheroapp.services.MarvelService

class MarvelRepository {
    private val api = MarvelService()
    suspend fun getAllCharacters(limit: Int, nameStartsWith: String): CharacterDataWrapper {
        //Aqui se deberian de guardar los datos en una BD
        return api.getCharactersNameStartWith(limit, nameStartsWith)
    }

    suspend fun getCharacterByName(name: String): CharacterDataWrapper{
        return api.getCharacterByName(name)
    }

    suspend fun getCharacterById(characterId: Int): CharacterDataWrapper{
        return api.getCharacterById(characterId)
    }
}