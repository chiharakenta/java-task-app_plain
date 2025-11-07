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
    <h1>タスク管理アプリ</h1>
    <form action="/TaskServlet" method="post">
        <input type="text" name="taskName">
        <input type="submit" value="作成">
    </form>
    <div>
        <h2>タスク</h2>
        <ul>
            <c:forEach var="task" items="${tasks}">
                <li>
                    ${task.id}. ${task.name} ${task.done}
                    <form action="/TaskCheckServlet" method="post">
                        <input type="hidden" name="id" value="${task.id}" >
                        <input type="submit" value="完了">
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div>
        <h2>完了したタスク</h2>
        <ul>
            <c:forEach var="doneTask" items="${doneTasks}">
                <li>
                    ${doneTask.name} ${doneTask.done}
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>