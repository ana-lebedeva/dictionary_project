<%--
  Created by IntelliJ IDEA.
  User: alebedeva
  Date: 09.10.2018
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Dictionaries</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript">
            function doDelete() {
                $.ajax({
                    url: '/dictionaries/delete',
                    data: ({key : $('#entries option:selected').val()}),
                    success: function (data) {
                        updateSelect(data);
                    }
                })
            }
            function updateSelect(data) {
                var options = '';
                for (var i = 0; i < data.length; i++){
                    options += "<option value='" + data[i] + "'>"
                        + data[i] + "</option>\r\n";
                }
                $("#entries").empty().html(options);
            }
            function doSearch() {
                var temp = document.getElementById("searchValue").value;
                $.ajax({
                    url: '/dictionaries/search',
                    data: ({key : temp}),
                    success: function (data) {
                        $('#serachResult').val(data);
                    }
                })
            }
            function doSearchAll() {
                var temp = document.getElementById("searchValue").value;
                $.ajax({
                    url: '/dictionaries/searchAll',
                    data: ({key : temp}),
                    success: function (data) {
                        $('#serachResult').val(data);
                    }
                })
            }
            function doEntry() {
                var temp =$('#entries option:selected').val();
                var str = temp.split('-')[0].trim();
                var url = '/entry/' + str;
               document.location.href = url;
            }
            function doNewEntry() {
                var temp =document.getElementById("newEntry").value;
                var url = '/entry/' + temp;
                document.location.href = url;
            }
            $(document).ready( function () {
                if(${dictionaryButtonActive})
                    $(".dictionaryButton").prop("disabled", false);
                else
                    $(".dictionaryButton").prop("disabled", true);
            })
            function checkInput() {
                var entry = $('#newEntry').val();
                if (entry.length != 0 && ${dictionaryButtonActive})
                    $("#addNewEntry").prop("disabled", false);
                else
                    $("#addNewEntry").prop("disabled", true);
            }
        </script>
    </head>
    <body>
        <div>
            <h3>Dictionaries:</h3>
             <c:forEach items="${dictionaries}" var="dictionary" >
                 <input type="button" class="choice" value="${dictionary.name}"
                        onclick='location.href="/dictionaries/" + "${dictionary.name}"' />
             </c:forEach>
        </div>
        <div id="list">
            <select name="entries" id="entries" >
                <c:forEach items="${dictionary.allEntries}" var="entry">
                    <option value="${entry}" > ${entry}</option>
                </c:forEach>
            </select>
            <input class="dictionaryButton"  type="button" value="Delete" onclick="doDelete()"/>
            <input class="dictionaryButton"  type="button" value="Edit" onClick='doEntry()'/>
        </div>
        <div>
            <input size="10px" id="newEntry" onkeyup="checkInput()">
            <input id="addNewEntry"  type="button" value="Add new entry" disabled onclick='doNewEntry()'/>
        </div>
    <div>
        <h3>Search:</h3>
        <input id="searchValue" size="10px">
        <input type="button" value="in all" onclick="doSearchAll()">
        <input type="button" value="in ${activeDictionary.name}" onclick="doSearch()">
    </div>
    <div>
        <textarea disabled id="serachResult"></textarea>
    </div>
    </body>
</html>
