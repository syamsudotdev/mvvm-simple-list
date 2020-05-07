package net.mnsam.mvvmsimplelist

interface WordService {
    fun getAllWords(): List<Word>

    fun searchWord(query: String): List<Word>
}