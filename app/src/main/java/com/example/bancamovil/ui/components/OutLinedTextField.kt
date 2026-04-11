package com.example.bancamovil.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bancamovil.R
import com.example.bancamovil.ui.theme.BancaMovilTheme
import com.example.bancamovil.ui.theme.DisplaySize
import com.example.bancamovil.ui.theme.gray500


@Composable
fun UsernameComponent(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions,
    keyboarOptions: KeyboardOptions,
    ) {
    Row {
        TextFieldComponent(
            value,
            onValueChange = onValueChange,
            modifier = modifier,
            placeholder = { Text("Ingresa tu nombre de usuario",
                style = BancaMovilTheme.typography.PreloMediumGrayDisplay16,
                color = MaterialTheme.colorScheme.onSurface
            ) },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_account_new),
                    modifier = Modifier
                        .size(18.dp),
                    contentDescription = null
                )
            },
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                errorContainerColor = Color.Transparent,
            ),
            keyboardOptions = keyboarOptions,
            keyboardActions = keyboardActions
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_m,
        color = MaterialTheme.colorScheme.onBackground
    ),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors,
    shape: Shape = RoundedCornerShape(4.dp)
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = MaterialTheme.colorScheme.onBackground,
        backgroundColor = Color.Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            interactionSource = interactionSource,
            enabled = enabled,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            readOnly = readOnly,
            maxLines = maxLines,
            textStyle = textStyle,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                singleLine = singleLine,
                enabled = enabled,
                interactionSource = interactionSource,
                contentPadding = OutlinedTextFieldDefaults.contentPadding(
                    top = 0.dp,
                    bottom = 0.dp
                ),
                placeholder = placeholder,
                colors = colors,
                isError = error != null,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                supportingText = {
                    if (error != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else supportingText?.invoke()
                },
                label = label,
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(enabled,
                        error != null,
                        interactionSource, colors, shape,
                        focusedBorderThickness = 0.5.dp,
                        unfocusedBorderThickness = 0.5.dp
                    )
                },
            )
        }
    }
}


@Preview
@Composable
fun ForgotPasswordComponentPreview(
    modifier: Modifier = Modifier.height(50.dp),
    value: String = "pass",
    onValueChange: (String) -> Unit = {},
    passwordVisible: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboarOptions: KeyboardOptions = KeyboardOptions.Default,
    returnPassVisible: (Boolean) -> Unit = { }
) {

    Row() {
        PasswordTextField(
            value,
            onValueChange = onValueChange,
            modifier = modifier,
            placeholder = { Text("Escribe tu contraseña",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.prelo_medium)),
                    fontWeight = FontWeight.Normal,
                    fontSize = DisplaySize().text_size_s,
                    color = gray500
                ),
                color = MaterialTheme.colorScheme.onSurface
            ) },
            leadingIcon = {
                /*
                Image(
                    painter = painterResource(R.drawable.ic_password),
                    modifier = Modifier
                        .padding(0.dp)
                        .size(10.dp),
                    contentDescription = null)

                 */

            },
            maxLines = 1,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(mask = '\u25CF'),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                errorContainerColor = Color.Transparent
            ),
            keyboardOptions = keyboarOptions,
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.width(8.dp))

        Card(modifier = Modifier
            .height(50.dp)
            .width(40.dp)
            .align(Alignment.CenterVertically),
            shape = RoundedCornerShape(3.dp)
        ) {
            var image: Painter
            if (passwordVisible) {
                image = painterResource(R.drawable.ic_eyes_visible_16dp)
                VisualTransformation.None
            } else {
                image = painterResource(R.drawable.ic_eyes_invisibile_16dp)
                PasswordVisualTransformation()
            }
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = image,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .size(24.dp)
                        .clickable {
                            returnPassVisible(!passwordVisible)
                        },
                    contentDescription = null)
            }
        }
    }

}


@Composable
fun ForgotPasswordComponent(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean = false,
    keyboardActions: KeyboardActions,
    keyboarOptions: KeyboardOptions,
    returnPassVisible: (Boolean) -> Unit
) {
    Row {
        PasswordTextField(
            value,
            onValueChange = onValueChange,
            modifier = modifier,
            placeholder = { Text("Escribe tu contraseña",
                style = BancaMovilTheme.typography.PreloMediumGrayDisplay16,
                color = MaterialTheme.colorScheme.onSurface
            ) },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_password),
                    modifier = Modifier
                        .size(18.dp),
                    contentDescription = null)
            },
            maxLines = 1,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(mask = '\u25CF'),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                errorContainerColor = Color.Transparent),
            keyboardOptions = keyboarOptions,
            keyboardActions = keyboardActions)

        Spacer(modifier = Modifier.width(10.dp))

        Card(modifier = Modifier
            .height(50.dp)
            .width(40.dp),
            shape = RoundedCornerShape(3.dp)
        ) {
            var image: Painter
            if (passwordVisible) {
                image = painterResource(R.drawable.ic_eyes_visible_16dp)
                VisualTransformation.None
            } else {
                image = painterResource(R.drawable.ic_eyes_invisibile_16dp)
                PasswordVisualTransformation()
            }
            Box(modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = image,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = { returnPassVisible(!passwordVisible) },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(),
                        )
                    ,
                    contentDescription = null
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.prelo_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = DisplaySize().text_size_m,
        color = MaterialTheme.colorScheme.onBackground
    ),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    error: String? = null,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(mask = '\u25CF'),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors,
    shape: Shape = RoundedCornerShape(4.dp),
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = MaterialTheme.colorScheme.onBackground,
        backgroundColor = Color.Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            interactionSource = interactionSource,
            enabled = enabled,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            readOnly = readOnly,
            maxLines = maxLines,
            textStyle = textStyle,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        ) { innerTextField ->

            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                singleLine = singleLine,
                enabled = enabled,
                interactionSource = interactionSource,
                placeholder = placeholder,
                contentPadding =
                OutlinedTextFieldDefaults.contentPadding(
                    top = 0.dp,
                    bottom = 0.dp
                ),
                colors = colors,
                isError = error != null,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                supportingText = {
                    if (error != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else supportingText?.invoke()
                },
                label = label,
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(enabled,
                        error != null,
                        interactionSource, colors, shape,
                        focusedBorderThickness = 0.5.dp,
                        unfocusedBorderThickness = 0.5.dp
                    )
                }
            )

        }
    }
}