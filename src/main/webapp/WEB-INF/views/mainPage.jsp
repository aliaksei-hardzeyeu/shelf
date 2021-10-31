<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style1.css"/>

</head>

<body>
<div class="content-table">
    <div class="flex-row">
        <div class="cell">
            Title
        </div>
        <div class="cell">
            Author
        </div>
        <div class="cell">
            Publisher
        </div>
        <div class="cell">
            Actions
        </div>
    </div>


    <c:forEach var="book" items="${listOfBooks}">
        <div class="flex-row">
            <div class="cell">
                <form action="${pageContext.request.contextPath}/" method="get">
                    <input type="hidden" name="id" value="<c:out value='${book.id}'/>"/>
                    <input type="hidden" name="type" value="existing"/>
                    <input type="hidden" name="action" value="view"/>
                    <input type="submit" name="view" value="<c:out value='${book.title}'/>"/>
                </form>
            </div>

            <div class="cell">
                <c:out value="${book.author}"/>
            </div>

            <div class="cell">
                <c:out value="${book.publisher}"/>
            </div>

            <div class="cell">
                <form action="${pageContext.request.contextPath}/" method="post">
                    <input type="hidden" name="id" value="<c:out value='${book.id}'/>"/>
                    <input type="hidden" name="action" value="remove"/>
                    <input type="submit" name="view" value="remove"/>
                </form>
            </div>
        </div>
    </c:forEach>


    <form class="button-add" action="${pageContext.request.contextPath}/" method="get">
        <input type="hidden" name="action" value="view"/>
        <input type="hidden" name="type" value="new"/>
        <input type="submit" name="add" value="ADD"/>
    </form>
</div>

</body>
</html>