package com.example.otusfirstapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class PosterItemDecoration(context: Context, orientation: Int) :
    DividerItemDecoration(context, orientation) {
    val randoms = Random();

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val randomLeft = randoms.nextInt(10) * 30
        Log.i("rect", randomLeft.toString())
        outRect.set(randomLeft, 0, 0, 0);
    }
}
