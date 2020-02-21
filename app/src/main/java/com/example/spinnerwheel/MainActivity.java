package com.example.spinnerwheel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spinnerwheel.renderer.SettingsRenderer;
import com.example.spinnerwheel.settings.SettingsProvider;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SettingsRenderer.OnSettingsClickListener {

    private static final String POLICY_URL = "http://google.com";
    @BindView(R.id.option_recycler)
    RecyclerView optionRecycler;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.rotate_btn)
    Button rotateBtn;

    private Random random = new Random();

    private Handler handler = new Handler();
    private Runnable rotateWorker = () -> {
        rotateBtn.setEnabled(true);
        isRunning = false;
    };

    private volatile boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecycler();
    }

    @OnClick(R.id.policy_btn)
    public void onPolicyClick() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.policy_message)
                .setPositiveButton(R.string.yes, (dialog1, which) -> {
                    Uri uri = Uri.parse(POLICY_URL);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }).show();
    }

    @OnClick(R.id.rotate_btn)
    public void onRotateClick(Button button) {
        int animTime = random.nextInt(4000) + 4000;
        int rotationValue = random.nextInt(4000) + 1000;
        arrow.animate().rotationBy(rotationValue).setDuration(animTime);
        button.setEnabled(false);
        isRunning = true;
        handler.postDelayed(rotateWorker, animTime);
    }

    private void initRecycler() {
        SettingsRenderer renderer = new SettingsRenderer();
        renderer.setData(SettingsProvider.getSettings());
        renderer.setListener(this);

        optionRecycler.setAdapter(renderer);
        optionRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSettingsClick(int id) {
        if (isRunning) return;
        showSettingsDialog(id);
    }

    private void showSettingsDialog(int optionPos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText valueKey = new EditText(this);
        builder.setTitle(R.string.settings_dialog_title)
                .setView(valueKey)
                .setCancelable(true)
                .setPositiveButton(R.string.ok,
                        (dialog, id) -> {
                            changeSettingsData(String.valueOf(valueKey.getText()), optionPos);
                            dialog.dismiss();
                        });

        builder.show();

    }

    private void changeSettingsData(String newOption, int optionPos) {
        SettingsRenderer adapter = (SettingsRenderer) optionRecycler.getAdapter();
        if (adapter != null) {
            adapter.getData().get(optionPos).setOption(newOption);
            adapter.notifyDataSetChanged();
        }
    }
}
