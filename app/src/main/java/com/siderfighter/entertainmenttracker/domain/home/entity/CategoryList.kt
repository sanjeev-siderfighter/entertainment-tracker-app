package com.siderfighter.entertainmenttracker.domain.home.entity

data class CategoryList(
    val categories: List<Category>
)

data class Category(
    val id: Int,
    val name: String,
)