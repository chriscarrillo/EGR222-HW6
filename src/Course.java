import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Chris on 11/22/16.
 */
public class Course {
    private String name;
    private int credits, duration;
    private Set<Weekday> days;
    private Time startTime;

    public Course(String n, int c, Set<Weekday> d, Time t, int m) {
        if (d == null || (c > 5 || c < 1) || (n == "" || n == null || !n.contains(" ")) || (t == null) || (m <= 0))
            throw new IllegalArgumentException();

        this.name = n;
        this.credits = c;
        this.days = EnumSet.copyOf(d);
        this.duration = m;
        this.startTime = t.clone();
    }

    // returns true if the course given conflicts with the course that called it
    // returns false if otherwise
    public boolean conflictsWith(Course c) {
        for (Weekday d : c.days) {
            if (days.contains(d)) {
                if (contains(d, c.getStartTime()) || c.contains(d, getStartTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns true if the course contains the given parameters
    public boolean contains(Weekday d, Time t) {
        if (days.contains(d)) {
            if (getStartTime().equals(t))
                return true;
            else if (getEndTime().equals(t))
                return false;
            else
                return getStartTime().compareTo(t) < 0 && getEndTime().compareTo(t) > 0;
        }
        else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (credits != course.credits) return false;
        if (duration != course.duration) return false;
        if (!name.equals(course.name)) return false;
        if (!days.equals(course.days)) return false;
        return startTime.equals(course.startTime);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + credits;
        result = 31 * result + duration;
        result = 31 * result + days.hashCode();
        result = 31 * result + startTime.hashCode();
        return result;
    }

    // returns the course name
    public String getName() {
        return name;
    }

    // returns the total amount of credits the course has
    public int getCredits() {
        return credits;
    }

    // returns the duration of the course
    public int getDuration() {
        return duration;
    }

    // returns the start time of the course
    public Time getStartTime() {
        return startTime.clone();
    }

    // returns the end time of the course
    public Time getEndTime() {
        Time end = getStartTime().clone();
        end.shift(getDuration());
        return end;
    }

    @Override
    public String toString() {
        String str = "";
        for (Weekday d : days) {
            str += d.toShortName();
        }
        return name + "," + credits + "," + str + "," + startTime + "," + duration;
    }
}