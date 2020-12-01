function validateDateFieldExists(date) {
	var errorCode = "00";
	if(date=='') {
		errorCode='01';
	}
	return errorCode;
}

function convertTo24Hour(time) {
	    var hours = parseInt(time.substr(0, 2));
	    if(time.indexOf('AM') != -1 && hours == 12) {
	        time = time.replace('12', '0');
	    }
	    if(time.indexOf('PM')  != -1 && hours < 12) {
	        time = time.replace(hours, (hours + 12));
	    }
	    return time.replace(/(AM|PM)/, '');
	}
	
	
	function validateStartDateEndDate(fromDate, toDate) {
		var validationFlag = false;
		var fromDateArray = fromDate.match(/\d+/g);
		var toDateArray = toDate.match(/\d+/g);
		var finalFromDate = fromDateArray[2] + "-" + fromDateArray[1] + "-"	+ fromDateArray[0];
		var finalToDate = toDateArray[2] + "-" + toDateArray[1] + "-" + toDateArray[0];
		var fromDateformat = new Date(finalFromDate);
		var toDateformat = new Date(finalToDate);
		if (fromDateformat <= new Date() && toDateformat <= new Date()) {
			if (fromDateformat <= toDateformat) {
				if (finalFromDate == finalToDate) {
					var selectTimeFrom = convertTo24Hour(fromDate.split(' ')[1]);
					var selectTimeTo = convertTo24Hour(toDate.split(' ')[1]);
					var timefrom = new Date();
					temp = selectTimeFrom.split(":");
					timefrom.setHours((parseInt(temp[0]) + 24) % 24);
					timefrom.setMinutes(parseInt(temp[1]));
					timefrom.setSeconds(parseInt(temp[2]));
					var timeto = new Date();
					temp = selectTimeTo.split(":");
					timeto.setHours((parseInt(temp[0]) + 24) % 24);
					timeto.setMinutes(parseInt(temp[1]));
					timeto.setSeconds(parseInt(temp[2]));
					if (timeto <= timefrom) {
						validationFlag = false;
					} else {
						validationFlag = true;
					}
				} else {
					validationFlag = true;
				}
			} else {
			
			}
		} else {
			
		}
		return validationFlag;
	}
	
	function validateStartDateEndDateBy24(fromDate, toDate) {
		var validationFlag = false;
		var fromDateArray = fromDate.match(/\d+/g);
		var toDateArray = toDate.match(/\d+/g);
		
		var finalFromDate = fromDateArray[2] + "-" + fromDateArray[1] + "-"	+ fromDateArray[0];
		var finalToDate = toDateArray[2] + "-" + toDateArray[1] + "-" + toDateArray[0];
	
		var fromDateformat = new Date(fromDateArray[2], parseInt(fromDateArray[1])-1, fromDateArray[0], fromDateArray[3], fromDateArray[4], fromDateArray[5], 0);
		var toDateformat = new Date(toDateArray[2], parseInt(toDateArray[1])-1, toDateArray[0], toDateArray[3], toDateArray[4], toDateArray[5], 0);
		
		if (fromDateformat <= new Date() && toDateformat <= new Date()) {
			
			if (fromDateformat <= toDateformat) {
				
				validationFlag = true;
			} else {
				validationFlag = false;
			}
		} else {
			if(finalFromDate == finalToDate) {
				if(parseInt(fromDateArray[3]) < parseInt(toDateArray[3])) {
					validationFlag = true;
				} else {
					if(parseInt(fromDateArray[3]) == parseInt(toDateArray[3])) {
						if(parseInt(fromDateArray[4]) < parseInt(toDateArray[4])) {
							validationFlag = false;
						} else {
							if(parseInt(fromDateArray[4]) == parseInt(toDateArray[4])) {
								if(parseInt(fromDateArray[5]) < parseInt(toDateArray[5])) {
									validationFlag = true;
								}
							} else {
								validationFlag = true;
							}
							
						}
					} else {
						validationFlag = true;
					}
				}
			} else {
				validationFlag = true;
			}
			
		}
		return validationFlag;
}
	
	function validateFromToDate(fromDate, toDate) {
		var b = fromDate.split(/\D/);
		var tempfromDate = b[2] + "-" + b[1] + "-"	+ b[0];
		var c = toDate.split(/\D/);
		var temptoDate = c[2] + "-" + c[1] + "-"	+ c[0];
		
		var fromDateConvert = new Date(tempfromDate);
		var toDateConvert = new Date(temptoDate);
		if(toDateConvert >= fromDateConvert) {
			return true;
		} else {
			return false;
		}
		
}
function validateDropDown(dropdownvalue){
	var errorCode = '00';
	if(dropdownvalue == '-1' || dropdownvalue == ''){
		errorCode = '28';
	}
	return errorCode;
}
function validateCustomerId(customerId) {
	var errorCode = validateFields(customerId,'N','Y', 'numeric',8,12,'Y',customerIdErr);
	return errorCode;
}
function validateMobileNumber(mobileNumber) {
	var errorCode = validateFields(mobileNumber,'N','Y', 'mobilenumber',10,10,'Y',mobileNumberErr);
	return errorCode;
}
function validateFields(inputValue, mandatoryOrNot, allZeroAllowedOrNot,
		inputType, minLengthAllowed, maxLengthAllowed, doubleSpaceAllowed,
		numericError) {
	var errorCode = '';

	errorCode = validateFrontSpace(inputValue);
	if ('00' != errorCode)
		return errorCode;

	errorCode = validateEndSpace(inputValue);
	if ('00' != errorCode)
		return errorCode;

	errorCode = validateMandatoryOrNot(inputValue, mandatoryOrNot);
	if ('00' != errorCode)
		return errorCode;

	errorCode = validateAllZeroAllowedOrNot(inputValue, allZeroAllowedOrNot);
	if ('00' != errorCode)
		return errorCode;

	errorCode = validateInputType(inputValue, inputType);
	if ('00' != errorCode)
		return errorCode;

	errorCode = validateMinLengthAllowed(inputValue, mandatoryOrNot,
			minLengthAllowed);
	if ('00' != errorCode)
		return errorCode;

	errorCode = validateMaxLengthAllowed(inputValue, maxLengthAllowed);
	if ('00' != errorCode)
		return errorCode;

	errorCode = validateDoubleSpace(inputValue, doubleSpaceAllowed);

	return errorCode;
}

