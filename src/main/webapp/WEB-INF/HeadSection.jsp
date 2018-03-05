<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ProductApp</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<link href="sapient.css" rel="stylesheet">
<link href="catlog.css" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<style>
#catlog table div {
	background-color: orange;
	position: absolute;
	display: none;
}

#catalog table div article {
	background-color: red;
	color: white;
}

#catlog .editemp tr:nth-child(odd) {
	background-color: orange;
}

#catlog .editemp tr:nth-child(even) {
	background-color: orange;
}
</style>
<script>
	function display(id) {
		if(document.getElementById(id).style.display == 'block'){
			document.getElementById(id).style.display = 'none';
			return 
		}
		for (var i = 0; i < 3; i++) {
			if(document.getElementById(i) != null)
			    document.getElementById(i).style.display = 'none';
		}
		document.getElementById(id).style.display = "block";
	}
	function hide(id) {
		document.getElementById(id).style.display = 'none';
	}
	function viewEmployee() {
		var eid = document.getElementById("txteid").value;
		var ajax = new XMLHttpRequest();
		ajax.open("GET","viewbyeid?eid="+eid,true);
		ajax.send();
		ajax.onreadystatechange = function(){
			var res = ajax.responseText;
			if(res.length>0){
				var json = JSON.parse(res);
				document.getElementById("emptbl").style.display="block";
				document.getElementById("div2").style.display="none";
				document.getElementById("timg").innerHTML ="<img src='img?eimg="+json.img+"'/>";
				document.getElementById("tid").innerHTML=json.empId;
				document.getElementById("tname").innerHTML=json.empName;
				document.getElementById("tsal").innerHTML=json.sal;
				document.getElementById("tdept").innerHTML=json.dept.deptName;
			}else{
				document.getElementById("emptbl").style.display="none";
				document.getElementById("div2").style.display="block";
			}
			
		}
	}
	function checkMatch(){
		var pwd = document.getElementById("pwd").value
		var cpwd= document.getElementById("cpwd").value
		if(pwd!=cpwd){
			document.getElementById("passwarn").style.display="block";
		}
		else{
		document.getElementById("passwarn").style.display="none";
		}
	}
</script>
</head>