package space.lobanovi.taskapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var  _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.web.loadUrl("file:///android_asset/www/cat/dict/index.html")

        return binding.root
    }


}

