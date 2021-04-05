package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uc.edu.klopfsea.groupscheduler.R


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    companion object {
        fun newInstance() = MainFragment()
    }
}

