package ivse.dev.myheroapp.domain

import ivse.dev.myheroapp.model.CharacterDataWrapper
import ivse.dev.myheroapp.repository.MarvelRepository
import javax.inject.Inject

class GetCharactersNameStartWith
@Inject constructor(private val repository: MarvelRepository) {

    suspend operator fun invoke(limit: Int, nameStartsWith: String):
            CharacterDataWrapper = repository.getAllCharacters(limit,nameStartsWith)
}