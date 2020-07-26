package com.catatan.x;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.*;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.ViewHolder> {
    private Context context;
    private List<CatatanModel> notes;

    public CatatanAdapter(Context context) {
        this.context = context;
        this.notes = new ArrayList<>();
    }

    public void setNotes(List<CatatanModel> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_catatan, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final CatatanModel noteModel = notes.get(i);

        viewHolder.tvTitle.setText(noteModel.getNoteTitle());
        viewHolder.tvContent.setText(noteModel.getNoteContent());
        viewHolder.tvDate.setText(noteModel.getNoteDate());
        viewHolder.cvItem.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, Add.class);
					intent.putExtra("isEdit", true);
					intent.putExtra("noteID", noteModel.getNoteID());
					context.startActivity(intent);
				}
			});
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
	void setFilter(ArrayList<CatatanModel> filterList){
        notes = new ArrayList<>();
        notes.addAll(filterList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout cvItem;
        private TextView tvTitle, tvContent, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem =(RelativeLayout) itemView.findViewById(R.id.cv_item);
            tvTitle =(TextView) itemView.findViewById(R.id.tv_title);
            tvContent =(TextView) itemView.findViewById(R.id.tv_content);
            tvDate =(TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
