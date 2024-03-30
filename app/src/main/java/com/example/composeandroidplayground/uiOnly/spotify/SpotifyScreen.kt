package com.example.composeandroidplayground.uiOnly.spotify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeandroidplayground.R
import com.example.composeandroidplayground.common.theme.BaseTheme
import com.example.composeandroidplayground.common.theme.Dimens
import com.example.composeandroidplayground.common.theme.SpotifyColor
import kotlin.math.ceil

@Composable
fun SpotifyScreen() {
    SpotifyScreenContent(
        categorySection = {
            SpotifyCategorySection(false)
        },
        playlistSection = {
            spotifyPlaylistSection()
        },
        songSection = arrayOf(
            {
                spotifySongSection("Recently Played")
            }, {
                spotifySongSection("Top Song")
            }, {
                spotifySongSection("Best 2024")
            }
        )
    )
}

@Composable
fun SpotifyScreenContent(
    categorySection: @Composable () -> Unit,
    playlistSection: LazyListScope.() -> Unit,
    vararg songSection: LazyListScope.() -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(Dimens.large)
    ) {
        categorySection.invoke()
        LazyColumn {
            playlistSection.invoke(this)
            songSection.forEach {
                it.invoke(this)
            }
        }
    }
}

@Composable
private fun SpotifyCategorySection(isSelected: Boolean) {
    LazyRow {
        items(3) {
            val backgroundColor = if (isSelected) SpotifyColor.Green else SpotifyColor.DarkGray
            val textColor = if (isSelected) SpotifyColor.Black else SpotifyColor.White

            Box(
                modifier = Modifier
                    .padding(start = Dimens.medium)
                    .background(
                        backgroundColor,
                        shape = RoundedCornerShape(Dimens.Radius.rounded)
                    )
                    .padding(horizontal = Dimens.extraLarge, vertical = Dimens.dp6)
            ) {
                Text(text = "All", color = textColor, fontSize = Dimens.TextSize.mediumText)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
private fun LazyListScope.spotifyPlaylistSection() {
    val items = listOf(
        "Aimyon Mix 2021",
        "Aimyon Mix 2022",
        "Aimyon Mix 2024",
        "Aimyon Mix 2020",
        "Aimyon Japanese Mix 2019"
    )
    val maxItemInRow: Int = 2
    item {
//        var index = 0
//        for(i in 0..ceil((items.size / maxItemInRow).toDouble()).toInt()){
//            LazyRow {
//                item {
//                    for(j in 1..maxItemInRow) {
//                        if(index >= items.size) break;
//                        Box(
//                            modifier = Modifier.fillParentMaxWidth(fraction = 1F / maxItemInRow),
//                        ) {
//                            SpotifyPlaylistItem(items[index++])
//                        }
//                    }
//                }
//            }
//        }
        FlowRow(maxItemsInEachRow = maxItemInRow) {
            for (i in items.indices) {
                Box(
                    modifier = Modifier.fillParentMaxWidth(fraction = 1F / maxItemInRow),
                ) { SpotifyPlaylistItem(items[i]) }
            }
        }
    }
}

private fun LazyListScope.spotifySongSection(title: String) {
    val items = listOf(
        "Lazy Song",
        "Anytime Anywhere",
        "See You Again",
        "Photograph"
    )
    item {
        Column(modifier = Modifier.padding(top = Dimens.extraLarge)) {
            Text(
                text = title,
                color = SpotifyColor.White,
                fontSize = Dimens.TextSize.largeText,
                fontWeight = FontWeight.Bold
            )

            LazyRow {
                items(items.size) {
                    SpotifySongItem(title = items[it])
                }
            }
        }
    }
}

@Composable
fun SpotifySongItem(title: String) {
    Column(modifier = Modifier.padding(end = Dimens.large, top = Dimens.medium)) {
        Image(
            modifier = Modifier
                .size(150.dp, 150.dp)
                .clip(RoundedCornerShape(Dimens.Radius.small)),
            painter = painterResource(id = R.drawable.spotify_playlist_photo),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(top = Dimens.small),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = SpotifyColor.White,
            fontSize = Dimens.TextSize.sp14,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(top = Dimens.small),
            text = "Lorem ipsum dolor sit amet",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = SpotifyColor.LightGray,
            fontSize = Dimens.TextSize.smallText
        )
    }
}

@Composable
fun SpotifyPlaylistItem(playlist: String) {
    Row(
        modifier = Modifier
            .padding(top = Dimens.large, start = Dimens.large)
            .background(SpotifyColor.DarkGray, shape = RoundedCornerShape(Dimens.Radius.small))
            .clip(RoundedCornerShape(Dimens.Radius.small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = R.drawable.spotify_playlist_photo),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.padding(horizontal = Dimens.large),
            text = playlist,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = SpotifyColor.White,
            fontSize = Dimens.TextSize.sp14,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun SpotifyScreenPreview() {
    BaseTheme(true) {
        SpotifyScreen()
    }
}