function validateFrontSpace(inputValue) {
	var errorCode = '';
	var frontSpaceExpression = /^\s/;
	if (frontSpaceExpression.test(inputValue)) {
		errorCode = '26';
	} else {
		errorCode = '00';
	}

	return errorCode;

}

function validateEndSpace(inputValue) {
	var errorCode = '';
	var lastSpaceExpression = /\s$/;
	if (lastSpaceExpression.test(inputValue)) {
		errorCode = '27';
	} else {
		errorCode = '00';
	}

	return errorCode;

}

function validateMandatoryOrNot(inputValue, mandatoryOrNot) {
	var errorCode = '';
	if (mandatoryOrNot == 'Y') {
		if (inputValue == '' || inputValue == undefined) {
			errorCode = '01';
		} else {
			errorCode = '00';
		}

	} else {
		errorCode = '00';
	}

	return errorCode;

}

function validateAllZeroAllowedOrNot(inputValue, allZeroAllowedOrNot) {
	var errorCode = '';
	if (allZeroAllowedOrNot == 'Y') {

		if (inputValue!='' && inputValue.valueOf() == 0) {
			errorCode = '02';
		} else {
			errorCode = '00';
		}

	} else {
		errorCode = '00';
	}

	return errorCode;

}
function validateMinLengthAllowed(inputValue, mandatoryOrNot, minLengthAllowed) {
	var errorCode = '';
	if (minLengthAllowed != '-') {

		if (inputValue.length < parseInt(minLengthAllowed)) {
			if (mandatoryOrNot == 'Y') {
				
				errorCode = '03';
			} else {
				if(inputValue != '' && inputValue.length < parseInt(minLengthAllowed)) {
					errorCode = '03';
				} else {
					errorCode = '00';
				}
				
			}
		} else {
			errorCode = '00';
		}

	} else {
		errorCode = '00';
	}

	return errorCode;

}

function validateMaxLengthAllowed(inputValue, maxLengthAllowed) {
	var errorCode = '';
	if (maxLengthAllowed != '-') {

		if (inputValue.length > parseInt(maxLengthAllowed)) {
			errorCode = '04';
		} else {
			if(inputValue != '' && inputValue.length > parseInt(maxLengthAllowed)) {
				errorCode = '04';
			} else {
				errorCode = '00';
			}
			
		}

	} else {
		errorCode = '00';
	}

	return errorCode;

}
function validateDoubleSpace(inputValue, doubleSpaceAllowed) {
	var errorCode = '';
	var doubleSpaceExpression = /^(?!.*  ).+/;
	if (doubleSpaceAllowed == 'Y') {
		if (inputValue.length != inputValue.replace(/\s{2,}/g, '').length) {
			errorCode = '09';
		} else {
			errorCode = '00';
		}
	} else {
		errorCode = "00";
	}

	return errorCode;
}

function validateInputType(inputValue, inputType) {

	var errorCode = '';

	if (inputValue == '' || inputValue == undefined) {
		errorCode = '00';
	} else {
		switch (inputType) {
		case 'numeric':
			errorCode = validateNumeric(inputValue);
			break;
		case 'decimal':
			errorCode = validateDecimal(inputValue);
			break;
		case 'character':
			errorCode = validateCharacter(inputValue);
			break;
		case 'alphabet':
			errorCode = validateAlphabet(inputValue);
			break;
		case 'alphabetwithspace':
			errorCode = validateAlphabetWithSpace(inputValue);
			break;
		case 'alphabetwithdot':
			errorCode = validateAlphabetWithDot(inputValue);
			break;
		case 'emailid':
			errorCode = validateEmailid(inputValue);
			break;
		case 'alphanumeric':
			errorCode = validateAlphaNumeric(inputValue);
			break;
		case 'alphanumericwithspace':
			errorCode = validateAlphanumericWithSpace(inputValue);
			break;
		case 'alphabetswithspecialcharacter':
			errorCode = validateAlphabetsWithSpecialCharacter(inputValue);
			break;
		case 'mobilenumber':
			errorCode = validateMobileNumber(inputValue);
			break;
		case 'ipaddress':
			errorCode = validateIpaddress(inputValue);
			break;
		case 'url':
			errorCode = validateUrl(inputValue);
			break;
		case 'hours':
			errorCode = validateHours(inputValue);
			break;
		case 'minutes':
			errorCode = validateMinutes(inputValue);
			break;
		case 'seconds':
			errorCode = validateSecond(inputValue);
			break;
		case 'decimalwithtwodigits':
			errorCode = validateTwoDigit(inputValue);
			break;
		case 'decimalwiththreedigits':
			errorCode = validateThreeDigit(inputValue);
			break;
		case 'percentage':
			errorCode = validatePercentage(inputValue);
			break;
		default:
			errorCode = '05';
			break;
		}

	}

	return errorCode;

}

