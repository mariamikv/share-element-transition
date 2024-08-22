package ge.mk.sharedelementtransition.listing

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import ge.mk.sharedelementtransition.data.contentList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListingScreen(
    onItemClick: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    with(sharedTransitionScope) {
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(
                vertical = 48.dp,
                horizontal = 8.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = contentList,
                key = { it.id },
            ) { content ->
                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null,
                            onClick = {
                                onItemClick(content.id)
                            }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${content.imageUrl}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                            .height(250.dp)
                            .width(170.dp)
                            .clip(RoundedCornerShape(24.dp))
                    ) {
                        AsyncImage(
                            modifier = Modifier,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(content.imageUrl)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(6.dp))
                    
                    Text(
                        text = content.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        modifier = Modifier
                            .sharedElement(
                                state = sharedTransitionScope.rememberSharedContentState(key = "text/${content.title}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                            .width(200.dp)
                            .padding(horizontal = 24.dp)
                    )
                    
                    Text(
                        text = content.description,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .sharedElement(
                                state = sharedTransitionScope.rememberSharedContentState(key = "description/${content.description}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                            .width(200.dp)
                            .padding(horizontal = 24.dp)
                    )
                }
            }
        }
    }
}
