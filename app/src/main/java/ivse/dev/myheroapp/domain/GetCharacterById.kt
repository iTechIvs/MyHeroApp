package ivse.dev.myheroapp.domain

import ivse.dev.myheroapp.model.CharacterDataWrapper
import ivse.dev.myheroapp.repository.MarvelRepository

class GetCharacterById {
    private val repository = MarvelRepository()
    suspend operator fun invoke(characterId: Int):
            CharacterDataWrapper = repository.getCharacterById(characterId)
}