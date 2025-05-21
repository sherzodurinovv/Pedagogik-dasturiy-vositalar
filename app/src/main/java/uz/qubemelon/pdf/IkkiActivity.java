package uz.qubemelon.pdf;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class IkkiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_arrow_back;
    private CardView lesson_one, lesson_two, lesson_third, lesson_four, lesson_five, lesson_six, lesson_seven, lesson_eigthn, lesson_nine;

    private CardView card_view_lessons, card_view_laboratory, card_view_uch, card_view_turt, cardview_5, card_view_6, cardview_7, card_view_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ikki);
        init_view();
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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.card_view_lessons) {
            Intent intent = new Intent(getApplicationContext(), ikActivity.class);
            startActivity(intent);
        } else if (id == R.id.card_view_laboratory) {
            Intent intent = new Intent(getApplicationContext(), AvActivity.class);
            startActivity(intent);
        } else if (id == R.id.card_view_uch) {
            Intent intent = new Intent(getApplicationContext(), AvActivity.class);
            startActivity(intent);
        } else if (id == R.id.card_view_turt) {
            Intent intent = new Intent(getApplicationContext(), AvActivity.class);
            startActivity(intent);
        } else if (id == R.id.cardview_5) {
            Intent intent = new Intent(getApplicationContext(), AvActivity.class);
            startActivity(intent);
                }
    }

    private void init_view() {
        card_view_lessons = findViewById(R.id.card_view_lessons);
        card_view_laboratory = findViewById(R.id.card_view_laboratory);
        card_view_uch = findViewById(R.id.card_view_uch);
        card_view_turt = findViewById(R.id.card_view_turt);
        cardview_5 = findViewById(R.id.cardview_5);


        card_view_lessons.setOnClickListener(this);
        card_view_laboratory.setOnClickListener(this);
        card_view_uch.setOnClickListener(this);
        card_view_turt.setOnClickListener(this);
        cardview_5.setOnClickListener(this);

    }
}
