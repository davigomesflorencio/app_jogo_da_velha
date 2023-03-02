package com.example.gamevelha.ui

import android.os.Bundle
import android.widget.Button
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gamevelha.R
import com.example.gamevelha.databinding.ActivityGameBinding
import com.example.gamevelha.domain.model.Player
import com.example.gamevelha.ui.fragments.GameEndDialog
import com.example.gamevelha.ui.livemodel.GameViewModel
import com.example.gamevelha.ui.livemodel.GameViewModelFactory
import com.example.gamevelha.util.Constants
import com.example.gamevelha.util.StringUtil


class GameActivity : AppCompatActivity() {


    private var gameViewModel: GameViewModel? = null
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initDialogGame()
    }

    fun initDialogGame() {
        alertDialog = AlertDialog.Builder(this)
            .setTitle(R.string.game_dialog_title)
            .setCancelable(false)
            .setPositiveButton(R.string.done, null)
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.setOnShowListener { onDialogShow(alertDialog) }
        alertDialog.show()
    }

    private fun onDialogShow(dialog: AlertDialog) {
        val positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener { onDoneClicked() }
    }

    private fun onDoneClicked() {
        initDataBinding("Jogador 1 ", "Jogador 2")
        alertDialog.dismiss()
    }


    private fun initDataBinding(player1: String?, player2: String?) {
        val activityGameBinding: ActivityGameBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_game)
        gameViewModel =
            ViewModelProvider(this, GameViewModelFactory())[GameViewModel::class.java]
        gameViewModel!!.init(player1, player2)
        activityGameBinding.gameViewModel = gameViewModel
        setUpOnGameEndListener()
    }

    private fun setUpOnGameEndListener() {
        gameViewModel!!.game!!.winner.observe(this, this::onGameWinnerChanged)
    }

    @VisibleForTesting
    fun onGameWinnerChanged(winner: Player?) {
        val winnerName =
            if (winner == null || StringUtil.isNullOrEmpty(winner.name)) Constants.NO_WINNER else winner.name
        val dialog: GameEndDialog = GameEndDialog.newInstance(this, winnerName)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, Constants.GAME_END_DIALOG_TAG)
    }
}