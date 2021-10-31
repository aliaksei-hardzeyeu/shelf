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
        BOOK-FORM

        <form id="send-values" action="${pageContext.request.contextPath}/" method="post">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="<c:out value="${book.title}"/>" required="required"><br>

            <label for="author">Author:</label>
            <input type="text" id="author" name="author" value="<c:out value="${book.author}"/>" required="required"><br>

            <label for="publisher">Publisher:</label>
            <input type="text" id="publisher" name="publisher" value="<c:out value="${book.publisher}"/>" required="required"><br>



            <input type="hidden" id="id" name="id" value="<c:out value="${book.id}"/>"><br>

            <input type="hidden" name="action" value="${actionOnPage}"/>

            <input type="submit" form="send-values" name="update"/>
        </form>


        <button onclick="window.location.href='/';">
            Discard changes
        </button>
    </div>

<%--    <div class="content-table">--%>
<%--        BORROWS TABLE--%>
<%--        <div class="flex-row">--%>
<%--            <div class="cell">--%>
<%--                Reader email--%>
<%--            </div>--%>
<%--            <div class="cell">--%>
<%--                Reader name--%>
<%--            </div>--%>
<%--            <div class="cell">--%>
<%--                Borrow date--%>
<%--            </div>--%>
<%--            <div class="cell">--%>
<%--                Due date--%>
<%--            </div>--%>
<%--            <div class="cell">--%>
<%--                Return date--%>
<%--            </div>--%>
<%--        </div>--%>


<%--        <c:forEach var="borrow" items="${listOfBorrows}">--%>

<%--            <div class="flex-row">--%>
<%--                <div class="cell">--%>
<%--                    <c:out value="${borrow.userEmail}"/>--%>
<%--                </div>--%>
<%--                <div class="cell">--%>
<%--                        &lt;%&ndash;here should be modal window to switch status of borrow&ndash;%&gt;--%>
<%--                    <a href="#openModal2"><c:out value="${borrow.userName}"/></a>--%>

<%--                </div>--%>
<%--                <div class="cell">--%>
<%--                    <c:out value="${borrow.borrowDate}"/>--%>
<%--                </div>--%>
<%--                <div class="cell">--%>
<%--                    <c:out value="${borrow.dueDate}"/>--%>
<%--                </div>--%>
<%--                <div class="cell">--%>
<%--                    <c:out value="${borrow.returnDate}"/>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--            <div id="openModal2" class="modal">--%>
<%--                <div class="modal-dialog">--%>
<%--                    <div class="modal-content">--%>
<%--                        <div class="modal-header">--%>
<%--                            <h3 class="modal-title">Borrow status</h3>--%>
<%--                        </div>--%>
<%--                        <div class="modal-body">--%>


<%--                            <form method="post" action="${pageContext.request.contextPath}/borrow">--%>
<%--                                <p>Please select borrow status:</p>--%>
<%--                                <div>--%>
<%--                                    <input type="radio" id="statusChoice1"--%>
<%--                                           name="status" value="returned">--%>
<%--                                    <label for="statusChoice1">Returned</label>--%>

<%--                                    <input type="radio" id="statusChoice2"--%>
<%--                                           name="status" value="damaged">--%>
<%--                                    <label for="statusChoice2">Returned & damaged</label>--%>

<%--                                    <input type="radio" id="statusChoice3"--%>
<%--                                           name="status" value="lost">--%>
<%--                                    <label for="statusChoice3">Lost</label>--%>

<%--                                    <input type="hidden" id="borrowId3" name="borrowId" value="${borrow.borrowId}">--%>
<%--                                    <input type="hidden" id="action" name="action" value="changeStatus">--%>

<%--                                </div>--%>
<%--                                <div>--%>
<%--                                    <button type="submit">Submit</button>--%>
<%--                                </div>--%>
<%--                            </form>--%>


<%--                            <a href="#close" title="Close" class="close">Discard</a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>

<%--        <a href="#openModal1">Add borrow</a>--%>

<%--        <div id="openModal1" class="modal">--%>
<%--            <div class="modal-dialog">--%>
<%--                <div class="modal-content">--%>
<%--                    <div class="modal-header">--%>
<%--                        <h3 class="modal-title">New borrow</h3>--%>
<%--                    </div>--%>
<%--                    <div class="modal-body">--%>


<%--                        <div class="table-values">--%>
<%--                            BORROW-FORM--%>

<%--                            <form id="send-borrows" action="${pageContext.request.contextPath}/borrow" method="post">--%>
<%--                                <label for="email">Email:</label>--%>
<%--                                <input type="email" id="email" name="email" value=""><br>--%>


<%--                                <label for="name">Name:</label>--%>
<%--                                <input type="text" id="name" name="name" value=""><br>--%>


<%--                                <label for="period">Time period:</label>--%>
<%--                                <input type="int" id="period" name="period" value=""><br>--%>


<%--                                <label for="comment">Comment:</label>--%>
<%--                                <input type="text" id="comment" name="comment" value=""><br>--%>

<%--                                <input type="hidden" id="bookIdBorrow" name="bookId" value="${book.bookId}"><br>--%>
<%--                                <input type="hidden" name="action" value="addBorrow"/>--%>
<%--                                <input type="hidden" name="type" value="old"/>--%>
<%--                                <input type="submit" form="send-borrows" name="submit"/>--%>

<%--                                <a href="#close" title="Close" class="close">Discard</a>--%>
<%--                            </form>--%>

<%--                        </div>--%>


<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>




<%--    </div>--%>


</div>
<script>

</script>
</body>

</html>