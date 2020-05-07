package net.mnsam.mvvmsimplelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel(private val wordService: WordService) : ViewModel() {

    private val wordState = MutableLiveData<WordListState>()

    fun getWordState(): LiveData<WordListState> = wordState

    fun fetchAllWords() {
        val words = wordService.getAllWords()
        if (words.isNotEmpty()) {
            wordState.value = WordList(words)
        } else {
            wordState.value = WordEmpty
        }
    }

    fun searchWords(query: String) {
        val words = wordService.searchWord(query)
        if (words.isNotEmpty()) {
            wordState.value = WordList(words)
        } else {
            wordState.value = WordEmpty
        }
    }
}