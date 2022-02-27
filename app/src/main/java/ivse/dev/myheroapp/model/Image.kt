package ivse.dev.myheroapp.model

data class Image(val path: String, val extension: String){
    fun getImageUrl() = "$path.$extension"
}
