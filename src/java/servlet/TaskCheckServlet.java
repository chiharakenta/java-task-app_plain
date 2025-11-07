package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;
import model.TaskList;

@WebServlet("/TaskCheckServlet")
public class TaskCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        // 完了処理
        TaskList taskList = TaskList.getInstance();
        Task task = taskList.findTaskById(id);
        if (task != null) {
            task.check();
            taskList.save();
        }

        // リダイレクト処理
        response.sendRedirect("/TaskServlet");
    }
}
