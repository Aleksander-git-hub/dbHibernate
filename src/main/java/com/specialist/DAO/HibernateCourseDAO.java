package com.specialist.DAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("courseDao")
public class HibernateCourseDAO implements CourseDAO
{
    private static final Log LOG = LogFactory.getLog(HibernateCourseDAO.class);

    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id) {
        return (Course) getSessionFactory().getCurrentSession().
                byId(Course.class).load(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return getSessionFactory().getCurrentSession().
                createQuery("from Course c").list(); //HQL
    }

    @Override
    public List<Course> findByTitle(String title) {
        return getSessionFactory().getCurrentSession().
                createQuery("from Course c where title LIKE: title").
                setString("title", "%" + title.trim() + "%").list();
    }

    @Override
    public void insert(Course course) {
        getSessionFactory().getCurrentSession().save(course);
        LOG.info("Course saved with id: " + course.getId());
    }

    @Override
    public void update(Course course) {
        getSessionFactory().getCurrentSession().update(course);
        LOG.info("Course updated with id: " + course.getId());
    }

    @Override
    public void delete(int id) {
        Course c = new Course();
        c.setId(id);
        getSessionFactory().getCurrentSession().delete(c);
        LOG.info("Course updated with id: " + c.getId());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
