package cc.ytdttj.costeffectiveness;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.os.Build;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsControllerCompat;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput1, priceInput1, weightInput2, priceInput2;
    private Button calculateButton;
    private TextView resultTextView;
    private ImageView imageView;
    private View rootView;
    private WindowInsetsControllerCompat windowInsetsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
            window.setNavigationBarColor(android.graphics.Color.TRANSPARENT);
            windowInsetsController = new WindowInsetsControllerCompat(window, window.getDecorView());
        }

        rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                v.setPadding(
                        v.getPaddingLeft(),
                        v.getPaddingTop(),
                        v.getPaddingRight(),
                        insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
                );
                return insets;
            }
        });

        weightInput1 = findViewById(R.id.weightInput1);
        priceInput1 = findViewById(R.id.priceInput1);
        weightInput2 = findViewById(R.id.weightInput2);
        priceInput2 = findViewById(R.id.priceInput2);
        calculateButton = findViewById(R.id.button);
        resultTextView = findViewById(R.id.textView8);
        imageView = findViewById(R.id.imageView);

        updateStatusBarColor();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndCompare();
            }
        });
    }

    private void calculateAndCompare() {
        String weightStr1 = weightInput1.getText().toString();
        String priceStr1 = priceInput1.getText().toString();
        String weightStr2 = weightInput2.getText().toString();
        String priceStr2 = priceInput2.getText().toString();

        if (weightStr1.isEmpty() || priceStr1.isEmpty() || weightStr2.isEmpty() || priceStr2.isEmpty()) {
            Toast.makeText(this, "请确保所有输入框都有值", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight1 = Double.parseDouble(weightStr1);
        double price1 = Double.parseDouble(priceStr1);
        double weight2 = Double.parseDouble(weightStr2);
        double price2 = Double.parseDouble(priceStr2);

        double unitPrice1 = price1 / weight1;
        double unitPrice2 = price2 / weight2;

        String result;
        if (unitPrice1 < unitPrice2) {
            result = "一号商品单位价格更低";
        } else if (unitPrice1 > unitPrice2) {
            result = "二号商品单位价格更低";
        } else {
            result = "两件商品的单位价格相同";
        }

        resultTextView.setText(result);
        displayGif();
    }

    private void displayGif() {
        imageView.setVisibility(View.VISIBLE);
        Glide.with(this)
                .asGif()
                .load(R.drawable.xtxg)
                .into(imageView);
    }

    private void updateStatusBarColor() {
        boolean isLightBackground = isLightBackground();

        if (windowInsetsController != null) {
            if (isLightBackground) {
                windowInsetsController.setAppearanceLightStatusBars(true); // 设置状态栏图标为深色
            } else {
                windowInsetsController.setAppearanceLightStatusBars(false); // 设置状态栏图标为浅色
            }
        }
    }

    private boolean isLightBackground() {
        //  返回 true 表示浅色背景，false 表示深色背景
        return true; // 默认返回true
    }
}
