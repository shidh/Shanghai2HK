$(function () {
	var d = new Date("2015-04-01");
    $('#datetimepicker6').datetimepicker({format: 'YYYY/MM/DD'});
    $('#datetimepicker7').datetimepicker({format: 'YYYY/MM/DD'});
    $('#datetimepicker6').data("DateTimePicker").minDate(d);

    $("#datetimepicker6").on("dp.change", function (e) {
        $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        
    });
    $("#datetimepicker7").on("dp.change", function (e) {
        $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
    });
});

$(document).ready(function(){
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }

	var maps;
	$("div#progressBar").show();
	
	xmlhttp.open("GET","Application/weekly",true);
	xmlhttp.send();
	
	xmlhttp.onreadystatechange=function()
	{
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  maps=JSON.parse(xmlhttp.responseText);
	      $("div#progressBar").hide();
		  //drawSeriesChart(map);
	      //alert(JSON.stringify(map));
	      //alert(JSON.stringify(maps[0].date));

	      //drawAreaChart(map);
	      drawColumnChart(maps);
	      drawColumnChartHK(maps);

	    }
	  }
});

function weeklyOnclick(){
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }

	var maps;
	$("div#progressBar").show();
	
	xmlhttp.open("GET","Application/weekly",true);
	xmlhttp.send();
	
	xmlhttp.onreadystatechange=function()
	{
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  maps=JSON.parse(xmlhttp.responseText);
	      $("div#progressBar").hide();
		  //drawSeriesChart(map);
	      //alert(JSON.stringify(map));
	      //alert(JSON.stringify(maps[0].table1));

	      //drawAreaChart(map);
	      drawColumnChart(maps); 
	      drawColumnChartHK(maps);

	    }
	  }
}

/**
 * parse json
 */
function monthlyOnclick(){
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }

	var maps;
	$("div#progressBar").show();
	
	xmlhttp.open("GET","Application/monthly",true);
	xmlhttp.send();
	
	xmlhttp.onreadystatechange=function()
	{
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  maps=JSON.parse(xmlhttp.responseText);
	      $("div#progressBar").hide();
		  //drawSeriesChart(map);
	      //alert(JSON.stringify(map));
	      //alert(JSON.stringify(maps[0].table1));

	      //drawAreaChart(map);
	      drawColumnChart(maps); 
	      drawColumnChartHK(maps);

	    }
	  }
}

function allOnclick(){
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }

	var maps;
	$("div#progressBar").show();
	
	xmlhttp.open("GET","Application/all",true);
	xmlhttp.send();
	
	xmlhttp.onreadystatechange=function()
	{
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  maps=JSON.parse(xmlhttp.responseText);
	      $("div#progressBar").hide();
		  //drawSeriesChart(map);
	      //alert(JSON.stringify(map));
	      //alert(JSON.stringify(maps[0].table1));

	      //drawAreaChart(map);
	      drawColumnChart(maps);
	      drawColumnChartHK(maps);
	    }
	  }
}


function selectOnclick(){
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }

	//TODO get the dates
	
	var maps;
	$("div#progressBar").show();
	
	xmlhttp.open("GET","Application/datesBetween?from="+document.getElementById("datetimepickerFrom").value +"&"+
												 "to="+document.getElementById("datetimepickerTo").value 
					  , true);
	alert(document.getElementById("datetimepickerFrom").value);
	xmlhttp.send();
	
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  maps=JSON.parse(xmlhttp.responseText);
	      $("div#progressBar").hide();
	      drawColumnChart(maps);
	      drawColumnChartHK(maps);	    }
	  }
}

