window.onload=function() {
	// var table=document.createElement('table');
	// // table.setAttribute('width','100');
	// table.width='300'
	// table.border=1; 	

	// var caption=document.createElement('caption');
	// table.appendChild(caption);
	// caption.innerHTML='人员表';
	// // var captionText=document.createTextNode('人员表');
	// // caption.appendChild(captionText);
	// document.body.appendChild(table);
	
	// var table=document.getElementsByTagName('table')[0];
	var table=document.createElement('table');
	table.width=300;
	table.border=1;
	table.createCaption().innerHTML='汇率表'; 
	var thead=table.createTHead();
	var tr=thead.insertRow();
	tr.insertCell(0).innerHTML='shuju1';
	tr.insertCell(1).innerHTML='shuju2';
	tr.insertCell(2).innerHTML='shuju3';
	document.body.appendChild(table);
}