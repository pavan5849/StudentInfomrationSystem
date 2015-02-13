<script>
function showsinglenews(nid,ntitle)
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    document.getElementById("maincenter").innerHTML=xmlhttp.responseText;
	  };
	xmlhttp.open("GET","./getnews?nid="+nid,true);
	xmlhttp.send();
}
function viewmyprofile()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	     document.getElementById("maincenter").innerHTML=xmlhttp.responseText;
	  };
	xmlhttp.open("GET","./getmyprofile",true);
	xmlhttp.send();
}
function loadresetpasswordpage()
{
	$('#maincenter').load('./jsp/resetpassword.jsp');
}
function loaddescriptionpage()
{
	$('#maincenter').load('./jsp/descriptionpage.jsp');
}
function resetpwd()
{
	console.log("entered");
	if(document.resetpassword.confirm_password.value == document.resetpassword.new_password.value)
	{
		var xmlhttp;
		if (window.XMLHttpRequest)
			 xmlhttp=new XMLHttpRequest();
		else
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		     document.getElementById("maincenter").innerHTML=xmlhttp.responseText;
		  };
		xmlhttp.open("POST","./resetpassword?cp="+document.resetpassword.current_password.value+"&np="+document.resetpassword.new_password.value,true);
		xmlhttp.send();
		//window.alert("Password Updated Successfully");
	}
	else
	{
		window.alert("Password does not match");
		document.resetpassword.current_password.value="";
		document.resetpassword.new_password.value="";
		document.resetpassword.confirm_password.value="";
		document.resetpassword.current_password.focus();
	}
	return false;
}
function searchfriend()
{
	$('#maincenter').load('./jsp/studenthome/searchfriend.jsp');
}
function searchcourses()
{
	$('#maincenter').load('./jsp/studenthome/searchcourses.jsp');
}
function getsearchfriends()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.onreadystatechange=function()
	  {
	  	if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    document.getElementById("searchfriendpanelbodytable").innerHTML=xmlhttp.responseText;
	  };
	xmlhttp.open("GET","./getsearchfriends",true);
	xmlhttp.setRequestHeader("friendname",document.searchfriendform.friendname.value);
	xmlhttp.setRequestHeader("friendrollno",document.searchfriendform.friendrollno.value);
	xmlhttp.send();
}
function getsearchcourses()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.onreadystatechange=function()
	  {
	  	if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    document.getElementById("searchcoursepanelbodytable").innerHTML=xmlhttp.responseText;
	  };
	xmlhttp.open("GET","./getsearchcourses",true);
	xmlhttp.setRequestHeader("coursecode",document.searchfriendform.coursecode.value);
	xmlhttp.setRequestHeader("coursename",document.searchfriendform.coursename.value);
	xmlhttp.send();
}
function loadfriendprofile()
{
 	 var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	     document.getElementById("friend").innerHTML=xmlhttp.responseText; 
	  };
	var sid=$('input[name="friendsgroup"]:checked').val();
	xmlhttp.open("GET","./getfriendprofile?sid="+sid,true);
	xmlhttp.send(); 	
}
function loadcoursedetail()
{
 	 var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	     document.getElementById("course").innerHTML=xmlhttp.responseText; 
	  };
	var ccode=$('input[name="coursegroup"]:checked').val();
	xmlhttp.open("GET","./getcoursedetails?ccode="+ccode,true);
	xmlhttp.send(); 	
}
function viewmygrades()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    document.getElementById("maincenter").innerHTML=xmlhttp.responseText;
	  };
	xmlhttp.open("GET","./getmygrades",true);
	xmlhttp.send();
}
function getregisteredcourses()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  xmlhttp=new XMLHttpRequest();
	else
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    document.getElementById("maincenter").innerHTML=xmlhttp.responseText;
	  };
	xmlhttp.open("GET","./getregisteredcourses",true);
	xmlhttp.send();
}
</script>
