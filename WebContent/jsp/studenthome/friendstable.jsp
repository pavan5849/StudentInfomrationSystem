<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<%
		if(Integer.parseInt(response.getHeader("count").toString())!=0)
		{%>
	<div class="col-lg-12">
		<table class="table table-striped table-bordered table-hover table-responsive" style="width:600px">
			<thead >
				<tr>
					<th class="col-lg-1">  # </th>
					<th class="col-lg-6">  Name </th>
					<th class="col-lg-5">Rollno</th>
				</tr>
			</thead>
				<c:forEach items="${fr}" var="user">
					<tr>
						<td><input type="radio" name="friendsgroup" id="${user.getFrollno()}" value="${user.getFrollno()}"></td>
						<td> ${user.getFname()}</td>
						<td>${user.getFrollno()}</td>
					</tr>
				</c:forEach>
		</table>
		<input type="button" id="view" value="View" class="btn btn-primary" onclick="loadfriendprofile()">
   	</div>
   	<div class="col-lg-12">
   		<label class="contol-label">  </label>
	</div>
	<div class="col-lg-12" id="friend">
	</div>
	<% }
		else
		{%>
				<b>Sorry No Results were Found!!! Please change the search criteria...</b>
		<%}
	%>
