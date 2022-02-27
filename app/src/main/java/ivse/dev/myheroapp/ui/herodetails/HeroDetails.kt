package ivse.dev.myheroapp.ui.herodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ivse.dev.myheroapp.R
import ivse.dev.myheroapp.databinding.FragmentHeroDetailsBinding
import ivse.dev.myheroapp.ui.home.HomeNavigationVM

class HeroDetails : Fragment() {
    private var _binding: FragmentHeroDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HeroDetailsVM by viewModels()
    private val args : HeroDetailsArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHeroDetailsBinding.inflate(layoutInflater,container, false)
        binding.lifecycleOwner = this

        binding.url = args.url
        viewModel.onCreate(args.name)
        binding.viewModel = viewModel

        viewModel.returnForEmpty.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(HeroDetailsDirections.returnToHome())
        }
        return binding.root
    }
}