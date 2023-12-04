package com.ex;

import com.ex.model.HibernateUtil;
import com.ex.model.Teacher;
import com.ex.model.Subject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SubjectFormServlet", value = "/editS")
public class SubjectFormServlet  extends HttpServlet {

    private Long id;
    private Teacher t;

    private Subject s;
    private String idParam;



    private List<Teacher> teachers;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        idParam = request.getParameter("id");
        if (idParam != null) {
            id = Long.parseLong(idParam);

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
                s = session.get(Subject.class, id);
                teachers = session.createQuery("FROM Teacher order by id", Teacher.class).list();
            transaction.commit();
            session.close();


            request.setAttribute("Name", s.getName());
            request.setAttribute("Classrooms", s.getClassrooms());
            request.setAttribute("DayOfWeek", s.getDayOfWeek());
            request.setAttribute("selectedTeacher", s.getTeacher().getId());


        } else {
            request.setAttribute("title", "Добавление");
        }

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        manufacturers = session.createQuery("FROM Teacher order by id", Teacher.class).list();
        transaction.commit();
        session.close();


        request.setAttribute("teachers",teachers);

        request.getRequestDispatcher("/SForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(idParam!=null){

            s.setBrand(request.getParameter("teacher"));
            s.setPrice(Integer.parseDouble(request.getParameter("date")));
            s.setQuantity(Integer.parseInt(request.getParameter("kol")));
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
                Teacher t  = session.get(Teacher.class, Long.parseLong(request.getParameter("selectedTeacherr")));
            transaction.commit();
            session.close();
            s.setManufacturer(t);

            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.update(s);
            transaction.commit();
            session.close();
        } else {

            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Teacher t   = session.get(Teacher.class, Long.parseLong(request.getParameter("selectedTeacher")));
            transaction.commit();
            session.close();

            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
                session.save(
                        new Watch(
                                request.getParameter("Subject"),
                                Integer.parseInt(request.getParameter("date")),
                                Integer.parseInt(request.getParameter("kol")),
                                t
                        )
                );
            transaction.commit();
            session.close();
        }
        response.sendRedirect("/example");

    }

    @Override
    public void init() throws ServletException {
        super.init();

    }
}
