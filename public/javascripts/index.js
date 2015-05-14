/**
 * Start button onclick. Start the analysis asynchronously
 */
function startOnclick(){
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }

	var map;
	
	xmlhttp.open("GET","Application/start?jsonParms="+variables+"&k="+clusterPanel.getValue()+"&fileName="+fileName+"&analysisType="+document.getElementById("selectDropDown").innerHTML.split(" ")[0],true);
	xmlhttp.send();
	
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  map=JSON.parse(xmlhttp.responseText);
	      $("div#progressBar").hide();
		  drawSeriesChart(map);
	    }
	  }
	//alert(JSON.stringify(map));
}