package com.varqulabs.dolarblue.auth.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.varqulabs.dolarblue.R

@Composable
fun DialogConfirmEmail() {
    Dialog(onDismissRequest = {  }) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { }
        ) {
           Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background
                    )
                    .padding(8.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .clip(RoundedCornerShape(4.dp))
                       .background(MaterialTheme.colorScheme.primary),
                   horizontalArrangement = Arrangement.Center
               ) {
                   Text(
                       text = stringResource(R.string.title_dialog_confirm_email),
                       style = MaterialTheme.typography.titleLarge.copy(
                           color = MaterialTheme.colorScheme.onPrimary
                       )
                   )
               }
               Text(
                   text = stringResource(R.string.body_dialog_confirm_email),
                   style = MaterialTheme.typography.bodyMedium.copy(
                       color = MaterialTheme.colorScheme.onPrimary,
                       textAlign = TextAlign.Center
                   )
               )
               CircularProgressIndicator()
           }
        }
    }
}