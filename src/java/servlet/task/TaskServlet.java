package servlet.task;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;
import model.TaskDao;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskDao taskDao = TaskDao.getInstance();
        List<Task> tasks = taskDao.getTasks();
        List<Task> doneTasks = taskDao.getDoneTasks();
        request.setAttribute("tasks", tasks);
        request.setAttribute("doneTasks", doneTasks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskName = request.getParameter("taskName");
        TaskDao taskDao = TaskDao.getInstance();
        taskDao.create(taskName);
        response.sendRedirect("/TaskServlet");
    }
}
