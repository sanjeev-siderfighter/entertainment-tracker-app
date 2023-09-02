package com.siderfighter.entertainmenttracker.applayers.domain.categories.entity

data class CategoryList(
    val categories: List<Category>
)

data class Category(
    val id: Int,
    val name: String,
)