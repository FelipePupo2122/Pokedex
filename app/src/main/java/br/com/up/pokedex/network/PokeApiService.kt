package br.com.up.pokedex.network

import br.com.up.pokedex.model.PokeResponse
import br.com.up.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    // aqui temos 3 get's que irão retornar as nossas ações/comandos
    // 1- retornar todos os pokemons
    //2- retorna pelo id od pokemon
    // 3- retorna pelo nome dele

    @GET("pokemon?limit=151")
    fun getPokemons() : Call<PokeResponse>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id:String) : Call<Pokemon>

    @GET("pokemon/{pokemonName}")
    fun getPokemonByName(@Path("pokemonName") pokemon:String) : Call<Pokemon>
}