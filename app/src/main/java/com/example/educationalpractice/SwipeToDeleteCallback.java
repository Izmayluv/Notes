package com.example.educationalpractice;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private final NoteAdapter mAdapter;
    private final Context mContext;

    public SwipeToDeleteCallback(NoteAdapter adapter, Context context) {
        super(0, ItemTouchHelper.RIGHT);
        mAdapter = adapter;
        mContext = context;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // Ничего не делаем, если элементы перемещаются между позициями
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // Удаляем элемент из адаптера
        int position = viewHolder.getAdapterPosition();
        mAdapter.deleteItem(position, mContext);
    }
}
