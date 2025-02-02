package com.example.roomfinder.models

data class LoginRequest(
    val studentNumber: String,
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String?,
    val user: UserData?
)

data class UserData(
    val id: String,
    val studentNumber: String,
    val email: String,
    // Add other user fields as needed
)

//JAVA SYNTAX
//class LoginRequest() {
//
//    public String studentNumber;
//    public String email;
//    public String password;
//
//    public String getStudentNumber() {
//        return studentNumber;
//    }
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setStudentNumber(String studentNumber) {
//        this.studentNumber = studentNumber;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
