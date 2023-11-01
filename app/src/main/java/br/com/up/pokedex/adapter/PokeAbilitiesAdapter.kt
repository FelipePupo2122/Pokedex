package br.com.up.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.R
import br.com.up.pokedex.model.Pokemon

class PokeAbilitiesAdapter(private val pokemon: Pokemon, private val pokeAbility : TextView) : RecyclerView.Adapter<PokeAbilitiesAdapter.PokeAbilitiesViewHolder>() {

    // como na API ele retorna os dados e pokemons juntos aqui tivemos que trabalhar para ele
    //somente retornar conforme você seleciona o pokemon para visualizar sua habilidade

    inner class PokeAbilitiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeAbilitiesViewHolder {
        pokeAbility.text = "Habilidades"
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = layoutInflater.inflate(
                R.layout.poke_text_view,
                parent,
                false   // aqui para não retornar com a lista
            )
        return PokeAbilitiesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PokeAbilitiesViewHolder, position: Int) {
        val data : TextView = holder.itemView.findViewById(R.id.data)
        data.text = pokemon.abilities[position].ability.name
    }

    override fun getItemCount(): Int {
        return pokemon.abilities.size
    }
}