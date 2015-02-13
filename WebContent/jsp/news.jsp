 <%@page import="org.iiitb.sis.util.ConnectionPool"%>
 <%@page import="org.iiitb.sis.util.Constants"%>
 <%@page import="java.sql.*"%>
<div class="container-fluid">
   <div class="row">
     <div class="col-md-2 sidebar-right">
	   <ul class="nav nav-sidebar">
         <li class="active"><a href="#">News / Announcements<span class="sr-only">(current)</span></a></li>	
         <% 
         	Connection con=ConnectionPool.getConnection();
         	PreparedStatement ps=con.prepareStatement(Constants.GET_NEWS);
         	ps.setInt(1,Integer.parseInt(session.getAttribute("userid").toString()));
         	ResultSet rs=ps.executeQuery();
         	while(rs.next())
         	{%>
         		<li><a id="<%=rs.getString(1) %>"  onclick="showsinglenews(<%=rs.getString(1)%>,'<%= rs.getString(2)%>')"><%= rs.getString(2)%></a></li>
         	<%}
	       	ConnectionPool.freeConnection(con);
         	%>
      </ul>      
  	</div>
  </div>
 </div>
