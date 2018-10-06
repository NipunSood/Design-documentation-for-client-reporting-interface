// Create a request variable and assign a new XMLHttpRequest object to it.
var request = new XMLHttpRequest();
var ClientCategory = {"Derivatives": 0, "Stocks": 0, "Bonds": 0, "Commodities": 0};
var ClientCategoryGraph = [];
var FinalDataPoitns = [];
var FinalDataPoitnsCurrent = [];
var apiData = [];
var ClientData = {};
var FinalDataPoitns = [];
var FinalDataPoitnsCurrent = [];
var ClientCurrentData = [];
var temp = false;
// Open a new connection, using the GET request on the URL endpoint
var url = sessionStorage.getItem("Url");

//var url = 'http://localhost:8585/BankAPI/rest/client/report/clientname/Google';
request.open('GET', url, true);

request.onload = function () {
  var data = JSON.parse(this.response);
  apiData = data;
data.forEach(clientTransactions => {
    ClientCurrentData[clientTransactions.fundname]=clientTransactions.current_value;
	ClientCategory[clientTransactions.investment_type] = ClientCategory[clientTransactions.investment_type]+1;
    ClientData[clientTransactions.fundname]=clientTransactions.amount_invested;
});

	var tempsum = 0;
	for(i in ClientCategory){
		tempsum += ClientCategory[i];
	}

    for(i in ClientData){
        FinalDataPoitns.push({label:i, y:ClientData[i]});
    }

    for(i in ClientCurrentData){
		FinalDataPoitnsCurrent.push({label:i, y:ClientCurrentData[i]});
    }

	for(i in ClientCategory){
		if(!temp){
		ClientCategoryGraph.push({y:((ClientCategory[i]/tempsum)*100).toFixed(2), name: i, exploded: true});
		temp = true;	
	} else {
		ClientCategoryGraph.push({y:((ClientCategory[i]/tempsum)*100).toFixed(2), name: i});
	}
}

	var chart = new CanvasJS.Chart("chartContainer", {
		title:{
			text: "Total Investment By Client"              
		},
		data: [              
		{
			// Change type to "doughnut", "line", "splineArea", etc.
			type: "column",
			dataPoints: FinalDataPoitns
		}
		]
	});
    	chart.render();

var chart = new CanvasJS.Chart("chartContainer2", {
	exportEnabled: true,
	animationEnabled: true,
	title:{
		text: "Invested Amount Vs Current Amount"
	},
	subtitles: [{
		text: "Click Legend to Hide or Unhide Data Series"
	}], 
	axisX: {
		title: "Investments"
	},
	axisY: {
		title: "Current Amount",
		titleFontColor: "#C0504E",
		lineColor: "#C0504E",
		labelFontColor: "#C0504E",
		tickColor: "#C0504E"
	},
	axisY2: {
		title: "Invested Amount",
		titleFontColor: "#4F81BC",
		lineColor: "#4F81BC",
		labelFontColor: "#4F81BC",
		tickColor: "#4F81BC"
	},

	toolTip: {
		shared: true
	},
	legend: {
		cursor: "pointer",
		itemclick: toggleDataSeries
	},
	data: [{
		type: "column",
		name: "Current",
		showInLegend: true,      
		yValueFormatString: "#,##0.# Units",
		dataPoints: FinalDataPoitnsCurrent 
        
	},
	{
		type: "line",
		name: "Invested",
		showInLegend: true,
		yValueFormatString: "#,##0.# Units",
		dataPoints: FinalDataPoitns 
	}]
});



	chart.render();


var chart = new CanvasJS.Chart("chartContainer3", {
	exportEnabled: true,
	animationEnabled: true,
	title:{
		text: "Investment Type"
	},
	legend:{
		cursor: "pointer",
		itemclick: explodePie
	},
	data: [{
		type: "pie",
		showInLegend: true,
		toolTipContent: "{name}: <strong>{y}%</strong>",
		indexLabel: "{name} - {y}%",
		dataPoints: ClientCategoryGraph
	}]
});
chart.render();


  }

// Send request
request.send();

function explodePie (e) {
	if(typeof (e.dataSeries.dataPoints[e.dataPointIndex].exploded) === "undefined" || !e.dataSeries.dataPoints[e.dataPointIndex].exploded) {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = true;
	} else {
		e.dataSeries.dataPoints[e.dataPointIndex].exploded = false;
	}
	e.chart.render();

}

function toggleDataSeries(e) {
	if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		e.dataSeries.visible = false;
	} else {
		e.dataSeries.visible = true;
	}
	e.chart.render();
}


$(document).ready(function () {
    $.getJSON(url,
    function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td bgcolor='#D7E6FF'>" + json[i].fundname + "</td>");
            tr.append("<td bgcolor='#D7E6FF'>" + formatter.format(json[i].amount_invested) + "</td>");
			tr.append("<td bgcolor='#D7E6FF'>" + formatter.format(json[i].current_value) + "</td>");
			tr.append("<td bgcolor='#D7E6FF'>" + json[i].date_time.slice(0,10) + "</td>");
			tr.append("<td bgcolor='#D7E6FF'>" + json[i].investment_type + "</td>");
            $('table').append(tr);
        }
    });
});


const formatter = new Intl.NumberFormat('en-in', {
  style: 'currency',
  currency: 'INR',
  minimumFractionDigits: 0
})

// Downloadable CSV File Code.
function downloadReport() {
	exportToCsvFile(apiData);
}

function exportToCsvFile(jsonData) {
    // let csvStr = parseJSONToCSVStr(jsonData);
	let csvStr =JSON2CSV(jsonData);
    let dataUri = 'data:text/csv;charset=utf-8,'+ csvStr;
    
    let exportFileDefaultName = 'data.csv';
    
    let linkElement = document.createElement('a');
    linkElement.setAttribute('href', dataUri);
    linkElement.setAttribute('download', exportFileDefaultName);
    linkElement.click();
}

function JSON2CSV(objArray) {
    var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
    var str = '';
    var line = '';

	let keys = Object.keys(objArray[0]);
    
    let columnDelimiter = ',';
    let lineDelimiter = '\n';
    
    let csvColumnHeader = keys.join(columnDelimiter);
    let csvStr = csvColumnHeader + lineDelimiter;

    if ($("#labels").is(':checked')) {
        var head = array[0];
        if ($("#quote").is(':checked')) {
            for (var index in array[0]) {
                var value = index + "";
                line += '"' + value.replace(/"/g, '""') + '",';
            }
        } else {
            for (var index in array[0]) {
                line += index + ',';
            }
        }

        line = line.slice(0, -1);
        str += line + '\r\n';
    }

    for (var i = 0; i < array.length; i++) {
        var line = '';

        if ($("#quote").is(':checked')) {
            for (var index in array[i]) {
                var value = array[i][index] + "";
                line += '"' + value.replace(/"/g, '""') + '",';
            }
        } else {
            for (var index in array[i]) {
                line += array[i][index] + ',';
            }
        }

        line = line.slice(0, -1);
        str += line + '\r\n';
    }
    return csvStr+str;
}