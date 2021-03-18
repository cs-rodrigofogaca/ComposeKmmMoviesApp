package br.com.concrete.components

import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppComposeScreen(
    val route: String,
) {
    object SampleComposeHome : AppComposeScreen("composeHome")
    object XmlCompose : AppComposeScreen("xmlCompose")
    object XmlHome: AppComposeScreen("xmlHome")
    object XmlComposeActivity: AppComposeScreen("xmlComposeActivity")
}