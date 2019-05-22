package validatetest;

import com.gethin.validator.validator.po.Student;
import com.gethin.validator.validator.po.StudentLecture;
import com.gethin.validator.validator.util.ValidationUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: gethin
 * @email: denggx3@chinaunicom.cn
 * @Date: 2019/5/21 15:17
 * @description:
 */
public class ValidatorTest {
    private Student student;

    @Before
    public void setUp() {
        List<StudentLecture> studentLectures = new ArrayList<StudentLecture>();
        StudentLecture chinese = new StudentLecture();
        chinese.setGrade(87.5f);
        chinese.setId(1);
        chinese.setLectureName("语文");
        chinese.setStudentId(1);
        studentLectures.add(chinese);

        StudentLecture math = new StudentLecture();
//        math.setGrade(89f);
        math.setId(2);
        math.setLectureName("数学");
        math.setStudentId(1);
        studentLectures.add(math);

        student = new Student();
        student.setId(1);
        student.setCnname("gehin");
        student.setStudentLectureList(studentLectures);
        student.setEmail("326121");
    }

    @Test
    public void testvalidate(){
        try {
            ValidationUtil.validate(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
