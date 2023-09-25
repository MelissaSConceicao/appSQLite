package com.example.appsqlite.model

data class CourseModel(
    // Criando um modelo de objeto para encaixar os dados do curso
    var courseName: String,
    var courseDuration: String,
    var courseTracks: String,
    var courseDescription: String
)