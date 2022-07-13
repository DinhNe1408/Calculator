package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    EditText editDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        editDisplay.setShowSoftInputOnFocus(false);

        editDisplay.setOnClickListener(v -> {
            if (getString(R.string.display).equals(editDisplay.getText().toString())) {
                editDisplay.setText("");
            }
        });
    }

    private void AnhXa() {
        editDisplay = findViewById(R.id.editDisplay);
    }

    private void updateText(String strAdd) {
        String oldStr = editDisplay.getText().toString();
        int curPos = editDisplay.getSelectionStart();
        String leftStr = oldStr.substring(0, curPos);
        String rightStr = oldStr.substring(curPos);

        if (getString(R.string.display).equals(editDisplay.getText().toString())) {
            editDisplay.setText(strAdd);
        } else {
            editDisplay.setText(String.format("%s%s%s", leftStr, strAdd, rightStr));
            editDisplay.setSelection(curPos + 1);
        }
    }

    public void btnZero(View view) {
        updateText("0");
    }

    public void btnOne(View view) {
        updateText("1");
    }

    public void btnTwo(View view) {
        updateText("2");
    }

    public void btnThree(View view) {
        updateText("3");
    }

    public void btnFour(View view) {
        updateText("4");
    }

    public void btnFive(View view) {
        updateText("5");
    }

    public void btnSix(View view) {
        updateText("6");
    }

    public void btnSeven(View view) {
        updateText("7");
    }

    public void btnEight(View view) {
        updateText("8");
    }

    public void btnNine(View view) {
        updateText("9");
    }

    public void btnMul(View view) {
        updateText(getString(R.string.multiply));
    }

    public void btnDiv(View view) {
        updateText(getString(R.string.divide));
    }

    public void btnSub(View view) {
        updateText("-");
    }

    public void btnAdd(View view) {
        updateText("+");
    }

    public void btnClear(View view) {
        editDisplay.setText("");
    }

    public void btnPar(View view) {
        int curPos = editDisplay.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = editDisplay.getText().length();

        for (int i = 0; i < curPos; i++) {
            if (editDisplay.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (editDisplay.getText().toString().substring(i, i + 1).equals(")")) {
                closePar += 1;
            }
        }

        if (openPar == closePar || editDisplay.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText("(");
        } else if (openPar > closePar && !editDisplay.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText(")");

        }
        editDisplay.setSelection(curPos + 1);
    }

    public void btnExp(View view) {
        updateText("^");
    }

    public void btnPlusMinus(View view) {
        updateText("-");
    }

    public void btnPoint(View view) {
        updateText(".");
    }

    public void btnEqual(View view) {
        String userExp = editDisplay.getText().toString();

        userExp = userExp.replaceAll(getString(R.string.divide), "/");
        userExp = userExp.replaceAll(getString(R.string.multiply), "*");
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        editDisplay.setText(result);
        editDisplay.setSelection(result.length());

    }

    public void btnBackspace(View view) {
        int curPos = editDisplay.getSelectionStart();
        int textLen = editDisplay.getText().length();

        if (curPos != 0 && textLen != 0) {
            SpannableStringBuilder sel = (SpannableStringBuilder) editDisplay.getText();
            sel.replace(curPos - 1, curPos, "");
            editDisplay.setText(sel);
            editDisplay.setSelection(curPos - 1);
        }
    }
}