package com.example.spinnerwheel.renderer;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spinnerwheel.R;
import com.example.spinnerwheel.settings.Settings;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsRenderer extends RecyclerView.Adapter<SettingsRenderer.ViewHolder> {

    private List<Settings> data;
    private OnSettingsClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Settings settings = data.get(position);
        Resources res = holder.color.getContext().getResources();
        int color = res.getColor(settings.getColor());
        holder.color.setBackgroundColor(color);
        holder.option.setText(settings.getOption());
        GradientDrawable drawable = (GradientDrawable) res.getDrawable(R.drawable.rounded_view);
        drawable.setColor(color);
        holder.color.setBackground(drawable);
        holder.setting.setOnClickListener((v) -> listener.onSettingsClick(settings.getId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Settings> data) {
        this.data = data;
    }

    public List<Settings> getData() {
        return data;
    }

    public void setListener(OnSettingsClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.settings)
        ImageView setting;
        @BindView(R.id.circle)
        View color;
        @BindView(R.id.option)
        TextView option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface OnSettingsClickListener {

        void onSettingsClick(int id);
    }
}
