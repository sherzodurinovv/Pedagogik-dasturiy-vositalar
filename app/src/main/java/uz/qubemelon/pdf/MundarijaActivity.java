package uz.qubemelon.pdf;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MundarijaActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView card_view_lessons, card_view_laboratory, card_view_literature;

    private final Map<Integer, Class<?>> activityMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mundarija);

        activityMap.put(R.id.card_view_lessons, BirActivity.class);
        activityMap.put(R.id.card_view_laboratory, IkkiActivity.class);

        initView();
    }

    @Override
    public void onClick(View view) {
        Class<?> targetActivity = activityMap.get(view.getId());
        if (targetActivity != null) {
            Intent intent = new Intent(getApplicationContext(), targetActivity);
            startActivity(intent);
        } else {

            Toast.makeText(this, "Noma'lum xato yuz berdi!", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFile(Context context, String fileName) {
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            File outFile = new File(context.getCacheDir(), new File(fileName).getName());

            try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
                byte[] buffer = new byte[1024];
                int read;
                while ((read = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, read);
                }
            }

            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", outFile);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            context.startActivity(intent);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Fayl yuklanmadi!", Toast.LENGTH_SHORT).show();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Faylni ochish uchun mos dastur topilmadi!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        card_view_lessons = findViewById(R.id.card_view_lessons);
        card_view_laboratory = findViewById(R.id.card_view_laboratory);
        card_view_literature = findViewById(R.id.card_view_literature);


        card_view_lessons.setOnClickListener(this);
        card_view_laboratory.setOnClickListener(this);
        card_view_literature.setOnClickListener(v -> openFile(this, "bir/Muallif.pdf"));

    }
}