import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardFrame extends JFrame {

    private static final Color NAVY = new Color(0x13, 0x27, 0x43);
    private static final Color GOLD = new Color(0xc9, 0xa1, 0x3b);
    private static final Color CREAM = new Color(0xf6, 0xf2, 0xe8);
    private static final Color LINE = new Color(0xd8, 0xcf, 0xb8);

    private final CardLayout cards = new CardLayout();
    private final JPanel cardPanel = new JPanel(cards);
    private final String role;
    private final String uid;
    private final String sampleClass = "Form 4A"; // used by tic/teacher/student sample views

    public DashboardFrame(String uid, String role, String displayName) {
        super("Shallom High School - Dashboard");
        this.uid = uid;
        this.role = role;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 620);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        setContentPane(root);

        root.add(buildHeader(displayName, roleLabel(role)), BorderLayout.NORTH);

        Map<String, String> nav = navFor(role);
        JList<String> navList = new JList<>(nav.values().toArray(new String[0]));
        navList.setFont(new Font("SansSerif", Font.PLAIN, 13));
        navList.setFixedCellHeight(34);
        navList.setBorder(BorderFactory.createEmptyBorder(10, 6, 10, 6));
        navList.setSelectedIndex(0);

        String[] keys = nav.keySet().toArray(new String[0]);
        for (String key : keys) {
            cardPanel.add(buildPanel(key, displayName), key);
        }
        cards.show(cardPanel, keys[0]);

        navList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cards.show(cardPanel, keys[navList.getSelectedIndex()]);
            }
        });

        JScrollPane navScroll = new JScrollPane(navList);
        navScroll.setPreferredSize(new Dimension(200, 0));
        navScroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, LINE));

        root.add(navScroll, BorderLayout.WEST);
        root.add(cardPanel, BorderLayout.CENTER);
    }

    private String roleLabel(String role) {
        switch (role) {
            case "HEADMASTER": return "Headmaster";
            case "DEPUTY": return "Deputy Headmaster";
            case "TIC": return "Teacher in Charge";
            case "TEACHER": return "Teacher";
            case "ANCILLARY": return "Ancillary Staff";
            default: return "Student";
        }
    }

    private JPanel buildHeader(String displayName, String roleTag) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(NAVY);
        header.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        JLabel school = new JLabel("Shallom High School");
        school.setForeground(Color.WHITE);
        school.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel tag = new JLabel(roleTag + " Dashboard");
        tag.setForeground(new Color(0xe8, 0xd9, 0xad));
        tag.setFont(new Font("SansSerif", Font.PLAIN, 11));
        titles.add(school);
        titles.add(tag);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        right.setOpaque(false);
        JLabel who = new JLabel("<html><div style='text-align:right;color:white'><b>" + displayName +
                "</b><br><span style='color:#e8d9ad;font-size:9px'>" + uid + "</span></div></html>");
        JButton logout = new JButton("Log Out");
        logout.setFocusPainted(false);
        logout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        right.add(who);
        right.add(logout);

        header.add(titles, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    /** key -> nav label, in display order, per role */
    private Map<String, String> navFor(String role) {
        Map<String, String> m = new LinkedHashMap<>();
        switch (role) {
            case "HEADMASTER":
                m.put("overview", "Overview"); m.put("timetable", "Master Timetable");
                m.put("staff", "Staff & Class Teachers"); m.put("subjects", "Subjects Offered");
                m.put("facilities", "Facilities"); m.put("fees", "Fees & Payment");
                break;
            case "DEPUTY":
                m.put("overview", "Overview"); m.put("timetable", "Master Timetable");
                m.put("staff", "Staff & Class Teachers"); m.put("discipline", "Discipline Log");
                m.put("facilities", "Facilities");
                break;
            case "TIC":
                m.put("overview", "My Class"); m.put("timetable", "Class Timetable");
                m.put("register", "Class Register"); m.put("subjects", "Subjects Offered");
                break;
            case "TEACHER":
                m.put("overview", "Overview"); m.put("timetable", "My Timetable");
                m.put("classes", "My Classes"); m.put("subjects", "Subjects Offered");
                break;
            case "ANCILLARY":
                m.put("overview", "My Duty"); m.put("roster", "Duty Roster");
                m.put("facilities", "Facilities");
                break;
            default: // STUDENT
                m.put("overview", "Overview"); m.put("timetable", "My Timetable");
                m.put("subjects", "Subjects Offered"); m.put("fees", "School Fees");
                m.put("facilities", "Facilities");
        }
        return m;
    }

    private JPanel buildPanel(String key, String displayName) {
        switch (key) {
            case "overview": return overviewPanel(displayName);
            case "timetable": return timetablePanel();
            case "staff": return staffPanel();
            case "subjects": return subjectsPanel();
            case "facilities": return facilitiesPanel();
            case "fees": return feesPanel();
            case "discipline": return disciplinePanel();
            case "register": return registerPanel();
            case "classes": return classesPanel();
            case "roster": return rosterPanel();
            default: return new JPanel();
        }
    }

    // ---------- panel builders ----------

    private JPanel wrap(String title, JComponent body) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        JLabel h = new JLabel(title);
        h.setFont(new Font("Serif", Font.BOLD, 18));
        h.setForeground(NAVY);
        h.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));
        p.add(h, BorderLayout.NORTH);
        p.add(body, BorderLayout.CENTER);
        return p;
    }

    private JScrollPane scroll(JComponent c) {
        JScrollPane sp = new JScrollPane(c);
        sp.setBorder(null);
        return sp;
    }

    private JPanel overviewPanel(String displayName) {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        String text;
        switch (role) {
            case "HEADMASTER": case "DEPUTY":
                text = "612 students enrolled, 38 teaching staff, 10 classes (Form 1 - Upper 6), " +
                       SchoolData.SUBJECTS.length + " subjects offered.\n\nWelcome, " + displayName + ".";
                break;
            case "TIC":
                text = "Class Teacher: " + displayName + "\nYou are Teacher in Charge of " + sampleClass + " (38 students).";
                break;
            case "TEACHER":
                text = displayName + "\nSubject specialism: Chemistry.";
                break;
            case "ANCILLARY":
                text = displayName + "\nAssigned duty: Grounds & Maintenance.";
                break;
            default:
                String classTeacher = "Mr. S. Ncube";
                text = "Class: " + sampleClass + "\nClass teacher: " + classTeacher +
                       "\nFee balance: " + SchoolData.FEE_BALANCE;
        }
        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        area.setFont(new Font("SansSerif", Font.PLAIN, 13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        body.add(area);
        return wrap("Overview", scroll(body));
    }

    private JPanel timetablePanel() {
        String[] cols = new String[1 + SchoolData.DAYS.length];
        cols[0] = "Period";
        System.arraycopy(SchoolData.DAYS, 0, cols, 1, SchoolData.DAYS.length);

        DefaultTableModel model = new DefaultTableModel(cols, 0);
        for (int i = 0; i < SchoolData.PERIODS.length; i++) {
            String p = SchoolData.PERIODS[i];
            Object[] row = new Object[cols.length];
            row[0] = p;
            if (p.equals("BREAK") || p.equals("LUNCH")) {
                for (int d = 1; d < cols.length; d++) row[d] = "--";
            } else {
                for (int d = 1; d < cols.length; d++) row[d] = SchoolData.FORM4A_TIMETABLE[i];
            }
            model.addRow(row);
        }
        JTable table = new JTable(model);
        table.setRowHeight(26);
        table.setEnabled(false);
        table.getTableHeader().setBackground(NAVY);
        table.getTableHeader().setForeground(Color.WHITE);
        return wrap("Timetable - sample class " + sampleClass, scroll(table));
    }

    private JPanel staffPanel() {
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (String[] c : SchoolData.CLASS_TEACHERS) lm.addElement(c[0] + "  -  Class Teacher: " + c[1]);
        return wrap("Class Teacher Assignments", scroll(new JList<>(lm)));
    }

    private JPanel subjectsPanel() {
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (String s : SchoolData.SUBJECTS) lm.addElement(s);
        return wrap("Subjects Offered", scroll(new JList<>(lm)));
    }

    private JPanel facilitiesPanel() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        for (String[] f : SchoolData.FACILITIES) {
            JLabel name = new JLabel(f[0]);
            name.setFont(new Font("Serif", Font.BOLD, 14));
            JLabel desc = new JLabel("<html><div style='width:520px'>" + f[1] + "</div></html>");
            desc.setForeground(Color.DARK_GRAY);
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(LINE), BorderFactory.createEmptyBorder(10, 12, 10, 12)));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(name); card.add(desc);
            body.add(card);
            body.add(Box.createVerticalStrut(8));
        }
        return wrap("Facilities", scroll(body));
    }

    private JPanel feesPanel() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        JLabel bal = new JLabel("Balance: " + SchoolData.FEE_BALANCE);
        bal.setForeground(new Color(0xaa, 0x33, 0x33));
        bal.setFont(new Font("SansSerif", Font.BOLD, 14));
        bal.setAlignmentX(Component.LEFT_ALIGNMENT);
        body.add(bal);
        body.add(Box.createVerticalStrut(14));
        JLabel methodsTitle = new JLabel("Payment Methods");
        methodsTitle.setFont(new Font("Serif", Font.BOLD, 14));
        methodsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        body.add(methodsTitle);
        body.add(Box.createVerticalStrut(6));
        for (String[] pm : SchoolData.PAYMENT_METHODS) {
            JLabel name = new JLabel(pm[0]);
            name.setFont(new Font("SansSerif", Font.BOLD, 13));
            name.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel desc = new JLabel("<html><div style='width:520px;color:#555'>" + pm[1] + "</div></html>");
            desc.setAlignmentX(Component.LEFT_ALIGNMENT);
            body.add(name); body.add(desc); body.add(Box.createVerticalStrut(10));
        }
        return wrap("School Fees", scroll(body));
    }

    private JPanel disciplinePanel() {
        DefaultListModel<String> lm = new DefaultListModel<>();
        lm.addElement("Form 3B - late arrivals this week (3 cases)");
        lm.addElement("Form 2A - uniform warning issued (1 case)");
        lm.addElement("Form 4B - resolved, verbal caution (closed)");
        return wrap("Discipline Log", scroll(new JList<>(lm)));
    }

    private JPanel registerPanel() {
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (int i = 1; i <= 8; i++) lm.addElement("Form4A-" + String.format("%02d", i) + "  -  Present");
        lm.addElement("... 30 more students");
        return wrap("Class Register - " + sampleClass, scroll(new JList<>(lm)));
    }

    private JPanel classesPanel() {
        DefaultListModel<String> lm = new DefaultListModel<>();
        lm.addElement("Form 3A - Chemistry"); lm.addElement("Form 3B - Chemistry");
        lm.addElement("Form 4A - Chemistry"); lm.addElement("Lower Six - Chemistry");
        return wrap("My Classes", scroll(new JList<>(lm)));
    }

    private JPanel rosterPanel() {
        DefaultListModel<String> lm = new DefaultListModel<>();
        lm.addElement("Monday - Grounds & school gate");
        lm.addElement("Tuesday - Dining hall support");
        lm.addElement("Wednesday - Grounds & maintenance");
        lm.addElement("Thursday - Boarding house upkeep");
        lm.addElement("Friday - Assembly hall setup");
        return wrap("Weekly Duty Roster", scroll(new JList<>(lm)));
    }
}
