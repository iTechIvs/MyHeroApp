package ivse.dev.myheroapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ivse.dev.myheroapp.model.MarvelCharacter
import ivse.dev.myheroapp.databinding.ItemHeroBinding
import ivse.dev.myheroapp.ui.home.HomeNavigationDirections

class ItemHeroAdapter(var characters: List<MarvelCharacter>): RecyclerView.Adapter<ItemHeroAdapter.ViewHolder>(){

    inner class ViewHolder (var binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.character = character
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(HomeNavigationDirections.goToDetails(
                character.id,
                character.name,
                character.description,
                character.thumbnail.getImageUrl())
            )
        }
    }

    override fun getItemCount(): Int = characters.size
}
