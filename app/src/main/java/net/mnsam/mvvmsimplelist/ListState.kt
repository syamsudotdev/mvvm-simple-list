package net.mnsam.mvvmsimplelist

sealed class WordListState
class WordList(list: List<Word>) : WordListState()
object WordEmpty : WordListState()