package net.mnsam.mvvmsimplelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel(private val wordService: WordService) : ViewModel() {

    private val _wordState = MutableLiveData<WordListState>()
    val wordState: LiveData<WordListState>
        get() = _wordState

    fun fetchAllWords() {
        val words = wordService.getAllWords()
        if (words.isNotEmpty()) {
            _wordState.value = WordListState.WordList(words)
        } else {
            _wordState.value = WordListState.Empty
        }
    }

    fun searchWords(query: String) {
        val words = wordService.searchWord(query)
        if (words.isNotEmpty()) {
            _wordState.value = WordListState.WordList(words)
        } else {
            _wordState.value = WordListState.Empty
        }
    }
}