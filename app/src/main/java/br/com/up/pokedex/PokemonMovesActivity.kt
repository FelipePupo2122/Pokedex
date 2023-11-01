package br.com.up.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAbilitiesAdapter
import br.com.up.pokedex.adapter.PokeMovesAdapter
import br.com.up.pokedex.model.Pokemon
import java.util.ArrayList

class PokemonMovesActivity : AppCompatActivity() {

    // aqui temos a estrututa de movimento da lista, como são diversos pokemons ele retorna os po-
    //kemons de acordo com a a lista vai descendo
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_moves)

        val pokemonMoves = intent.getStringArrayListExtra("pokemonMoves") as ArrayList<String>
        val recyclerViewMoves : RecyclerView = findViewById(R.id.poke_recycler_moves)

        recyclerViewMoves.adapter       = PokeMovesAdapter(pokemonMoves)
        recyclerViewMoves.layoutManager = GridLayoutManager(
                this,
                2
            )
    }
}