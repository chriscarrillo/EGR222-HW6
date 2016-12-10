import java.io.PrintStream;
import java.util.*;

/**
 * Created by Chris on 11/30/16.
 * The Schedule class contains methods required to create a schedule
 */
public class Schedule implements Cloneable {
    private List<Course> schedule;
    private int totalCredits;

    public Schedule() {
        this.schedule = new ArrayList<Course>();
        this.totalCredits = 0;
    }

    // The add method adds the given course to the schedule
    public void add(Course c) {
        for (Course c1 : this.schedule) {
            if (c1.conflictsWith(c)) {
                throw new ScheduleConflictException(c, c1);
            }
        }
        this.schedule.add(c);
        this.totalCredits += c.getCredits();
    }

    // The clone method clones the schedule when called
    public Schedule clone() {
        try {
            Schedule sCopy = (Schedule) super.clone();
            sCopy.schedule = new ArrayList<>(schedule);
            return sCopy;
        } catch(CloneNotSupportedException e) {
            return null;
        }
    }

    // The getCourse method returns the Course given the day and time
    public Course getCourse(Weekday day, Time time) {
        for (Course c : this.schedule) {
            if (c.contains(day, time)) {
                return c;
            }
        }
        return null;
    }

    // The remove method removes the course with the given information
    public void remove(Weekday day, Time time) {
        for (int i = 0; i < this.schedule.size(); i++) {
            Course c = this.schedule.get(i);
            if (c.contains(day, time)) {
                int credits = c.getCredits();
                this.totalCredits -= credits;
                this.schedule.remove(c);
                break;
            }
        }
    }

    public void save(PrintStream p, Comparator<Course> comp) {
        Collections.sort(this.schedule, comp);
        for (Course c : this.schedule) {
            p.println(c);
        }
        p.close();
    }

    // returns all of the courses in the schedule
    public List<Course> getAllCourses() {
        return schedule;
    }

    // returns the total amount of credits in the schedule
    public int totalCredits() {
        return this.totalCredits;
    }
}
