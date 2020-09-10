package com.fasoh.trialproject.flow.lists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.fasoh.trialproject.AutoDisposable
import com.fasoh.trialproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnersFragment : Fragment() {

    private val pageViewModel by viewModels<LearnersViewModel>()

    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        pageViewModel.setIndex(page, AutoDisposable().bindTo(lifecycle))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val recycler: RecyclerView = root.findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = root.findViewById(R.id.progressBar)

        pageViewModel.hourLearners.observe(viewLifecycleOwner, Observer {
            recycler.adapter = TopLearnerHoursAdapter(it)
            progressBar.visibility = View.GONE
        })

        pageViewModel.iqLearners.observe(viewLifecycleOwner, Observer {
            recycler.adapter = TopLearnerIqAdapter(it)
            progressBar.visibility = View.GONE
        })
        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): LearnersFragment {
            return LearnersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}