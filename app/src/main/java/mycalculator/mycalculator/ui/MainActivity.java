package mycalculator.mycalculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import mycalculator.mycalculator.R;
import mycalculator.mycalculator.domain.CalculatorImpl;
import mycalculator.mycalculator.domain.MainContract;
import mycalculator.mycalculator.domain.Operation;


public class MainActivity extends AppCompatActivity implements MainContract.View{

    private final CalculatorPresenter calculatorPresenter = new CalculatorPresenter(new CalculatorViewResult(), this, new CalculatorImpl());
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);


        Map<Integer, Integer> numeralMap = new HashMap<>();
        numeralMap.put(R.id.button_1, 1);
        numeralMap.put(R.id.button_2, 2);
        numeralMap.put(R.id.button_3, 3);
        numeralMap.put(R.id.button_4, 4);
        numeralMap.put(R.id.button_5, 5);
        numeralMap.put(R.id.button_6, 6);
        numeralMap.put(R.id.button_7, 7);
        numeralMap.put(R.id.button_8, 8);
        numeralMap.put(R.id.button_9, 9);
        numeralMap.put(R.id.button_0, 0);

        Map<Integer, Operation> operationMap = new HashMap<>();
        operationMap.put(R.id.button_DIV, Operation.DIV);
        operationMap.put(R.id.button_ADD, Operation.ADD);
        operationMap.put(R.id.button_MULT, Operation.MULT);
        operationMap.put(R.id.button_SUB, Operation.SUB);

        View.OnClickListener numeralClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorPresenter.numeralWasClicked(numeralMap.get(v.getId()));
            }
        };

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorPresenter.commandWasClicked(operationMap.get(v.getId()));
            }
        };

        View.OnClickListener dotClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorPresenter.dotWasClicked();
            }
        };

        View.OnClickListener equalClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorPresenter.equallyWasClicked();
            }
        };

        findViewById(R.id.button_0).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_1).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_2).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_3).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_4).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_5).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_6).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_7).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_8).setOnClickListener(numeralClickListener);
        findViewById(R.id.button_9).setOnClickListener(numeralClickListener);

        findViewById(R.id.button_ADD).setOnClickListener(operationClickListener);
        findViewById(R.id.button_DIV).setOnClickListener(operationClickListener);
        findViewById(R.id.button_SUB).setOnClickListener(operationClickListener);
        findViewById(R.id.button_MULT).setOnClickListener(operationClickListener);

        findViewById(R.id.button_DOT).setOnClickListener(dotClickListener);

        findViewById(R.id.button_EQUALL).setOnClickListener(equalClickListener);

    }

    @Override
    public void showResult(String s) {
        result.setText(s);
    }
}