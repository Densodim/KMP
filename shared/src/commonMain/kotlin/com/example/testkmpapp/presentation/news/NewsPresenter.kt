package com.example.testkmpapp.presentation.news

class NewsPresenter: Presenter {
    override var view: View? = null

    override fun attach(view: View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
}
