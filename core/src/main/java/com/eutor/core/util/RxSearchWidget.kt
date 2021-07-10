package com.eutor.core.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import android.text.TextWatcher
import android.text.Editable
import android.util.Log
import android.widget.SearchView
import io.reactivex.Observable
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class RxSearchWidget {
    private val disposable: CompositeDisposable = CompositeDisposable()
    private var subject: PublishSubject<String>? = null

    constructor(search: EditText, listener: RxSearchListener?) {
        val searchObservable = fromEditTextView(search)
        handleSearchObserver(searchObservable, listener)
    }

    constructor(search: TextInputEditText, listener: RxSearchListener?) {
        val searchObservable = fromEditTextView(search)
        handleSearchObserver(searchObservable, listener)
    }

    constructor() {}

    fun textSearch(keyword: String, listener: RxSearchListener?) {
        if (subject == null) {
            subject = PublishSubject.create()
        }
        subject!!.onNext(keyword)
        handleSearchObserver(subject!!, listener)
    }

    private fun handleSearchObserver(
        searchObservable: Observable<String>,
        listener: RxSearchListener?
    ) {
        val d = searchObservable
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io()) //.switchMapSingle((Function<String, SingleSource<ArrayList<BeatModel>>>) s -> null)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result: String? ->
                Log.e("Consumer", result!!)
                listener?.onSearchTextChange(result) ?: Log.e("Consumer", "Listener not null")
            }
        disposable!!.add(d)
    }



    private fun fromEditTextView(searchView: EditText): Observable<String> {
        val subject = PublishSubject.create<String>()
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                subject.onNext(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        return subject
    }

    private fun fromSearchView(searchView: SearchView): Observable<String> {
        val subject = PublishSubject.create<String>()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                subject.onNext(s)
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return true
            }
        })
        return subject
    }

    fun clear() {
        disposable?.clear()
    }

    interface RxSearchListener {
        fun onSearchTextChange(query: String?)
    }
}