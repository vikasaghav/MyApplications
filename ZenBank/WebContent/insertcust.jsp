<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form id="insertCust" method="post" action="insertc"
		modelAttribute="customer">
		<table>

			<tr>
				<td><form:label path="customername">Enter your user-name</form:label></td>
				<td><form:input id="customername" name="customername" path="" /></td>
			</tr>

			<tr>
				<td><form:label path="customeremail">Please enter your email</form:label></td>
				<td><form:input id="customeremail" name="customeremail" path="" /></td>
			</tr>
			
			<tr>
				<td><form:label path="dateofbirth">Please enter your dateofbirth</form:label></td>
				<td><form:input id="dateofbirth" name="dateofbirth" path="" /></td>
			</tr>
			
			<tr>
				<td><form:label path="localcity">Please enter your localcity</form:label></td>
				<td><form:input id="localcity" name="localcity" path="" /></td>
			</tr>
			
			<tr>
				<td><form:label path="localstate">Please enter your localstate</form:label></td>
				<td><form:input id="localstate" name="localstate" path="" /></td>
			</tr>
			
			<tr>
				<td><form:label path="localpin">Please enter your localpin</form:label></td>
				<td><form:input id="localpin" name="localpin" path="" /></td>
			</tr>
			
			<tr>
				<td><form:label path="homecity">Please enter your homecity</form:label></td>
				<td><form:input id="homecity" name="homecity" path="" /></td>
			</tr>
			<tr>
				<td><form:label path="homestate">Please enter your homestate</form:label></td>
				<td><form:input id="homestate" name="homestate" path="" /></td>
			</tr>
			<tr>
				<td><form:label path="homepin">Please enter your homepin</form:label></td>
				<td><form:input id="homepin" name="homepin" path="" /></td>
			</tr>
			
			
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>










<%-- <form name="frm" action="InsertCust" method="post"><br/>
<table>
<tr>
<td> customer name</td>
<td><input type="text" name="customername" > </td>
</tr>
 <tr>
<td> customer email</td>
<td><input type="text" name="customeremail"> </td>
</tr>

 <tr>
<td> date of birth</td>
<td> <input type="text" name="dateofbirth" > </td>
</tr>
 <tr>
<td> local city</td>
<td>  <input type="text" name="localcity"> </td>
</tr>
 
 <tr>
<td> local state</td>
<td>   <input type="text" name="localstate"> </td>
</tr>
  <tr>
<td> local pin </td>
<td>   <input type="text" name="localpin" ></td>
</tr>
 
  <tr>
<td> home city </td>
<td>  <input type="text" name="homecity"></td>
</tr>

<tr>
<td> home state </td>
<td> <input type="text" name="homestate"></td>
</tr>

<tr>
<td>home pin  </td>
<td> <input type="text" name="homepin" ></td>
</tr>

 
<tr>

<td> 
<input type="submit" value="Enter"></td>
</tr>


</table>

</form> --%>
</body>
</html>