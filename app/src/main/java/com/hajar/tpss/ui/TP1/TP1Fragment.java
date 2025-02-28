package com.hajar.tpss.ui.TP1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hajar.tpss.R;

public class TP1Fragment extends Fragment {
    private EditText editText;
    private double firstOperand = 0;
    private String currentInput = "";
    private String operator = "";
    private boolean isNewInput = true;



    private TP1ViewModel mViewModel;

    public static TP1Fragment newInstance() {
        return new TP1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_t_p1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);

        // Number Buttons
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };
        for (int id : numberButtonIds) {
            view.findViewById(id).setOnClickListener(this::onNumberClick);
        }

        // Operator Buttons
        view.findViewById(R.id.buttonAdd).setOnClickListener(v -> onOperatorClick("+"));
        view.findViewById(R.id.buttonMinus).setOnClickListener(v -> onOperatorClick("-"));
        view.findViewById(R.id.buttonMultiply).setOnClickListener(v -> onOperatorClick("*"));
        view.findViewById(R.id.buttonDivide).setOnClickListener(v -> onOperatorClick("/"));

        // Equal Button
        view.findViewById(R.id.buttonEqual).setOnClickListener(v -> onEqualClick());

        // Clear Button
        view.findViewById(R.id.buttonClear).setOnClickListener(v -> onClearClick());
    }

    private void onNumberClick(View view) {
        if (isNewInput) {
            currentInput = "";
            isNewInput = false;
        }
        Button button = (Button) view;
        currentInput += button.getText().toString();
        editText.setText(currentInput);
    }

    private void onOperatorClick(String op) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            operator = op;
            isNewInput = true;
        }
    }

    @SuppressLint("SetTextI18n")
    private void onEqualClick() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        editText.setText("Error");
                        return;
                    }
                    break;
                default:
                    return;
            }
            editText.setText(String.valueOf(result));
            isNewInput = true;
        }
    }

    private void onClearClick() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        isNewInput = true;
        editText.setText("");
    }
}