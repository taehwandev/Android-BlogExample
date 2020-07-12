package tech.thdev.app.ui.activity_shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tech.thdev.app.R
import tech.thdev.app.databinding.ActivitySharedFragmentBinding

class ActivitySharedViewModelSampleFragment : Fragment() {

    private var _binding: ActivitySharedFragmentBinding? = null
    private val binding: ActivitySharedFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        ActivitySharedFragmentBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
        }
        binding.btnPrevious.setOnClickListener {
            findNavController().navigate(R.id.action_previous_activitySharedViewModel_to_DefaultViewModelFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}