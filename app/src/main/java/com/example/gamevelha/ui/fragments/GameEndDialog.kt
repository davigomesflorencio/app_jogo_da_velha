package com.example.gamevelha.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.gamevelha.R
import com.example.gamevelha.ui.GameActivity


class GameEndDialog : DialogFragment() {

    private var rootView: View? = null
    private var activity: GameActivity? = null
    private var winnerName: String? = null

    companion object {

        fun newInstance(activity: GameActivity?, winnerName: String?): GameEndDialog {
            val dialog = GameEndDialog()
            dialog.activity = activity
            dialog.winnerName = winnerName
            return dialog
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()
        val alertDialog: AlertDialog = AlertDialog.Builder(requireContext())
            .setView(rootView)
            .setCancelable(false)
            .setPositiveButton(R.string.done) { _, _ -> onNewGame() }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        return alertDialog
    }

    private fun initViews() {
        rootView = LayoutInflater.from(context).inflate(R.layout.game_end_dialog, null, false)
        (rootView!!.findViewById<View>(R.id.tv_winner) as TextView).text = winnerName
    }

    private fun onNewGame() {
        dismiss()
        activity?.initDialogGame()
    }
}