package com.susanafigueroa.u4_dessert_clicker_viewmodel.ui

import androidx.lifecycle.ViewModel
import com.susanafigueroa.u4_dessert_clicker_viewmodel.data.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DessertClickerViewModel : ViewModel() {

    // Emits and saves the current state and notifies observers every time the state changes.
    private val _uiState = MutableStateFlow(DessertUiState())

    // Exposes a state that can only be modified within this class. Other components can observe it.
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()
}