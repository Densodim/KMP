package com.example.testkmpapp.presentation.news

interface Presenter {
    var view: PresenterView?

    fun attach(view: PresenterView)

    fun detach()
}
interface PresenterView {

    val presenter: Presenter?
}