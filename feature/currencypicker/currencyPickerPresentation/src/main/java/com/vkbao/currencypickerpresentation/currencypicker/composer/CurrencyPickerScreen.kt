package com.vkbao.currencypickerpresentation.currencypicker.composer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vkbao.currencypickerpresentation.CurrencyPickerViewModel
import com.vkbao.currencypickerpresentation.R
import com.vkbao.currencypickerpresentation.model.CurrencyModel
import com.vkbao.currencypickerpresentation.model.GetCurrenciesState

val dividerColor = Color(0xFFCAD3F6)

@Composable
fun CurrencyPickerScreen(
    viewModel: CurrencyPickerViewModel,
    selectedCurrency: String,
    modifier: Modifier = Modifier,
    onGetBack: () -> Unit,
) {
    val currencyState by viewModel.currencyState.collectAsStateWithLifecycle()
    viewModel.getCurrencies()
    val selectedCurrencyState by viewModel.selectedCurrency.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xfff7f7ff))
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        TopNavigationBar(
            modifier = Modifier.padding(horizontal = 21.dp),
            rightIcon = painterResource(R.drawable.close),
            rightAction = onGetBack
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 21.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(12.dp)
                ),
            value = "",
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Left,
                lineHeight = 18.sp
            ),
            placeholder = {
                Text(
                    text = "Search currency",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            },
            onValueChange = {},
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFCAD3F6),
                unfocusedBorderColor = Color(0xFFCAD3F6),
                disabledBorderColor = Color(0xFFCAD3F6),
                focusedContainerColor = Color(0xFFF9FBFF),
                unfocusedContainerColor = Color(0xFFF9FBFF),
            )
        )
        Spacer(modifier = Modifier.height(48.dp))

        when(currencyState) {
            is GetCurrenciesState.Error -> {}
            GetCurrenciesState.Init,
            GetCurrenciesState.Loading -> {}
            is GetCurrenciesState.Success -> {
                val currencies = (currencyState as GetCurrenciesState.Success).data
                LazyColumn(modifier = Modifier.padding(horizontal = 21.dp)) {
                    itemsIndexed(currencies) { index, item ->
                        CurrencyItem(
                            isFirst = index == 0,
                            isSelected = false,
                            onSelect = {
                                viewModel.setSelectedCurrency(it)
                                onGetBack.invoke()
                            },
                            currency = item
                        )
                    }
                }
            }
        }

        if (selectedCurrencyState.isNotEmpty()) {
            onGetBack.invoke()
        }
    }
}

@Composable
fun CurrencyItem(
    isFirst: Boolean,
    isSelected: Boolean,
    onSelect: (String) -> Unit,
    currency: CurrencyModel
) {
    if (!isFirst) {
        Spacer(modifier = Modifier.padding(top = 21.dp))
    }
    Row(modifier = Modifier
        .clickable {
            onSelect.invoke(currency.code)
        }
        .padding(start = 16.dp)
    ) {
        Text(currency.code, fontSize = 18.sp, modifier = Modifier.defaultMinSize(minWidth = 60.dp))
        Spacer(modifier = Modifier.width(24.dp))
        Text(currency.name, fontSize = 18.sp)
    }
    Spacer(
        modifier = Modifier
            .padding(top = 21.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(dividerColor)
    )
}

@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    leftIcon: Painter? = null,
    leftAction: (() -> Unit)? = null,
    rightIcon: Painter? = null,
    rightAction: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leftIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { leftAction?.invoke() },
                    painter = leftIcon,
                    tint = Color.Black,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (rightIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { rightAction?.invoke() },
                    painter = rightIcon,
                    tint = Color.Black,
                    contentDescription = null
                )
            }
        }

        if (title != null) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black,
            )
        }
    }
}
