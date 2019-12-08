import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "PurchaseList")
@Table(name = "PurchaseList")
public class PurchaseList implements Serializable {

    @Id
    @Column(name = "student_name")
    private int studentName;

    @Column(name = "course_name")
    private int courseName;

    private int price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date date;

    public int getStudentName() {
        return studentName;
    }

    public void setStudentName(int studentName) {
        this.studentName = studentName;
    }

    public int getCourseName() {
        return courseName;
    }

    public void setCourseName(int courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
