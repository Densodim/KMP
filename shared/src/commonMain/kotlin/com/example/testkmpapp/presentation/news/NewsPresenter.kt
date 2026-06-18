package com.example.testkmpapp.presentation.news

class NewsPresenter: Presenter {
    override var view: PresenterView? = null

    override fun attach(view: PresenterView) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
}
