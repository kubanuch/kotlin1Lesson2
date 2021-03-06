package com.example.kotlin1lesson2.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.data.repositories.CharacterRepository
import com.example.kotlin1lesson2.models.RickAndMortyCharacters
import com.example.kotlin1lesson2.models.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : BaseViewModel() {

    private var page = 1
    var isLoading: Boolean = false

    private val _characterState = MutableLiveData<RickAndMortyResponse<RickAndMortyCharacters>>()
    val characterState: LiveData<RickAndMortyResponse<RickAndMortyCharacters>> = _characterState

    private val _characterLocaleState = MutableLiveData<List<RickAndMortyCharacters>>()
    val characterLocaleState: LiveData<List<RickAndMortyCharacters>> = _characterLocaleState

    fun fetchCharacter() {
        isLoading = true
        repository.fetchCharacters(page).collect(_characterState) {
            page++
            isLoading = false
        }
    }

    fun getCharacters() = repository.getCharacters().collect(_characterLocaleState, null)

}