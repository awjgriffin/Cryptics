
//functionalTools.init(); functionalTools = {init:function()}

// "pre-load" the data - this is an async call
//var jsonData = $.getJSON('/WebTest/rest/json', function(data) { jsonData=data; } );

function listJSON() {

	var jsonData = getData();
	
	alert( jsonData["puzzles"][1].name );
}


function getData() {

	var jsonData = {};
	
	jQuery.ajax({
	    type: "GET",
	    url: '/WebTest/rest/json',
	    success: function(result) {
	    	jsonData = result;
	    },
	    async: false
	});	
	
	return jsonData;
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
Array.method('makeSet', function(){

    var set = [];

    for(var i = 0; i < this.length; i++){
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

        if(fn (this[i])){
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


