package com.susanafigueroa.u4_dessert_clicker_viewmodel.ui

import androidx.lifecycle.ViewModel
import com.susanafigueroa.u4_dessert_clicker_viewmodel.data.DessertUiState
import com.susanafigueroa.u4_dessert_clicker_viewmodel.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DessertClickerViewModel : ViewModel() {

    // Emits and saves the current state and notifies observers every time the state changes.
    private val _uiState = MutableStateFlow(DessertUiState())

    // Exposes a state that can only be modified within this class. Other components can observe it.
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    // update to state to increment dessertSold
    fun onDessertSold(desserts: List<Dessert>) {
        onCurrentDessertIndex(desserts)
        onCurrentDessertPrice(desserts)
        _uiState.value = _uiState.value.copy(dessertsSold = _uiState.value.dessertsSold + 1)
        onRevenue()
        onCurrentDessertIndex(desserts)
        onCurrentDessertPrice(desserts)
    }

    // update to state to update revenue
    fun onRevenue() {
        _uiState.value = _uiState.value.copy(
            revenue = _uiState.value.revenue + _uiState.value.currentDessertPrice
        )
    }

    // update to state to currentDessertIndex
    fun onCurrentDessertIndex(
        desserts: List<Dessert>
    ) {
        val sold = _uiState.value.dessertsSold
        val newIndex = desserts.indexOfLast { sold >= it.startProductionAmount }

        if (newIndex >= 0) {
            _uiState.value = _uiState.value.copy(currentDessertIndex = newIndex)
        }
    }

    // update to state to currentDessertPrice
    fun onCurrentDessertPrice(desserts: List<Dessert>) {
        _uiState.value = _uiState.value.copy(
            currentDessertPrice = desserts[_uiState.value.currentDessertIndex].price
        )
    }
}