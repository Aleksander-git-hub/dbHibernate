package com.specialist;

import com.specialist.DAO.Course;
import com.specialist.DAO.CourseDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        CourseDAO courseDAO = context.getBean(CourseDAO.class);

        Course spring = new Course();
        spring.setTitle("Spring");
        spring.setLength(40);
        spring.setDescription("Spring Framework and Spring/MVC");
        courseDAO.insert(spring);

        for (Course c : courseDAO.findAll()) {
            System.out.println(c);
        }

        context.close();
    }
}
