package com.elhami.mvvmsample.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.elhami.mvvmsample.persistence.Word
import com.elhami.mvvmsample.persistence.WordDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.io.StringReader
import java.util.*

class MainActivityVM(private val wordDao: WordDao) : ViewModel() {
    val eng_word = ObservableField<String>()
    val spn_word = ObservableField<String>()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    /**
     * print the words
     */
    fun printList(words: List<Word>) {
        eng_word.set(words[0].text_eng)
        spn_word.set(words[0].text_spa)
    }

    /**
     * read data from db
     * if nothing exists
     * read data from json file (in assets)
     * and write it to db
     * return the list
     */
    @SuppressLint("CheckResult")
    fun loadData(context: Context) {
        Observable.fromCallable { wordDao.getAll() }.concatMap { db_word_List ->
            if (db_word_List.isEmpty()) {
                Observable.fromCallable { populate_data(context) }.concatMap { insert_list ->
                    wordDao.insertAll(insert_list)
                    Observable.just(insert_list)
                }
            } else {
                Observable.just(db_word_List)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> printList(result) }

    }

    /**
     * read data from json file in assets
     * @return string of data in assets
     */

    fun load_json_from_file(context: Context): String {
        try {
            val input_stream: InputStream = context.assets.open("words_v2.json")
            val input_string = input_stream.bufferedReader().use { it.readText() }
            Log.d(TAG, input_string)
            return input_string
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
        return ""
    }

    /**
     * convert string into list of Word
     * @property input_string name of the string
     * @return list of Word
     */

    fun generate_streaming_array(input_string: String): ArrayList<Word> {
        val klaxon = Klaxon()
        val result = arrayListOf<Word>()
        JsonReader(StringReader(input_string)).use { reader ->
            reader.beginArray {
                while (reader.hasNext()) {
                    val word = klaxon.parse<Word>(reader)
                    result.add(word!!)
                }
            }
        }
        return result
    }

    /**
     * Read data from assets file
     * @return a list of Word
     */
    fun populate_data(context: Context): ArrayList<Word> {
        val input_string = load_json_from_file(context)
        return generate_streaming_array(input_string)

    }
}