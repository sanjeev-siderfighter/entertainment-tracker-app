package com.siderfighter.entertainmenttracker.applayers.presentation.addcategoryscreen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.siderfighter.entertainmentprogress.R
import com.siderfighter.entertainmenttracker.applayers.presentation.homescreen.AppLoader
import com.siderfighter.entertainmenttracker.applayers.presentation.ui.theme.BlackOpacity75

@Composable
fun AddCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddCategoryViewModel = hiltViewModel(),
    onConfirmed: (category: String) -> Unit
) {
    val addCategoryState by viewModel.addCategoryFlow.collectAsStateWithLifecycle(initialValue = null)

    when (addCategoryState) {
        AddCategoryUiState.CategoryAlreadyExists -> {

        }

        AddCategoryUiState.FailedToAddCategory -> {

        }

        AddCategoryUiState.Loading -> {
            AppLoader()
        }

        AddCategoryUiState.Success -> {

        }

        null -> {
            AddCategoryCompose(
                modifier = modifier.padding(16.dp),
                validate = { categoryName ->
                    viewModel.validateCategoryName(categoryName = categoryName)
                },
                onConfirmed = onConfirmed,
                onButtonClick = { categoryName, categoryChapter -> /*viewModel.testAddCategory(categoryName = categoryName)*/ }
            )
        }
    }
}

@Composable
fun AddCategoryCompose(
    modifier: Modifier = Modifier,
    validate: (input: String) -> CategoryNameValidationState,
    onConfirmed: (category: String) -> Unit,
    onButtonClick: (categoryName: String, categoryChapter: String) -> Unit
) {
    var categoryName by remember { mutableStateOf("") }
    var categoryChapter by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        TextFieldWithErrorMessage(
            label = stringResource(id = R.string.category_name),
            placeholder = stringResource(id = R.string.category_name),
            validate = validate,
            updateInputText = {
                categoryName = it
            }
        )
        TextFieldWithErrorMessage(
            modifier = Modifier.padding(top = 16.dp),
            label = stringResource(id = R.string.episode_chapter),
            placeholder = stringResource(id = R.string.enter_episode_chapter),
            validate = validate,
            updateInputText = {
                categoryChapter = it
            }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = {
                onButtonClick(categoryName, categoryChapter)
            },
            contentPadding = PaddingValues(top = 0.dp) // to remove the extra default padding of button
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.add)
            )
        }
    }
}

private const val ERROR_ANIMATION_DURATION = 200

@Composable
fun TextFieldWithErrorMessage(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    validate: (input: String) -> CategoryNameValidationState,
    updateInputText: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var validationState by remember { mutableStateOf(validate(inputText)) }

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    color = if (validationState is CategoryNameValidationState.Invalid) Color.Red else if (isFocused) Color.Blue else Color.LightGray,
                    shape = RoundedCornerShape(14.dp)
                ),
            value = inputText,
            interactionSource = interactionSource,
            onValueChange = {
                inputText = it
                validationState = validate(inputText)
                updateInputText(it)
            },
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder, color = BlackOpacity75) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                errorContainerColor = Color.LightGray,
                disabledContainerColor = Color.LightGray,
                selectionColors = TextSelectionColors(
                    handleColor = Color.Blue,
                    backgroundColor = Color.Green
                ),
                focusedLabelColor = Color.Blue,
                errorLabelColor = Color.Red,
                unfocusedLabelColor = Color.Blue,
                disabledLabelColor = Color.DarkGray,
                focusedPlaceholderColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.LightGray,
                errorTextColor = Color.Black
            ),
            isError = validationState is CategoryNameValidationState.Invalid,
            shape = RoundedCornerShape(14.dp),

            )
        AnimatedVisibility(
            visible = validationState is CategoryNameValidationState.Invalid,
            enter = slideInHorizontally(
                initialOffsetX = {
                    -it
                },
                animationSpec = tween(
                    durationMillis = ERROR_ANIMATION_DURATION,
                    easing = LinearEasing
                )
            ),
            exit = slideOutHorizontally(
                targetOffsetX = {
                    -it
                },
                animationSpec = tween(
                    durationMillis = ERROR_ANIMATION_DURATION,
                    easing = LinearEasing
                )
            )
        ) {
            Text(
                text = stringResource(id = R.string.add_category_error),
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddCategoryComposePreview() {
    AddCategoryCompose(
        validate = { CategoryNameValidationState.Valid },
        onButtonClick = { _, _ -> },
        onConfirmed = {}
    )
}

@Preview(showSystemUi = false)
@Composable
private fun TextFieldWithErrorMessagePreview() {
    TextFieldWithErrorMessage(
        modifier = Modifier.padding(16.dp),
        label = stringResource(id = R.string.category_name),
        placeholder = stringResource(id = R.string.enter_category_name),
        updateInputText = {},
        validate = { CategoryNameValidationState.Valid }
    )
}