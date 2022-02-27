package ivse.dev.myheroapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ivse.dev.myheroapp.databinding.FragmentHomeNavigationBinding
import ivse.dev.myheroapp.ui.adapters.ItemHeroAdapter
@AndroidEntryPoint
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