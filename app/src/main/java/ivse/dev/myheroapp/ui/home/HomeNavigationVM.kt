package ivse.dev.myheroapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ivse.dev.myheroapp.domain.GetCharactersNameStartWith
import ivse.dev.myheroapp.model.CharacterDataWrapper
import ivse.dev.myheroapp.model.MarvelCharacter
import kotlinx.coroutines.launch

class HomeNavigationVM() : ViewModel() {
    private var getCharactersNameStartWith = GetCharactersNameStartWith()
    var liveDataCharacters = MutableLiveData<List<MarvelCharacter>>()
    var loading = MutableLiveData<Boolean>(true)

    fun onCreate(limit: Int, nameStartsWith: String){
        viewModelScope.launch {
            loading.postValue(true)
            val wrapper: CharacterDataWrapper = getCharactersNameStartWith(limit,nameStartsWith)
            if (wrapper.status == "Ok"){
                val container = wrapper.data
                if (container.total > 0){
                    loading.postValue(false)
                    liveDataCharacters.postValue(container.results)
                } else loading.postValue(false)
            }else loading.postValue(false)
        }
    }
}