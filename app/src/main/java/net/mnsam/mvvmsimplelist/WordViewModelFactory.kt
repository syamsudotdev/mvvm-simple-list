package net.mnsam.mvvmsimplelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WordViewModelFactory : ViewModelProvider.Factory {
    private val wordViewModel: WordViewModel by lazy {
        WordViewModel(WordServiceImpl())
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = wordViewModel as T

    companion object {
        val INSTANCE: WordViewModelFactory by lazy { WordViewModelFactory() }
    }
}