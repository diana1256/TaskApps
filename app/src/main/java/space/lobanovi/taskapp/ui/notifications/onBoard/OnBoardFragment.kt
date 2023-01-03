package space.lobanovi.taskapp.ui.notifications.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import space.lobanovi.taskapp.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {

    private var _binding: FragmentOnBoardBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter(childFragmentManager,this::onSkipClick,this::onNextClick)
        adapter.also { it.also { binding.vpBoard.adapter = it } }

        binding.dotsIndicator.attachTo(binding.vpBoard)
    }
    private fun onSkipClick(){
        binding.vpBoard.setCurrentItem(initClick(+2),true)
    }
    private fun onNextClick(){
        binding.vpBoard.setCurrentItem(initClick(+1),true)
    }
    private fun initClick(i:Int): Int {
        return binding.vpBoard.currentItem +i
    }
}