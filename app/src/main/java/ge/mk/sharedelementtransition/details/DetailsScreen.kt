package ge.mk.sharedelementtransition.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import ge.mk.sharedelementtransition.R
import ge.mk.sharedelementtransition.data.contentList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreen(
    productId: Int,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onBackButtonClicked: () -> Unit,
) {
    with(sharedTransitionScope) {
        val productContent = contentList.find { it.id == productId }
        productContent?.let { product ->
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .statusBarsPadding()
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 24.dp)
                            .size(32.dp)
                            .clickable {
                                onBackButtonClicked()
                            }
                    )
                    
                    Text(
                        text = product.title,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal,
                        lineHeight = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                    )
                }
                
                Row {
                    
                    AsyncImage(
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/${product.imageUrl}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                            .height(150.dp)
                            .width(100.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.imageUrl)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )
                    
                    Column(
                        modifier = Modifier
                            .padding(bottom = 42.dp),
                    ) {
                        Text(
                            text = product.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            fontStyle = FontStyle.Normal,
                            lineHeight = 35.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .sharedElement(
                                    state = sharedTransitionScope.rememberSharedContentState(key = "text/${product.title}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 1000)
                                    }
                                )
                                .padding(horizontal = 24.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(6.dp))
                        
                        Text(
                            text = product.description,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Normal,
                            lineHeight = 18.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .sharedElement(
                                    state = sharedTransitionScope.rememberSharedContentState(key = "description/${product.description}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 1000)
                                    }
                                )
                                .padding(horizontal = 24.dp)
                        )
                    }
                    
                }
            }
        }
    }
}
