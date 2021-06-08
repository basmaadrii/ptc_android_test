package android.ptc.com.ptcflixing.ui.activities

import android.os.Bundle
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ActivityMainBinding
import android.ptc.com.ptcflixing.viewmodels.MainViewModel
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = mainViewModel
        mainViewModel.backCLickEvent.observeForever { navController().popBackStack() }
    }


    private fun navController(): NavController {
        return findNavController(R.id.nav_host_fragment)
    }



}