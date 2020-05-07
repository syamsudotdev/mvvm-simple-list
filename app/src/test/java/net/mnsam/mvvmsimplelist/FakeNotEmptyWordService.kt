package net.mnsam.mvvmsimplelist

class FakeNotEmptyWordService : WordService {
    override fun getAllWords(): List<Word> {
        return listOf(Word("1"))
    }

    override fun searchWord(query: String): List<Word> {
        return listOf(Word("1"))
    }
}