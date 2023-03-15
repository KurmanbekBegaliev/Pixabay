package com.example.pixabay

data class PixabayModel(
    val hits: List<ImageModel>
) : List<ImageModel>

data class ImageModel(
    val largeImageURL: String
)
