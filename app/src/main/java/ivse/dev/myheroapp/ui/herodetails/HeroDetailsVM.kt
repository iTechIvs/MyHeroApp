package ivse.dev.myheroapp.ui.herodetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ivse.dev.myheroapp.domain.GetCharacterByName
import ivse.dev.myheroapp.model.CharacterDataWrapper
import ivse.dev.myheroapp.model.MarvelCharacter
import kotlinx.coroutines.launch

class HeroDetailsVM(): ViewModel() {
    private var getCharacterByName = GetCharacterByName()
    var marvelCharacter = MutableLiveData<MarvelCharacter>()
    var loading = MutableLiveData<Boolean>(true)
    var returnForEmpty = MutableLiveData<Boolean>(false)

    fun onCreate(name: String){
        loading.postValue(true)
        viewModelScope.launch {
            val wrapper: CharacterDataWrapper = getCharacterByName(name)
            if (wrapper.status == "Ok"){
                val container = wrapper.data
                if (container.total == 1){
                    loading.postValue(false)
                    marvelCharacter.postValue(container.results.first())
                } else{
                    loading.postValue(true)
                    returnForEmpty.postValue(true)
                }
            } else loading.postValue(false)
        }
    }
}