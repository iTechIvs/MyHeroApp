package ivse.dev.myheroapp.model

data class ComicList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<ComicSummary>
){
    override fun toString(): String {
        var data = ""
        for(item in items) data += "$item\n"
        return data
    }
}