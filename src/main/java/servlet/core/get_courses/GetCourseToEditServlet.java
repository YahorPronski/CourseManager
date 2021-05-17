package servlet.core.get_courses;

import domain.course.Course;
import handlers.view_handlers.ViewHandler;
import handlers.view_handlers.impl.JspViewHandler;
import lombok.extern.slf4j.Slf4j;
import services.impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static servlet.core.students_actions.GetCourseStudentsServlet.COURSE_ATTR;

@Slf4j
public class GetCourseToEditServlet extends HttpServlet {

    public static final String COURSE_ID_PARAM = "course_id";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Receiving course id to edit");
        long courseId = Long.parseLong(request.getParameter(COURSE_ID_PARAM));

        log.debug("Finding course by received id");
        Course courseToEdit = CourseServiceImpl.getService()
                .getCourseById_OrThrowEx(courseId);

        log.debug("Preparing attributes to response");
        Map<String, Object> respAttrs = new HashMap<>();
        respAttrs.put(COURSE_ATTR, courseToEdit);

        log.debug("Invoking view handler");
        ViewHandler viewHandler = new JspViewHandler();
        viewHandler.renderView("/view/core/create-new-course.jsp", respAttrs, request, response);
    }
}
