package com.example.roomfinder.apiHandler

import com.example.roomfinder.model.Admin
import com.example.roomfinder.model.AuthResponse
import com.example.roomfinder.model.CreateRoomRequest
import com.example.roomfinder.model.MessageResponse
import com.example.roomfinder.model.Room
import com.example.roomfinder.model.RoomRequest
import com.example.roomfinder.model.Student
import com.example.roomfinder.model.UpdateAdminRequest
import com.example.roomfinder.model.UpdateRoomRequest
import com.example.roomfinder.model.UpdateStudentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    // Student Endpoints
    @POST("student/signup")
    @Headers("Content-Type: application/json"
        ,"Accept: application/json")
    suspend fun createStudent(
        @Body student: Student
    ): Response<AuthResponse>

    @POST("student/login")
    @Headers("Content-Type: application/json"
        ,"Accept: application/json")
    suspend fun loginStudent(
        @Body student: Student
    ): Response<AuthResponse>

    @Headers("Content-Type: application/json"
        ,"Accept: application/json")
    @GET("student/{id}")
    suspend fun getStudent(@Path("id") id: Int): Response<Student>

    @GET("student")
    @Headers("Content-Type: application/json"
        ,"Accept: application/json")
    suspend fun getAllStudents(): Response<List<Student>>

    @PUT("student/{id}")
    @Headers("Content-Type: application/json"
        ,"Accept: application/json")
    suspend fun updateStudent(
        @Path("id") id: Int,
        @Body student: UpdateStudentRequest
    ): Response<MessageResponse>

    @DELETE("student/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<MessageResponse>

    // Room Endpoints
    @GET("room/{id}")
    suspend fun getRoom(@Path("id") id: Int): Response<Room>

    @GET("room")
    @Headers("Content-Type: application/json")
    suspend fun getAllRooms(): Response<List<Room>>

    @POST("room")
    suspend fun createRoom(@Body room: CreateRoomRequest): Response<MessageResponse>

    @PUT("room/{id}")
    suspend fun updateRoom(
        @Path("id") id: Int,
        @Body room: UpdateRoomRequest
    ): Response<MessageResponse>

    @DELETE("room/{id}")
    suspend fun deleteRoom(@Path("id") id: Int): Response<MessageResponse>

    // Room Request Endpoints
    @GET("room_request/{id}")
    suspend fun getRoomRequest(@Path("id") id: Int): Response<RoomRequest>

    @GET("room_request")
    suspend fun getAllRoomRequests(): Response<List<RoomRequest>>

    @POST("room_request")
    suspend fun createRoomRequest(
        @Body request: CreateRoomRequest
    ): Response<MessageResponse>

    @DELETE("room_request/{id}")
    suspend fun deleteRoomRequest(@Path("id") id: Int): Response<MessageResponse>

    // Admin Endpoints
    @GET("admin/{id}")
    suspend fun getAdmin(@Path("id") id: Int): Response<Admin>

    @GET("admin")
    suspend fun getAllAdmins(): Response<List<Admin>>

    @PUT("admin/{id}")
    suspend fun updateAdmin(
        @Path("id") id: Int,
        @Body admin: UpdateAdminRequest
    ): Response<MessageResponse>

    @DELETE("admin/{id}")
    suspend fun deleteAdmin(@Path("id") id: Int): Response<MessageResponse>
}