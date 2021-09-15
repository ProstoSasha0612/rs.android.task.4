package com.hfad.android.storageapp;

import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelper {
    
//    val itemTouchHelper =     //TODO: Add icons to swaps ( i know, it is hard but i might make it)
//            ItemTouchHelper(object :
//            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//        override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//            return true
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            if (direction == ItemTouchHelper.LEFT) {
//                val humanToDelete = (viewHolder as HumanAdapter.HumanViewHolder).human
//                delete(humanToDelete)
//            } else {
//
//            }
//
//
//        }
//
//        override fun onChildDraw(
//                c: Canvas,
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                dX: Float,
//                dY: Float,
//                actionState: Int,
//                isCurrentlyActive: Boolean
//            ) {
//            val itemView = viewHolder.itemView
//            val iconMargin = itemView.height - (editIcon.intrinsicHeight ?: 0) / 2
//
//            if (dX > 0) {    //swipe right
//                swipeBackgroundUpdate.setBounds(
//                        itemView.left,
//                        itemView.top,
//                        dX.toInt(),
//                        itemView.bottom
//                )
//                editIcon.setBounds(
//                        itemView.left + iconMargin,
//                        itemView.top + iconMargin,
//                        itemView.left + iconMargin + editIcon.intrinsicWidth,
//                        itemView.bottom - iconMargin
//                )
//                swipeBackgroundUpdate.draw(c)
//                editIcon?.draw(c)
//            } else {        //swipe left
//                swipeBackgroundDelete.setBounds(
//                        itemView.right + dX.toInt(),
//                        itemView.top,
//                        itemView.right,
//                        itemView.bottom
//                )
//                deleteIcon.setBounds(
//                        itemView.right - iconMargin - deleteIcon.intrinsicWidth,
//                        itemView.top + iconMargin,
//                        itemView.right - iconMargin + deleteIcon.intrinsicWidth,
//                        itemView.bottom - iconMargin
//                )
//
//
//            }
//
//            swipeBackgroundUpdate.draw(c)
//            editIcon.draw(c)
//
//
//            super.onChildDraw(
//                    c,
//                    recyclerView,
//                    viewHolder,
//                    dX,
//                    dY,
//                    actionState,
//                    isCurrentlyActive
//            )
//        }
//    })
}
