package com.company.manager.servlet.core.students_actions;

import com.company.manager.domain.course.Course;
import lombok.extern.slf4j.Slf4j;
import com.company.manager.services.impl.CourseServiceImpl;
import com.company.manager.handlers.view_handlers.impl.JspViewHandler;
import com.company.manager.handlers.view_handlers.ViewHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.company.manager.constans.CourseAttrAndParamNames.*;

@Slf4j
public class GetCourseStudentsServlet extends HttpServlet {

    // Course students view targets
    public static final String GET_STUDENTS_TO_SEE = "get_students_to_see";
    public static final String GET_STUDENTS_TO_GRADE = "get_students_to_grade";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Receiving course id and scope from request");
        long courseId = Long.parseLong(request.getParameter(COURSE_ID));
        String scope = request.getParameter(COURSE_STUDENTS_VIEW_TARGET);

        log.debug("Getting course by received id");
        Course course = CourseServiceImpl.getService().getCourseById(courseId);

        log.debug("Preparing attributes for response");
        Map<String, Object> respAttrs = new HashMap<>();
        respAttrs.put(COURSE, course);
        respAttrs.put(COURSE_STUDENTS_VIEW_TARGET, scope);

        log.debug("Invoking view handler");
        ViewHandler viewHandler = new JspViewHandler();
        viewHandler.renderView("/view/core/course-students.jsp", respAttrs, request, response);
    }
}
