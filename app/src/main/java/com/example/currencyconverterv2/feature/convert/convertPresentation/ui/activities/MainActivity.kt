package com.example.currencyconverterv2.feature.convert.convertPresentation.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.currencyconverterv2.R
import com.example.currencyconverterv2.databinding.ActivityMainBinding
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.dto.request.ExchangeRate
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.Currency
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.CurrencyState
import com.example.currencyconverterv2.feature.convert.convertPresentation.ui.adapters.CurrencySpinnerAdapter
import com.example.currencyconverterv2.feature.convert.convertPresentation.ui.custom_views.CircularKey
import com.example.currencyconverterv2.feature.convert.convertPresentation.ui.custom_views.StateIndicator
import com.example.currencyconverterv2.feature.convert.convertPresentation.ui.dialogs.ErrorDialog
import com.example.currencyconverterv2.feature.convert.convertPresentation.ui.viewmodels.CurrencyViewModel
import com.example.currencyconverterv2.feature.convert.convertPresentation.ui.viewmodels.ExchangeRateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val currencyViewModel: CurrencyViewModel by viewModels()
    private val exchangeRateViewModel: ExchangeRateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initCurrencyFrom()
        initCurrencyTo()
        initKeyboard()
        initConverter()
        initReverse()
        initRate()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initCurrencyFrom() {
        binding.currencyFromContainer.input.apply {
            setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    hideKeyboard(v)
                }
            }
            setOnTouchListener  { v, _ ->
                hideKeyboard(v)
                false
            }
            inputType = InputType.TYPE_CLASS_NUMBER
            requestFocus()
        }

        binding.currencyFromContainer.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.getItemAtPosition(position)?.let {
                    val currency = it as Currency
                    binding.currencyFromContainer.symbol.text = currency.symbol

                    val toCurrencyAdapter = binding.currencyToContainer.spinner.adapter as CurrencySpinnerAdapter?
                    toCurrencyAdapter?.setNewDisable(arrayOf(position))
                    exchangeRateViewModel.getExchangeRates(ExchangeRate(currency.code, null))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.currencyFromContainer.input.doAfterTextChanged {
            refreshResult()
        }
    }

    private fun refreshResult(setRateTextView: Boolean = false) {
        val exchangeResult = exchangeRateViewModel.exchangeRates.value

        when(exchangeResult) {
            is CurrencyState.Success -> {
                val rates = exchangeResult.successResponse.data
                val valueFrom = binding.currencyFromContainer.input.text.toString().toLongOrNull()
                val currencyTo = binding.currencyToContainer.spinner.selectedItem as Currency
                val rate = rates[currencyTo.code]

                if (setRateTextView) {
                    binding.rateProgressbar.visibility = View.GONE
                    binding.rate.text = rate.toString()
                    binding.rate.visibility = View.VISIBLE
                }

                binding.currencyToContainer.input.setText(
                    if (rate != null && valueFrom != null) (rate*valueFrom).toString()
                    else ""
                )
            }
            else -> {
                binding.currencyToContainer.input.setText("")
            }
        }
    }

    private fun initCurrencyTo() {
        binding.currencyToContainer.input.apply {
            isFocusable = false
            isClickable = false
            isLongClickable = false
        }

        binding.currencyToContainer.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.getItemAtPosition(position)?.let {
                    val currency = it as Currency
                    binding.currencyToContainer.symbol.text = currency.symbol

                    val fromCurrencyAdapter = binding.currencyFromContainer.spinner.adapter as CurrencySpinnerAdapter?
                    fromCurrencyAdapter?.setNewDisable(arrayOf(position))

                    refreshResult(true)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun initKeyboard() {
        val inputFrom = binding.currencyFromContainer.input
        for (i in 0 until binding.keyboard.root.childCount) {
            val childView = binding.keyboard.root.getChildAt(i)
            childView.setOnClickListener { v ->
                val number = inputFrom.text.toString()
                val cursorPosition = inputFrom.selectionStart

                when(v.id) {
                    R.id.delete -> {
                        if (cursorPosition > 0) {
                            val newNumber = number.substring(0, cursorPosition - 1) + number.substring(cursorPosition)
                            inputFrom.setText(newNumber)
                            inputFrom.setSelection(cursorPosition - 1)
                        }
                    }
                    R.id.delete_all -> {
                        inputFrom.setText("")
                    }
                    R.id.key_0 -> {
                        if (cursorPosition != 0) {
                            val newNumber = number.substring(0, cursorPosition) + (v as CircularKey).text + number.substring(cursorPosition)
                            inputFrom.setText(newNumber)
                            inputFrom.setSelection(cursorPosition + 1)
                        }

                    }
                    else -> {
                        val newNumber = number.substring(0, cursorPosition) + (v as CircularKey).text + number.substring(cursorPosition)
                        inputFrom.setText(newNumber)
                        inputFrom.setSelection(cursorPosition + 1)
                    }
                }
            }
        }
    }

    private fun initConverter() {
        binding.stateIndicatorContainer.setOnClickReloadListener {
            currencyViewModel.getCurrencies()
        }

        lifecycleScope.launch {
            currencyViewModel.currencies.collect {
                when (it) {
                    is CurrencyState.Idle -> {}
                    is CurrencyState.Error -> {
                        binding.stateIndicatorContainer.state = StateIndicator.State.ERROR
                        val dialog = ErrorDialog()
                        dialog.message = it.message
                        dialog.show(supportFragmentManager, null)
                    }
                    is CurrencyState.Loading -> {
                        binding.stateIndicatorContainer.state = StateIndicator.State.LOADING
                    }
                    is CurrencyState.Success -> {
                        binding.stateIndicatorContainer.state = StateIndicator.State.SUCCESS

                        val data = it.successResponse.data
                        val currencies = data.values.toTypedArray()
                        val adapterFrom = CurrencySpinnerAdapter(this@MainActivity, currencies)
                        binding.currencyFromContainer.spinner.adapter = adapterFrom

                        val adapterTo = CurrencySpinnerAdapter(this@MainActivity, currencies, arrayOf(0))
                        binding.currencyToContainer.spinner.adapter = adapterTo
                        binding.currencyToContainer.spinner.setSelection(1)
                    }
                }
            }
        }

        if (currencyViewModel.currencies.value == CurrencyState.Idle) {
            currencyViewModel.getCurrencies()
        }

    }

    private fun initReverse() {
        binding.reverse.setOnClickListener {
            val currencyFromPosition = binding.currencyFromContainer.spinner.selectedItemPosition
            val currencyToPosition = binding.currencyToContainer.spinner.selectedItemPosition

            binding.currencyFromContainer.spinner.setSelection(currencyToPosition)
            binding.currencyToContainer.spinner.setSelection(currencyFromPosition)
        }
    }

    private fun initRate() {
        lifecycleScope.launch {
            exchangeRateViewModel.exchangeRates.collect {
                when(it) {
                    is CurrencyState.Error -> {}
                    CurrencyState.Idle -> {}
                    CurrencyState.Loading -> {
                        binding.rateProgressbar.visibility = View.VISIBLE
                        binding.rate.visibility = View.GONE
                    }
                    is CurrencyState.Success -> refreshResult(true)
                }
            }
        }
    }
}