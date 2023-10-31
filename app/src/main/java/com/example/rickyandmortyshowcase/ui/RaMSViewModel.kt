package com.example.rickyandmortyshowcase.ui

import android.content.Intent.ShortcutIconResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.database.local.data.Favorite
import com.example.rickyandmortyshowcase.database.local.domain.FavoriteDao
import com.example.rickyandmortyshowcase.database.local.domain.FavoriteState
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterDetailed
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersByNameUseCase
import com.example.rickyandmortyshowcase.database.remote.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaMSViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val favoritesDao: FavoriteDao
) : ViewModel() {

    //TODO: Much likely to fail
    private val _ramsState = MutableStateFlow(RickAndMortyShowcaseState(favoriteCharacters = FavoriteState()))
    val state = _ramsState.asStateFlow()

    init {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    isCharactersListLoading = true
                )
            }
            _ramsState.update {
                it.copy(
                    characters = getCharactersUseCase.execute(),
                    isCharactersListLoading = false
                )
            }
        }
    }

    fun selectCharacter(id: String) {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    isShowingHomepage = false,
                    isCharacterDetailsListLoading = true
                )
            }
            _ramsState.update {
                it.copy(
                    selectedCharacter = getCharacterDetailsUseCase.execute(id),
                    isCharacterDetailsListLoading = false
                )
            }
        }
    }

    fun enterSearch() {
        _ramsState.update {
            it.copy(
                isShowingHomepage = true,
                currentCharactersList = CharactersListType.FILTER
            )
        }
    }

    fun enterFavorites() {
        _ramsState.update {
            it.copy(
                isShowingHomepage = true,
                currentCharactersList = CharactersListType.FAVORITES,
                charactersIconResource = R.drawable.characters_unselected,
                favoritesIconResource = R.drawable.favorites_selected
            )
        }
    }

    fun enterCharacters() {
        _ramsState.update {
            it.copy(
                isShowingHomepage = true,
                currentCharactersList = CharactersListType.FAVORITES,
                charactersIconResource = R.drawable.characters_selected,
                favoritesIconResource = R.drawable.favorites_unselected
            )
        }
    }

    fun filterCharacters(name: String) {
        viewModelScope.launch {
            _ramsState.update {
                it.copy(
                    isCharactersListLoading = true
                )
            }
            _ramsState.update {
                it.copy(
                    characters = getCharactersByNameUseCase.execute(name),
                    isCharactersListLoading = false
                )
            }
        }
    }

    //TODO: Much likely to fail
    fun addCharacterToFavorites(id: String) {
        viewModelScope.launch {
            favoritesDao.upsertCharacter(Favorite(id))
        }
    }

    //TODO: Much likely to fail
    fun removeCharacterFromFavorites(id: String) {
        viewModelScope.launch {
            favoritesDao.deleteCharacter(Favorite(id))
        }
    }

    data class RickAndMortyShowcaseState(
        val characters: List<CharacterSimple> = emptyList(),
        val filter: String = "",
        val favoriteCharacters: FavoriteState,
        val currentCharactersList: CharactersListType = CharactersListType.CHARACTERS,
        val isCharactersListLoading: Boolean = false,
        val isCharacterDetailsListLoading: Boolean = false,
        val selectedCharacter: CharacterDetailed? = null,
        val isShowingHomepage: Boolean = true,
        val charactersIconResource: Int = R.drawable.characters_selected,
        val favoritesIconResource: Int = R.drawable.favorites_unselected
    )

    enum class CharactersListType {
        CHARACTERS, FAVORITES, FILTER
    }
}