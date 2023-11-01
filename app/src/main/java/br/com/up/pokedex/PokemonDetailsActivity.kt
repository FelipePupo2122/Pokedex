package br.com.up.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAbilitiesAdapter
import br.com.up.pokedex.adapter.PokeStatsAdapter
import br.com.up.pokedex.adapter.PokeTypeAdapter
import br.com.up.pokedex.network.PokeApi
import com.squareup.picasso.Picasso
import java.util.stream.Collectors

class PokemonDetailsActivity : AppCompatActivity() {


    //aqui nós temos os usos do jetpack compose, nos alinhamentos dos pokemons e de suas informações
    // utilizamos muito do layout manager para dar um padrão na estrutura de lista da pokedex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokeName : TextView = findViewById(R.id.pokemonName)
        val button : Button     = findViewById(R.id.toMoves)
        val recyclerViewStats : RecyclerView = findViewById(R.id.poke_recycler_stats)
        val recyclerViewType  : RecyclerView = findViewById(R.id.poke_recycler_types)
        val recyclerViewAbilities : RecyclerView = findViewById(R.id.poke_recycler_abilities)

        val pokemonUrl = intent.getStringExtra("pokemonUrl")
        val pokemonId  = this.getPokemonId(pokemonUrl!!)

        PokeApi().getPokemonById(pokemonId) { pokemon ->
            this.putImageOfPokemon(pokemonId)
            pokeName.text = pokemon?.name

            recyclerViewStats.adapter       = PokeStatsAdapter(pokemon!!)
            recyclerViewStats.layoutManager = GridLayoutManager(this, 2)

            recyclerViewType.adapter       = PokeTypeAdapter(pokemon, findViewById(R.id.pokeType))
            recyclerViewType.layoutManager = LinearLayoutManager(this)

            recyclerViewAbilities.adapter       = PokeAbilitiesAdapter(pokemon, findViewById(R.id.pokeAbilities))
            recyclerViewAbilities.layoutManager = GridLayoutManager(this, 2)

            button.setOnClickListener {
                val intent = Intent(
                        this,
                        PokemonMovesActivity::class.java
                    )

                intent.putStringArrayListExtra(
                    "pokemonMoves",
                    ArrayList(
                        pokemon.moves.map {
                                pokemonMoves ->  pokemonMoves.move.name
                            }))

                startActivity(intent)
            }
        }
    }

    // aqui temos o retorno da imagem do pokemon via API

    private fun putImageOfPokemon(id: String) {
        val imageView : ImageView = findViewById(R.id.pokemon_png)
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

        Picasso.get().load(url).into(imageView)
    }

    // aqui cada pokemon tem um id e retornamos ele somente ao clicar no pokemon
    private fun getPokemonId(url: String): String {
        return url.replace(
                "https://pokeapi.co/api/v2/pokemon/",
                ""
            ).replace("/","")
    }
}