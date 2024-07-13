package com.varqulabs.dolarblue.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.varqulabs.dolarblue.settings.presentation.components.childrens.SingleCurrencySelector

@Composable
fun FavoriteCurrencyConfigs(
    favoriteCurrency : Currency,
    modifier: Modifier = Modifier,
    onSelectFavoriteCurrency: (Currency) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        SelectFavoriteCurrencyLabels()

        SingleCurrencySelector(
            currencySelected = favoriteCurrency,
            modifier = Modifier.fillMaxWidth(),
            onSelectCurrency = onSelectFavoriteCurrency
        )
    }
}

@Composable
private fun SelectFavoriteCurrencyLabels(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = stringResource(id = R.string.copy_favorite_currency).uppercase(),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 16.sp),
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = stringResource(id = R.string.copy_select_most_used_currency),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.inverseSurface
        )
    }
}