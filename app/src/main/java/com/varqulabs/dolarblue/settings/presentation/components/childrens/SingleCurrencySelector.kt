package com.varqulabs.dolarblue.settings.presentation.components.childrens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.core.domain.enums.Currency

private val currencies = listOf(
    Currency.BOLIVIANO,
    Currency.DOLLAR,
    Currency.PESOS
)

@Composable
fun SingleCurrencySelector(
    currencySelected: Currency,
    modifier: Modifier = Modifier,
    onSelectCurrency: (Currency) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        currencies.forEach { currency ->
            SelectableCurrency(
                selected = currencySelected == currency,
                currencyCode = currency.code,
                modifier = Modifier.weight(1f)
            ) { onSelectCurrency(currency) }
        }
    }
}