package com.example.composeandroidplayground.uiOnly.instagram

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeandroidplayground.R
import com.example.composeandroidplayground.common.theme.CAPColor
import com.example.composeandroidplayground.common.theme.Dimens
import com.example.composeandroidplayground.common.view.AppIcon
import com.example.composeandroidplayground.common.view.SpacerHeight
import com.example.composeandroidplayground.common.view.SpacerWidth

@Preview
@Composable
fun InstagramScreen() {
    Surface {
        InstagramScreenContent(
            modifier = Modifier.background(Color.Black),
            header = { InstagramHeader() },
            stories = {
                instagramStoriesSection()
            },
            posts = {
                instagramPostSection()
            }
        )
    }
}

@Composable
fun InstagramScreenContent(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit),
    stories: (LazyListScope.() -> Unit),
    posts: (LazyListScope.() -> Unit)
) {
    Column(modifier = modifier) {
        header.invoke()


        LazyColumn(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            item {
                LazyRow(modifier = modifier.padding(top = Dimens.large)) {
                    stories.invoke(this)
                }
            }

            posts.invoke(this)
        }
    }
}

private fun LazyListScope.instagramStoriesSection() {
    items(5, key = { it }) {
        InstagramStory()
    }
}

@Composable
fun InstagramStory() {
    Column(
        modifier = Modifier.padding(start = Dimens.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.border(
                Dimens.small, Brush.verticalGradient(
                    listOf(Color(0XFFFBAA47), Color(0XFFD91A46), Color(0XFFA60F93)),
                    startY = 0.0f,
                    endY = 100.0f
                ), CircleShape
            )
        ) {
            AppIcon(
                modifier = Modifier
                    .size(75.dp)
                    .padding(Dimens.extraSmall)
                    .clip(CircleShape), icon = R.drawable.girl
            )
        }
    }
}

@Composable
fun InstagramHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.large),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(icon = R.drawable.camera_icon)
        AppIcon(icon = R.drawable.insta_logo)
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(icon = R.drawable.igtv)
            SpacerWidth(16.dp)
            AppIcon(icon = R.drawable.message)
        }
    }
}

private fun LazyListScope.instagramPostSection() {
    items(5, key = { it }) {
        InstagramPost()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InstagramPost() {
    Column {
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
        Column(
            modifier = Modifier.padding(
                top = Dimens.extraLarge,
                start = Dimens.extraLarge,
                end = Dimens.extraLarge
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppIcon(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(Dimens.extraSmall), icon = R.drawable.girl
                    )
                    SpacerWidth()
                    Column {
                        Text(
                            text = "mariaxzhang", style = TextStyle(
                                color = CAPColor.InstaWhite,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        SpacerHeight(2.dp)
                        Text(
                            text = "Jakarta, Indonesia", style = TextStyle(
                                color = CAPColor.InstaWhite,
                                fontSize = 11.sp,
                            )
                        )
                    }
                }
                AppIcon(icon = R.drawable.hori_dots)
            }
        }
        SpacerHeight()
        HorizontalPager(state = pagerState) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.photo_2),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .background(CAPColor.InstaDark.copy(alpha = 0.33f), CircleShape)
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
                        style = TextStyle(
                            color = CAPColor.InstaWhite,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                AppIcon(icon = R.drawable.like)
                SpacerWidth(16.dp)
                AppIcon(icon = R.drawable.comment)
                SpacerWidth(16.dp)
                AppIcon(icon = R.drawable.message)
            }
            Row(modifier = Modifier.weight(1f)) {
                repeat(3) {
                    Spacer(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .background(
                                if (pagerState.currentPage == it) CAPColor.InstaBlue else CAPColor.InstaGray,
                                CircleShape
                            )
                            .size(6.dp)
                    )
                }
            }
            AppIcon(icon = R.drawable.save)
        }
        val text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = CAPColor.InstaWhite,
                    fontSize = 13.sp
                )
            ) {
                append("Liked by")
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = CAPColor.InstaWhite,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("lalisa")
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = CAPColor.InstaWhite,
                    fontSize = 13.sp
                )
            ) {
                append("And")
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = CAPColor.InstaWhite,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("44.545 others")
            }
        }
        val des = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = CAPColor.InstaWhite,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("mariaxzhang")
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = CAPColor.InstaWhite,
                    fontSize = 13.sp,
                )
            ) {
                append("Lorem ipsum")
            }
        }
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AppIcon(
                    icon = R.drawable.girl, modifier = Modifier
                        .clip(CircleShape)
                        .size(17.dp)
                )
                SpacerWidth(5.dp)
                Text(text = text)
            }
            SpacerHeight(5.dp)
            Text(text = des)
        }
    }
}