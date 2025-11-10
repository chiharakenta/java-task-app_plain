<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="layout/header.jsp" />
    <h1>タスク編集</h1>
    <form action="/TaskEditServlet" method="post">
        <input type="hidden" name="id" value="${task.id}">
        <input type="text" name="name" value="${task.name}">
        <input type="submit" value="更新">
    </form>
<jsp:include page="layout/footer.jsp" />
