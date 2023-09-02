package com.siderfighter.entertainmenttracker.applayers.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.siderfighter.entertainmentprogress.R

@Composable
fun HomeNoDataScreen(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Min)
    ) {
        LogoComponent(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        )

        NoDataBackgroundComponent(
            modifier = Modifier
                .padding(0.dp, 32.dp)
                .align(Alignment.CenterHorizontally)
        )

        ButtonComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        ) {
            onButtonClick()
        }
    }
}

@Composable
private fun LogoComponent(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.app_logo),
        contentDescription = "The app logo",
        modifier = modifier
    )
}

@Composable
private fun NoDataBackgroundComponent(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.no_categories_bg),
        contentDescription = "No Categories",
        modifier = modifier
    )
}

@Composable
private fun ButtonComponent(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier.height(56.dp), content = {
            Text(
                stringResource(id = R.string.add_new_category),
                color = colorResource(id = R.color.white),
                fontSize = 18.sp
            )
        }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.light_orange))
    )
}

@Preview(showSystemUi = true)
@Composable
private fun HomeNoDataScreenPreview() {
    HomeNoDataScreen {}
}

@Preview(showSystemUi = true)
@Composable
private fun LogoComponentPreview() {
    LogoComponent()
}

@Preview(showSystemUi = true)
@Composable
private fun NoDataBackgroundComponentPreview() {
    NoDataBackgroundComponent()
}

@Preview(showSystemUi = true)
@Composable
private fun ButtonComponentPreview() {
    ButtonComponent(onClick = {})
}