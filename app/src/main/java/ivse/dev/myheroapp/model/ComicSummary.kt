package ivse.dev.myheroapp.model

data class ComicSummary (
    val resourceURI: String,
    val name: String
){
    override fun toString(): String {
        return name
    }
}
