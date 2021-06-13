package android.ptc.com.ptcflixing.ui.fragments

import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.FragmentSearchBinding
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import android.ptc.com.ptcflixing.ui.common.BaseFragment

class FragmentSearch : BaseFragment<FragmentSearchBinding>() {
    override fun layout(): Int = R.layout.fragment_search
    override fun onActivityCreated() {
        initSearchView()
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        findNavController().navigate(
                            FragmentSearchDirections.actionFragmentSearchToFragmentProducts(
                                query
                            )
                        )
                    }
                    return false
                }
            })
    }


}