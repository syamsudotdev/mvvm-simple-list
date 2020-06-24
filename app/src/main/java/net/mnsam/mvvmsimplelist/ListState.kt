package net.mnsam.mvvmsimplelist

sealed class WordListState {
    class WordList(val list: List<Word>) : WordListState()
    object Empty : WordListState()
}
