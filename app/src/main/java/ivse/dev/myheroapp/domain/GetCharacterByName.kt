package ivse.dev.myheroapp.domain

import ivse.dev.myheroapp.model.CharacterDataWrapper
import ivse.dev.myheroapp.repository.MarvelRepository

class GetCharacterByName {
    private val repository = MarvelRepository()
    suspend operator fun invoke(name: String):
            CharacterDataWrapper = repository.getCharacterByName(name)
}