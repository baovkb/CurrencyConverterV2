package com.vkbao.landing.home.composer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vkbao.landing.LandingViewModel
import com.vkbao.test.R

val backgroundColor = Color(0xfff7f7ff)
val textColor = Color(0xFF6391FF)

@Composable
internal fun HomeScreen (
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onSettingPress: () -> Unit
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        Image(
            painter = painterResource(R.drawable.top_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier.height(400.dp)
        )

        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Spacer(modifier = modifier.height(140.dp))

            CardSection(
                modifier = modifier
                    .padding(horizontal = 21.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(12.dp),
                        ambientColor = Color(0x3A0092F3),
                    ),
                itemList = listOf("USD", "VND")
            )

            Spacer(modifier = modifier.weight(1f))

            val keyList = mutableListOf<@Composable () -> Unit>()
            keyList.addAll(listOf(
                {Text("0", fontSize = 24.sp)},
                {Text(".", fontSize = 24.sp)},
                { Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = null,
                    tint = textColor,
                    modifier = modifier.size(28.dp)
                ) },
            ))

            for (i in 1..9) {
                keyList.add{
                    Text(i.toString(), fontSize = 24.sp)
                }
            }

            CustomKeyboard(
                modifier = modifier.padding(21.dp),
                itemButtons = keyList,
                rowSpace = 42.dp,
                columnSpace = 72.dp
            )
        }
    }
}

@Composable
fun CustomKeyboard(
    modifier: Modifier = Modifier,
    itemButtons: List<@Composable () -> Unit>,
    columnSpace: Dp = 0.dp,
    rowSpace: Dp = 0.dp
) {
    LazyColumn (
        modifier = modifier,
        reverseLayout = true
    ) {
        val rowButton = itemButtons.chunked(3)

        itemsIndexed(rowButton) { indexRow, keyRow ->
            if (indexRow != 0)
                Spacer(modifier = Modifier.height(rowSpace))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                keyRow.forEachIndexed { indexColumn, keyColumn ->
                    Row {
                        Button(
                            modifier = Modifier.size(56.dp),
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black,
                                disabledContentColor = Color.Gray,
                                disabledContainerColor = Color.Transparent
                            ),
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            keyColumn.invoke()
                        }

                        if (indexColumn != keyRow.lastIndex)
                            Spacer(modifier = Modifier.width(columnSpace))
                    }

                }
            }
        }

    }
}

@Composable
fun CardSection(
    modifier: Modifier = Modifier,
    itemList: List<String>,
) {
    var firstCurrency by rememberSaveable { mutableStateOf(itemList[0]) }
    var secondCurrency by rememberSaveable { mutableStateOf(itemList[1]) }

    var fromValue by rememberSaveable { mutableStateOf("0") }
    var toValue by rememberSaveable { mutableStateOf("0") }

    Column(modifier = modifier
        .fillMaxWidth()
        .background(backgroundColor, shape = RoundedCornerShape(12.dp))
        .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 40.dp)

    ) {
        CurrencyRow(
            currencyName = firstCurrency,
            enableEdit = true,
            onValueChange = { fromValue = it },
            value = fromValue
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {},
                modifier = Modifier
                    .width(46.dp)
                    .height(46.dp)
                    .background(Color(0xFF2196F3), shape = CircleShape)
                    .padding(top = 12.dp, bottom = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.up_down),
                    contentDescription = null,
                    tint = backgroundColor,
                )
            }

            Spacer(modifier.weight(1f))
            Text("Rate")

        }

        Spacer(modifier = modifier.height(16.dp))

        CurrencyRow(
            currencyName = secondCurrency,
            enableEdit = false,
            onValueChange = {},
            value = toValue
        )
    }

}

@Composable
fun CurrencyRow(
    currencyName: String,
    enableEdit: Boolean,
    onValueChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier,
) {

    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currencyName,
            fontSize = 18.sp,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
        IconButton(onClick = {  }) {
            Icon(
                painter = painterResource(id = R.drawable.down) ,
                contentDescription = null,
                modifier = modifier
                    .width(12.dp)
                    .height(12.dp)
            )
        }

        OutlinedTextField(
            modifier = modifier.height(60.dp),
            value = value,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Right
            ),
            onValueChange = onValueChange,
            shape = RoundedCornerShape(12.dp),
            enabled = enableEdit,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFCAD3F6),
                unfocusedBorderColor = Color(0xFFCAD3F6),
                disabledBorderColor = Color(0xFFCAD3F6),
                focusedContainerColor = Color(0xFFedf2ff),
                unfocusedContainerColor = Color(0xFFedf2ff),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenReview() {
    HomeScreen(
        onBackPress = {},
        onSettingPress = {},
    )
}