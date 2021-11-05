<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Book Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style2.css"/>

</head>
<body>
<div class="main-container">

    <div class="table-values">
        BOOK-FORM

        <form id="send-values" action="${pageContext.request.contextPath}/" method="post">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required="required"><br>

            <label for="author">Author:</label>
            <input type="text" id="author" name="author" required="required"><br>

            <label for="publisher">Publisher:</label>
            <input type="text" id="publisher" name="publisher"  required="required"><br>


            <input type="hidden" name="action" value="add" ><br>
            <input type="submit" form="send-values" name="add"/>
        </form>


        <button onclick="window.location.href='/';">
            Discard changes
        </button>
    </div>



</div>
<script>

</script>
</body>

</html>
