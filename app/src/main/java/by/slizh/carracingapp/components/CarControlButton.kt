package by.slizh.carracingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.slizh.carracingapp.R

@Composable
fun CarControlButton(onMoveLeft: () -> Unit, onMoveRight: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.left_arrow),
            contentDescription = stringResource(id = R.string.move_left),
            modifier = Modifier
                .size(80.dp)
                .clickable { onMoveLeft() }
        )

        Image(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = stringResource(id = R.string.move_right),
            modifier = Modifier
                .size(80.dp)
                .clickable { onMoveRight() }
        )
    }
}