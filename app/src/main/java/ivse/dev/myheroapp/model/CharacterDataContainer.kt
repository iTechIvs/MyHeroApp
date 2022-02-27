package ivse.dev.myheroapp.model

data class CharacterDataContainer(
    val offset: Int,
    val limit : Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<MarvelCharacter>
)
