package com.lesula.app;

import com.lesula.db.conf.HibernateUtils;
import com.lesula.db.dao.CourseDAO;
import com.lesula.db.dao.CourseSessionDAO;
import com.lesula.db.table.Course;
import com.lesula.db.table.CourseSession;

import java.util.Date;

public class SimpleHibernateMain {

	public static void main(String[] args) {

		String courseName = "Computer Networks";

		System.out.println(String.format("Creating course %s", courseName));
		Long courseId = createCourse(courseName);
		System.out.println(String.format("Course Created with id %d\n", courseId));

		System.out.println(String.format("Adding cascade course session to course %d", courseId));
		addCourseSessionByCascade(courseId);
		System.out.println(String.format("Session added to course %d\n", courseId));

		System.out.println(String.format("Adding manually course session to course %d", courseId));
		addCourseSessionManually(courseId);
		System.out.println(String.format("Session added to course %d\n", courseId));

		System.out.println(String.format("Deleting course %d", courseId));
		deleteCourse(courseId);
		System.out.println(String.format("Deleted course %d\n", courseId));


	}

	private static Long createCourse(String courseName){
		Course course = null;
		try{
			HibernateUtils.beginTransaction();

			course = new Course(courseName);
			CourseSession session = new CourseSession(course, new Date(2014, 5, 10), new Date(2014, 10, 10));
			course.getSessions().add(session);
			CourseDAO dao = new CourseDAO();
			dao.save(course);
			HibernateUtils.commitTransaction();
		} catch(Exception e){
			e.printStackTrace();
			HibernateUtils.rollbackTransaction();
		} finally{
			HibernateUtils.closeSession();
		}

		return course.getId();
	}

	private static Course retrieveCourse(Long courseId){
		Course course = null;
		try{
			HibernateUtils.beginTransaction();
			CourseDAO dao = new CourseDAO();
			course = dao.findById(courseId);
			HibernateUtils.commitTransaction();
		} catch(Exception e){
			e.printStackTrace();
			HibernateUtils.rollbackTransaction();
		} finally{
			HibernateUtils.closeSession();
		}

		return course;
	}

	private static Course addCourseSessionByCascade(Long courseId){
		Course course = null;
		try{
			HibernateUtils.beginTransaction();
			course = new CourseDAO().findById(courseId);
			CourseSession courseSession = new CourseSession(course, new Date(), new Date());
			course.getSessions().add(courseSession);
			HibernateUtils.commitTransaction();
		} catch(Exception e){
			e.printStackTrace();
			HibernateUtils.rollbackTransaction();
		} finally{
			HibernateUtils.closeSession();
		}

		return course;
	}

	private static Course addCourseSessionManually(Long courseId){
		Course course = null;
		try{
			HibernateUtils.beginTransaction();

			//we are not setting course as foreign key, not enough
			CourseSession courseSession = new CourseSession();
			courseSession.setStartDate(new Date());
			courseSession.setEndDate(new Date());
			course = new CourseDAO().findById(courseId);
			courseSession.setCourse(course);

			CourseSessionDAO sessionDAO = new CourseSessionDAO();
			sessionDAO.save(courseSession);
			HibernateUtils.commitTransaction();
		} catch(Exception e){
			e.printStackTrace();
			HibernateUtils.rollbackTransaction();
		} finally{
			HibernateUtils.closeSession();
		}

		return course;
	}

	private static void deleteCourse(Long courseId){
		Course course = null;
		try{
			HibernateUtils.beginTransaction();
			CourseDAO courseDAO = new CourseDAO();
			course = courseDAO.findById(courseId);
			courseDAO.delete(course);
			HibernateUtils.commitTransaction();
		} catch(Exception e){
			e.printStackTrace();
			HibernateUtils.rollbackTransaction();
		} finally{
			HibernateUtils.closeSession();
		}

	}
}
