package com.example.roomfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomfinder.apiHandler.ApiService
import com.example.roomfinder.apiHandler.RetrofitClient
import com.example.roomfinder.model.CreateRoomRequestRequest
import com.example.roomfinder.model.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoomRequestViewModel : ViewModel() {
    private val _requestStatus = MutableStateFlow<RequestStatus>(RequestStatus.Idle)
    val requestStatus = _requestStatus.asStateFlow()

    private val _selectedRoom = MutableStateFlow<Room?>(null)
    val selectedRoom = _selectedRoom.asStateFlow()

    private val apiService = RetrofitClient.apiService

    sealed class RequestStatus {
        object Idle : RequestStatus()
        object Loading : RequestStatus()
        data class Success(val message: String) : RequestStatus()
        data class Error(val message: String) : RequestStatus()
    }

    fun submitRequest(request: CreateRoomRequestRequest) {
        viewModelScope.launch {
            _requestStatus.value = RequestStatus.Loading
            try {
                val response = apiService.createRoomRequest(request)
                if (response.isSuccessful) {
                    _requestStatus.value = RequestStatus.Success("Request submitted successfully")
                } else {
                    _requestStatus.value = RequestStatus.Error("Failed to submit request")
                }
            } catch (e: Exception) {
                _requestStatus.value = RequestStatus.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun setSelectedRoom(room: Room) {
        _selectedRoom.value = room
    }
}