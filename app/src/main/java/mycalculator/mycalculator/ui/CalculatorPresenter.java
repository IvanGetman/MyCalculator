package mycalculator.mycalculator.ui;

import android.content.Context;

import mycalculator.mycalculator.domain.MainContract;
import mycalculator.mycalculator.domain.Operation;
import mycalculator.mycalculator.R;

public class CalculatorPresenter implements MainContract.Presenter {

    private final MainContract.View viewResult;

    private final Context context;

    private final MainContract.Calculator calculator;

    private Double leftArg = 0.0;

    private Operation operation = null;

    private Double tmpValue = 0.0;

    private int power = 1;

    private boolean isEnteringRealPart = false;

    private boolean isNumeralPressed = false;

    private boolean isCommandPressed = false;


    public CalculatorPresenter(MainContract.View viewResult, Context context, MainContract.Calculator calculator) {
        this.viewResult = viewResult;
        this.context = context;
        this.calculator = calculator;
    }

    @Override
    public void commandWasClicked(Operation operation) {

        if (isNumeralPressed && isCommandPressed) {
            leftArg = calculator.binaryOperation(leftArg, tmpValue, this.operation);
            viewResult.showResult(context.getString(R.string.res, leftArg));
            tmpValue = 0.0;
            power = 1;
            isEnteringRealPart = false;
            isCommandPressed = false;
            isNumeralPressed = false;
        } else {
            
            this.operation = operation;
            isCommandPressed = true;
        }
    }

    @Override
    public void numeralWasClicked(int numeral) {

        isNumeralPressed = true;

        if (isEnteringRealPart) {
            tmpValue = tmpValue + numeral / Math.pow(10, power);
            power++;
        } else {
            tmpValue = tmpValue * 10 + numeral;
        }

        viewResult.showResult(context.getString(R.string.res, tmpValue));
    }

    @Override
    public void dotWasClicked() {
        isEnteringRealPart = true;
        isNumeralPressed = false;
    }

    @Override
    public void equallyWasClicked() {
        if (this.operation != null && isNumeralPressed) {
            leftArg = calculator.binaryOperation(leftArg, tmpValue, this.operation);
            viewResult.showResult(context.getString(R.string.res, leftArg));
            tmpValue = 0.0;
            power = 1;
            isEnteringRealPart = false;
            isNumeralPressed = false;
            isCommandPressed = false;
        }
    }
}
