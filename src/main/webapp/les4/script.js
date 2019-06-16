var firstValue = 0;
var secondValue = 0;
var operator;
var returnValue = 0;

function insert(num) {
	if (operator == null) {
		firstValue = firstValue.toString() + num.toString();
		firstValue = Number(firstValue);
		document.getElementById("display").innerText = firstValue;
	} else {
		secondValue = secondValue.toString() + num.toString();
		secondValue = Number(secondValue);
		document.getElementById("display").innerText = firstValue + operator
				+ secondValue;
	}
}

function operate(o) {
	operator = o;
	secondValue = 0;
	document.getElementById("display").innerText = firstValue + operator;
}

function return_value() {
	if (operator == '/') {
		returnValue = firstValue / secondValue;
	} else if (operator == '*') {
		returnValue = firstValue * secondValue;
	} else if (operator == '-') {
		returnValue = firstValue - secondValue;
	} else if (operator == '+') {
		returnValue = firstValue + secondValue;
	} else {
		returnValue = firstValue;
	}

	firstValue = returnValue;
	secondValue = 0;
	operator = null;
	document.getElementById("display").innerText = returnValue;
}

function clearDisplay() {
	firstValue = 0;
	secondValue = 0;
	operator = null;
	returnValue = 0;
	document.getElementById("display").innerText = 0;
}