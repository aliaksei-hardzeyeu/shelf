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
    <div class="cover">
<%--        // using Web Server for Chrome due to security issues -> Chrome does not allow img viewing from local sources--%>
<%--        // so we need to emulate server for local files!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!edit below--%>
<%--        <img src="http://127.0.0.1:8887/${book.isbn}${book.coverExtension}" height=70% alt="cover_file">--%>
    </div>

    <div class="table-values">
        table-values

        <form id="send-values" action="${pageContext.request.contextPath}/" method="post" >
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="<c:out value="${book.title}"/>"><br>

            <label for="authors">Author:</label>
            <input type="text" id="authors" name="authors" value="<c:out value="${book.authors}"/>"><br>

            <label for="publisher">Publisher:</label>
            <input type="text" id="publisher" name="publisher" value="<c:out value="${book.publisher}"/>"><br>

            <label for="publDate">Publication date:</label>
            <input type="date" id="publDate" name="publDate" value="<c:out value="${book.publDate}"/>"><br>

            <label for="genres">Genre:</label>
            <input type="text" id="genres" name="genres" value="<c:out value="${book.genres}"/>"><br>

            <label for="pageCount">Page count:</label>
            <input type="number" id="pageCount" name="pageCount" value="<c:out value="${book.pageCount}"/>"><br>

            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" name="isbn" value="<c:out value="${book.isbn}"/>"><br>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="<c:out value="${book.des}"/>"><br>

            <label for="amount">Total amount:</label>
            <input type="number" id="amount" name="amount" value="<c:out value="${book.amount}"/>"><br>


            <input type="hidden" id="bookId" name="bookId" value="<c:out value="${book.bookId}"/>"><br>

            <input type="hidden" name="action" value="${actionOnPage}"/>

<%--            <div>--%>
<%--                <label for="file">Choose a file</label>--%>
<%--                <input type="file" id="file" name="file" accept="image/*">--%>
<%--            </div>--%>
            <input type="submit" form="send-values" name="update" />
        </form>


        <button onclick="window.location.href='/';">
            Discard changes
        </button>


    </div>
</div>
<div class="buttons-area">
    buttons-area
</div>
</body>
</html>
<%--<c:out value="${book.isbn}"/>--%>