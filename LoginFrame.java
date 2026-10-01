import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private static final Color NAVY = new Color(0x13, 0x27, 0x43);
    private static final Color GOLD = new Color(0xc9, 0xa1, 0x3b);
    private static final Color CREAM = new Color(0xf6, 0xf2, 0xe8);
    private static final Color GREEN = new Color(0x2e, 0x53, 0x39);

    private final JTextField uidField = new JTextField(16);
    private final JPasswordField pwdField = new JPasswordField(16);
    private final JLabel errorLabel = new JLabel(" ");

    public LoginFrame() {
        super("Shallom High School - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(CREAM);
        setContentPane(root);

        // ---- header ----
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(CREAM);
        header.setBorder(BorderFactory.createEmptyBorder(28, 20, 10, 20));
        JLabel crest = new JLabel("\u2726", SwingConstants.CENTER);
        crest.setFont(new Font("SansSerif", Font.BOLD, 30));
        crest.setForeground(GOLD);
        crest.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = new JLabel("Shallom High School", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        title.setForeground(NAVY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel motto = new JLabel("\u201cWisdom, Peace, Excellence\u201d", SwingConstants.CENTER);
        motto.setFont(new Font("SansSerif", Font.ITALIC, 13));
        motto.setForeground(GREEN);
        motto.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(crest);
        header.add(Box.createVerticalStrut(4));
        header.add(title);
        header.add(Box.createVerticalStrut(2));
        header.add(motto);

        // ---- form ----
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JComboBox<String> roleBox = new JComboBox<>(new String[]{
                "Headmaster", "Deputy Headmaster", "Teacher in Charge", "Teacher", "Ancillary Staff", "Student"});
        roleBox.setSelectedItem("Student");
        roleBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(labelled("I am logging in as", roleBox));
        form.add(Box.createVerticalStrut(10));
        form.add(labelled("User ID", uidField));
        form.add(Box.createVerticalStrut(10));
        form.add(labelled("Password", pwdField));
        form.add(Box.createVerticalStrut(16));

        JButton loginBtn = new JButton("Log In");
        loginBtn.setBackground(NAVY);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        loginBtn.addActionListener(this::onLogin);
        form.add(loginBtn);

        errorLabel.setForeground(new Color(0xaa, 0x33, 0x33));
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(Box.createVerticalStrut(8));
        form.add(errorLabel);

        JTextArea hint = new JTextArea(
                "Demo access:\n" +
                "Headmaster: hm001 / head2026\n" +
                "Deputy: dh001 / deputy2026\n" +
                "Teacher in Charge: tic001 / tic2026\n" +
                "Teacher: tch001 / teach2026\n" +
                "Ancillary: anc001 / staff2026\n" +
                "Student: stu2026001 / student2026");
        hint.setEditable(false);
        hint.setBackground(CREAM);
        hint.setFont(new Font("Monospaced", Font.PLAIN, 11));
        hint.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        hint.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(Box.createVerticalStrut(14));
        form.add(hint);

        pwdField.addActionListener(this::onLogin);

        root.add(header, BorderLayout.NORTH);
        root.add(form, BorderLayout.CENTER);
    }

    private JPanel labelled(String text, JComponent field) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 12));
        l.setForeground(new Color(0x55, 0x55, 0x55));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        p.add(l);
        p.add(field);
        return p;
    }

    private void onLogin(ActionEvent e) {
        String uid = uidField.getText().trim().toLowerCase();
        String pwd = new String(pwdField.getPassword());
        String[] record = SchoolData.USERS.get(uid);

        if (record == null || !record[0].equals(pwd)) {
            errorLabel.setText("Incorrect User ID or password.");
            return;
        }
        String role = record[1];
        String displayName = record[2];
        errorLabel.setText(" ");
        new DashboardFrame(uid, role, displayName).setVisible(true);
        dispose();
    }
}
