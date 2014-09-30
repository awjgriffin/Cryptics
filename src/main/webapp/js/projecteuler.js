

var multipleOf3or5 = function(num) {  	return (num % 3 == 0 || num % 5 == 0);  };
var add = function(int1, int2) {	return int1 + int2; 	};

var EulerProblem = function (desc, solutionFn) {
		this.description = desc || 'Description';
		this.solution = solutionFn || function() {	alert ('No function defined');};
}

function problem1() {
	return range( 999 ).filter( multipleOf3or5 ).foldLeft( 0, add );
}


function problem2() {

	alert( new EulerProblem().description );
	alert( new EulerProblem('Actual description').description );
	
}