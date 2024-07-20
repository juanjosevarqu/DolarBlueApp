package com.varqulabs.dolarblue.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.domain.enums.Currency
import com.varqulabs.dolarblue.settings.presentation.components.childrens.OptionTogglelable
import com.varqulabs.dolarblue.settings.presentation.components.childrens.SelectableCurrency

@Composable
fun NewsSettings(
    doNotDisturbEnabled: Boolean,
    bolivianNewsEnabled: Boolean,
    dollarNewsEnabled: Boolean,
    argentinianNewsEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClickDoNotDisturbToggle: () -> Unit,
    onClickBolivianNews: () -> Unit,
    onClickDollarNews: () -> Unit,
    onClickArgentinianNews: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.copy_news).uppercase(),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 16.sp),
            color = MaterialTheme.colorScheme.primary
        )

        NotificationsToggle(
            doNotDisturbEnabled = doNotDisturbEnabled,
            modifier = Modifier.fillMaxWidth(),
            onClickToggle = onClickDoNotDisturbToggle
        )

        InterestingNewsSelector(
            modifier = Modifier.fillMaxWidth(),
            bolivianNewsEnabled = bolivianNewsEnabled,
            dollarNewsEnabled = dollarNewsEnabled,
            argentinianNewsEnabled = argentinianNewsEnabled,
            onClickBolivianNews = onClickBolivianNews,
            onClickDollarNews = onClickDollarNews,
            onClickArgentinianNews = onClickArgentinianNews
        )
    }
}

@Composable
private fun NotificationsToggle(
    doNotDisturbEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClickToggle: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = stringResource(id = R.string.copy_notifications),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseSurface
        )

        OptionTogglelable(
            title = stringResource(id = R.string.copy_do_not_disturb),
            checked = doNotDisturbEnabled,
            onClickToggle = { onClickToggle() }
        )
    }
}

@Composable
private fun InterestingNewsSelector(
    bolivianNewsEnabled: Boolean,
    dollarNewsEnabled: Boolean,
    argentinianNewsEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClickBolivianNews: () -> Unit,
    onClickDollarNews: () -> Unit,
    onClickArgentinianNews: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = stringResource(id = R.string.copy_interesting_news),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseSurface
        )

        FilterInterestingNews(
            modifier = Modifier.fillMaxWidth(),
            bolivianNewsEnabled = bolivianNewsEnabled,
            dollarNewsEnabled = dollarNewsEnabled,
            argentinianNewsEnabled = argentinianNewsEnabled,
            onClickBolivianNews = onClickBolivianNews,
            onClickDollarNews = onClickDollarNews,
            onClickArgentinianNews = onClickArgentinianNews
        )
    }
}

@Composable
private fun FilterInterestingNews(
    bolivianNewsEnabled: Boolean,
    dollarNewsEnabled: Boolean,
    argentinianNewsEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClickBolivianNews: () -> Unit,
    onClickDollarNews: () -> Unit,
    onClickArgentinianNews: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        SelectableCurrency(
            selected = bolivianNewsEnabled,
            currencyCode = Currency.BOLIVIANO.code,
            modifier = Modifier.weight(1f),
            onClick = onClickBolivianNews
        )

        SelectableCurrency(
            selected = dollarNewsEnabled,
            currencyCode = Currency.DOLLAR.code,
            modifier = Modifier.weight(1f),
            onClick = onClickDollarNews
        )

        SelectableCurrency(
            selected = argentinianNewsEnabled,
            currencyCode = Currency.PESOS.code,
            modifier = Modifier.weight(1f),
            onClick = onClickArgentinianNews
        )
    }
}