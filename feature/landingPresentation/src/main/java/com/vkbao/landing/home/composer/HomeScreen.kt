package com.vkbao.landing.home.composer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vkbao.landing.LandingViewModel
import com.vkbao.test.R

val backgroundColor = Color(0xFFF5F7FB)

@Composable
internal fun HomeScreen (
    landingViewModel: LandingViewModel,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onSettingPress: () -> Unit
) {
    var itemList = listOf("USA", "VND", "CND", "Y", "CYN")
    Column(modifier = modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        Box(modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2196F3),
                        Color(0xFF42A5F5)
                    )
                ),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .fillMaxWidth()
            .padding(bottom = 24.dp)
        ) {
            Column {
                TopNavigationBar(
                    modifier = modifier.padding(4.dp),
                    title = stringResource(id = R.string.app_name),
                    leftIcon = painterResource(id = R.drawable.back),
                    leftAction = onBackPress,
                    rightIcon = painterResource(id = R.drawable.setting),
                    rightAction = onSettingPress
                )

                Spacer(modifier = modifier.height(48.dp))
                CardSection(
                    modifier = modifier.padding(start = 16.dp, end = 16.dp),
                    itemList = itemList
                )
            }
        }
    }
}

@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    title: String,
    leftIcon: Painter,
    leftAction: () -> Unit,
    rightIcon: Painter?,
    rightAction: (() -> Unit)?,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Row(modifier = modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier
                    .size(28.dp)
                    .clickable { leftAction() },
                painter = leftIcon,
                tint = Color.White,
                contentDescription = null
            )

            if (rightIcon != null) {
                Icon(
                    modifier = modifier
                        .size(28.dp)
                        .clickable { rightAction?.invoke() },
                    painter = rightIcon,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }

        Text(
            text = title,
            fontSize = 20.sp,
            color = Color.White,
        )
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
        .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
        .background(backgroundColor, shape = RoundedCornerShape(12.dp))
        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        CurrencyRow(
            currencyName = firstCurrency,
            itemList = itemList,
            onSelect = { firstCurrency = it },
            enableEdit = true,
            onValueChange = { fromValue = it },
            value = fromValue
        )

        Spacer(modifier = modifier.height(16.dp))
        IconButton(
            onClick = {  },
            modifier = modifier.background(Color(0xFF2196F3), shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.up_down),
                contentDescription = null,
                tint = backgroundColor,
                modifier = Modifier.width(18.dp).height(18.dp)
            )
        }
        Spacer(modifier = modifier.height(16.dp))

        CurrencyRow(
            currencyName = secondCurrency,
            itemList = itemList,
            onSelect = { secondCurrency = it },
            enableEdit = false,
            onValueChange = {},
            value = toValue
        )
    }

}

@Composable
fun CurrencyRow(
    currencyName: String,
    itemList: List<String>,
    onSelect: (String) -> Unit,
    enableEdit: Boolean,
    onValueChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(modifier = modifier) {
        Row(modifier = modifier,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = currencyName, fontSize = 18.sp)
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    painter = painterResource(id = R.drawable.down) ,
                    contentDescription = null,
                    modifier = modifier
                        .width(12.dp)
                        .height(12.dp)
                )
            }
        }

        //dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(text = { Text(text = item) }, onClick = { onSelect.invoke(item) })
            }
        }

        OutlinedTextField(
            value = value,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Right
            ),
            onValueChange = onValueChange,
            shape = RoundedCornerShape(12.dp),
            enabled = enableEdit,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                disabledBorderColor = Color.LightGray,
                focusedContainerColor = Color(0xFFeff3fe),
                unfocusedContainerColor = Color(0xFFeff3fe),
            )
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenReview() {
//    HomeScreen (onBackPress = {}, onSettingPress = {})
//}