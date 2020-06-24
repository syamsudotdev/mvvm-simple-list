package net.mnsam.mvvmsimplelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel =
            ViewModelProvider(this, WordViewModelFactory()).get(WordViewModel::class.java)
        setupRecyclerView()
        observeWord()
    }

    private val wordObserver = Observer<WordListState> { state ->
        if (state != null) {
            when(state) {
                is WordListState.WordList -> {
                    val list = state.list
                    //set adapter dataset from list
                }

                is WordListState.Empty -> {
                    //hide recyclerview, show text empty
                }
            }
        }
    }

    private fun observeWord() {
        wordViewModel.wordState.observe(this, wordObserver)
    }

    private fun setupRecyclerView() {
        //setup recycler view here
    }
}
