<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>タスク管理アプリ</title>
</head>
<body>
    <h1>タスク編集</h1>
    <form action="/TaskEditServlet" method="post">
        <input type="hidden" name="id" value="${task.id}">
        <input type="text" name="name" value="${task.name}">
        <input type="submit" value="更新">
    </form>
</body>
</html>