/**
 * 
 */
 // Load the Visualization API and the piechart package.
      google.load('visualization', '1.1', {'packages':['corechart',"bar"]});


      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawSeriesChart);
      
      
      google.setOnLoadCallback(drawAreaChart);
      
      
      google.setOnLoadCallback(drawColumnChart);
      google.setOnLoadCallback(drawColumnChartHK);

     

      
      
      function drawColumnChart(maps) {
    	var array = [];
    	var i;
      	array[0] = ['日期', '买入卖出总共成交额(million RMB)', '买入成交额(million RMB)', '卖出成交额(million RMB)', { role: 'style' }];
      	for (i = 0; i < maps.length; i++) {
      		daily = maps[i];
      		var d = new Date(daily.date);
      		var month = d.getMonth() + 1;

      		array[i+1] = [d.getFullYear()+"/"+month+"/"+ d.getDate()+'', daily.table1['买入及卖出成交额 (RMB mil)'], daily.table1['     买入成交额 (RMB mil)'], daily.table1['     卖出成交额 (RMB mil)'], 'gold' ];
      	}

      		var data = google.visualization.arrayToDataTable(array);
	        var options = {
	          chart: {
	            title: '沪股通-买入卖出额',
	            subtitle: '资金一路向北',
	          },
	          chartArea:{left:20,top:0,width:'100%',height:'100%'},
	          trendlines: {
	              0: {
	                type: 'linear',
	                color: 'green',
	                lineWidth: 3,
	                opacity: 0.3,
	                showR2: true,
	                visibleInLegend: true
	              }
	            }
	        };
	
	        var chart = new google.charts.Bar(document.getElementById('columnchart_2SH'));
	
	        chart.draw(data, options);
      	}
      
      function drawColumnChartHK(maps) {
      	var arrayHK = [];
      	var i;
      	arrayHK[0] = ['日期', '买入卖出总共成交额(million HKD)', '买入成交额(million HKD)', '卖出成交额(million HKD)'];
        	for (i = 0; i < maps.length; i++) {
        		dailyHK = maps[i];
        		var d = new Date(dailyHK.date);
        		var month = d.getMonth() + 1;

        		arrayHK[i+1] = [d.getFullYear()+"/"+month+"/"+ d.getDate()+'', dailyHK.table3['买入及卖出成交额(HKD mil)'], dailyHK.table3['     买入成交额 (HKD mil)'], dailyHK.table3['     卖出成交额 (HKD mil)']];
        	}

        		var data = google.visualization.arrayToDataTable(arrayHK);
  	        var options = {
  	          chart: {
  	            title: '港股通-买入卖出额',
  	            subtitle: '资金一路向南',
  	          },
//  	          axes: {
//  	            x: {
//  	              0: { side: 'top', label: 'White to move'} // Top x-axis.
//  	            }
//  	          },
//  	          bar: { groupWidth: "90%" }
  	          chartArea:{left:20,top:0,width:'100%',height:'100%'},
  	          trendlines: {
  	              0: {
  	                type: 'linear',
  	                color: 'green',
  	                lineWidth: 3,
  	                opacity: 0.3,
  	                showR2: true,
  	                visibleInLegend: true
  	              }
  	            }
  	        };
  	
  	        var chart = new google.charts.Bar(document.getElementById('columnchart_2HK'));
  	
  	        chart.draw(data, options);
        	}
      
      
      
      
      
      function drawAreaChart(map) {
          var data = google.visualization.arrayToDataTable([
            ['Year', 'Sales', 'Expenses'],
            ['2013',  1000,      400],
            ['2014',  1170,      460],
            ['2015',  660,       1120],
            ['2016',  1030,      540]
          ]);

          var options = {
            title: 'Company Performance',
            hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
          };

          var chart = new google.visualization.AreaChart(document.getElementById('areaChart_div'));
          chart.draw(data, options);
        }
      
      
      function drawSeriesChart(map) {
    	var k = map.K;
    	var type = map.AnalysisType;
    	var array = [];
    	array[0] = ['ID', 'FactorX', 'FactorY', 'Risk Level', 'Number of cluster members'];

    	for (i = 1; i <= k; i++) { 
    		array[i] = ["cluster"+i, parseFloat(map["X"+i]), parseFloat(map["Y"+i]), parseFloat(map["RiskRate"+i]) ,parseInt(map["S"+i])];
    	}
    	
    	
    	var data;
    	if(k > 0){
    		data = google.visualization.arrayToDataTable(array);
    	}else{
    		data = google.visualization.arrayToDataTable([
            //check https://google-developers.appspot.com/chart/interactive/docs/gallery/bubblechart#javascript
            //ID (name) of the bubble   X coordinate, Y coordinate, Either a series ID or a value representing a color ,  Size; values in this column are mapped to actual pixel values using the sizeAxis option.
          ['ID', 'FactorX', 'FactorY', 'Region',     'Number of cluster members'],
          ['cluster1',    98,              1.67,      'High level education',  33739900],
          ['cluster2',    49.84,              1.36,      'Middle level education',  81902307],
          ['cluster3',    42.73,              2.78,      'Low level education',   79716203],
          ['cluster4',    38.09,              4.77,      'Low level education',   31090763],
          ['cluster5',    81.55,              2.96,      'Low level education',   7485600],
          ['cluster6',    5,               1.54,      'Middle level education',  141850000],
          ['cluster7',    78.09,              2.05,      'High level education',  307007000]
          ]);
    	}
        //TODO
        //change the Region to Risk alarm, red for risk cluster, green for good cluster, so on

    	var options;
    	if (type == "Performance") {
	        options = {
	        		//animation: {duration: 30},
	                title: 'HrForecast Workforce Clustering Presentation',
	                hAxis: {title: 'Factor 1'},
	                vAxis: {title: 'Factor 2'},
	                hAxis: {format:'#.##'},
	                vAxis: {format:'#.##'},
	                colorAxis: {minValue: 1, maxValue: 5, colors: ['red', 'yellow']},
	                //chartArea: {backgroundColor, 'grey'},
	                legend: {alignment: 'center'},
	                sizeAxis: {minValue: 10,  maxSize: 40},
	                chartArea: {'width': '100%', 'height': '80%'},
	                legend: {'position': 'bottom'},
	                bubble: {
	                  textStyle: {
	                    fontSize: 14,
	                    fontName: 'STHeiti',
	                    color: 'green',
	                    bold: true,
	                    auraColor: 'none'
	                    //italic: true
	                  }
	                }
	        };
    	} else{
	        options = {
	        		//animation: {duration: 30},
	                title: 'HrForecast Workforce Clustering Presentation',
	                hAxis: {title: 'Factor 1'},
	                vAxis: {title: 'Factor 2'},
	                hAxis: {format:'#.##'},
	                vAxis: {format:'#.##'},
	                colorAxis: {colors: ['yellow', 'red']},
	                //colorAxis: {minValue: },
	                //colorAxis: {maxValue: },
	                //chartArea: {backgroundColor, 'grey'},
	                legend: {alignment: 'center'},
	                sizeAxis: {minValue: 10,  maxSize: 40},
	                chartArea: {'width': '100%', 'height': '80%'},
	                legend: {'position': 'bottom'},
	                bubble: {
	                  textStyle: {
	                    fontSize: 14,
	                    fontName: 'STHeiti',
	                    color: 'green',
	                    bold: true,
	                    auraColor: 'none'
	                    //italic: true
	                  }
	                }
	        };
    	}

        var chart = new google.visualization.BubbleChart(document.getElementById('series_chart_div'));
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
              var topping = data.getValue(selectedItem.row, 0);
              
              if (window.XMLHttpRequest)
        	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	        	xmlhttp=new XMLHttpRequest();
	          }
              else
			  {// code for IE6, IE5
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			  xmlhttp.open("GET","Application/detail?name="+topping,false);
			  xmlhttp.send();
			  var map=JSON.parse(xmlhttp.responseText);
			  
			  showDetail(map,topping);
	         }
          }

        google.visualization.events.addListener(chart, 'select', selectHandler);
        chart.draw(data, options);
      }
      

      
            