package br.com.up.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAdapter
import br.com.up.pokedex.model.Pokemon
import br.com.up.pokedex.network.PokeApi
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class MainActivity : AppCompatActivity() {

    // aqui eu realizo a criação do retorno da API em forma de lista com 3 pokemons por linha
    // e todos com o mesmo tamanho para não ocupar tanto espaço na tela e todos em uma box padronizada
    // para não perdermos o padrão e utilizarmos da arquitetura do jetpack


    private var pokemonList: List<Pokemon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(
                R.id.poke_recycler
            )

        val inputTextSearchPokemon : TextInputEditText = findViewById(
                R.id.text_input_search_pokemon
            )

        inputTextSearchPokemon.addTextChangedListener(
            object : TextWatcher{

                // aqui é o padrão do pokemon em sí e suas habilidades/id/nome,etc..
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(Objects.nonNull(pokemonList)){

                        val filteredList = pokemonList!!
                            .filter { pokemon->
                            pokemon.name.contains(value!!)
                        }
                        recyclerView.adapter = PokeAdapter(filteredList)
                        { pokemon ->
                            callPokemonDetail(pokemon)
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            }
        )

        recyclerView.layoutManager = GridLayoutManager(
                this,
                3
            )

        PokeApi().getPokemons{ pokemons->
            pokemonList          = pokemons
            recyclerView.adapter = PokeAdapter(pokemons!!)
            { pokemon ->
                callPokemonDetail(pokemon)
            }
        }
    }

    //aqui temos o retorno dos detalhes do pokemon, ele retorna esses dados da Api e é inserido
    // como podemos dizer alinhado ao clicar no pokemon
    fun callPokemonDetail(pokemon: Pokemon){

        val intent = Intent(
                this,
                PokemonDetailsActivity::class.java
            )
        intent.putExtra(
                "pokemonUrl",
                pokemon.url
            )

        startActivity(intent)
    }
}