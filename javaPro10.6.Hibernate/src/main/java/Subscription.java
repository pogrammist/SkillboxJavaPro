import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Subscription")
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("course_id")
    private Course course;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date date;

    private Subscription() {
    }

    public Subscription(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.id = new SubscriptionId(student.getId(), course.getId());
    }

    public SubscriptionId getId() {
        return id;
    }

    public void setId(SubscriptionId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
