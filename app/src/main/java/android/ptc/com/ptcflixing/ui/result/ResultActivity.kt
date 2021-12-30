package android.ptc.com.ptcflixing.ui.result

import android.os.Bundle
import android.ptc.com.ptcflixing.databinding.ActivityResultBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

private const val SPAN_COUNT = 2

class ResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.activityResultToolbar)
        setListeners()
        setupObservers()
        setupRecyclerView()
    }

    private fun setListeners() {
        binding.activityResultBackBtn.setOnClickListener { onBackPressed() }
    }

    private fun setupObservers() {

    }

    private fun setupRecyclerView() {
        binding.activityResultRecyclerView.apply {
            layoutManager = GridLayoutManager(this@ResultActivity, SPAN_COUNT)
            adapter = ResultAdapter(
                listOf(
                    "https://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-s9-.jpg",
                    "https://cdn2.gsmarena.com/vv/bigpic/huawei-p20-pro-.jpg",
                    "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-nexus-new.jpg",
                    "https://cdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-nexus-new.jpg"
                )
            ) {}
        }
    }
}