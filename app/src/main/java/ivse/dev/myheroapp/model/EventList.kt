package ivse.dev.myheroapp.model

data class EventList (
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<EventSummary>
)
