package com.example.notesapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notesapp.R;
import com.example.notesapp.room.Note;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends ListAdapter<Note,NotesAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    public NotesAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.mListener = listener;
    }
    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return  oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position != RecyclerView.NO_POSITION){
            Note note = getItem(position);
            holder.bind(note);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView noteTitle;
        private AppCompatTextView noteDesc;
        private AppCompatTextView notePriority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.tv_note_title);
            noteDesc = itemView.findViewById(R.id.tv_note_desc);
            notePriority = itemView.findViewById(R.id.tv_note_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition() != -1)
                        mListener.OnNoteClick(getItem(getAdapterPosition()));
                }
            });

        }

        public void bind(Note note) {
            noteTitle.setText(note.getTitle());
            noteDesc.setText(note.getDescription());
            notePriority.setText(""+note.getPriority());
        }
    }

    interface OnItemClickListener{
        void OnNoteClick(Note note);
    }
}
