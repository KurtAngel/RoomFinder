package com.example.roomfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomfinder.apiHandler.RetrofitClient
import com.example.roomfinder.model.Room
import com.example.roomfinder.model.RoomRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms = _rooms.asStateFlow()

    private val _pendingRequests = MutableStateFlow<List<RoomRequest>>(emptyList())
    val pendingRequests = _pendingRequests.asStateFlow()

    private val apiService = RetrofitClient.apiService

    init {
        loadRooms()
        loadPendingRequests()
    }
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading
        .onStart {
            loadData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )
    private fun loadData(){
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000L)
            _isLoading.value = false
        }
    }

    private fun loadRooms() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.getAllRooms()
                if (response.isSuccessful) {
                    _rooms.value = response.body() ?: emptyList()
                }
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loadPendingRequests() {
        viewModelScope.launch {
            try {
                val response = apiService.getAllRoomRequests()
                if (response.isSuccessful) {
                    _pendingRequests.value = response.body() ?: emptyList()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}