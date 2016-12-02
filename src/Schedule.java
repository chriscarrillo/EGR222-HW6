import java.io.PrintStream;
import java.util.*;

/**
 * Created by Chris on 11/30/16.
 */
public class Schedule implements Cloneable {
    private List<Course> schedule;
    private int totalCredits;

    public Schedule() {
        this.schedule = new ArrayList<Course>();
        this.totalCredits = 0;
    }

    public void add(Course c) {
        for (Course c1 : this.schedule) {
            if (c1.conflictsWith(c)) {
                throw new ScheduleConflictException(c, c1);
            }
        }
        this.schedule.add(c);
        this.totalCredits += c.getCredits();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            Schedule s = (Schedule) super.clone();
            s.schedule = new ArrayList<Course>(schedule);
            return s;
        } catch(CloneNotSupportedException e) {
            return null;
        }
    }

    public Course getCourse(Weekday day, Time time) {
        for (Course c : this.schedule) {
            if (c.contains(day, time)) {
                return c;
            }
        }
        return null;
    }

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

    public List<Course> getAllCourses() {
        return schedule;
    }

    public int totalCredits() {
        return this.totalCredits;
    }
}
