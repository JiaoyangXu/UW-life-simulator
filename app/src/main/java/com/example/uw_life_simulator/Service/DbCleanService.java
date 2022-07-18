package com.example.uw_life_simulator.Service;

import android.content.Context;

import androidx.room.Room;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.DAO.SpellCardDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.Database.PlayerAttributeDatabase;
import com.example.uw_life_simulator.Database.SpellCardDatabase;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.data.SpellCard;

public class DbCleanService {
    protected CourseDatabase courseDb;

    protected PlayerAttributeDatabase PlayerDb;
    protected SpellCardDatabase spellCardDb;

    protected CourseDao courseDao;
    protected CourseSelectionRecordDAO recordDAO;
    protected PlayerAttributeDAO playerDAO;
    protected SpellCardDAO spellCardDAO;


    public DbCleanService(Context context) {
        PlayerDb = Room.databaseBuilder(context,
                PlayerAttributeDatabase.class, "PlayerAttributes").allowMainThreadQueries().build();
        courseDb = Room.databaseBuilder(context,
                        CourseDatabase.class, "Courses").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();
        spellCardDb = Room.databaseBuilder(context,
                        SpellCardDatabase.class, "SpellCard").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();

        courseDao = courseDb.courseDao();
        recordDAO = courseDb.courseSelectionRecordDAO();
        playerDAO = PlayerDb.playerAttributeDAO();
        spellCardDAO = spellCardDb.spellCardDAO();

    }

    public void cleanDb() {
        //uncheckAllCourses();
        cleanCoursesTable();
        cleanCourseRecordTable();
        cleanPlayerTable();
        uncheckAllSpellCard();
    }

    public void cleanCoursesTable() {
        courseDao.deleteAll();
    }

    public void cleanCourseRecordTable() {
        recordDAO.deleteAll();
    }

    public void uncheckAllCourses() {
        courseDao.update_uncheckAll();
    }

    public void cleanPlayerTable() {
        playerDAO.deleteAll();
    }

    public void uncheckAllSpellCard() {
        spellCardDAO.unselectAll();
        spellCardDAO.unUsedAll();
    }
}
