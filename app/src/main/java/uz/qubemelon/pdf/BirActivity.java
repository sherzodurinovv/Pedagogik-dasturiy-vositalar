package uz.qubemelon.pdf;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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

public class BirActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_arrow_back;
    private CardView lesson_one, lesson_two, lesson_third, lesson_four, lesson_five, lesson_six, lesson_seven, lesson_eigthn, lesson_nine, lesson_ten;

    // Map view IDs to file names
    private final Map<Integer, String> lessonFiles = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bir);

        // Initialize lesson files map
        lessonFiles.put(R.id.lesson_one, "bir/1.pdf");
        lessonFiles.put(R.id.lesson_two, "bir/2.pdf");
        lessonFiles.put(R.id.lesson_third, "bir/3.pdf");
        lessonFiles.put(R.id.lesson_four, "bir/4.pdf");
        lessonFiles.put(R.id.lesson_five, "bir/5.pdf");
        lessonFiles.put(R.id.lesson_six, "bir/6.pdf");
        lessonFiles.put(R.id.lesson_seven, "bir/7.pdf");
        lessonFiles.put(R.id.lesson_eigthn, "bir/8.pdf");
        lessonFiles.put(R.id.lesson_nine, "bir/9.pdf");
        lessonFiles.put(R.id.lesson_ten, "bir/10.pdf");

        // Initialize CardViews
        lesson_one = findViewById(R.id.lesson_one);
        lesson_one.setOnClickListener(this);
        lesson_two = findViewById(R.id.lesson_two);
        lesson_two.setOnClickListener(this);
        lesson_third = findViewById(R.id.lesson_third);
        lesson_third.setOnClickListener(this);
        lesson_four = findViewById(R.id.lesson_four);
        lesson_four.setOnClickListener(this);
        lesson_five = findViewById(R.id.lesson_five);
        lesson_five.setOnClickListener(this);
        lesson_six = findViewById(R.id.lesson_six);
        lesson_six.setOnClickListener(this);
        lesson_seven = findViewById(R.id.lesson_seven);
        lesson_seven.setOnClickListener(this);
        lesson_eigthn = findViewById(R.id.lesson_eigthn);
        lesson_eigthn.setOnClickListener(this);
        lesson_nine = findViewById(R.id.lesson_nine);
        lesson_nine.setOnClickListener(this);
        lesson_ten = findViewById(R.id.lesson_ten);
        lesson_ten.setOnClickListener(this);

        // Optional: Initialize back button (uncomment if needed)
        /*
        btn_arrow_back = findViewById(R.id.btn_arrow_back);
        btn_arrow_back.setOnClickListener(v -> finish());
        */
    }

    @Override
    public void onClick(View view) {
        String fileName = lessonFiles.get(view.getId());
        if (fileName != null) {
            openFile(this, fileName);
        } else {
            Toast.makeText(this, "Noma'lum xato yuz berdi!", Toast.LENGTH_SHORT).show();
        }
    }

    public static void openFile(Context context, String fileName) {
        try {

            android.content.res.AssetManager assetManager = context.getAssets();


            InputStream inputStream = assetManager.open(fileName);

            String outputFileName = fileName.substring(fileName.lastIndexOf('/') + 1);
            File outputFile = new File(context.getCacheDir(), outputFileName);


            try (InputStream input = inputStream; OutputStream output = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int read;
                while ((read = input.read(buffer)) != -1) {
                    output.write(buffer, 0, read);
                }
            }

            android.net.Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", outputFile);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, fileName.endsWith(".pdf") ? "application/pdf" : "application/msword");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Faylni ochish uchun mos dastur topilmadi!", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Faylni ochishda xatolik yuz berdi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}