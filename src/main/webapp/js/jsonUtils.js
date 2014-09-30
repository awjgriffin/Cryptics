


// retrieves JSON data from the server, SYNCHRONOUSLY 
function getJSONData() {

	var jsonData = {};
	
	jQuery.ajax({
	    type: "GET",
	    url: '/Cryptics/rest/json',
	    success: function(result) {
	    	jsonData = result;
	    },
	    async: false
	});	
	
	return jsonData;
}

//retrieves JSON data from the server, SYNCHRONOUSLY 
function getJSONDataAsText() {

	var jsonData = {};
	
	jQuery.ajax({
	    type: "GET",
	    url: '/Cryptics/rest/json/asText',
	    success: function(result) {
	    	jsonData = result;
	    },
	    async: false
	});	
	
	return jsonData;
}


function postJSONData() {

	var jsonData = getJSONDataAsText();
	
	jQuery.ajax({
	    type: "POST",
	    url: '/Cryptics/rest/json/save?json=' + jsonData,
	    success: function(result) {
	    	alert('JSON data successfully posted.');
	    	//jsonData = result;
	    },
	    async: false
	});	
	
	return true;
}


var theWord = "beautiful";

// creates a 1-based Array with optional
function range( limit, step ) {
	
	var returnArray = [];
	
	for(var i = 1; i <= limit; i += (step | 1) ) { returnArray.push(i);  }
	
	return returnArray;
}

function makeArrayFromString(strIn) {
	
	var arrayOut = [];
	for(var i=0; i < strIn.length; i++) {  		arrayOut[i] = strIn.charAt(i);  	}
	
	return arrayOut;
}


// modify the Function prototype to allow for quick method decoration later
Function.prototype.method = function (name, fn) {
	
    if(!this.prototype[name]){
        this.prototype[name] = fn;
    }
    return this;
};

Array.method('contains', function(val) {

    for(var i = 0; i < this.length; i++){
        if(val === this[i]) {	return true;	}
    }
    return false;
});

// remove duplicates
// requires 'contains' method
Array.method('makeSet', function() {

    var set = [];

    for(var i = 0; i < this.length; i++) {
        var elem = this[i];
        var tail = this.slice(i + 1);
        if(!tail.contains(elem)){
            set[set.length] = elem;
        }
    }
    return set;
});

// filter that takes a single-argument function
Array.method('filter', function (fn) {

    var filteredArray = [];

    for(var i = 0; i < this.length; i++){

        if( fn ( this[i] ) ){
            filteredArray.push(this[i]);
        }
    }

    return filteredArray;
});

Array.method('map', function (fn) {

    var filteredArray = [];
    for(var i = 0; i < this.length; i++){
        filteredArray.push( fn( this[i] ) );
    }

    return filteredArray;
});


// a.k.a reduce
Array.method('foldLeft', function(init, fn) {
	
	var result = init;
	for(var i = 0; i < this.length; i++) {
		result = fn( result, this[i] );
	}
	
	return result;
});




// ---- TRASH HEAP ------------------------------------------


//functionalTools.init(); functionalTools = {init:function()}

//"pre-load" the data - this is an async call
//var jsonData = $.getJSON('/WebTest/rest/json', function(data) { jsonData=data; } );




function testDataRetrieval() {

	var jsonData = getJSONData();
	
	alert( jsonData["puzzles"][1].name );
}
