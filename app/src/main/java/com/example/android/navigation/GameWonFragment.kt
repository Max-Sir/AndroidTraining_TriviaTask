package com.example.android.navigation

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vibro=activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if(vibro.hasVibrator()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                vibro.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE))
            }
            else{
                vibro.vibrate(1000)
            }
        }
        else{
            Log.i("Vibrator_Service","Cannot perform such effect")
        }
        // Inflate the layout for this fragment
        args = GameWonFragmentArgs.fromBundle(arguments!!)
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false
        )
        binding.nextMatchButton.setOnClickListener { findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment) }


        setHasOptionsMenu(true)
        Toast.makeText(
            context,
            "Num Questions Correct is: ${args.numCorrect}\nTotal Questions Num: ${args.numQuestions}",
            Toast.LENGTH_SHORT
        ).show()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)

        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            menu?.findItem(R.id.share).setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private lateinit var args: GameWonFragmentArgs
    private fun getShareIntent(): Intent =
        Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_success_text, args.numCorrect, args.numQuestions)
        )

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
