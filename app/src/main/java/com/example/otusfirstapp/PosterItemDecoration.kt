package com.example.otusfirstapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class PosterItemDecoration(context: Context, orientation: Int) :
    DividerItemDecoration(context, orientation) {
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
        /*{
             val offset = 25
             outRect.top = offset
             outRect.left = offset
             outRect.right = offset/2

         }*/
        super.getItemOffsets(outRect, view, parent, state)
    }

}