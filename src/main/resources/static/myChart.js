var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);
var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];
for(var i=0; i < arrayLength; i++){
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label:'My first Dataset',
            //backgroundColor: ['#3e95cd', '#8e5ea2', '#3cba9f'],
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            borderColor: 'rgb(255,90,100)',
            data: numericData
        }]
    },
    options:{
    title: {
    display:true,
    text: 'Project Status'
    }
    }
    });
    function decodeHtml(html){
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
 }

//
//    // Configuration options go here
//    options: {
//    	title: {
//    		display: true,
//    		text: 'Project Statuses'
//    	}
//
//    }
//});
//
//// "[{"value": 1, "label": "COMPLETED"},{"value": 2, "label": "INPROGRESS"},{"value": 1, "label": "NOTSTARTED"}]"

