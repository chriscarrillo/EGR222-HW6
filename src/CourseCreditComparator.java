import java.util.Comparator;

/**
 * Created by Chris on 11/30/16.
 */
public class CourseCreditComparator implements Comparator<Course> {
    public CourseCreditComparator() {
    }

    public int compare(Course c1, Course c2) {
        if (c1.getCredits() != c2.getCredits()) {
            return c1.getCredits() - c2.getCredits();
        }

        return c1.getName().compareTo(c2.getName());
    }
}
