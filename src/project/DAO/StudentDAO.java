package project.DAO;

import org.hibernate.cfg.Configuration;
import project.Model.Student;
import org.hibernate.Session;
import project.Utils.HibernateUtil;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергій on 01.06.2015.
 */

public class  StudentDAO {
    public void addStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(stud);
            session.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(stud);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Student getStudentById(Long id) throws SQLException {
        Session session = null;
        Student stud = null;
        try {

      //      File f = new File("D:\\Java - копия - копия (6)\\src\\resources\\hibernate.cfg.xml");
     //       session = (Session) new Configuration().configure(f).buildSessionFactory();

            session = HibernateUtil.getSessionFactory().openSession();
            stud = (Student) session.load(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stud;
    }

    public List<Student> getAllStudents() throws SQLException {
        Session session = null;
        List<Student> studs = new ArrayList<Student>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            studs = session.createCriteria(Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return studs;
    }

    public void deleteStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(stud);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}

