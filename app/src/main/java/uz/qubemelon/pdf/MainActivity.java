package uz.qubemelon.pdf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            Intent intent = new Intent(this, MundarijaActivity.class); // Use 'this' instead of getApplicationContext()
            startActivity(intent);
        }
    }

    private void initView() {
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }
}