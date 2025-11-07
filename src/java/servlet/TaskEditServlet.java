package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;
import model.TaskList;

@WebServlet("/TaskEditServlet")
public class TaskEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task task = TaskList.getInstance().findTaskById(id);
        request.setAttribute("task", task);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/edit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("id"));
        String taskName = request.getParameter("name");
        Task task = TaskList.getInstance().findTaskById(taskId);
        task.setName(taskName);
        response.sendRedirect("/TaskServlet");
    }
}
