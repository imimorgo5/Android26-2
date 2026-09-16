package ru.urfu.droidpractice1.content

import ru.urfu.droidpractice1.ArticleViewModel

class MainActivityScreenState(
    private val viewModel: ArticleViewModel
) {
    val ratingFlow = viewModel.rating
    val secondReadFlow = viewModel.secondRead

    fun like() = viewModel.like()
    fun dislike() = viewModel.dislike()
}