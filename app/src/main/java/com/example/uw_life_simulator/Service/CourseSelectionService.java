package
        com.example.uw_life_simulator.Service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.CheckBox;

import androidx.room.Room;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.Database.PlayerAttributeDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.activities.EventActivity;
import com.example.uw_life_simulator.component.CourseSelectionComponent;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.model.Summarypage;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSelectionService {
    private PlayerAttributeDatabase playerAttributeDatabase;

    private CourseDao courseDao;
    private CourseSelectionRecordDAO courseSelectionRecordDAO;
    private PlayerAttributeDAO playerAttributeDAO;

    private CourseSelectionComponent component;

    private Context context;

    private List<String> courseCode;

    Map<String, Integer> myMap = new HashMap<String, Integer>() {{
        put("MANA 100", 20);
        put("HERB 100", 50);
        put("HIST 100", 20);
        put("MEDI 100", 70);
        put("SPEL 100", 80);
        put("ATRO 100", 60);
        put("HERB 200", 30);
        put("MANA 200", 70);
        put("SPEL 200", 90);
        put("HIST 200", 50);
        put("MEDI 200", 70);
        put("ATRO 200", 60);
        put("HERB 300", 30);
        put("MANA 300", 70);
        put("SPEL 300", 90);
        put("HIST 300", 50);
        put("MEDI 300", 70);
        put("ATRO 300", 60);
        put("HERB 400", 30);
        put("MANA 400", 70);
        put("SPEL 400", 90);
        put("HIST 400", 50);
        put("MEDI 400", 70);
        put("ATRO 400", 60);
    }};


    public CourseSelectionService(CourseDatabase db, CourseSelectionComponent component,Context context) {
        this.courseDao = db.courseDao();
        this.context = context;
        this.courseSelectionRecordDAO = db.courseSelectionRecordDAO();

        this.playerAttributeDatabase = Room.databaseBuilder(context,
                PlayerAttributeDatabase.class, "PlayerAttributes").
                allowMainThreadQueries().fallbackToDestructiveMigration().build();
        this.playerAttributeDAO = playerAttributeDatabase.playerAttributeDAO();

        this.component = component;
        this.courseCode = new ArrayList<>();
    }

    /**
     * construct courseSelectionRecord and insert them to database
     */

    public boolean InsertCheckedCourses() {
        updateCheckedCourses();

        if (courseCode.size() != 4) {
            AlertDialog alertDialog = new AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setMessage("You have to select 4 courses each term")
                    .setCancelable(true)
                    .setTitle("INVALID COURSE SELECTION")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Send the TryAgain button event back to the host fragment
                            dialog.dismiss();
                            return;
                        }
                    })
                    .show();
            uncheckAllBoxes();
            return false;
        }

        if (!checkPre()) {
            AlertDialog alertDialog = new AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setMessage("You have to take lower year courses before taking upper year course")
                    .setCancelable(true)
                    .setTitle("PREREQUISITE NOT SATISFIED")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Send the TryAgain button event back to the host fragment
                            dialog.dismiss();
                            return;
                        }
                    })
                    .show();
            uncheckAllBoxes();
            return false;
        }


        // insert course selection info to database
        for (String code : courseCode) {
            CourseSelectionRecord record = new CourseSelectionRecord(1, code);
            courseSelectionRecordDAO.insertAll(record);
            courseDao.update_check(code);
        }

        updatePlayerAttribute();
        return true;
    }

    private boolean checkPre() {
        for (String code : courseCode) {

            String courseLevel = code.substring(code.length() - 3);


            if ( courseLevel.equals("100")) continue;

            String pre = courseDao.getPreqByName(code);
            List<Course> taken = courseDao.getTakenPreCourse(pre);

            if (taken == null || taken.size() == 0) {
                return false;
            }
        }
        return true;
    }


    private void updatePlayerAttribute() {

        playerAttributeDAO.updateCourse1(courseCode.get(0));
        playerAttributeDAO.updateCourse2(courseCode.get(1));
        playerAttributeDAO.updateCourse3(courseCode.get(2));
        playerAttributeDAO.updateCourse4(courseCode.get(3));

        playerAttributeDAO.increasePressure(myMap.get(courseCode.get(0))/10);
        playerAttributeDAO.increasePressure(myMap.get(courseCode.get(1))/10);
        playerAttributeDAO.increasePressure(myMap.get(courseCode.get(2))/10);
        playerAttributeDAO.increasePressure(myMap.get(courseCode.get(3))/10);
        /*if (courseCode.size() != 4) {
            int diff1 = courseDao.selectDiffByID(courseCode.get(0)).get(0);
            int diff2 = courseDao.selectDiffByID(courseCode.get(1)).get(0);
            int diff3 = courseDao.selectDiffByID(courseCode.get(2)).get(0);
            int diff4 = courseDao.selectDiffByID(courseCode.get(3)).get(0);

        playerAttributeDAO.increasePressure(diff1%25);
        playerAttributeDAO.increasePressure(diff2%25);
        playerAttributeDAO.increasePressure(diff3%25);
        playerAttributeDAO.increasePressure(diff4%25);

         */
    }


    /**
     * get all checked courses
     */
    private void updateCheckedCourses(){
        List<Course> selectedCourses = new ArrayList<>();
        List<CheckBox> checkBoxes = component.getCheckBoxes();
        HashMap<String,Course> courseMap = component.getCourseMap();
        for(CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                String courseCode = checkBox.getText().toString();
                this.courseCode.add(courseCode);
            }
        }
    }

    private void uncheckAllBoxes() {
        for (CheckBox checkBox : component.getCheckBoxes()) {
            checkBox.setChecked(false);
        }
        courseCode.clear();
    }

    private CourseSelectionComponent getComponent() {
        return component;
    }
}
