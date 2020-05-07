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

    private val wordObserver = Observer<WordListState> {
        if (it != null) {
            when(it) {
                is WordList -> {
                    //set adapter dataset
                }

                is WordEmpty -> {
                    //hide recyclerview, show text empty
                }
            }
        }
    }

    private fun observeWord() {
        wordViewModel.getWordState().observe(this, wordObserver)
    }

    private fun setupRecyclerView() {
        //setup recycler view here
    }
}
