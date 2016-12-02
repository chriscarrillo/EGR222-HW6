/**
 * Created by Chris on 11/30/16.
 */
public class ScheduleConflictException extends RuntimeException {
    public ScheduleConflictException(Course c1, Course c2) {
        super(c1.getName() + " conflicts with " + c2.getName() + "!");
    }
}
