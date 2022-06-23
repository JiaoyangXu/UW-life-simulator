package
        com.example.uw_life_simulator.Service;

import android.widget.CheckBox;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.component.CourseSelectionComponent;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.data.CourseSelectionRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseSelectionService {
    private CourseDao courseDao;
    private CourseSelectionRecordDAO courseSelectionRecordDAO;
    private CourseSelectionComponent component;


    public CourseSelectionService(CourseDatabase db, CourseSelectionComponent component) {
        this.courseDao = db.courseDao();
        this.courseSelectionRecordDAO = db.courseSelectionRecordDAO();
        this.component = component;
    }


    /**
     * construct courseSelectionRecord and insert them to database
     */
    public void InsertCheckedCourses(){
        List<Course> selectedCourses = new ArrayList<>();
        List<CheckBox> checkBoxes = component.getCheckBoxes();
        HashMap<String,Course> courseMap = component.getCourseMap();
        for(CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                String courseCode = checkBox.getText().toString();
                CourseSelectionRecord record = new CourseSelectionRecord(1,courseCode);
                courseSelectionRecordDAO.insertAll(record);
                courseDao.update_check(courseCode);
            }
        }
    }

    public CourseSelectionComponent getComponent() {
        return component;
    }
}