function validateNumeric(inputValue) {
	var errorCode = '';
	var numberExpression = /^[0-9]*$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (numberExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '06';
	}

	return errorCode;
}
function validateDecimal(inputValue) {
	var errorCode = '';
	var decimalExpression = /^[0-9]+(\.[0-9]+)?$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (decimalExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '07';
	}
	return errorCode;
}
function validateCharacter(inputValue) {
	var errorCode = '';
	var alphaExpression = /^[a-zA-Z]?$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (alphaExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '08';
	}
	return errorCode;
}
function validateAlphabet(inputValue) {
	var errorCode = '';
	var alphabetExpression = /^[A-z]*$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (alphabetExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '10';
	}
	return errorCode;
}
function validateAlphabetWithSpace(inputValue) {
	var errorCode = '';
	var alphabetExpression = /^([a-zA-Z]+\s+)*[a-zA-Z]+$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (alphabetExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '11';
	}
	return errorCode;
}
function validateAlphabetWithDot(inputValue) {
	var errorCode = '';
	var alphabetWithDotExpression = /^[A-Z a-z]*(\.[A-Z a-z]*)?$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (alphabetWithDotExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '12';
	}
	return errorCode;
}
function validateEmailid(inputValue) {
	var errorCode = '';
	var emailIdExpression = /^[a-zA-Z0-9_!#$%&’*+^.]+@[a-zA-Z]+.[a-zA-Z-]+$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (emailIdExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '13';
	}

	return errorCode;
}
function validateAlphaNumeric(inputValue) {
	var errorCode = '';
	var alphaNumericExpression = /^[a-zA-Z0-9]*$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (alphaNumericExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '14';
	}

	return errorCode;
}

function validateAlphanumericWithSpace(inputValue) {
	var errorCode = '';
	var alphaNumericExpression = /^([a-zA-Z0-9]+\s+)*[a-zA-Z0-9]+$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (alphaNumericExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '15';
	}

	return errorCode;
}
function validateAlphabetsWithSpecialCharacter(inputValue) {
	var errorCode = '';
	var alphabetsWithSpecialCharacterExpression = /^[A-Za-z*.,:]*$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (alphabetsWithSpecialCharacterExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '16';
	}

	return errorCode;
}

function validateMobileNumber(inputValue) {
	var errorCode = '';
	var mobileNumberExpression = /^[0-9]{10}$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (mobileNumberExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '17';
	}

	return errorCode;
}
function validateipaddress(inputValue) {
	var errorCode = '';
	var ipaddressExpression = /^\b\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\b$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (ipaddressExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '18';
	}

	return errorCode;
}
function validateUrl(inputValue) {
	var errorCode = '';
	var urlExpression = /^((https?|ftp|smtp):\/\/)?(www.)?[a-z0-9]+\.[a-z]+(\/[a-zA-Z0-9-#]+\/?)*$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (urlExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '19';
	}

	return errorCode;
}
function validateHours(inputValue) {
	var errorCode = '';
	var hoursExpression = /^([0-1][0-9]|2[0-3])$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (hoursExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '20';
	}

	return errorCode;
}
function validateMinutes(inputValue) {
	var errorCode = '';
	var minutesExpression = /^([0-5][0-9])$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (minutesExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '21';
	}

	return errorCode;
}
function validateSecond(inputValue) {
	var errorCode = '';
	var secondExpression = /^([0-5][0-9])$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (secondExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '22';
	}

	return errorCode;
}
function validateTwoDigit(inputValue) {
	var errorCode = '';
	var twoDigitExpression = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (twoDigitExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '23';
	}

	return errorCode;
}
function validateThreeDigit(inputValue) {
	var errorCode = '';
	var threeDigitExpression = /^[0-9]+(\.[0-9]{1,3})?$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (threeDigitExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '24';
	}

	return errorCode;
}
function validatePercentage(inputValue) {
	var errorCode = '';
	var percentageExpression = /^[0-9]{1,2}$/;
	if(inputValue == '') {
		errorCode = '00';
	} else if (percentageExpression.test(inputValue)) {
		errorCode = '00';
	} else {
		errorCode = '25';
	}

	return errorCode;
}