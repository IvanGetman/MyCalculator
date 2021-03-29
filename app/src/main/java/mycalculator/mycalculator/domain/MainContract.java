package mycalculator.mycalculator.domain;

public interface MainContract {

    interface Calculator {
        double binaryOperation(double argumentOne, double argumentTwo, Operation operation);
    }

    interface Presenter {
        void commandWasClicked(Operation operation);

        void numeralWasClicked(int numeral);

        void dotWasClicked();

        void equallyWasClicked();

        void clrWasClicked();

        void delWasClicked();
    }

    interface View {
        void showResult(String result);
    }
}
