package ivse.dev.myheroapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import ivse.dev.myheroapp.model.MarvelCharacter
import ivse.dev.myheroapp.databinding.FragmentHomeNavigationBinding
import ivse.dev.myheroapp.model.CharacterDataWrapper
import ivse.dev.myheroapp.ui.adapters.ItemHeroAdapter
import ivse.dev.myheroapp.services.MarvelApiClient
import ivse.dev.myheroapp.services.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeNavigation : Fragment() {
    private var _binding: FragmentHomeNavigationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeNavigationVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeNavigationBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        //binding.homeNavigationRv.layoutManager = GridLayoutManager(context, 2)
        binding.homeNavigationRv.layoutManager = LoopingLayoutManager(
            requireContext(),
            LoopingLayoutManager.HORIZONTAL,
            false
        )

        val letter = getLetter()
        viewModel.onCreate(10, letter)
        viewModel.liveDataCharacters.observe(viewLifecycleOwner) {
            binding.homeNavigationRv.adapter = ItemHeroAdapter(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loading = it
        }

        return binding.root
    }
    private fun getLetter():String{
        val alphabet = 'a'..'z'
        val randomLetter = alphabet.random()
        return randomLetter.lowercase()
    }
}