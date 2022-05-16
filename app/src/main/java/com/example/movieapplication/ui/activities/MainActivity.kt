package com.example.movieapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapplication.R
import com.example.movieapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding  get() = _binding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        bottomNavigationView.background = null
        bottomNavigationView.menu.get(1).isEnabled = false
        fragment.findNavController().navigate(R.id.popularMovieFragment)
        bottomNavigationView.setupWithNavController(fragment.findNavController())
        fab.setOnClickListener {
            fragment.findNavController().navigate(R.id.searchFragment)
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.popularMovieFragment-> {
                    fragment.findNavController().navigate(R.id.popularMovieFragment)
                }
                R.id.topRatedMovieFragment-> {
                    fragment.findNavController().navigate(R.id.topRatedMovieFragment)
                }
            }
            true

        }


    }
}