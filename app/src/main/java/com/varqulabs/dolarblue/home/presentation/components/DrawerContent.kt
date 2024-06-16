package com.varqulabs.dolarblue.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.presentation.model.DrawerItem
import com.varqulabs.dolarblue.navigation.Routes

@Composable
internal fun DrawerContent(
    drawerItems: List<DrawerItem>,
    currentRoute: Routes,
    modifier: Modifier = Modifier,
    onDrawerItemClick: (Routes) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "User Profile Image",
            modifier = Modifier.size(200.dp)
        )

        drawerItems.forEach {
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = it.title)) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = it.icon),
                        contentDescription = null
                    )
                },
                selected = currentRoute == it.route,
                onClick = { onDrawerItemClick(it.route) }
            )
        }
    }
}