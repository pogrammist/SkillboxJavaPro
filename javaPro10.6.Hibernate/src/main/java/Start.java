import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Set;

// https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/

public class Start {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getName());
        System.out.println(course.getTeacher().getName());
        System.out.println(course.getStudents().size());
        Set<Subscription> studentList = course.getStudents();
        studentList.forEach(student -> System.out.println(student.getStudent().getName()));

        Teacher teacher = session.get(Teacher.class, 2);
        System.out.println(teacher.getName());

        Student student = session.get(Student.class, 1);
        System.out.println(student.getDate());

//        Subscription subscription = session.get(Subscription.class, 1);
//        System.out.println(subscription.getDate());

//        PurchaseList purchaseList = session.get(PurchaseList.class, 1);
//        System.out.println(purchaseList.getDate());

        sessionFactory.close();
    }
}
