package com.example.gamevelha.domain.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gamevelha.util.StringUtil

class Game(namePlayerOne: String?, namePlayerTwo: String?) {
    private var player1: Player?
    private var player2: Player?
    var currentPlayer: Player?
    var cells: Array<Array<Cell?>>? = null
    var winner = MutableLiveData<Player?>()

    init {
        initsCells()
        player1 = Player(namePlayerOne!!, "x")
        player2 = Player(namePlayerTwo!!, "o")
        currentPlayer = player1
    }

    private fun initsCells() {
        cells = Array(BOARD_SIZE) {
            arrayOfNulls(
                BOARD_SIZE
            )
        }
        for (i in 0 until BOARD_SIZE)
            for (j in 0 until BOARD_SIZE)
                cells!![i][j] = Cell(Player("-", "-"))
    }

    fun hasGameEnded(): Boolean {
        if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()) {
            winner.value = currentPlayer
            return true
        }
        if (isBoardFull) {
            winner.value = null
            return true
        }
        return false
    }

    private fun hasThreeSameHorizontalCells(): Boolean {
        return try {
            for (i in 0 until BOARD_SIZE) if (areEqual(
                    cells!![i][0]!!,
                    cells!![i][1]!!, cells!![i][2]!!
                )
            ) return true
            false
        } catch (e: NullPointerException) {
            Log.e("Game ", "Null point exception")
            e.message?.let { Log.e(TAG, it) }
            false
        }
    }

    private fun hasThreeSameVerticalCells(): Boolean {
        return try {
            for (i in 0 until BOARD_SIZE) if (areEqual(
                    cells!![0][i]!!,
                    cells!![1][i]!!, cells!![2][i]!!
                )
            ) return true
            false
        } catch (e: NullPointerException) {
            e.message?.let { Log.e(TAG, it) }
            false
        }
    }

    private fun hasThreeSameDiagonalCells(): Boolean {
        return try {
            areEqual(
                cells!![0][0]!!, cells!![1][1]!!,
                cells!![2][2]!!
            ) ||
                    areEqual(
                        cells!![0][2]!!, cells!![1][1]!!,
                        cells!![2][0]!!
                    )
        } catch (e: NullPointerException) {
            e.message?.let { Log.e(TAG, it) }
            false
        }
    }

    private val isBoardFull: Boolean
        get() {
            for (row in cells!!) for (cell in row) if (cell == null || cell.isEmpty || cell.player!!.name == "-") return false
            return true
        }

    private fun areEqual(vararg cells: Cell): Boolean {
        if (cells.isEmpty()) return false

        for (cell in cells) {
            if (StringUtil.isNullOrEmpty(
                    cell.player!!.value
                )
            ) return false
        }

        val comparisonBase = cells[0]
        for (i in 1 until cells.size) if ((comparisonBase.player!!.value != cells[i].player!!.value) || (comparisonBase.player!!.name == "-") || (cells[i].player!!.name == "-")) return false
        return true
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    fun reset() {
        player1 = null
        player2 = null
        currentPlayer = null
        initsCells()
    }

    companion object {
        private val TAG = Game::class.simpleName
        private const val BOARD_SIZE = 3
    }
}