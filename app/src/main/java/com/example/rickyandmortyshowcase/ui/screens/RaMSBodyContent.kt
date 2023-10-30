package com.example.rickyandmortyshowcase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.rickyandmortyshowcase.R
import com.example.rickyandmortyshowcase.database.remote.domain.entities.CharacterSimple
import com.example.rickyandmortyshowcase.ui.RaMSViewModel

@Composable
fun RaMSListAndDetailContent(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val characters = state.characters

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_padding))
    ) {
        item {
            RaMSTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.top_bar_padding_vertical))
            )
        }
        items(characters, key = { character -> character.id }) { character ->
            CharactersListItem(
                character = character,
                selected = false,
                filterMode = state.currentCharactersList == RaMSViewModel.CharactersListType.FILTER,
                onCardClick = { onSelectCharacter(character.id) }
            )
        }
    }
}

@Composable
fun CharactersListItem(
    character: CharacterSimple,
    selected: Boolean,
    filterMode: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected && filterMode)
                MaterialTheme.colorScheme.background
            else if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.tertiaryContainer
        ),
        onClick = onCardClick
    ) {

    }
}

@Composable
fun RaMSListOnlyContent(
    state: RaMSViewModel.RickAndMortyShowcaseState,
    onSelectCharacter: (id: String) -> Unit,
    onEnterSearch: () -> Unit,
    onEnterCharacters: () -> Unit,
    onAddCharacterToFavorites: (id: String) -> Unit,
    onRemoveCharacterFromFavorites: (id: String) -> Unit,
    onFilterCharacters: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {

}

@Composable
fun CharacterScreen() {

}

@Composable
fun CharacterDetailsScreen() {

}

@Composable
fun FilterCharacterScreen() {

}

//@Preview
//@Composable
//fun RaMSListAndDetailContentPreview() {
//    RickyAndMortyShowcaseTheme {
//        RaMSListAndDetailContent()
//    }
//}
//
//@Preview
//@Composable
//fun RaMSListOnlyContentPreview() {
//    RickyAndMortyShowcaseTheme {
//        ReplyListOnlyContent()
//    }
//}
//@Preview
//@Composable
//fun FilterCharacterScreenPreview() {
//    RickyAndMortyShowcaseTheme {
//        FilterCharacterScreen()
//    }
//}
//
//@Preview
//@Composable
//fun CharacterDetailsScreenPreview() {
//    RickyAndMortyShowcaseTheme {
//        CharacterDetailsScreen()
//    }
//}
//
//@Preview
//@Composable
//fun CharacterScreenPreview() {
//    RickyAndMortyShowcaseTheme {
//        CharacterScreen()
//    }
//}