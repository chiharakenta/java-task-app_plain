package servlet.task;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.TaskDao;

@WebServlet("/TaskCheckServlet")
public class TaskCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Boolean done = Boolean.parseBoolean(request.getParameter("done"));
        // 完了処理
        TaskDao taskDao = TaskDao.getInstance();
        taskDao.setDone(id, done);

        // リダイレクト処理
        response.sendRedirect("/TaskServlet");
    }
}
