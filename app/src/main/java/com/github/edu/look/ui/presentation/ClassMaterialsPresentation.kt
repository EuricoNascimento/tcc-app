package com.github.edu.look.ui.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.github.edu.look.R
import com.github.edu.look.data.enums.TypeFile
import com.github.edu.look.data.materials.Material
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.ClassMaterialsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassMaterialsPresentation (
    courseId: String?,
    materialId: String?,
    viewModel: ClassMaterialsViewModel = hiltViewModel()
) {
    if (!viewModel.isStartMaterial && courseId != null && materialId != null) {
        viewModel.getClassMaterials(courseId, materialId)
        viewModel.isStartMaterial = true
    }
    val classMaterial by viewModel.classMaterial.collectAsState()
    val material by viewModel.currentMaterial.collectAsState()
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = LookDefault.Padding.large)
                    .clip(
                        RoundedCornerShape(
                            topEnd = LookDefault.Padding.large,
                            topStart = LookDefault.Padding.large
                        )
                    )
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (classMaterial.materials.size > 1) {
                            viewModel.getCurrentMaterial(ClassMaterialsViewModel.Navigation.BACK)
                        }
                    }
                ) {
                    ScaleText(text = context.getString(R.string.back))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (classMaterial.materials.size > 1) {
                            viewModel.getCurrentMaterial(ClassMaterialsViewModel.Navigation.NEXT)
                        }
                    }
                ) {
                    ScaleText(text = context.getString(R.string.next))
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(it)
        ) {
            ScaleText(
                text = classMaterial.description,
                fontSize = LookDefault.FontSize.medium,
                modifier = Modifier
                    .padding(LookDefault.Padding.small)
                    .align(alignment = Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
                )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
                    .verticalScroll(rememberScrollState())
            ) {
                when (TypeFile.getTypeFile(material.type)) {
                    TypeFile.IMAGE -> MaterialImage(material)
                    TypeFile.PDF -> {}
                    TypeFile.VIDEO -> MaterialVideo(material = material, context = context)
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun MaterialVideo(material: Material, context: Context, modifier: Modifier = Modifier) {
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaSource = remember(material.origin) {
        MediaItem.fromUri(material.origin)
    }

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun MaterialImage(material: Material, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .semantics(mergeDescendants = true) {}
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        ScaleText(
            text = material.name,
            fontSize = LookDefault.FontSize.small,
            modifier = Modifier
                .padding(LookDefault.Padding.small)
                .align(alignment = Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.small))
        AsyncImage(
            model = material.origin,
            contentDescription = material.description,
            modifier = Modifier.padding(LookDefault.Padding.small)
        )
    }
}