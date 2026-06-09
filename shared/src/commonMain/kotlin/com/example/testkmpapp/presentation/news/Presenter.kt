package com.example.testkmpapp.presentation.news

interface Presenter {
    var view: View?

    fun attach(view: View)

    fun detach()
}
interface View {

    val presenter: Presenter?
}