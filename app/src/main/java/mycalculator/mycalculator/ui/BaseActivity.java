package mycalculator.mycalculator.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import mycalculator.mycalculator.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String APP_THEME = "APP_THEME";
    protected static final String NAME_SHARED_PREFERENCE = "LOGIN";
    protected final int themeLight = 0;
    protected final int themeDark = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyCalculatorStyle));
    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME, codeStyle);
    }

    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case themeDark:
                return R.style.MyCalculatorStyleDark;
            default:
                return R.style.MyCalculatorStyle;
        }
    }
}
