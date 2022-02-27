package ivse.dev.myheroapp.model

data class SeriesList (
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<SeriesSummary>
)
