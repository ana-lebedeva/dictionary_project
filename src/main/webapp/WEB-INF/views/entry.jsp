<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alebedeva
  Date: 24.10.2018
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${key}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        function doDelete() {
            var str = $('#list option:selected').val();
            $.ajax({
                url: '/entry/delete',
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    key: "${key}",
                    value: str + ""
                }),
                success: function (data){
                    updateSelect(data);
                    checkSelect()
                }
            })
        }
        function updateSelect(data) {
            var options = '';
            alert(data[0]);
            for (var i = 1; i < data.length; i++){
                options += "<option value='" + data[i] + "'>"
                    + data[i] + "</option>\r\n";
            }
            $("#list").empty().html(options);
        }
        function doAdd() {
            var str =  document.getElementById("valueAdd").value;
            $.ajax({
                url: '/entry/add',
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    key: "${key}",
                    value: str + ""
                }),
                success: function (data) {
                    updateSelect(data);
                    checkSelect()
                }
            })
        }
        function checkSelect(){
            var length = $('#list').children('option').length;
            if ( length != 0 )
                $("#buttonDelete").prop("disabled", false);
            else $("#buttonDelete").prop("disabled", true);
        }
        $(document).ready(function() {checkSelect()})
    </script>
</head>
<body>
<div>
<h3>${key}:</h3>
    <select id="list">
        <c:forEach items="${entries}" var="entry">
            <option value="${entry}">${entry}</option>
        </c:forEach>
    </select>
    <input type="button" value="Delete" id="buttonDelete" onclick="doDelete()"/>
</div>
<div>
    <input size="8px" id="valueAdd">
    <input type="button" value="Add" onclick="doAdd()">
</div>
<div>
    <input type="button" value="Back" onclick='location.href="/dictionaries"'>
</div>
</body>
</html>
