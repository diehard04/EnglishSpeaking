package com.diehard04.englishspeaking.view.grammar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.diehard04.englishspeaking.databinding.FragmentGrammarBinding
import com.diehard04.englishspeaking.viewmodel.GrammarViewModel

class GrammarFragment : Fragment() {

    private lateinit var grammarViewModel: GrammarViewModel
    private var _binding: FragmentGrammarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        grammarViewModel = ViewModelProvider(this).get(GrammarViewModel::class.java)

        _binding = FragmentGrammarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        grammarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}