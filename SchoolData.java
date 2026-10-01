import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All the school's data lives here as plain static fields.
 * No database, no file storage — everything resets when the app restarts.
 */
public class SchoolData {

    // uid -> {password, role, displayName}
    public static final Map<String, String[]> USERS = new LinkedHashMap<>();
    static {
        USERS.put("hm001",      new String[]{"head2026",    "HEADMASTER", "Dr. T. Moyo"});
        USERS.put("dh001",      new String[]{"deputy2026",  "DEPUTY",     "Mrs. R. Chikwanha"});
        USERS.put("tic001",     new String[]{"tic2026",     "TIC",        "Mr. S. Ncube"});
        USERS.put("tch001",     new String[]{"teach2026",   "TEACHER",    "Ms. P. Dube"});
        USERS.put("anc001",     new String[]{"staff2026",   "ANCILLARY",  "Mr. J. Banda"});
        USERS.put("stu2026001", new String[]{"student2026", "STUDENT",    "Tanaka Marufu"});
    }

    public static final String[] SUBJECTS = {
        "Mathematics", "English Language", "Combined Science", "Biology", "Chemistry",
        "Physics", "Shona / Ndebele", "History", "Geography", "Accounts & Commerce",
        "Business Studies", "Computer Science / ICT", "Agriculture",
        "Physical Education", "Religious Studies", "Art & Design"
    };

    public static final String[][] FACILITIES = {
        {"Library & ICT Centre", "Reference library plus a 40-seat computer lab for research and coursework."},
        {"Science Laboratories", "Separate Biology, Chemistry and Physics labs for practical work."},
        {"Sports Fields & Courts", "Football and athletics field, netball and basketball courts."},
        {"Assembly Hall", "Venue for assemblies, examinations and school functions."},
        {"Boarding Houses", "Separate boys' and girls' hostels with resident matrons."},
        {"Dining Hall", "Central kitchen serving breakfast, lunch and supper to boarders."},
        {"Sick Bay", "First-aid and basic medical care on site during school hours."},
        {"Music & Art Room", "Space for creative arts, choir practice and design work."}
    };

    public static final String[][] CLASS_TEACHERS = {
        {"Form 1A", "Mrs. L. Sibanda"}, {"Form 1B", "Mr. K. Mpofu"},
        {"Form 2A", "Mrs. F. Chirwa"}, {"Form 2B", "Mr. E. Gumbo"},
        {"Form 3A", "Ms. N. Zulu"},   {"Form 3B", "Mr. D. Mutasa"},
        {"Form 4A", "Mr. S. Ncube"},  {"Form 4B", "Mrs. A. Marufu"},
        {"Lower Six", "Mr. P. Chidziva"}, {"Upper Six", "Mrs. G. Nyathi"}
    };

    public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    public static final String[] PERIODS = {
        "1  08:00", "2  08:40", "3  09:20", "BREAK", "4  10:10",
        "5  10:50", "6  11:30", "LUNCH", "7  12:40", "8  13:20"
    };

    // Same subject shown across the row for a period, one value per period (sample class: Form 4A)
    public static final String[] FORM4A_TIMETABLE = {
        "Mathematics", "English", "Chemistry", "\u2014", "Physics",
        "Biology", "History", "\u2014", "Shona", "P.E."
    };

    public static final String FEE_BALANCE = "$45.00 outstanding of $180.00 termly fee";

    public static final String[][] PAYMENT_METHODS = {
        {"Bank Transfer", "Shallom High School, CBZ Bank, Acc. 01234567891. Use student ID as reference."},
        {"Mobile Money", "EcoCash / Zipit to the school Bursar's line 077x xxx xxx."},
        {"Cash at the Bursar's Office", "Payable in person, Mon-Fri, 7:30-15:30. A receipt is issued on the spot."},
        {"Online Parent Portal", "Card payment through the school's parent portal, instant emailed receipt."}
    };
}
