package ivse.dev.myheroapp.model

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val comics: ComicList,
    val thumbnail: Image
)
