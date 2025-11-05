import java.util.*;

public class TimetableGenerator {

    static final int DAYS = 5;
    static final int SLOTS = 5;
    static final int CLASSES = 3;
    static final String[] subjects = {"Math", "Science", "English"};
    static final String[] teachers = {"T1", "T2", "T3"};
    static String[][][] timetable = new String[CLASSES][DAYS][SLOTS];

    public static void main(String[] args) {
        if (generate(0, 0, 0)) {
            printTimetable();
        } else {
            System.out.println("No valid timetable found.");
        }
    }

    static boolean generate(int classId, int day, int slot) {
        if (classId == CLASSES) return true;

        for (int s = 0; s < subjects.length; s++) {
            for (int t = 0; t < teachers.length; t++) {
                if (isSafe(classId, day, slot, teachers[t])) {
                    timetable[classId][day][slot] = subjects[s] + "-" + teachers[t];
                    int nextSlot = (slot + 1) % SLOTS;
                    int nextDay = (slot + 1 == SLOTS) ? (day + 1) % DAYS : day;
                    int nextClass = (slot + 1 == SLOTS && day + 1 == DAYS) ? classId + 1 : classId;

                    if (generate(nextClass, nextDay, nextSlot)) return true;

                    timetable[classId][day][slot] = null; // backtrack
                }
            }
        }
        return false;
    }

    static boolean isSafe(int classId, int day, int slot, String teacher) {
        for (int c = 0; c < CLASSES; c++) {
            if (timetable[c][day][slot] != null && timetable[c][day][slot].endsWith(teacher)) {
                return false; // teacher already assigned in this slot
            }
        }
        return true;
    }

    static void printTimetable() {
        for (int c = 0; c < CLASSES; c++) {
            System.out.println("Class " + (char)('A' + c) + ":");
            for (int d = 0; d < DAYS; d++) {
                for (int s = 0; s < SLOTS; s++) {
                    System.out.print((timetable[c][d][s] != null ? timetable[c][d][s] : "Free") + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}