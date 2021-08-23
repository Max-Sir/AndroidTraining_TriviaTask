
package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_over, container, false
        )
        binding.tryAgainButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameOverFragment2_to_gameFragment)
        }
        val args=GameOverFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context,"U failed ^_^\nTry Again\n Your result:\nNum Questions Correct is: ${args.numAnswered}\n" +
                "Total Questions Num: ${args.numQuestions}",Toast.LENGTH_SHORT).show()
        return binding.root
    }
}
