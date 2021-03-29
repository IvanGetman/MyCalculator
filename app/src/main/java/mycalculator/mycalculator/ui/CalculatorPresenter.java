package mycalculator.mycalculator.ui;

import android.content.Context;

import java.util.TooManyListenersException;

import mycalculator.mycalculator.domain.MainContract;
import mycalculator.mycalculator.domain.Operation;
import mycalculator.mycalculator.R;

public class CalculatorPresenter implements MainContract.Presenter {

    private final MainContract.View viewResult;

    private final Context context;

    private final MainContract.Calculator calculator;

    private Double leftArg = null;

    private Operation operation = null;

    private Double tmpValue = 0.0;

    private int power = 1;

    private boolean isEnteringRealPart = false;

    private boolean isNumeralPressed = false;

    private boolean isDelPressed = false;

    public CalculatorPresenter(MainContract.View viewResult, Context context, MainContract.Calculator calculator) {
        this.viewResult = viewResult;
        this.context = context;
        this.calculator = calculator;
    }

    @Override
    public void commandWasClicked(Operation operation) {
        if (isNumeralPressed) {
            if (this.operation != null) {
                leftArg = calculator.binaryOperation(leftArg, tmpValue, this.operation);
            } else {
                leftArg = tmpValue;
            }
        }
        viewResult.showResult(context.getString(R.string.res, leftArg));
        this.operation = operation;
        isEnteringRealPart = false;
        isNumeralPressed = false;
        isDelPressed = false;
        tmpValue = 0.0;
        power = 1;
    }

    @Override
    public void numeralWasClicked(int numeral) {
        if(isDelPressed){
            power++;
        }
        if (isEnteringRealPart) {
            tmpValue = tmpValue + numeral / Math.pow(10, power);
            power++;
        } else {
            tmpValue = tmpValue * 10 + numeral;
        }
        isNumeralPressed = true;
        isDelPressed = false;
        viewResult.showResult(context.getString(R.string.res, tmpValue));
    }

    @Override
    public void dotWasClicked() {
        isEnteringRealPart = true;
        isNumeralPressed = false;
        isDelPressed = false;
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
            isDelPressed = false;
            this.operation = null;
        }
    }

    @Override
    public void clrWasClicked() {
        leftArg = 0.0;
        this.operation = null;
        tmpValue = 0.0;
        power = 1;
        isEnteringRealPart = false;
        isNumeralPressed = false;
        isDelPressed = false;
        viewResult.showResult(context.getString(R.string.res, leftArg));
    }

    @Override
    public void delWasClicked() {
        if (!isDelPressed){
            power--;
        }
        if (isEnteringRealPart && power >= 1) {
            power--;
            tmpValue = Math.floor(tmpValue * Math.pow(10, power)) / Math.pow(10, power);
        } else {
            tmpValue = Math.floor(tmpValue / 10);
            isEnteringRealPart = false;
        }
        isDelPressed = true;
        viewResult.showResult(context.getString(R.string.res, tmpValue));
    }
}
