<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ProductApp</title>
<link href="sapient.css" rel="stylesheet">
<link href="catlog.css" rel="stylesheet">
<style>
	#catlog table div{
		background-color:orange;
		position:absolute;
		display: none;	
	}
	#catlog table div article{
	background-color: red;
	color: white;
	}
</style>
<script>
	function display(pid){
		document.getElementById(pid).style.display='block';
	}
</script>
</head>