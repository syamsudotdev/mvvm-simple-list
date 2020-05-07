package net.mnsam.mvvmsimplelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class WordViewModelUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockWordService: WordService

    @Mock
    private lateinit var wordObserver: Observer<WordListState>

    private lateinit var wordViewModel: WordViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when fetchAllWords, should invoke service getAll`() {
        //given
        wordViewModel = WordViewModel(mockWordService)

        //when
        wordViewModel.getWordState().observeForever(wordObserver)
        wordViewModel.fetchAllWords()

        //expected
        //mockWordService#getAllWords() should be invoked
        Mockito.verify(mockWordService).getAllWords()
        //observer#onChanged should be invoked with empty state
        Mockito.verify(wordObserver).onChanged(Mockito.isA(WordEmpty::class.java))
    }

    @Test
    fun `when viewmodel fetchAllWords is given not empty list by service, observer should have not empty list state`() {
        //given
        val fakeNotEmptyWordService = FakeNotEmptyWordService()
        wordViewModel = WordViewModel(fakeNotEmptyWordService)

        //when
        wordViewModel.getWordState().observeForever(wordObserver)
        wordViewModel.fetchAllWords()

        //expected
        //observer#onChanged should be invoked with not empty state
        Mockito.verify(wordObserver).onChanged(Mockito.isA(WordList::class.java))
    }

    @Test
    fun `when viewmodel searchWords is given not empty list by service, observer should have not empty list state`() {
        //given
        val fakeNotEmptyWordService = FakeNotEmptyWordService()
        wordViewModel = WordViewModel(fakeNotEmptyWordService)

        //when
        wordViewModel.getWordState().observeForever(wordObserver)
        wordViewModel.searchWords("fake query")

        //expected
        //observer#onChanged should be invoked with not empty state
        Mockito.verify(wordObserver).onChanged(Mockito.isA(WordList::class.java))
    }

    @Test
    fun `when searchWords, should invoke service searchWords with the same query`() {
        //given
        wordViewModel = WordViewModel(mockWordService)

        //when
        wordViewModel.getWordState().observeForever(wordObserver)
        val query = "fake query"
        wordViewModel.searchWords(query)

        //expected
        //make sure mockWordService#searchWord is invoked with the exact same query
        Mockito.verify(mockWordService).searchWord(query)
        //observer#onChanged should be invoked with empty state
        Mockito.verify(wordObserver).onChanged(Mockito.isA(WordEmpty::class.java))
    }

    //second test as above with different query, to make sure query is dynamic
    @Test
    fun `when searchWords, should invoke service searchWords with the same query_2`() {
        //given
        wordViewModel = WordViewModel(mockWordService)

        //when
        wordViewModel.getWordState().observeForever(wordObserver)
        val query = "fake different query"
        wordViewModel.searchWords(query)

        //expected
        //make sure mockWordService#searchWord is invoked with the exact same query
        Mockito.verify(mockWordService).searchWord(query)
        //observer#onChanged should be invoked with empty state
        Mockito.verify(wordObserver).onChanged(Mockito.isA(WordEmpty::class.java))
    }

    @Test
    fun `when searchWords is given not empty list by service, observer should invoke onChanged with not empty list`() {
        //given
        val fakeNotEmptyWordService = FakeNotEmptyWordService()
        wordViewModel = WordViewModel(fakeNotEmptyWordService)

        //when
        wordViewModel.getWordState().observeForever(wordObserver)
        val query = "fake not empty query"
        wordViewModel.searchWords(query)

        //expected
        //observer#onChanged should be invoked with empty state
        Mockito.verify(wordObserver).onChanged(Mockito.isA(WordList::class.java))
    }
}