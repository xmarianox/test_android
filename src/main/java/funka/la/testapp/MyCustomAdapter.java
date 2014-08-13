package funka.la.testapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private ArrayList<String> mDataset;
    private static Context sContext;

    // Constructor para el adaptador
    public MyCustomAdapter(Context context, ArrayList<String> myDataset) {
        mDataset = myDataset;
        sContext = context;
    }

    // Creamos nuevas vistas. Este metodo es invocado por el layput manager.
    @Override
    public MyCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Creamos la nueva vista a partir del item_row
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        // Llenamos la vista con los datos.
        ViewHolder holder = new ViewHolder(v);
        holder.mNameTextView.setOnClickListener(MyCustomAdapter.this);
        holder.mNameTextView.setOnLongClickListener(MyCustomAdapter.this);

        holder.mNameTextView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.mNumberRowTextView.setText(String.valueOf(position) + ". ");
        viewHolder.mNameTextView.setText(mDataset.get(position));

        if (position % 2 == 0) {
            viewHolder.mNumberRowTextView.setTextColor(Color.RED);
        } else {
            viewHolder.mNumberRowTextView.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (view.getId() == holder.mNameTextView.getId()) {
            Toast.makeText(sContext, holder.mNameTextView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (view.getId() == holder.mNameTextView.getId()) {
            mDataset.remove(holder.getPosition());

            notifyDataSetChanged();

            Toast.makeText(sContext, "El Item " + holder.mNameTextView.getText() + " fue removido de la lista", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    // Clase estatica para implementar viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNumberRowTextView;
        public TextView mNameTextView;

        public ViewHolder(View v) {
            super(v);

            mNumberRowTextView = (TextView) v.findViewById(R.id.rowNumberTextView);
            mNameTextView = (TextView) v.findViewById(R.id.nameTextView);
        }
    }

}
