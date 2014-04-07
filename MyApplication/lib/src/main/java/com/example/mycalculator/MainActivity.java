package com.example.mycalculator;

import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonDel,
			buttonAdd, buttonSub, buttonMult, buttonDiv, buttonDot, buttonEq;
	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setButtons();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void setButtons() {
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);
		button0 = (Button) findViewById(R.id.button0);
		buttonDel = (Button) findViewById(R.id.buttonDel);
		buttonAdd = (Button) findViewById(R.id.buttonAdd);
		buttonSub = (Button) findViewById(R.id.buttonSub);
		buttonMult = (Button) findViewById(R.id.buttonMult);
		buttonDiv = (Button) findViewById(R.id.buttonDiv);
		buttonDot = (Button) findViewById(R.id.buttonDot);
		buttonEq = (Button) findViewById(R.id.buttonEq);
		editText = (EditText) findViewById(R.id.editText);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
		button9.setOnClickListener(this);
		button0.setOnClickListener(this);
		buttonDel.setOnClickListener(this);
		buttonAdd.setOnClickListener(this);
		buttonSub.setOnClickListener(this);
		buttonMult.setOnClickListener(this);
		buttonDiv.setOnClickListener(this);
		buttonDot.setOnClickListener(this);
		buttonEq.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String currentStr = editText.getText().toString();
		switch (v.getId()) {
		case R.id.button1:
			currentStr += "1";
			break;
		case R.id.button2:
			currentStr += "2";
			break;
		case R.id.button3:
			currentStr += "3";
			break;
		case R.id.button4:
			currentStr += "4";
			break;
		case R.id.button5:
			currentStr += "5";
			break;
		case R.id.button6:
			currentStr += "6";
			break;
		case R.id.button7:
			currentStr += "7";
			break;
		case R.id.button8:
			currentStr += "8";
			break;
		case R.id.button9:
			currentStr += "9";
			break;
		case R.id.button0:
			currentStr += "0";
			break;
		case R.id.buttonDel:
			currentStr = currentStr.substring(0, Math.max(0, currentStr.length() - 1));
			break;
		case R.id.buttonAdd:
			while (currentStr.endsWith("+") || currentStr.endsWith("-") || currentStr.endsWith("÷")
					|| currentStr.endsWith("×")) {
				currentStr = currentStr.substring(0, currentStr.length() - 1);
			}
			if (currentStr.length() > 0) {
				currentStr += "+";
			}
			break;
		case R.id.buttonSub:
			if (currentStr.endsWith("+") || currentStr.endsWith("-")) {
				currentStr = currentStr.substring(0, currentStr.length() - 1);
			}
			currentStr += "-";
			break;
		case R.id.buttonMult:
			while (currentStr.endsWith("+") || currentStr.endsWith("-") || currentStr.endsWith("÷")
					|| currentStr.endsWith("×")) {
				currentStr = currentStr.substring(0, currentStr.length() - 1);
			}
			if (currentStr.length() > 0) {
				currentStr += "×";
			}
			break;
		case R.id.buttonDiv:
			while (currentStr.endsWith("+") || currentStr.endsWith("-") || currentStr.endsWith("÷")
					|| currentStr.endsWith("×")) {
				currentStr = currentStr.substring(0, currentStr.length() - 1);
			}
			if (currentStr.length() > 0) {
				currentStr += "÷";
			}
			break;
		case R.id.buttonDot:
			if (!currentStr.endsWith(".")) {
				currentStr += ".";
			}
			break;
		case R.id.buttonEq:
			if (currentStr.endsWith("÷") || currentStr.endsWith("×") || currentStr.endsWith("+")
					|| currentStr.endsWith("-")) {
				currentStr = currentStr.substring(0, currentStr.length() - 1);
			}
			currentStr = calculate(currentStr);
			break;

		default:
			break;
		}
		editText.setText(currentStr);

	}

	private String calculate(String currentStr) {
		String result = "";
		String tmpStr = "";
		Double calcResult = 0.0;
		Double operand1 = 0.0;
		Double operand2 = 0.0;
		String operator = "";
		int intResult = 0;

		char[] a = currentStr.toCharArray();
		Stack<Double> numbersStack = new Stack<Double>();
		Stack<String> operatorsStack = new Stack<String>();
		for (int i = a.length - 1; i >= 0; i--) {
			if ("1234567890.".indexOf(a[i]) >= 0) { // if it's a number
				tmpStr = "" + a[i] + tmpStr;
				if (i == 0) {
					numbersStack.push(Double.parseDouble(tmpStr));
					Log.d("MainActivity", "positive number pushed=" + Double.parseDouble(tmpStr));
				}
			} else {
				if ((a[i] == '-') && ((i == 0) || (a[i - 1] == '÷') || (a[i - 1] == '×'))) { // if it's a negative number
					tmpStr = "-" + tmpStr;
					numbersStack.push(Double.parseDouble(tmpStr));
					Log.d("MainActivity", "negative number pushed=" + Double.parseDouble(tmpStr));
				} else { // it's an operator
					Log.d("MainActivity", "OPERATOR=" + a[i]);
					if (tmpStr.length() > 0) {
						numbersStack.push(Double.parseDouble(tmpStr));
						Log.d("MainActivity", "number pushed=" + Double.parseDouble(tmpStr));
					}
					if (!operatorsStack.isEmpty() && ("+-".contains("" + a[i]))) { // calculate × ÷ operators on top of the stack
						while ("×÷".contains(operatorsStack.peek())) {
							operand1 = numbersStack.pop();
							operand2 = numbersStack.pop();
							if (operatorsStack.pop().contains("×")) {
								calcResult = operand1 * operand2;
							} else {
								calcResult = operand1 / operand2;
							}
							numbersStack.push(calcResult);
							Log.d("MainActivity", "number calculated and pushed=" + calcResult);
						}
					}
					operatorsStack.push("" + a[i]);
					Log.d("MainActivity", "Operator " + a[i] + " pushed");
				}
				tmpStr = "";
			}
		}
		Log.d("MainActivity", "First part is over");

		// clear the stacks
		calcResult = numbersStack.pop();
		Log.d("MainActivity", "First result popped");
		while (!operatorsStack.isEmpty()) {
			Log.d("MainActivity", "popping operator");
			operator = operatorsStack.pop();
			Log.d("MainActivity", "operator " + operator + " popped");
			if ("+".equals(operator)) {
				calcResult += numbersStack.pop();
			} else if ("-".equals(operator)) {
				calcResult -= numbersStack.pop();
			} else if ("×".equals(operator)) {
				calcResult *= numbersStack.pop();
			} else {
				calcResult /= numbersStack.pop();
			}
		}
		Log.d("MainActivity", "Second part is over");
		intResult = calcResult.intValue();
		Log.d("MainActivity", "Integer.parseInt");
		if (calcResult == intResult)
			return (intResult + "");
		return ("" + calcResult + "");
	}
}
