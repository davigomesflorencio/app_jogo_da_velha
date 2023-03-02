package com.example.gamevelha.ui.livemodel

import android.util.Log
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.ViewModel
import com.example.gamevelha.domain.model.Cell
import com.example.gamevelha.domain.model.Game
import com.example.gamevelha.util.StringUtil


class GameViewModel() : ViewModel() {
    var cells: ObservableArrayMap<String, String>? = null
    var game: Game? = null

    fun init(player1: String?, player2: String?) {
        game = Game(player1, player2)
        initsCellsView()
    }

    private fun initsCellsView() {
        cells = ObservableArrayMap()
        for (i in 0 until BOARD_SIZE)
            for (j in 0 until BOARD_SIZE)
                cells!![StringUtil.stringFromNumbers(i, j)] = " "
    }

    fun onClickedCellAt(row: Int, column: Int) {
        try {
            if (game!!.cells!![row][column] == null || game!!.cells!![row][column]!!.player!!.name == "-") {

                game!!.cells!![row][column] = Cell(game!!.currentPlayer)
                cells!![StringUtil.stringFromNumbers(row, column)] = game!!.currentPlayer!!.value
                if (game!!.hasGameEnded()) game!!.reset() else game!!.switchPlayer()

            }
        } catch (e: NullPointerException) {
            e.message?.let { Log.e("GameViewModel", it) }

        }
    }

    companion object {
        private val TAG = GameViewModel::class.simpleName
        private const val BOARD_SIZE = 3
    }
}