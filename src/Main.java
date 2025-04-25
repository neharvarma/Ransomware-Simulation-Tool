import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.util.*;
import java.util.Timer;

public class Main {

    static JButton button1, button2, button3, button4, button5, button6, button7;
    static String arr[]= new String[100];
    static String temp =  "";
    static ImageIcon imgtodis;
    static BufferedImage image;
    static File selfile;
    static boolean flag = false;
    static File selectedFile ;
    static String inputdataToEnc;
    static int cnt = 0;
    static int tempi =0;

//    static void embedInteger( int n, int start, int storageBit) {
//        int maxX = image.getWidth(), maxY = image.getHeight(),
//                startX = start/maxY, startY = start - startX*maxY, count=0;s
//        for(int i=startX; i<maxX && count<32; i++) {
//            for(int j=startY; j<maxY && count<32; j++) {
//                int rgb = image.getRGB(i, j), bit = getBitValue(n, count);
//                rgb = setBitValue(rgb, storageBit, bit);
//                image.setRGB(i, j, rgb);
//                count++;
//            }
//        }
//    }
//
//    public static void embedByte(byte b, int start, int storageBit) {
//        int maxX = image.getWidth(), maxY = image.getHeight(),
//                startX = start/maxY, startY = start - startX*maxY, count=0;
//        for(int i=startX; i<maxX && count<8; i++) {
//            for(int j=startY; j<maxY && count<8; j++) {
//                int rgb = image.getRGB(i, j), bit = getBitValue(b, count);
//                rgb = setBitValue(rgb, storageBit, bit);
//                image.setRGB(i, j, rgb);
//                count++;
//            }
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and display the undecorated splash screen
            JFrame splashFrame = new JFrame();
            splashFrame.setSize(600, 350);
            splashFrame.setUndecorated(true); // Set the frame undecorated

            // Set the background color to #022D36
            splashFrame.getContentPane().setBackground(new Color(2, 45, 54));

            // Customize the splash screen content
            JPanel contentPanel = new JPanel(new BorderLayout());

            // Add determinate progress bar at the bottom with decreased height
            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(false);
            progressBar.setStringPainted(false);
            progressBar.setPreferredSize(new Dimension(progressBar.getPreferredSize().width, 5)); // Further decreased height
            progressBar.setForeground(new Color(0, 127, 255)); // Set the progress bar color to #007FFF
            contentPanel.add(progressBar, BorderLayout.SOUTH);

            JPanel extra = new JPanel();
            contentPanel.add(extra, BorderLayout.CENTER);
            extra.setLayout(null);


            ImageIcon iconMain = new ImageIcon("Images/logo_ag60.png");
            JLabel blank = new JLabel("AnyGuard: The Next Gen Sec");
            Font f123 = new Font("Arial", Font.BOLD,48);
            blank.setFont(f123);
            blank.setIcon(iconMain);
            blank.setBounds(12,120,1000,54);
            blank.setForeground(Color.WHITE);

            extra.setBackground(new Color(2, 45, 54));
            extra.add(blank);


            splashFrame.add(contentPanel);

            // Center the splash screen on the screen
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - splashFrame.getWidth()) / 2;
            int y = (screenSize.height - splashFrame.getHeight()) / 2;
            splashFrame.setLocation(x, y);

            // Display the splash screen
            splashFrame.setVisible(true);

            // Simulate loading time with a TimerTask
            int totalProgress = 100;
            int progressInterval = 10;
            int delay = 200;

            final int[] progress = {0}; // Use an array to make it effectively final

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    // Update progress bar
                    progressBar.setValue(progress[0]);

                    if (progress[0] >= totalProgress) {
                        // Close the splash screen
                        splashFrame.dispose();

                        // Open the main frame with side navigation
                        JFrame mainFrame = new JFrame();
                        mainFrame.setUndecorated(true);
                        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        // Set the background color to a dark blue color
                        mainFrame.getContentPane().setBackground(new Color(54, 63, 73)); // Dark blue



                        // Add custom title bar with software name to the left
                        JPanel titleBar = new JPanel(new BorderLayout());
                        titleBar.setBackground(new Color(30, 30, 30));
                        titleBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5)); // Add margin to the left

                        // Add software name to the left
                        JLabel softwareNameLabel = new JLabel("AnyGuard 2024.1.0");
                        softwareNameLabel.setForeground(Color.WHITE);
                        softwareNameLabel.setHorizontalAlignment(JLabel.LEFT);
                        titleBar.add(softwareNameLabel, BorderLayout.WEST);

                        // Add close button with hover effect to the right
                        JButton closeButton = new JButton("X");
                        closeButton.setFocusPainted(false);
                        closeButton.setBorderPainted(false);
                        closeButton.setContentAreaFilled(false);
                        closeButton.setForeground(Color.WHITE);

                        // Add hover effect by changing the background color
                        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                closeButton.setOpaque(true);
                                closeButton.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                closeButton.setOpaque(false);
                            }
                        });

                        closeButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                closeButton.setEnabled(false);
                                int choice = JOptionPane.showConfirmDialog(mainFrame, "Do you want to continue?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                                if (choice == JOptionPane.OK_OPTION) {
                                    System.exit(0);
                                } else if (choice == JOptionPane.CANCEL_OPTION) {
                                    closeButton.setEnabled(true);
                                    closeButton.setOpaque(false);

                                } else if (choice == JOptionPane.CLOSED_OPTION) {
                                    closeButton.setEnabled(true);
                                }
                            }
                        });
                        titleBar.add(closeButton, BorderLayout.EAST);

                        mainFrame.add(titleBar, BorderLayout.NORTH);

//All Panels---------------------------------------------------------------------------------------------------------------------


                        JPanel pln = new JPanel();
                        CardLayout crd = new CardLayout();
                        pln.setLayout(crd);


                        // button1--------------------------------------------------------------------------------------
                        JPanel home = new JPanel();
                        home.setBackground(new Color(34, 34, 34));
                        home.setLayout(null);

                        ImageIcon img = new ImageIcon("src/Images/logo_ag180.png");
                        JLabel iconlogo = new JLabel();
                        iconlogo.setIcon(img);
                        home.add(iconlogo);
                        iconlogo.setBounds(80,60,180,180);

                        JLabel lbl = new JLabel("AnyGuard");
                        Font f = new Font("serif", Font.BOLD, 48);
                        lbl.setFont(f);
                        home.add(lbl);
                        lbl.setBounds(300,60,800,100);
                        lbl.setForeground(Color.WHITE);

                        JLabel subt = new JLabel("A n   I n n o v a t i v e   S e c u r i t y   S u i t e");
                        Font f1 = new Font("serif", Font.PLAIN, 20);
                        subt.setFont(f1);
                        subt.setBounds(300,104,800,100);
                        home.add(subt);
                        subt.setForeground(Color.WHITE);

                        JLabel author1 = new JLabel("<html><u>Atharva Barge</html>");
                        home.add(author1);
                        Font adminfont = new Font("serif", Font.PLAIN, 16);
                        author1.setForeground(new Color(0, 127, 255));
                        author1.setFont(adminfont);
                        author1.setBounds(300,130,94,100);
                        author1.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                try {
                                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/atharvasbarge/"));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });

                        JLabel author_comma = new JLabel(",");
                        home.add(author_comma);
                        author_comma.setForeground(Color.WHITE);
                        author_comma.setFont(adminfont);
                        author_comma.setBounds(394,130,10,100);

                        JLabel author2 = new JLabel("<html><u>Neha Varma</html>");
                        home.add(author2);
                        author2.setForeground(new Color(0, 127, 255));
                        author2.setFont(adminfont);
                        author2.setBounds(404,130,180,100);

                        author2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                try {
                                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/himeshfegade09"));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });

                        JLabel vertext = new JLabel("2024.1.0");
                        Font verfont = new Font("serif", Font.PLAIN, 16);
                        vertext.setFont(verfont);
                        vertext.setBounds(300,160,800,100);
                        home.add(vertext);
                        vertext.setForeground(Color.WHITE);

                        JLabel des = new JLabel("Description");
                        Font destitfont = new Font("serif", Font.BOLD, 24);
                        des.setFont(destitfont);
                        des.setBounds(100,240,800,100);
                        home.add(des);
                        des.setForeground(Color.WHITE);

                        JLabel destxt = new JLabel("<html><div style='text-align: justify;'>AnyGuard sets the standard for modern encryption solutions, offering a comprehensive suite of cutting-edge features to protect your digital assets. With its advanced capabilities and intuitive interface, AnyGuard ensures the confidentiality, integrity, and privacy of your sensitive data.<br><br>"
                                + "<b>Advanced Encryption and Decryption:</b> AnyGuard empowers users to encrypt and decrypt both text and files with ease, utilizing robust encryption algorithms to secure data during transmission and storage. Whether you're safeguarding personal documents, financial records, or confidential communications, AnyGuard provides unparalleled protection against unauthorized access.<br><br>"
                                + "<b>Steganography Integration:</b> Take security to the next level with AnyGuard's steganography functionality, allowing you to hide confidential information within images. By embedding data covertly, AnyGuard adds an extra layer of security, ensuring that your sensitive data remains hidden from prying eyes.<br><br>"
                                + "<b>Password Strength Analysis and Generation:</b> Worried about weak passwords? AnyGuard includes a comprehensive password strength checker to evaluate the robustness of your credentials, helping you create stronger and more secure passwords effortlessly. Additionally, AnyGuard's built-in password generator simplifies the process of generating complex and randomized passwords, enhancing your overall security posture.<br><br>"
                                + "<b>User-Friendly Interface:</b> AnyGuard features an intuitive and user-friendly interface, making it accessible to users of all skill levels. Whether you're a cybersecurity professional or a novice user, AnyGuard provides a seamless and hassle-free experience, allowing you to encrypt, decrypt, and manage your data with confidence.<br><br>"
                                + "<b>Protect Your Digital Assets with AnyGuard:</b> Safeguard your sensitive information and maintain control over your privacy with AnyGuard's Innovative Security suite. Trust AnyGuard to deliver unmatched security and peace of mind in an ever-evolving digital landscape. Download AnyGuard today and experience the future of encryption technology.</div></html>");
                        destxt.setVerticalAlignment(SwingConstants.TOP);
                        destxt.setForeground(Color.WHITE);
                        Font destxtfont = new Font("serif", Font.PLAIN, 16);
                        destxt.setFont(destxtfont);
                        home.add(destxt);
                        destxt.setBounds(100,310,1100,1000);

                        JButton share_app = new JButton("Share");
                        share_app.setForeground(Color.WHITE);
                        home.add(share_app);
                        share_app.setBounds(1080,130,100,30);
                        share_app.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        share_app.setBackground(new Color(51, 51, 51));
                        share_app.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                share_app.setOpaque(true);
                                share_app.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                share_app.setBackground(new Color(51, 51, 51));
                            }
                        });





                        // button2--------------------------------------------------------------------------------------
                        JPanel mainPanel = new JPanel();
                        mainPanel.setBackground(new Color(34, 34, 34));
                        mainPanel.setLayout(null);


                        JButton encryptBtn = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/new_lock.png") +
                                "'><br><h3>Encrypt File</h3></center></html>");
                        encryptBtn.setBounds(50, 60, 250, 300);
                        encryptBtn.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        encryptBtn.setBackground(new Color(51, 51, 51));
                        encryptBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                encryptBtn.setOpaque(true);
                                encryptBtn.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                encryptBtn.setBackground(new Color(51, 51, 51));
                            }
                        });
                        encryptBtn.setForeground(Color.WHITE);

                        JButton decryptBtn = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/new_unlock.png") +
                                "'><br><h3>Decrypt File</h3></center></html>");
                        decryptBtn.setBounds(350, 60, 250, 300);
                        decryptBtn.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        decryptBtn.setBackground(new Color(51, 51, 51));
                        decryptBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                decryptBtn.setOpaque(true);
                                decryptBtn.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                decryptBtn.setBackground(new Color(51, 51, 51));
                            }
                        });
                        decryptBtn.setForeground(Color.WHITE);


                        JButton usage = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/new_help.png") +
                                "'><br><h3>How to Use?</h3></center></html>");
                        usage.setBounds(650, 60, 250, 300);
                        usage.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        usage.setBackground(new Color(51, 51, 51));
                        usage.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                usage.setOpaque(true);
                                usage.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                usage.setBackground(new Color(51, 51, 51));
                            }
                        });
                        usage.setForeground(Color.WHITE);

                        mainPanel.add(encryptBtn);
                        mainPanel.add(decryptBtn);
                        mainPanel.add(usage);


//                        Encrypt File Panel
                        JPanel encryptFile = new JPanel();
                        encryptFile.setBackground(new Color(34, 34, 34));
                        encryptFile.setLayout(null);


                        JLabel encfilesec = new JLabel("Select File to Encrypt");
                        encfilesec.setBounds(200, 100, 300,25);
                        encfilesec.setForeground(Color.WHITE);
                        encryptFile.add(encfilesec);

                        JTextField directoryshow = new JTextField();
                        directoryshow.setBounds(200, 124, 700,25);
                        directoryshow.disable();
                        directoryshow.setBackground(new Color(30,31,32));
                        encryptFile.add(directoryshow);
                        directoryshow.setForeground(Color.WHITE);

                        JButton browseToEnc = new JButton("Browse");
                        browseToEnc.setBounds(900, 124, 100,25);
                        encryptFile.add(browseToEnc);
                        browseToEnc.setFocusPainted(false);

                        browseToEnc.setBackground(new Color(51, 51, 51));
                        browseToEnc.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                browseToEnc.setOpaque(true);
                                browseToEnc.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                browseToEnc.setBackground(new Color(51, 51, 51));
                            }
                        });
                        browseToEnc.setForeground(Color.WHITE);

                        browseToEnc.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser fileChooser = new JFileChooser();
                                int returnValue = fileChooser.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    temp = fileChooser.getSelectedFile().getName().toString();
                                    directoryshow.setText(selectedFile.getAbsolutePath());
                                }
                            }
                        });




                        JLabel encfilesave = new JLabel("Save Encrypted File to:");
                        encfilesave.setBounds(200, 225, 300,25);
                        encfilesave.setForeground(Color.WHITE);
                        encryptFile.add(encfilesave);

                        JTextField directoryshowsave = new JTextField();
                        directoryshowsave.setBounds(200, 250, 700,25);
                        directoryshowsave.disable();
                        directoryshowsave.setBackground(new Color(30,31,32));
                        encryptFile.add(directoryshowsave);
                        directoryshowsave.setForeground(Color.WHITE);

                        JButton EncSave = new JButton("Browse");
                        EncSave.setBounds(900, 250, 100,25);
                        encryptFile.add(EncSave);
                        EncSave.setFocusPainted(false);

                        EncSave.setBackground(new Color(51, 51, 51));
                        EncSave.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                EncSave.setOpaque(true);
                                EncSave.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                EncSave.setBackground(new Color(51, 51, 51));
                            }
                        });
                        EncSave.setForeground(Color.WHITE);


                        EncSave.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser fileChooser = new JFileChooser();
                                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                int returnValue = fileChooser.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    directoryshowsave.setText(selectedFile.getAbsolutePath());
                                }
                            }
                        });
//                        ------------------------------------------------------------------------------------------

                        JLabel encpasstxt = new JLabel("Enter Your Password:");
                        encryptFile.add(encpasstxt);
                        encpasstxt.setForeground(Color.WHITE);
                        encpasstxt.setBounds(544, 342, 200,20);

                        JTextField encPass = new JTextField();
                        encryptFile.add(encPass);
                        encPass.setBounds(460, 364, 300,25);
                        encPass.setBackground(new Color(30,31,32));
                        encPass.setForeground(Color.WHITE);

                        JButton encbtn = new JButton("Encrypt");
                        encryptFile.add(encbtn);
                        encbtn.setBounds(552, 400, 100,25);

                        encbtn.setFocusPainted(false);

                        encbtn.setBackground(new Color(51, 51, 51));
                        encbtn.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                encbtn.setOpaque(true);
                                encbtn.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                encbtn.setBackground(new Color(51, 51, 51));
                            }
                        });
                        encbtn.setForeground(Color.WHITE);

                        encbtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String gettingPass = encPass.getText();
                                String fandd = directoryshowsave.getText() + "/" + temp;
                                String encryptionKey = String.valueOf(gettingPass);



                                try {
                                    byte[] key = new byte[16]; // 128-bit key (16 bytes)
                                    System.arraycopy(encryptionKey.getBytes("UTF-8"), 0, key, 0, Math.min(key.length, encryptionKey.getBytes("UTF-8").length));
                                    SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                                    Cipher cipher = Cipher.getInstance("AES");
                                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

                                    File file = new File(directoryshow.getText());

                                    FileInputStream fileInputStream = new FileInputStream(file);
                                    byte[] inputBytes = new byte[(int) file.length()];
                                    fileInputStream.read(inputBytes);
                                    fileInputStream.close();

                                    // Encrypt the file content
                                    byte[] outputBytes = cipher.doFinal(inputBytes);
                                    file = new File(fandd);
                                    // Write the encrypted content back to the same file
                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                    fileOutputStream.write(outputBytes);
                                    fileOutputStream.close();

                                    Lock.showme("Encrypted File Sucessfully");

                                } catch (Exception exp){
                                    Lock.showme("File Encryption Failed !!");
                                    System.out.println(exp);
                                }
                            }
                        });



                        JLabel noteenc = new JLabel("Note:");
                        Font fonty = new Font("serif", Font.BOLD, 20);
                        noteenc.setForeground(Color.WHITE);
                        noteenc.setFont(fonty);
                        encryptFile.add(noteenc);
                        noteenc.setBounds(200,500,100,25);
                        JLabel noteencContent = new JLabel("The Content");
                        encryptFile.add(noteencContent);
                        encryptFile.setForeground(Color.WHITE);
                        noteencContent.setBounds(200,530,100,25);


//                        ---------------------------------------------------------------------------------------------












                        JPanel decryptFile = new JPanel();
                        decryptFile.setBackground(new Color(34, 34, 34));
                        decryptFile.setLayout(null);


                        JLabel decfilesec = new JLabel("Select File to Decrypt");
                        decfilesec.setBounds(200, 100, 300,25);
                        decfilesec.setForeground(Color.WHITE);
                        decryptFile.add(decfilesec);

                        JTextField directoryshowdec = new JTextField();
                        directoryshowdec.setBounds(200, 124, 700,25);
                        directoryshowdec.disable();
                        directoryshowdec.setBackground(new Color(30,31,32));
                        decryptFile.add(directoryshowdec);
                        directoryshowdec.setForeground(Color.WHITE);

                        JButton browseToDec = new JButton("Browse");
                        browseToDec.setBounds(900, 124, 100,25);
                        decryptFile.add(browseToDec);
                        browseToDec.setFocusPainted(false);

                        browseToDec.setBackground(new Color(51, 51, 51));
                        browseToDec.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                browseToDec.setOpaque(true);
                                browseToDec.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                browseToDec.setBackground(new Color(51, 51, 51));
                            }
                        });
                        browseToDec.setForeground(Color.WHITE);

                        browseToDec.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser fileChooser = new JFileChooser();
                                int returnValue = fileChooser.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    temp = fileChooser.getSelectedFile().getName().toString();
                                    directoryshowdec.setText(selectedFile.getAbsolutePath());
                                }
                            }
                        });




                        JLabel decfilesave = new JLabel("Save Decrypted File to:");
                        decfilesave.setBounds(200, 225, 300,25);
                        decfilesave.setForeground(Color.WHITE);
                        decryptFile.add(decfilesave);

                        JTextField directoryshowsavedec = new JTextField();
                        directoryshowsavedec.setBounds(200, 250, 700,25);
                        directoryshowsavedec.disable();
                        directoryshowsavedec.setBackground(new Color(30,31,32));
                        decryptFile.add(directoryshowsavedec);
                        directoryshowsavedec.setForeground(Color.WHITE);

                        JButton DecSave = new JButton("Browse");
                        DecSave.setBounds(900, 250, 100,25);
                        decryptFile.add(DecSave);
                        DecSave.setFocusPainted(false);

                        DecSave.setBackground(new Color(51, 51, 51));
                        DecSave.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                DecSave.setOpaque(true);
                                DecSave.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                DecSave.setBackground(new Color(51, 51, 51));
                            }
                        });
                        DecSave.setForeground(Color.WHITE);


                        DecSave.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser fileChooser = new JFileChooser();
                                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                int returnValue = fileChooser.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    directoryshowsavedec.setText(selectedFile.getAbsolutePath());
                                }
                            }
                        });
//                        ------------------------------------------------------------------------------------------

                        JLabel decpasstxt = new JLabel("Enter Your Password:");
                        decryptFile.add(decpasstxt);
                        decpasstxt.setForeground(Color.WHITE);
                        decpasstxt.setBounds(544, 342, 200,20);

                        JTextField decPass = new JTextField();
                        decryptFile.add(decPass);
                        decPass.setBounds(460, 364, 300,25);
                        decPass.setBackground(new Color(30,31,32));
                        decPass.setForeground(Color.WHITE);

                        JButton decbtn = new JButton("Decrypt");
                        decryptFile.add(decbtn);
                        decbtn.setBounds(552, 400, 100,25);

                        decbtn.setFocusPainted(false);

                        decbtn.setBackground(new Color(51, 51, 51));
                        decbtn.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                decbtn.setOpaque(true);
                                decbtn.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                decbtn.setBackground(new Color(51, 51, 51));
                            }
                        });
                        decbtn.setForeground(Color.WHITE);

                        decbtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String gettingPass = decPass.getText(); // Assuming you have a JTextField named encPass for password input
                                String fandd = directoryshowsavedec.getText() + "\\fr" + temp; // Assuming directoryshowsave is a JTextField showing the save directory
                                String encryptionKey = String.valueOf(gettingPass);

                                try {
                                    byte[] key = new byte[16]; // 128-bit key (16 bytes)
                                    System.arraycopy(encryptionKey.getBytes("UTF-8"), 0, key, 0, Math.min(key.length, encryptionKey.getBytes("UTF-8").length));
                                    SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                                    Cipher cipher = Cipher.getInstance("AES");
                                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

                                    File file = new File(directoryshowdec.getText()); // Assuming fandd contains the path to the encrypted file

                                    FileInputStream fileInputStream = new FileInputStream(file);
                                    byte[] inputBytes = new byte[(int) file.length()];
                                    fileInputStream.read(inputBytes);
                                    fileInputStream.close();

                                    // Decrypt the file content
                                    byte[] outputBytes = cipher.doFinal(inputBytes);
                                    file = new File(fandd); // Assuming directoryshow contains the path to the original file
//                                    System.out.println(fandd);


                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                    fileOutputStream.write(outputBytes);
                                    fileOutputStream.close();
                                    Lock.showme("Decryption File Sucessfully");

                                } catch (Exception exp){
                                    System.out.println(exp);
                                    Lock.showme("Decryption Failed !!");
                                }
                            }
                        });



                        JLabel notedec = new JLabel("Note:");
                        Font fontydec = new Font("serif", Font.BOLD, 20);
                        notedec.setForeground(Color.WHITE);
                        notedec.setFont(fontydec);
                        decryptFile.add(notedec);
                        notedec.setBounds(200,500,100,25);


                        JLabel notedecContent = new JLabel("The Content");
                        decryptFile.add(notedecContent);
                        notedecContent.setForeground(Color.WHITE);
                        notedecContent.setBounds(200,530,100,25);











                        //TextEncryption Main Page----------------------------------------------------------------------

                        JPanel textEncMainPage = new JPanel();
                        textEncMainPage.setBackground(new Color(34, 34, 34));
                        textEncMainPage.setLayout(null);


                        JButton encText = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/lock_filee.png") +
                                "'><br><h3>Encrypt Text</h3></center></html>");
                        encText.setBounds(50, 60, 250, 300);
                        encText.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        encText.setBackground(new Color(51, 51, 51));
                        encText.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                encText.setOpaque(true);
                                encText.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                encText.setBackground(new Color(51, 51, 51));
                            }
                        });
                        encText.setForeground(Color.WHITE);

                        JButton decText = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/unlock_filee.png") +
                                "'><br><h3>Decrypt Text</h3></center></html>");
                        decText.setBounds(350, 60, 250, 300);
                        encText.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        decText.setBackground(new Color(51, 51, 51));
                        decText.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                decText.setOpaque(true);
                                decText.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                decText.setBackground(new Color(51, 51, 51));
                            }
                        });
                        decText.setForeground(Color.WHITE);


                        JButton usage1 = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/new_help.png") +
                                "'><br><h3>How to Use?</h3></center></html>");
                        usage1.setBounds(650, 60, 250, 300);
                        usage1.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        usage1.setBackground(new Color(51, 51, 51));
                        usage1.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                usage1.setOpaque(true);
                                usage1.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                usage1.setBackground(new Color(51, 51, 51));
                            }
                        });

                        usage1.setForeground(Color.WHITE);

                        textEncMainPage.add(encText);
                        textEncMainPage.add(decText);
                        textEncMainPage.add(usage1);


                        //Decrypt Text----------------------------------------------------------------------------------
                        JPanel textDec = new JPanel();
                        textDec.setBackground(new Color(34, 34, 34));
                        textDec.setLayout(null);

                        JLabel lbl11 = new JLabel("Input Encrypted Text");
                        textDec.add(lbl11);
                        lbl11.setBounds(100, 70, 200, 20);
                        lbl11.setForeground(Color.WHITE);

                        JTextArea input1 = new JTextArea();
                        input1.setBackground(new Color(30, 31, 32));
                        input1.setForeground(Color.WHITE);
                        textDec.add(input1);
                        input1.setLineWrap(true);
                        JScrollPane jScrollPane1 = new JScrollPane(input1);
                        textDec.add(jScrollPane1);
                        jScrollPane1.setBounds(100, 100, 1000, 200);

                        JLabel Passlbl1 = new JLabel("Input Your Password");
                        textDec.add(Passlbl1);
                        Passlbl1.setBounds(450, 320, 200, 20);
                        Passlbl1.setForeground(Color.WHITE);

                        JTextField passField1 = new JTextField();
                        passField1.setBounds(450, 350, 300, 25);
                        textDec.add(passField1);

                        JButton decTextBtn1 = new JButton("Decrypt Text");
                        decTextBtn1.setBounds(500, 390, 200, 30);
                        textDec.add(decTextBtn1);

                        JTextArea output1 = new JTextArea();
                        output1.setEditable(false); // Ensure output is not editable
                        textDec.add(output1);
                        output1.setLineWrap(true);
                        JScrollPane jScrollPane11 = new JScrollPane(output1);
                        textDec.add(jScrollPane11);
                        jScrollPane11.setBounds(100, 500, 1000, 200);


                        decTextBtn1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String encryptedText = input1.getText();
                                    String key = passField1.getText().toString() + "hack_404"; // Replace this with your secret key
                                    Cipher cipher = Cipher.getInstance("AES");
                                    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
                                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                                    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
                                    String decryptedText = new String(decryptedBytes);
                                    output1.setText(decryptedText);
                                    Lock.showme("Text Encrypted Sucessfully");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    Lock.showme("Text Decryption Failed");
                                }
                            }
                        });

                        JLabel lbl21 = new JLabel("Decrypted Text");
                        textDec.add(lbl21);
                        lbl21.setBounds(100, 470, 100, 20);
                        lbl21.setForeground(Color.WHITE);


//                        decTextBtn1.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                try {
//                                    String encryptedText = output1.getText();
//                                    String key = new String(passField1.getText()); // Retrieve password as string
//
//                                    // Check if the key length is at least 8 characters
//                                    if (key.length() < 8) {
//                                        JOptionPane.showMessageDialog(textDec, "Password should be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
//                                        return; // Exit method if key is too short
//                                    }
//
//                                    // Derive a 128-bit key using PBKDF2
//                                    byte[] salt = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07}; // Example salt
//                                    int iterations = 10000; // Example iteration count
//                                    int keyLength = 128; // Desired key length in bits
//
//                                    SecretKeySpec secretKeySpec;
//                                    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//                                    KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, iterations, keyLength);
//                                    SecretKey tmp = factory.generateSecret(spec);
//                                    secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//                                    Cipher cipher = Cipher.getInstance("AES");
//                                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//
//                                    byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
//                                    String decryptedText = new String(decrypted);
//                                    output1.setText(decryptedText);
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    JOptionPane.showMessageDialog(textDec, "Error decrypting text: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                                }
//                            }
//                        });



                        JButton cpyBtn1 = new JButton("Copy Text");
                        cpyBtn1.setBounds(500,720,200,30);
                        textDec.add(cpyBtn1);

                        cpyBtn1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String textd = output1.getText();

                                if (!textd.isEmpty()) {
                                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                    StringSelection selection = new StringSelection(textd);
                                    clipboard.setContents(selection, null);
                                    JOptionPane.showMessageDialog(textDec, "Text copied to clipboard!");
                                } else {
                                    JOptionPane.showMessageDialog(textDec, "No text to copy!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });


                        // Encrypt Text---------------------------------------------------------------------------------
                        JPanel textEnc = new JPanel();
                        textEnc.setBackground(new Color(34, 34, 34));
                        textEnc.setLayout(null);

                        JLabel lbl1 = new JLabel("Input Your Text");
                        textEnc.add(lbl1);
                        lbl1.setBounds(100, 70, 100, 20);
                        lbl1.setForeground(Color.WHITE);

                        JTextArea input = new JTextArea();
                        textEnc.add(input);
                        input.setLineWrap(true);
                        JScrollPane jScrollPane = new JScrollPane(input);
                        textEnc.add(jScrollPane);
                        jScrollPane.setBounds(100, 100, 1000, 200);

                        JLabel Passlbl = new JLabel("Input Your Password (at least 8 characters)");
                        textEnc.add(Passlbl);
                        Passlbl.setBounds(450, 320, 300, 20);
                        Passlbl.setForeground(Color.WHITE);

                        JPasswordField passField = new JPasswordField();
                        passField.setBounds(450, 350, 300, 25);
                        textEnc.add(passField);

                        JButton encTextBtn = new JButton("Encrypt Text");
                        encTextBtn.setBounds(500, 390, 200, 30);
                        textEnc.add(encTextBtn);

                        JTextArea output = new JTextArea();
                        output.setEditable(false); // Ensure output is not editable
                        textEnc.add(output);
                        output.setLineWrap(true);
                        JScrollPane jScrollPane111 = new JScrollPane(output);
                        textEnc.add(jScrollPane111);
                        jScrollPane111.setBounds(100, 500, 1000, 200);


                        encTextBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String inputText = input.getText();
                                    String key = passField.getText().toString() + "hack_404"; // Replace this with your secret key
                                    Cipher cipher = Cipher.getInstance("AES");
                                    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
                                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                                    byte[] encryptedBytes = cipher.doFinal(inputText.getBytes());
                                    String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
                                    output.setText(encryptedText);
                                    Lock.showme("Text Encrypted Sucessfully");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    Lock.showme("Text Encryption Failed !!");
                                }
                            }
                        });

                        JLabel lbl2 = new JLabel("Encrypted Text");
                        textEnc.add(lbl2);
                        lbl2.setBounds(100, 470, 100, 20);
                        lbl2.setForeground(Color.WHITE);



//                        encTextBtn.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                try {
//                                    String text = input.getText();
//                                    String key = new String(passField.getPassword()); // Retrieve password as string
//
//                                    // Check if the key length is at least 8 characters
//                                    if (key.length() < 8) {
//                                        JOptionPane.showMessageDialog(textEnc, "Password should be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
//                                        return; // Exit method if key is too short
//                                    }
//
//                                    // Derive a 128-bit key using PBKDF2
//                                    byte[] salt = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07}; // Example salt
//                                    int iterations = 10000; // Example iteration count
//                                    int keyLength = 128; // Desired key length in bits
//
//                                    SecretKeySpec secretKeySpec;
//                                    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//                                    KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, iterations, keyLength);
//                                    SecretKey tmp = factory.generateSecret(spec);
//                                    secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//                                    Cipher cipher = Cipher.getInstance("AES");
//                                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//
//                                    byte[] encrypted = cipher.doFinal(text.getBytes());
//                                    output.setText(new String(encrypted));
//                                } catch (Exception ex) {
//                                    ex.printStackTrace();
//                                    JOptionPane.showMessageDialog(textEnc, "Error encrypting text: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                                }
//                            }
//                        });


                        JButton cpyBtn = new JButton("Copy Text");
                        cpyBtn.setBounds(500,720,200,30);
                        textEnc.add(cpyBtn);
                        cpyBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String textd = output.getText();

                                if (!textd.isEmpty()) {
                                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                    StringSelection selection = new StringSelection(textd);
                                    clipboard.setContents(selection, null);
                                    JOptionPane.showMessageDialog(textEnc, "Text copied to clipboard!");
                                } else {
                                    JOptionPane.showMessageDialog(textEnc, "No text to copy!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });



                        encryptBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "encryptFile");
                            }
                        });


                        decryptBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "decryptFile");
                            }
                        });


                        // button4--------------------------------------------------------------------------------------
                        JPanel dataHidingPanel = new JPanel();
                        dataHidingPanel.setBackground(new Color(34, 34, 34));
                        dataHidingPanel.setLayout(null);


                        JButton hide = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/hide_data.png") +
                                "'><br><h3>Hide Data</h3></center></html>");
                        hide.setBounds(50, 60, 250, 300);
                        hide.setFocusPainted(false);
//                        hide.setContentAreaFilled(false);  // Make the button transparent
                        hide.setBackground(new Color(51, 51, 51));
                        hide.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                hide.setOpaque(true);
                                hide.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                hide.setBackground(new Color(51, 51, 51));
                            }
                        });
                        hide.setForeground(Color.WHITE);

                        JButton unhide = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/unhide_data.png") +
                                "'><br><h3>Disclose Data</h3></center></html>");
                        unhide.setBounds(350, 60, 250, 300);
                        unhide.setFocusPainted(false);
//                        unhide.setContentAreaFilled(false);  // Make the button transparent
                        unhide.setBackground(new Color(51, 51, 51));
                        unhide.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                unhide.setOpaque(true);
                                unhide.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                unhide.setOpaque(false);
                                unhide.setBackground(new Color(51, 51, 51));
                            }
                        });
                        unhide.setForeground(Color.WHITE);



                        JPanel hideData = new JPanel();
                        hideData.setBackground(new Color(34, 34, 34));
                        hideData.setLayout(null);

                        JPanel imgPreview = new JPanel();
                        imgPreview.setBounds(340,60,600,300);
                        imgPreview.setBackground(new Color(30,31,32));
                        imgPreview.setBorder(BorderFactory.createLineBorder(Color.WHITE));

                        JLabel imgic = new JLabel();
                        imgPreview.add(imgic);

                        hideData.add(imgPreview);

                        JLabel prvtxt = new JLabel("Preview Panel");
                        hideData.add(prvtxt);
                        prvtxt.setForeground(Color.WHITE);
                        prvtxt.setBounds(340,370,100,25);

                        JButton hidbrowse = new JButton("Browse");
                        hidbrowse.setBounds(840,370,100,25);
                        hideData.add(hidbrowse);

                        hidbrowse.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser hifc = new JFileChooser();
                                int returnValue = hifc.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    selfile = hifc.getSelectedFile();
                                    try {
                                        image = ImageIO.read(selfile);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }

                                    temp = hifc.getSelectedFile().getName();
                                    String str = selfile.getAbsolutePath();
                                    imgtodis = new ImageIcon(str);

//                                     Set the image to be completely fitted in the panel and centered
                                    int panelWidth = imgPreview.getWidth();
                                    int panelHeight = imgPreview.getHeight();

                                    // Get the dimensions of the image
                                    int imgWidth = imgtodis.getIconWidth();
                                    int imgHeight = imgtodis.getIconHeight();

                                    // Calculate the scale to fit the image into the panel
                                    double scale = Math.min((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);

                                    // Calculate the new dimensions for the scaled image
                                    int newImgWidth = (int) (imgWidth * scale);
                                    int newImgHeight = (int) (imgHeight * scale);

                                    // Calculate the position to center the image within the panel
                                    int x = (panelWidth - newImgWidth) / 2;
                                    int y = (panelHeight - newImgHeight) / 2;

                                    // Set the scaled image and its position
                                    imgic.setIcon(new ImageIcon(imgtodis.getImage().getScaledInstance(newImgWidth, newImgHeight, Image.SCALE_SMOOTH)));
                                    imgic.setBounds(x, y, newImgWidth, newImgHeight);

                                }
                            }
                        });


                        hidbrowse.setBackground(new Color(51, 51, 51));
                        hidbrowse.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                hidbrowse.setOpaque(true);
                                hidbrowse.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                hidbrowse.setBackground(new Color(51, 51, 51));
                            }
                        });
                        hidbrowse.setForeground(Color.WHITE);

//////////////////////////str

                        JLabel inphidtxt = new JLabel("Enter text to hide:");
                        inphidtxt.setBounds(390,420,120,25);
                        inphidtxt.setForeground(Color.WHITE);
                        hideData.add(inphidtxt);

                        JTextArea inphid = new JTextArea();
                        inphid.setBounds(390,448,500,120);
                        inphid.setLineWrap(true);
                        hideData.add(inphid);

                        inputdataToEnc = inphid.getText();

                        JLabel charcounter = new JLabel("0");
                        charcounter.setBounds(840,520,500,120);
                        charcounter.setForeground(Color.WHITE);
//                        charcounter.setAlignmentX(SwingConstants.LEFT);
//                        charcounter.setHorizontalAlignment(SwingConstants.LEFT);
                        hideData.add(charcounter);

                        ((AbstractDocument) inphid.getDocument()).setDocumentFilter(new DocumentFilter() {
                            int maxCharacters = 500;

                            @Override
                            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                                int currentLength = fb.getDocument().getLength();
                                int leftover = maxCharacters - currentLength + length;
                                if (leftover > 0) {
                                    int replaceLength = Math.min(leftover, text.length());
                                    super.replace(fb, offset, length, text.substring(0, replaceLength), attrs);
                                }
                            }
                        });


                        inphid.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                updateCount();
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                updateCount();
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {
                                updateCount();
                            }

                            private void updateCount() {
                                String text = inphid.getText();
                                int count = text.length();
                                charcounter.setText(Integer.toString(count
                                ));
                            }
                        });


                        JLabel charcnttxt = new JLabel("/500");
                        charcnttxt.setBounds(860,520,500,120);
                        charcnttxt.setForeground(Color.WHITE);
                        hideData.add(charcnttxt);


                        JLabel hidsaveto = new JLabel("Save picture to");
                        hidsaveto.setBounds(250, 620, 300,25);
                        hidsaveto.setForeground(Color.WHITE);
                        hideData.add(hidsaveto);


                        JTextField hiddir = new JTextField();
                        File hoame = FileSystemView.getFileSystemView().getHomeDirectory();
                        String desktoppath = hoame.getAbsolutePath() + "\\AnyGuard";
                        hiddir.setText(desktoppath);
                        hiddir.setForeground(Color.WHITE);
                        hiddir.setBounds(250, 648, 700,25);
                        hiddir.disable();
                        hiddir.setBackground(new Color(30,31,32));
                        hideData.add(hiddir);
                        hiddir.setForeground(Color.WHITE);

                        JButton browseTohid = new JButton("Browse");
                        browseTohid.setBounds(950, 648, 100,25);
                        hideData.add(browseTohid);
                        browseTohid.setFocusPainted(false);

                        browseTohid.setBackground(new Color(51, 51, 51));
                        browseTohid.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                browseTohid.setOpaque(true);
                                browseTohid.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                browseTohid.setBackground(new Color(51, 51, 51));
                            }
                        });
                        browseTohid.setForeground(Color.WHITE);


                        browseTohid.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser jfc = new JFileChooser();
                                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                int returnValue = jfc.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    selectedFile = jfc.getSelectedFile();
                                    hiddir.setText(selectedFile.toString() + "\\" + temp);
                                    File fn = new File(hiddir.getText().toString());
                                    if(!fn.exists()){
                                        try {
                                            fn.createNewFile();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                            }
                        });


                        JButton hided = new JButton("Hide Data");
                        hided.setBounds(600,700,100,25);
                        hideData.add(hided);

                        hided.setBackground(new Color(51, 51, 51));
                        hided.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                hided.setOpaque(true);
                                hided.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                hided.setBackground(new Color(51, 51, 51));
                            }
                        });
                        hided.setForeground(Color.WHITE);

                        hided.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String imagePath = selfile.getAbsolutePath();
                                String textToHide = inphid.getText().toString();
                                String outputImagePath = selectedFile.toString() + File.separator + temp; // Output file path

                                try {
                                    // Load the original image
                                    BufferedImage originalImage = ImageIO.read(new File(imagePath));
                                    int width = originalImage.getWidth();
                                    int height = originalImage.getHeight();

                                    // Convert text to binary
                                    byte[] textBytes = textToHide.getBytes();
                                    StringBuilder binaryStringBuilder = new StringBuilder();
                                    for (byte b : textBytes) {
                                        binaryStringBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
                                    }
                                    String binaryText = binaryStringBuilder.toString();
                                    System.out.println(binaryText);
                                    // Embed text in image
                                    int textLength = binaryText.length();
                                    int textIndex = 0;
                                    for (int y = 0; y < height; y++) {
                                        for (int x = 0; x < width; x++) {
                                            int pixel = originalImage.getRGB(x, y);
                                            if (textIndex < textLength) {
                                                int bit = (binaryText.charAt(textIndex) == '1') ? 1 : 0;
                                                int newPixel = (pixel & 0xFFFFFFFE) | bit; // Modify LSB
                                                originalImage.setRGB(x, y, newPixel);
                                                textIndex++;
                                            }
                                        }
                                    }

                                    // Save the modified image
                                    File outputFile = new File(outputImagePath);
                                    ImageIO.write(originalImage, "png", outputFile);
                                    Lock.showme("Text hidden successfully in the image. Output file: " + outputImagePath);
                                } catch (IOException exp) {
                                    Lock.showme("Error: " + exp.getMessage());
                                    exp.printStackTrace(); // Print full stack trace for debugging
                                }
                            }
                        });



//                        hided.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                String imagePath = selfile.getAbsolutePath();
//                                String textToHide = inputdataToEnc;
//                                String outputImagePath = selectedFile.toString();
//
//                                try {
//                                    // Load the original image
//                                    BufferedImage originalImage = ImageIO.read(new File(imagePath));
//                                    int width = originalImage.getWidth();
//                                    int height = originalImage.getHeight();
//
//                                    // Convert text to binary
//                                    byte[] textBytes = textToHide.getBytes();
//                                    StringBuilder binaryStringBuilder = new StringBuilder();
//                                    for (byte b : textBytes) {
//                                        binaryStringBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//                                    }
//                                    String binaryText = binaryStringBuilder.toString();
//
//                                    // Embed text in image
//                                    int textLength = binaryText.length();
//                                    int textIndex = 0;
//                                    for (int y = 0; y < height; y++) {
//                                        for (int x = 0; x < width; x++) {
//                                            int pixel = originalImage.getRGB(x, y);
//                                            if (textIndex < textLength) {
//                                                int bit = (binaryText.charAt(textIndex) == '1') ? 1 : 0;
//                                                int newPixel = (pixel & 0xFFFFFFFE) | bit; // Modify LSB
//                                                originalImage.setRGB(x, y, newPixel);
//                                                textIndex++;
//                                            }
//                                        }
//                                    }
//
//                                    // Save the modified image
//                                    File outputFile = new File(outputImagePath);
//                                    ImageIO.write(originalImage, "png", outputFile);
//                                    System.out.println("Text hidden successfully in the image.");
//                                } catch (IOException exp) {
//                                    System.out.println("Error: " + exp.getMessage());
//                                }
//
//                            }
//                        });

//                        hided.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                int messageLength = inphid.getText().length();
//
//                                int imageWidth =  image.getWidth(), imageHeight = image.getHeight(),
//                                        imageSize = imageWidth * imageHeight;
//                                if(messageLength * 8 + 32 > imageSize) {
//                                    JOptionPane.showMessageDialog(hideData, "Message is too long for the chosen image",
//                                            "Message too long!", JOptionPane.ERROR_MESSAGE);
//                                    return;
//                                }
//                                embedInteger(image, messageLength, 0, 0);
//
//                                byte b[] = inphid.getText().getBytes();
//                                for(int i=0; i<b.length; i++)
//                                    embedByte(image, b[i], i*8+32, 0);
//                            }
//                        });





                        JPanel discloseData = new JPanel();
                        discloseData.setBackground(new Color(34, 34, 34));
                        discloseData.setLayout(null);

                        JPanel imgPreviewdec = new JPanel();
                        imgPreviewdec.setBounds(340,60,600,300);
                        imgPreviewdec.setBackground(new Color(30,31,32));
                        imgPreviewdec.setBorder(BorderFactory.createLineBorder(Color.WHITE));

                        JLabel imgicdec = new JLabel();
                        imgPreviewdec.add(imgicdec);

                        discloseData.add(imgPreviewdec);

                        JLabel prvtxtdec = new JLabel("Preview Panel");
                        discloseData.add(prvtxtdec);
                        prvtxtdec.setForeground(Color.WHITE);
                        prvtxtdec.setBounds(340,370,100,25);

                        JButton hidbrowsedec = new JButton("Browse");
                        hidbrowsedec.setBounds(840,370,100,25);
                        discloseData.add(hidbrowsedec);
                        hidbrowsedec.setBackground(new Color(51, 51, 51));
                        hidbrowsedec.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                hidbrowsedec.setOpaque(true);
                                hidbrowsedec.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                hidbrowsedec.setBackground(new Color(51, 51, 51));
                            }
                        });
                        hidbrowsedec.setForeground(Color.WHITE);

                        hidbrowsedec.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser hifcdec = new JFileChooser();
                                int returnValuedec = hifcdec.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValuedec == JFileChooser.APPROVE_OPTION) {
                                    selfile = hifcdec.getSelectedFile();
                                    try {
                                        image = ImageIO.read(selfile);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }

                                    temp = hifcdec.getSelectedFile().getName();
                                    String strdec = selfile.getAbsolutePath();
                                    imgtodis = new ImageIcon(strdec);

//                                     Set the image to be completely fitted in the panel and centered
                                    int panelWidthdec = imgPreviewdec.getWidth();
                                    int panelHeightdec = imgPreviewdec.getHeight();

                                    // Get the dimensions of the image
                                    int imgWidthdec = imgtodis.getIconWidth();
                                    int imgHeightdec = imgtodis.getIconHeight();

                                    // Calculate the scale to fit the image into the panel
                                    double scaledec = Math.min((double) panelWidthdec / imgWidthdec, (double) panelHeightdec / imgHeightdec);

                                    // Calculate the new dimensions for the scaled image
                                    int newImgWidthdec = (int) (imgWidthdec * scaledec);
                                    int newImgHeightdec = (int) (imgHeightdec * scaledec);

                                    // Calculate the position to center the image within the panel
                                    int xdec = (panelWidthdec - newImgWidthdec) / 2;
                                    int ydec = (panelHeightdec - newImgHeightdec) / 2;

                                    // Set the scaled image and its position
                                    imgicdec.setIcon(new ImageIcon(imgtodis.getImage().getScaledInstance(newImgWidthdec, newImgHeightdec, Image.SCALE_SMOOTH)));
                                    imgicdec.setBounds(xdec, ydec, newImgWidthdec, newImgHeightdec);

                                }
                            }
                        });

                        JLabel inphidtxtdec = new JLabel("Enter text to hide:");
                        inphidtxtdec.setBounds(390,420,120,25);
                        inphidtxtdec.setForeground(Color.WHITE);
                        discloseData.add(inphidtxtdec);

                        JTextArea inphiddec = new JTextArea();
                        inphiddec.setBounds(390,448,500,120);
                        inphiddec.setLineWrap(true);
                        discloseData.add(inphiddec);

                        String inputdataToDec = inphiddec.getText();

                        JLabel charcounterdec = new JLabel("0");
                        charcounterdec.setBounds(840,520,500,120);
                        charcounterdec.setForeground(Color.WHITE);
//                        charcounterdec.setAlignmentX(SwingConstants.LEFT);
//                        charcounterdec.setHorizontalAlignment(SwingConstants.LEFT);
                        discloseData.add(charcounterdec);

                        ((AbstractDocument) inphiddec.getDocument()).setDocumentFilter(new DocumentFilter() {
                            int maxCharactersdec = 500;

                            @Override
                            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                                int currentLengthdec = fb.getDocument().getLength();
                                int leftoverdec = maxCharactersdec - currentLengthdec + length;
                                if (leftoverdec > 0) {
                                    int replaceLengthdec = Math.min(leftoverdec, text.length());
                                    super.replace(fb, offset, length, text.substring(0, replaceLengthdec), attrs);
                                }
                            }
                        });

                        inphiddec.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                updateCount();
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                updateCount();
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {
                                updateCount();
                            }

                            private void updateCount() {
                                String textdec = inphiddec.getText();
                                int countdec = textdec.length();
                                charcounterdec.setText(Integer.toString(countdec));
                            }
                        });

                        JLabel charcnttxtdec = new JLabel("/500");
                        charcnttxtdec.setBounds(860,520,500,120);
                        charcnttxtdec.setForeground(Color.WHITE);
                        discloseData.add(charcnttxtdec);


                        JLabel hidsavetodec = new JLabel("Save picture to");
                        hidsavetodec.setBounds(250, 620, 300,25);
                        hidsavetodec.setForeground(Color.WHITE);
                        discloseData.add(hidsavetodec);


                        JTextField hiddirdec = new JTextField();
                        File hoamedec = FileSystemView.getFileSystemView().getHomeDirectory();
                        String desktoppathdec = hoamedec.getAbsolutePath() + "\\AnyGuard";
                        hiddirdec.setText(desktoppathdec);
                        hiddirdec.setForeground(Color.WHITE);
                        hiddirdec.setBounds(250, 648, 700,25);
                        hiddirdec.disable();
                        hiddirdec.setBackground(new Color(30,31,32));
                        discloseData.add(hiddirdec);
                        hiddirdec.setForeground(Color.WHITE);


                        JButton browseTohiddec = new JButton("Browse");
                        browseTohiddec.setBounds(950, 648, 100,25);
                        discloseData.add(browseTohiddec);
                        browseTohiddec.setFocusPainted(false);

                        browseTohiddec.setBackground(new Color(51, 51, 51));
                        browseTohiddec.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                browseTohiddec.setOpaque(true);
                                browseTohiddec.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                browseTohiddec.setBackground(new Color(51, 51, 51));
                            }
                        });
                        browseTohiddec.setForeground(Color.WHITE);

                        browseTohiddec.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser jfcdec = new JFileChooser();
                                jfcdec.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                int returnValuedec = jfcdec.showOpenDialog(null); // Show the file chooser dialog
                                if (returnValuedec == JFileChooser.APPROVE_OPTION) {
                                    selectedFile = jfcdec.getCurrentDirectory();
                                    hiddirdec.setText(selectedFile.getAbsolutePath().toString() + "\\" + temp);
                                }
                            }
                        });

                        JButton hideddec = new JButton("Disclose Data");
                        hideddec.setBounds(600,700,100,25);
                        discloseData.add(hideddec);

                        // actionlistner

//                        hideddec.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                String imagePath = selfile.getAbsolutePath(); // Path of the image
//
//                                try {
//                                    // Load the image
//                                    BufferedImage image = ImageIO.read(new File(imagePath));
//                                    int width = image.getWidth();
//                                    int height = image.getHeight();
//
//                                    // Extract hidden text from the image
//                                    StringBuilder binaryTextBuilder = new StringBuilder();
//                                    int messageLength = 0;
//                                    outerLoop:
//                                    for (int y = 0; y < height; y++) {
//                                        for (int x = 0; x < width; x++) {
//                                            int pixel = image.getRGB(x, y);
//                                            int blue = (pixel) & 0xff; // Extract LSB from blue channel
//                                            binaryTextBuilder.append(blue & 1);
//                                            messageLength++;
//                                            if (messageLength >= 32) { // Check for message length of 4 bytes (32 bits)
//                                                break outerLoop;
//                                            }
//                                        }
//                                    }
//
//                                    // Convert binary text to string
//                                    String binaryText = binaryTextBuilder.toString();
//                                    StringBuilder textBuilder = new StringBuilder();
//                                    for (int i = 0; i < binaryText.length(); i += 8) {
//                                        String binaryChar = binaryText.substring(i, i + 8);
//                                        int charCode = Integer.parseInt(binaryChar, 2);
//                                        textBuilder.append((char) charCode);
//                                    }
//                                    String extractedText = textBuilder.toString();
//
//                                    // Update JTextArea with the extracted text
//                                    inphiddec.setText(extractedText);
//                                    System.out.println("Message extracted successfully.");
//                                } catch (IOException exp) {
//                                    System.out.println("Error: " + exp.getMessage());
//                                }
//                            }
//                        });




                        hideddec.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String imagePath = selfile.getAbsolutePath(); // Path of the image

                                try {
                                    // Load the image
                                    BufferedImage image = ImageIO.read(new File(imagePath));
                                    int width = image.getWidth();
                                    int height = image.getHeight();

                                    // Extract hidden text from the image
                                    StringBuilder binaryTextBuilder = new StringBuilder();
                                    StringBuilder textBuilder = new StringBuilder();
                                    boolean messageEnd = false;
                                    for (int y = 0; y < height && !messageEnd; y++) {
                                        for (int x = 0; x < width && !messageEnd; x++) {
                                            int pixel = image.getRGB(x, y);
                                            int blue = (pixel & 0xff); // Extract LSB from blue channel
                                            binaryTextBuilder.append(blue & 1);
                                            if (binaryTextBuilder.length() % 8 == 0) { // Check if we've extracted a full byte
                                                String binaryChar = binaryTextBuilder.substring(binaryTextBuilder.length() - 8);
                                                int charCode = Integer.parseInt(binaryChar, 2);
                                                if (charCode == 0) { // Check for null terminator
                                                    messageEnd = true;
                                                } else {
                                                    textBuilder.append((char) charCode);
                                                }
                                            }
                                        }
                                    }

                                    // Convert binary text to string
                                    String extractedText = textBuilder.toString();

                                    // Update JTextArea with the extracted text
                                    inphiddec.setText(extractedText);
                                    Lock.showme("Message extracted successfully.");
                                } catch (IOException exp) {
                                    Lock.showme("Error: " + exp.getMessage());
                                }
                            }
                        });










                        hideddec.setBackground(new Color(51, 51, 51));
                        hideddec.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                hideddec.setOpaque(true);
                                hideddec.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                hideddec.setBackground(new Color(51, 51, 51));
                            }
                        });
                        hideddec.setForeground(Color.WHITE);


                        hide.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "hideData");
                            }
                        });

                        unhide.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "discloseData");
                            }
                        });

                        JButton htu = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/new_help.png") +
                                "'><br><h3>How to Use?</h3></center></html>");
                        htu.setBounds(650, 60, 250, 300);
                        htu.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        htu.setBackground(new Color(51, 51, 51));
                        htu.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                htu.setOpaque(true);
                                htu.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                htu.setBackground(new Color(51, 51, 51));
                            }
                        });
                        htu.setForeground(Color.WHITE);

                        dataHidingPanel.add(hide);
                        dataHidingPanel.add(unhide);
                        dataHidingPanel.add(htu);


                        // button5--------------------------------------------------------------------------------------
                        JPanel passwords = new JPanel();
                        passwords.setBackground(new Color(34, 34, 34));
                        passwords.setLayout(null);


                        JButton pChk = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/pass_chk.png") +
                                "'><br><h3>Password Checker</h3></center></html>");
                        pChk.setBounds(50, 60, 250, 300);
                        pChk.setFocusPainted(false);
//                        hide.setContentAreaFilled(false);  // Make the button transparent
                        pChk.setBackground(new Color(51, 51, 51));
                        pChk.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                pChk.setOpaque(true);
                                pChk.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                pChk.setBackground(new Color(51, 51, 51));
                            }
                        });
                        pChk.setForeground(Color.WHITE);

                        JButton pGen = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/pass_gen.png") +
                                "'><br><h3>Password Generator</h3></center></html>");
                        pGen.setBounds(350, 60, 250, 300);
                        pGen.setFocusPainted(false);
//                        unhide.setContentAreaFilled(false);  // Make the button transparent
                        pGen.setBackground(new Color(51, 51, 51));
                        pGen.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                pGen.setOpaque(true);
                                pGen.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                unhide.setOpaque(false);
                                pGen.setBackground(new Color(51, 51, 51));
                            }
                        });
                        pGen.setForeground(Color.WHITE);

                        JButton htup = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/new_help.png") +
                                "'><br><h3>How to Use?</h3></center></html>");
                        htup.setBounds(650, 60, 250, 300);
                        htup.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        htup.setBackground(new Color(51, 51, 51));
                        htup.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                htup.setOpaque(true);
                                htup.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                htup.setBackground(new Color(51, 51, 51));
                            }
                        });
                        htup.setForeground(Color.WHITE);

                        passwords.add(pChk);
                        passwords.add(pGen);
                        passwords.add(htup);






                        JPanel vault = new JPanel();
                        vault.setBackground(new Color(34, 34, 34));
                        vault.setLayout(null);

                        JPanel addcrd = new JPanel();
                        vault.add(addcrd);
                        addcrd.setLayout(null);
                        addcrd.setBackground(new Color(34, 34, 34));

                        JPanel cc = new JPanel();
                        cc.setBounds(400,160,500,300);
                        cc.setBackground(Color.BLUE);
                        cc.setLayout(null);
                        addcrd.add(cc);

                        JLabel forimg = new JLabel();
                        ImageIcon icic = new ImageIcon("Images/chip_45.png");
                        forimg.setIcon(icic);
                        cc.add(forimg);
                        forimg.setBounds(20,40,45,45);

                        JLabel acchn = new JLabel("Account Holder Name:");
                        acchn.setBounds(20,228,300,25);
                        acchn.setForeground(Color.WHITE);
                        cc.add(acchn);

                        JTextField name_holder = new JTextField();
                        cc.add(name_holder);
                        name_holder.setBounds(20,250,300,25);

                        JTextField cardnum = new JTextField();
                        cc.add(cardnum);
                        cardnum.setBounds(20,150,400,25);

                        JTextField expday = new JTextField();
                        cc.add(expday);
                        expday.setBounds(30,190,30,25);

                        JTextField expyr = new JTextField();
                        cc.add(expyr);
                        expyr.setBounds(70,190,60,25);

                        JTextField cvv = new JTextField();
                        cc.add(cvv);
                        cvv.setBounds(180,190,60,25);

                        JComboBox<String> dropdown = new JComboBox<>();
                        dropdown.addItem("Debit Card");
                        dropdown.addItem("Credit Card");
                        dropdown.setBounds(20,20,180,25);
                        cc.add(dropdown);



                        JButton adcar = new JButton("Add Card");
                        addcrd.add(adcar);
                        adcar.setBounds(140, 600, 250, 30);
                        adcar.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        adcar.setBackground(new Color(51, 51, 51));
                        adcar.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                adcar.setOpaque(true);
                                adcar.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                adcar.setBackground(new Color(51, 51, 51));
                            }
                        });
                        adcar.setForeground(Color.WHITE);
                        adcar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                File fl = new File("c:\\Users\\Public\\Downloads\\Hack404\\src\\Data.txt");
                                try {
                                    if (!fl.exists()) {
                                        fl.createNewFile();
                                    }

                                    if (flag) {
                                        String gettingPass = "Hack404himesh";
                                        ; // Assuming directoryshowsave is a JTextField showing the save directory
                                        String encryptionKey = String.valueOf(gettingPass);

                                        try {
                                            byte[] key = new byte[16]; // 128-bit key (16 bytes)
                                            System.arraycopy(encryptionKey.getBytes("UTF-8"), 0, key, 0, Math.min(key.length, encryptionKey.getBytes("UTF-8").length));
                                            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                                            Cipher cipher = Cipher.getInstance("AES");
                                            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

                                            FileInputStream fileInputStream = new FileInputStream(fl);
                                            byte[] inputBytes = new byte[(int) fl.length()];
                                            fileInputStream.read(inputBytes);
                                            fileInputStream.close();

                                            // Decrypt the file content
                                            byte[] outputBytes = cipher.doFinal(inputBytes);
                                            fl = new File(fl.getAbsolutePath()); // Assuming directoryshow contains the path to the original file
//                                    System.out.println(fandd);


                                            FileOutputStream fileOutputStream = new FileOutputStream(fl);
                                            fileOutputStream.write(outputBytes);
                                            fileOutputStream.close();

                                        } catch (Exception exp) {
                                            System.out.println(exp);
                                        }
                                    }

                                    String str = name_holder.getText().toString() + "," + cardnum.getText().toString() + "," + cvv.getText().toString() + "," + expday.getText().toString() + "," + expyr.getText().toString() + "\n";
                                    FileWriter fr = new FileWriter(fl, true);
                                    fr.write(str);
                                    cnt++;
                                    fr.close();
                                    if (!flag) {
                                        String gettingPass1 = "Hack404himesh";
                                        String encryptionKey1 = String.valueOf(gettingPass1);

                                        try {
                                            byte[] key = new byte[16]; // 128-bit key (16 bytes)
                                            System.arraycopy(encryptionKey1.getBytes("UTF-8"), 0, key, 0, Math.min(key.length, encryptionKey1.getBytes("UTF-8").length));
                                            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                                            Cipher cipher = Cipher.getInstance("AES");
                                            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

                                            FileInputStream fileInputStream = new FileInputStream(fl);
                                            byte[] inputBytes = new byte[(int) fl.length()];
                                            fileInputStream.read(inputBytes);
                                            fileInputStream.close();

                                            // Encrypt the file content
                                            byte[] outputBytes = cipher.doFinal(inputBytes);
                                            // Write the encrypted content back to the same file
                                            FileOutputStream fileOutputStream = new FileOutputStream(fl);
                                            fileOutputStream.write(outputBytes);
                                            fileOutputStream.close();

                                        } catch (Exception exp) {
                                            System.out.println(exp);
                                        }
                                    }
                                    Lock.showme("Card Added Sucessfully");
                                }catch (Exception ex){
                                    Lock.showme("Failed Due to : " + ex.getMessage());
                                    System.out.println(ex);}
                            }
                        });


                        JButton addCard = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/pass_chk.png") +
                                "'><br><h3>Add Card</h3></center></html>");
                        addCard.setBounds(50, 60, 250, 300);
                        addCard.setFocusPainted(false);
//                        hide.setContentAreaFilled(false);  // Make the button transparent
                        addCard.setBackground(new Color(51, 51, 51));
                        addCard.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                addCard.setOpaque(true);
                                addCard.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                hide.setOpaque(false);
                                addCard.setBackground(new Color(51, 51, 51));
                            }
                        });
                        addCard.setForeground(Color.WHITE);
                        addCard.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "addcrd");
                            }
                        });




                        JButton viewCard = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/pass_gen.png") +
                                "'><br><h3>View Card</h3></center></html>");
                        viewCard.setBounds(350, 60, 250, 300);
                        viewCard.setFocusPainted(false);
//                        unhide.setContentAreaFilled(false);  // Make the button transparent
                        viewCard.setBackground(new Color(51, 51, 51));
                        viewCard.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                viewCard.setOpaque(true);
                                viewCard.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                unhide.setOpaque(false);
                                viewCard.setBackground(new Color(51, 51, 51));
                            }
                        });
                        viewCard.setForeground(Color.WHITE);



                        JPanel vwcard = new JPanel();
                        vault.add(vwcard);
                        vwcard.setBackground(new Color(34, 34, 34));
                        vwcard.setLayout(null);


                        JPanel ccview = new JPanel();
                        ccview.setBounds(400,160,500,300);
                        ccview.setBackground(Color.BLUE);
                        ccview.setLayout(null);
                        vwcard.add(ccview);

                        JLabel forimg1 = new JLabel();
                        ImageIcon icic1 = new ImageIcon("Images/chip_45.png");
                        forimg1.setIcon(icic1);
                        ccview.add(forimg1);
                        forimg1.setBounds(20,40,45,45);

                        JLabel acchn1 = new JLabel("");
                        acchn1.setBounds(20,228,300,25);
                        acchn1.setForeground(Color.WHITE);
                        ccview.add(acchn1);

                        JLabel name_holder1 = new JLabel("");
                        ccview.add(name_holder1);
                        name_holder1.setForeground(Color.WHITE);
                        name_holder1.setBounds(20,250,300,25);

                        JLabel cardnum1 = new JLabel("");
                        ccview.add(cardnum1);
                        cardnum1.setForeground(Color.WHITE);
                        cardnum1.setBounds(20,150,400,25);

                        JLabel expday1 = new JLabel("");
                        ccview.add(expday1);
                        expday1.setForeground(Color.WHITE);
                        expday1.setBounds(30,190,30,25);

                        JLabel expyr1 = new JLabel("");
                        ccview.add(expyr1);
                        expyr1.setForeground(Color.WHITE);
                        expyr1.setBounds(70,190,60,25);

                        JLabel cvv1 = new JLabel("");
                        ccview.add(cvv1);
                        cvv1.setForeground(Color.WHITE);
                        cvv1.setBounds(180,190,60,25);

                        JLabel dc = new JLabel("Debit Card");
                        dc.setBounds(20,20,180,25);
                        dc.setForeground(Color.WHITE);
                        ccview.add(dc);

                        JButton next = new JButton("Show");
                        vwcard.add(next);
                        next.setBounds(600,600,100,30);

                        JButton prev = new JButton("Previous");
                        vwcard.add(prev);
                        prev.setBounds(400,600,100,30);



                        next.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                next.setText("Next");
                                File fl = new File("c:\\Users\\Public\\Downloads\\Hack404\\src\\Data.txt");
                                try {
                                    if (!fl.exists()) {
                                        fl.createNewFile();
                                    }

                                    if (flag) {
                                        String gettingPass = "Hack404himesh";
                                        ; // Assuming directoryshowsave is a JTextField showing the save directory
                                        String encryptionKey = String.valueOf(gettingPass);

                                        try {
                                            byte[] key = new byte[16]; // 128-bit key (16 bytes)
                                            System.arraycopy(encryptionKey.getBytes("UTF-8"), 0, key, 0, Math.min(key.length, encryptionKey.getBytes("UTF-8").length));
                                            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                                            Cipher cipher = Cipher.getInstance("AES");
                                            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

                                            FileInputStream fileInputStream = new FileInputStream(fl);
                                            byte[] inputBytes = new byte[(int) fl.length()];
                                            fileInputStream.read(inputBytes);
                                            fileInputStream.close();

                                            // Decrypt the file content
                                            byte[] outputBytes = cipher.doFinal(inputBytes);
                                            fl = new File(fl.getAbsolutePath()); // Assuming directoryshow contains the path to the original file
//                                    System.out.println(fandd);


                                            FileOutputStream fileOutputStream = new FileOutputStream(fl);
                                            fileOutputStream.write(outputBytes);
                                            fileOutputStream.close();

                                        } catch (Exception exp) {
                                            System.out.println(exp);
                                        }
                                    }
                                    if(tempi <= cnt){
                                    FileReader fr;
                                    fr = new FileReader(new File("c:\\Users\\Public\\Downloads\\Hack404\\src\\Data.txt"));
                                    BufferedReader br = new BufferedReader(fr);
                                    for(int i = 0; i < tempi;i++){
                                        br.readLine();
                                    }
                                    String str = br.readLine();
                                    String str1[] = str.split(",");
                                    name_holder1.setText(str1[0]);
                                    cardnum1.setText(str1[1]);
                                    cvv1.setText(str1[2]);
                                    expday1.setText(str1[3]);
                                    expyr1.setText(str1[4]);
                                    tempi++;}
                                    else{
                                        tempi =0;
                                    }


                                    if (!flag) {
                                        String gettingPass1 = "Hack404himesh";
                                        String encryptionKey1 = String.valueOf(gettingPass1);

                                        try {
                                            byte[] key = new byte[16]; // 128-bit key (16 bytes)
                                            System.arraycopy(encryptionKey1.getBytes("UTF-8"), 0, key, 0, Math.min(key.length, encryptionKey1.getBytes("UTF-8").length));
                                            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                                            Cipher cipher = Cipher.getInstance("AES");
                                            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

                                            FileInputStream fileInputStream = new FileInputStream(fl);
                                            byte[] inputBytes = new byte[(int) fl.length()];
                                            fileInputStream.read(inputBytes);
                                            fileInputStream.close();

                                            // Encrypt the file content
                                            byte[] outputBytes = cipher.doFinal(inputBytes);
                                            // Write the encrypted content back to the same file
                                            FileOutputStream fileOutputStream = new FileOutputStream(fl);
                                            fileOutputStream.write(outputBytes);
                                            fileOutputStream.close();

                                        } catch (Exception exp) {
                                            System.out.println(exp);
                                        }
                                    }
                                    Lock.showme("Card Added Sucessfully");
                                }catch (Exception ex){
                                    Lock.showme("Failed Due to : " + ex.getMessage());
                                    System.out.println(ex);}
                            }
                       });

                        viewCard.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
//                                JFrame vali = new JFrame();
//                                vali.setSize(400,300);
//                                vali.setLocationRelativeTo(null);
//                                vali.setLayout(null);
//
//                                JLabel cllbl = new JLabel("Enter Your Password!");
//                                vali.add(cllbl);
//                                cllbl.setBounds(50,10, 100,25);
//
//                                JTextField tf = new JTextField();
//                                tf.setBounds(50,50,100,30);
//                                vali.add(tf);
//
//                                JButton validate = new JButton("Validate");
//                                vali.add(validate);
//                                cllbl.setBounds(50,10, 100,25);
//                                JButton cancelvali = new JButton("Cancel");
//                                vali.add(cancelvali);
//                                cllbl.setBounds(50,10, 100,25);
                                if(Lock.Lock1()){
                                crd.show(pln,"vwcard");}

                            }
                        });


                            JButton htuv = new JButton("<html><center><img src='" +
                                Main.class.getResource("Images/new_help.png") +
                                "'><br><h3>How to Use?</h3></center></html>");
                        htuv.setBounds(650, 60, 250, 300);
                        htuv.setFocusPainted(false);
//                        encryptBtn.setContentAreaFilled(false);  // Make the button transparent
                        htuv.setBackground(new Color(51, 51, 51));
                        htuv.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                htuv.setOpaque(true);
                                htuv.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                htuv.setBackground(new Color(51, 51, 51));
                            }
                        });
                        htuv.setForeground(Color.WHITE);

                        vault.add(addCard);
                        vault.add(viewCard);
                        vault.add(htuv);








//========================================================================================================================





                        JPanel pChkPanel = new JPanel();
                        pChkPanel.setBackground(new Color(34, 34, 34));
                        pChkPanel.setLayout(null);

                        JLabel entpass = new JLabel("Enter Your Password");
                        entpass.setForeground(Color.WHITE);
                        entpass.setBounds(200,100,180,25);
                        pChkPanel.add(entpass);

                        JTextField inppass44 = new JTextField();
                        inppass44.setBounds(200,130,600,25);
                        inppass44.setBackground(new Color(51, 51, 51));
                        inppass44.setForeground(Color.WHITE);
                        pChkPanel.add(inppass44);

                        JLabel lblStrength = new JLabel("Strength ");
                        lblStrength.setBounds(200, 160, 80, 25);
                        pChkPanel.add(lblStrength);
                        lblStrength.setForeground(Color.WHITE);


                        JProgressBar pbStrength = new JProgressBar(0, 101);
                        pbStrength.setBounds(260, 170, 540, 8);
                        pChkPanel.add(pbStrength);
                        pbStrength.setBackground(new Color(51, 51, 51));
                        pbStrength.setForeground(new Color(0, 127, 255));

                        JLabel indicator1 = new JLabel("O");
                        pChkPanel.add(indicator1);
                        indicator1.setBounds(208,180,20,25);
                        indicator1.setForeground(Color.RED);


                        JLabel ins1 = new JLabel("Includes atleast one Upper Case Character");
                        pChkPanel.add(ins1);
                        ins1.setBounds(220,180,300,25);
                        ins1.setForeground(Color.WHITE);

                        JLabel indicator2 = new JLabel("O");
                        pChkPanel.add(indicator2);
                        indicator2.setBounds(208,200,20,25);
                        indicator2.setForeground(Color.RED);

                        JLabel ins2 = new JLabel("Includes atleast one Lower Case Character");
                        pChkPanel.add(ins2);
                        ins2.setBounds(220,200,300,25);
                        ins2.setForeground(Color.WHITE);

                        JLabel indicator3 = new JLabel("O");
                        pChkPanel.add(indicator3);
                        indicator3.setBounds(208,220,20,25);
                        indicator3.setForeground(Color.RED);

                        JLabel ins3 = new JLabel("Inclues atlest one Numeric Value");
                        pChkPanel.add(ins3);
                        ins3.setBounds(220,220,300,25);
                        ins3.setForeground(Color.WHITE);

                        JLabel indicator4 = new JLabel("O");
                        pChkPanel.add(indicator4);
                        indicator4.setBounds(208,240,20,25);
                        indicator4.setForeground(Color.RED);

                        JLabel ins4 = new JLabel("Includes atleast one special Character");
                        pChkPanel.add(ins4);
                        ins4.setBounds(220,240,300,25);
                        ins4.setForeground(Color.WHITE);

                        JLabel indicator5 = new JLabel("O");
                        pChkPanel.add(indicator5);
                        indicator5.setBounds(208,260,20,25);
                        indicator5.setForeground(Color.RED);

                        JLabel ins5 = new JLabel("Includes atleast 8 characters");
                        pChkPanel.add(ins5);
                        ins5.setBounds(220,260,300,25);
                        ins5.setForeground(Color.WHITE);

                        inppass44.addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {}

                            @Override
                            public void keyPressed(KeyEvent e) {}

                            @Override
                            public void keyReleased(KeyEvent e) {
                                String password = inppass44.getText();
                                int strength = 0;

                                if (password.matches(".*[A-Z].*")) {
                                    strength += 20;
                                    indicator1.setForeground(new Color(0, 127, 255));
                                }else{
                                    indicator1.setForeground(Color.RED);
                                }

                                if (password.matches(".*[a-z].*")) {
                                    strength += 20;
                                    indicator2.setForeground(new Color(0, 127, 255));
                                }else{
                                    indicator2.setForeground(Color.RED);
                                }

                                if (password.matches(".*\\d.*")) {
                                    strength += 20;
                                    indicator3.setForeground(new Color(0, 127, 255));
                                }else{
                                    indicator3.setForeground(Color.RED);
                                }

                                if (password.matches(".*[^a-zA-Z0-9].*")) {
                                    strength += 20;
                                    indicator4.setForeground(new Color(0, 127, 255));
                                }else{
                                    indicator4.setForeground(Color.RED);
                                }

                                if (password.length() >= 8) {
                                    strength += 20;
                                    indicator5.setForeground(new Color(0, 127, 255));
                                }else{
                                    indicator5.setForeground(Color.RED);
                                }
                                pbStrength.setValue(strength);
                            }

                        });


//                        ================================================================================================


                        JPanel pGenPanel = new JPanel();
                        pGenPanel.setBackground(new Color(34, 34, 34));
                        pGenPanel.setLayout(null);

                        JLabel entpass1 = new JLabel("Generate Your Password");
                        entpass1.setForeground(Color.WHITE);
                        entpass1.setBounds(200,100, 180,25);
                        pGenPanel.add(entpass1);

                        JTextField passGentf = new JTextField();
                        passGentf.setBounds(200,130,700,25);
                        passGentf.setEditable(false);
                        passGentf.setBackground(new Color(51, 51, 51));
                        passGentf.setForeground(Color.WHITE);
                        pGenPanel.add(passGentf);

                        JSpinner lengthSpinner = new JSpinner(new SpinnerNumberModel(8, 8, 24, 1));
                        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor)
                        lengthSpinner.getEditor();
                        JTextField textField = editor.getTextField();
                        textField.setForeground(Color.WHITE);
                        pGenPanel.add(lengthSpinner);
                        lengthSpinner.setBounds(200,160,80,25);
                        textField.setBackground(new Color(51, 51, 51));


                        JButton generateButton = new JButton("Generate Password");
                        generateButton.setBounds(500,200,160,25);
                        generateButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                int length = (int) lengthSpinner.getValue();
                                String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                String smallLetters = "abcdefghijklmnopqrstuvwxyz";
                                String numbers = "0123456789";
                                String specialCharacters = "!@#$%^&*_=+-/";

                                String allCharacters = capitalLetters + smallLetters + numbers + specialCharacters;

                                StringBuilder password = new StringBuilder();

                                for (int i = 0; i < length; i++) {
                                    int index = (int) (Math.random() * allCharacters.length());
                                    password.append(allCharacters.charAt(index));
                                }

                                passGentf.setText(password.toString());

                            }
                        });

                        pGenPanel.add(generateButton);


                        generateButton.setFocusPainted(false);

                        generateButton.setBackground(new Color(51, 51, 51));
                        generateButton.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                generateButton.setOpaque(true);
                                generateButton.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                generateButton.setBackground(new Color(51, 51, 51));
                            }
                        });
                        generateButton.setForeground(Color.WHITE);


                        JButton cpyGenPass = new JButton("Copy");
                        cpyGenPass.setFocusPainted(false);
                        cpyGenPass.setBounds(900, 130, 100,25);
                        pGenPanel.add(cpyGenPass);

                        cpyGenPass.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                String textd = passGentf.getText();

                                if (!textd.isEmpty()) {
                                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                    StringSelection selection = new StringSelection(textd);
                                    clipboard.setContents(selection, null);
                                    JOptionPane.showMessageDialog(pGenPanel, "Text copied to clipboard!");
                                } else {
                                    JOptionPane.showMessageDialog(pGenPanel, "No text to copy!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });

                        cpyGenPass.setBackground(new Color(51, 51, 51));
                        cpyGenPass.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                cpyGenPass.setOpaque(true);
                                cpyGenPass.setBackground(new Color(0, 127, 255));
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
//                                encryptBtn.setOpaque(false);
                                cpyGenPass.setBackground(new Color(51, 51, 51));
                            }
                        });
                        cpyGenPass.setForeground(Color.WHITE);

                        pChk.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "pChkPanel");
                            }
                        });

                        pGen.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "pGenPanel");
                            }
                        });

//                        JTextField passChecker = new JTextField();
//                        passwords.add(passChecker);
//                        passChecker.setBounds(400,140,500,30);
//
//                        JLabel lbl5 = new JLabel("Enter Your Password");
//                        passwords.add(lbl5);
//                        lbl5.setBounds(400,100,200,30);
//                        lbl5.setForeground(Color.WHITE);

                        pln.add(home,"home");
                        pln.add(mainPanel, "mainPanel");
                        pln.add(encryptFile, "encryptFile");
                        pln.add(decryptFile,"decryptFile");
                        pln.add(textEncMainPage, "textEnc");
                        pln.add(textEnc, "TextEncryptionMainPage");
                        pln.add(textDec, "TextDecryptionMainPage");
                        pln.add(dataHidingPanel, "dataHiding");
                        pln.add(hideData, "hideData");
                        pln.add(discloseData, "discloseData");
                        pln.add(passwords, "passwords");
                        pln.add(pChkPanel, "pChkPanel");
                        pln.add(pGenPanel, "pGenPanel");
                        pln.add(vault, "vault");
                        pln.add(addcrd, "addcrd");
                        pln.add(vwcard, "vwcard");


//Side Navigation Panel----------------------------------------------------------------------------------------------------------

                        JPanel sideNavPanel = new JPanel();
                        sideNavPanel.setBackground(new Color(51, 51, 51));
                        sideNavPanel.setPreferredSize(new Dimension(270, 0)); // Set the preferred width

                        // Create buttons for navigation items
                        sideNavPanel.setLayout(null);  // Use null layout

                        JLabel lbl43 = new JLabel();
                        ImageIcon iconi = new ImageIcon("src/Images/logo_ag60.png");
                        sideNavPanel.add(lbl43);
                        lbl43.setIcon(iconi);
                        lbl43.setBounds(10,20,60,60);

                        JLabel titappname =  new JLabel("AnyGuard");
                        sideNavPanel.add(titappname);
                        Font ftit = new Font("serif", Font.BOLD, 20);
                        titappname.setFont(ftit);
                        titappname.setForeground(Color.WHITE);
                        titappname.setBounds(80,20,100,25);

                        JLabel stitappname =  new JLabel("An Innovative Security Suite");
                        sideNavPanel.add(stitappname);
                        Font fstit = new Font("serif", Font.PLAIN, 14);
                        stitappname.setFont(fstit);
                        stitappname.setForeground(Color.WHITE);
                        stitappname.setBounds(80,40,200,25);

                        JLabel vernamesn =  new JLabel("2024.1.0");
                        sideNavPanel.add(vernamesn);
                        vernamesn.setFont(fstit);
                        vernamesn.setForeground(Color.WHITE);
                        vernamesn.setBounds(80,56,200,25);

                        // Create buttons for navigation items
                        Icon homeIcon = new ImageIcon("src/Images/home_dark.png");
                        button1 = navigationButton("Home", homeIcon);

                        button1.setHorizontalAlignment(SwingConstants.LEFT);
                        button1.setIconTextGap(10);

                        button1.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
                                BorderFactory.createEmptyBorder(0, 15, 0, 0)));
                        button1.setOpaque(true);
                        button1.setBackground(new Color(0, 127, 255));
//        button1.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button1.setOpaque(true);
//                button1.setBackground(new Color(0, 127, 255));
//                button1.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
////                button1.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.WHITE));
//
//
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button1.setOpaque(false);
//                button1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
//                button1.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
//            }
//        });

                        button1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                button1.setOpaque(true);
                                button1.setBackground(new Color(0, 127, 255));

                                crd.show(pln,"home");


                                // Set button1 to transparent
                                button2.setOpaque(false);
                                button2.setBackground(new Color(0, 0, 0, 0));

                                button3.setOpaque(false);
                                button3.setBackground(new Color(0, 0, 0, 0));

                                button4.setOpaque(false);
                                button4.setBackground(new Color(0, 0, 0, 0));

                                button5.setOpaque(false);
                                button5.setBackground(new Color(0, 0, 0, 0));

                                button6.setOpaque(false);
                                button6.setBackground(new Color(0, 0, 0, 0));

                                button7.setOpaque(false);
                                button7.setBackground(new Color(0, 0, 0, 0));
                            }
                        });



                        Icon encryptIconDark = new ImageIcon("src/Images/encrypt_dark.png");
                        button2 = navigationButton("File Encryption", encryptIconDark);
                        button2.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
                                BorderFactory.createEmptyBorder(0, 20, 0, 0)));
                        button2.setHorizontalAlignment(SwingConstants.LEFT);
                        button2.setIconTextGap(12);
//        button2.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button2.setOpaque(true);
//                button2.setBackground(new Color(0, 127, 255));
//                button2.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 20, 0, 0)));
////                button2.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.WHITE));
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button2.setOpaque(false);
//                button2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
//                button2.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 20, 0, 0)));
//            }
//        });


//                        ----------------------------------------------------------------------------------------------------------






//                        ----------------------------------------------------------------------------------------------------------


                        button2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                button2.setOpaque(true);
                                button2.setBackground(new Color(0, 127, 255));

                                crd.show(pln, "mainPanel");

                                // Set button1 to transparent
                                button1.setOpaque(false);
                                button1.setBackground(new Color(0, 0, 0, 0));

                                button3.setOpaque(false);
                                button3.setBackground(new Color(0, 0, 0, 0));

                                button4.setOpaque(false);
                                button4.setBackground(new Color(0, 0, 0, 0));

                                button5.setOpaque(false);
                                button5.setBackground(new Color(0, 0, 0, 0));

                                button6.setOpaque(false);
                                button6.setBackground(new Color(0, 0, 0, 0));

                                button7.setOpaque(false);
                                button7.setBackground(new Color(0, 0, 0, 0));

                            }
                        });




                        Icon TextEncIconDark = new ImageIcon("src/Images/TextEnc_dark.png");
                        button3 = navigationButton("Text Encryption", TextEncIconDark);
                        button3.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
                                BorderFactory.createEmptyBorder(0, 15, 0, 0)));
                        button3.setHorizontalAlignment(SwingConstants.LEFT);
//        button3.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button3.setOpaque(true);
//                button3.setBackground(new Color(0, 127, 255));
////                button3.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.WHITE));
//                button3.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button3.setOpaque(false);
//                button3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
//                button3.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
//            }
//        });

                        button3.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                button3.setOpaque(true);
                                button3.setBackground(new Color(0, 127, 255));

                                crd.show(pln,"textEnc");

                                // Set button1 to transparent
                                button1.setOpaque(false);
                                button1.setBackground(new Color(0, 0, 0, 0));

                                button2.setOpaque(false);
                                button2.setBackground(new Color(0, 0, 0, 0));

                                button4.setOpaque(false);
                                button4.setBackground(new Color(0, 0, 0, 0));

                                button5.setOpaque(false);
                                button5.setBackground(new Color(0, 0, 0, 0));

                                button6.setOpaque(false);
                                button6.setBackground(new Color(0, 0, 0, 0));

                                button7.setOpaque(false);
                                button7.setBackground(new Color(0, 0, 0, 0));
                            }
                        });


                        Icon DataHidingIconDark = new ImageIcon("src/Images/hide_dark.png");
                        button4 = navigationButton("Data Hiding", DataHidingIconDark);
                        button4.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
                                BorderFactory.createEmptyBorder(0, 15, 0, 0)));
                        button4.setHorizontalAlignment(SwingConstants.LEFT);
                        button4.setIconTextGap(10);
//        button4.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button4.setOpaque(true);
//                button4.setBackground(new Color(0, 127, 255));
//                button4.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
////                button4.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.WHITE));
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button4.setOpaque(false);
//                button4.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
//                button4.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
//            }
//        });

                        button4.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                button4.setOpaque(true);
                                button4.setBackground(new Color(0, 127, 255));

                                crd.show(pln, "dataHiding");

                                // Set button1 to transparent
                                button1.setOpaque(false);
                                button1.setBackground(new Color(0, 0, 0, 0));

                                button3.setOpaque(false);
                                button3.setBackground(new Color(0, 0, 0, 0));

                                button2.setOpaque(false);
                                button2.setBackground(new Color(0, 0, 0, 0));

                                button5.setOpaque(false);
                                button5.setBackground(new Color(0, 0, 0, 0));

                                button6.setOpaque(false);
                                button6.setBackground(new Color(0, 0, 0, 0));

                                button7.setOpaque(false);
                                button7.setBackground(new Color(0, 0, 0, 0));
                            }
                        });



                        Icon passwordIconDark = new ImageIcon("src/Images/pass_dark.png");
                        button5 = navigationButton("Passwords", passwordIconDark);
                        button5.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
                                BorderFactory.createEmptyBorder(0, 16, 0, 0)));
                        button5.setHorizontalAlignment(SwingConstants.LEFT);
                        button5.setIconTextGap(10);
//        button5.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button5.setOpaque(true);
//                button5.setBackground(new Color(0, 127, 255));
//                button5.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
////                button4.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.WHITE));
//            }
//
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button5.setOpaque(false);
//                button5.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
//                button5.setBorder(BorderFactory.createCompoundBorder(
//                        BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                        BorderFactory.createEmptyBorder(0, 15, 0, 0)));
//            }
//        });


                        button5.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                button5.setOpaque(true);
                                button5.setBackground(new Color(0, 127, 255));

                                crd.show(pln, "passwords");

                                // Set button1 to transparent
                                button1.setOpaque(false);
                                button1.setBackground(new Color(0, 0, 0, 0));

                                button3.setOpaque(false);
                                button3.setBackground(new Color(0, 0, 0, 0));

                                button4.setOpaque(false);
                                button4.setBackground(new Color(0, 0, 0, 0));

                                button2.setOpaque(false);
                                button2.setBackground(new Color(0, 0, 0, 0));

                                button6.setOpaque(false);
                                button6.setBackground(new Color(0, 0, 0, 0));

                                button7.setOpaque(false);
                                button7.setBackground(new Color(0, 0, 0, 0));
                            }
                        });


//                        Icon VaultIconDark = new ImageIcon("src/Images/card_30.png");
//                        button6 = navigationButton("Card Vault", VaultIconDark);
//                        button6.setBorder(BorderFactory.createCompoundBorder(
//                                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                                BorderFactory.createEmptyBorder(0, 16, 0, 0)));
//                        button6.setHorizontalAlignment(SwingConstants.LEFT);
//                        button6.setIconTextGap(10);
//
//                        button6.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                button6.setOpaque(true);
//                                button6.setBackground(new Color(0, 127, 255));
//
//                                crd.show(pln, "vault");
//
//                                // Set button1 to transparent
//                                button1.setOpaque(false);
//                                button1.setBackground(new Color(0, 0, 0, 0));
//
//                                button5.setOpaque(false);
//                                button5.setBackground(new Color(0, 0, 0, 0));
//
//                                button7.setOpaque(false);
//                                button7.setBackground(new Color(0, 0, 0, 0));
//
//                                button3.setOpaque(false);
//                                button3.setBackground(new Color(0, 0, 0, 0));
//
//                                button4.setOpaque(false);
//                                button4.setBackground(new Color(0, 0, 0, 0));
//
//                                button2.setOpaque(false);
//                                button2.setBackground(new Color(0, 0, 0, 0));
//                            }
//                        });



                        // Set bounds for each button
                        int buttonWidth = 270;
                        int buttonHeight = 50;
                        button1.setBounds(0, 100, buttonWidth, buttonHeight);
                        button2.setBounds(0, 152, buttonWidth, buttonHeight);
                        button3.setBounds(0, 204, buttonWidth, buttonHeight);
                        button4.setBounds(0, 258, buttonWidth, buttonHeight);
                        button5.setBounds(0, 310, buttonWidth, buttonHeight);
//                        button6.setBounds(0, 362, buttonWidth, buttonHeight);

                        // Add buttons to the side navigation panel
                        sideNavPanel.add(button1);
                        sideNavPanel.add(button2);
                        sideNavPanel.add(button3);
                        sideNavPanel.add(button4);
                        sideNavPanel.add(button5);
//                        sideNavPanel.add(button6);


                        mainFrame.add(sideNavPanel, BorderLayout.WEST);
                        mainFrame.add(pln);



//---------------------------------------------------------------------------------------------------------------------------------
                        encText.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "TextEncryptionMainPage");
                            }
                        });

                        decText.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                crd.show(pln, "TextDecryptionMainPage");
                            }
                        });






//---------------------------------------------------------------------------------------------------------------------------------





                        // Make the main frame not resizable
                        mainFrame.setResizable(false);

                        // Set the main frame to fullscreen
                        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                        // Adjust the frame size and location to ensure the taskbar is visible
                        mainFrame.setSize(screenSize.width, screenSize.height - 1);
                        mainFrame.setLocation(0, 0);

                        // Display the main frame
                        mainFrame.setVisible(true);

                        // Cancel the timer
                        timer.cancel();
                    } else {
                        progress[0] += progressInterval;
                    }
                }
            }, delay, delay);
        });
    }



    private static JButton navigationButton(String text, Icon icon) {
        JButton button = new JButton(text, icon);
        button.setFocusPainted(false);
        button.setBorder(null);
//        button.setMargin(new Insets(0,50,0,0));
//        button.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
//                BorderFactory.createEmptyBorder(0, 15, 0, 0)));

        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally

        // Create a RoundBorder with a specific radius (adjust as needed)


        return button;
    }

}


//
//
//
//// Make the main frame not resizable
//                        mainFrame.setResizable(false);
//
//                                // Set the main frame to fullscreen
//                                mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//                                // Adjust the frame size and location to ensure the taskbar is visible
//                                mainFrame.setSize(screenSize.width, screenSize.height - 1);
//                                mainFrame.setLocation(0, 0);
//
//                                // Display the main frame
//                                mainFrame.setVisible(true);
//
//                                // Cancel the timer
//                                timer.cancel();
//                                } else {
//                                progress[0] += progressInterval;
//                                }
//                                }
//                                }, delay, delay);
//                                });
//                                }
//
//
//
//private static JButton navigationButton(String text, Icon icon) {
//        JButton button = new JButton(text, icon);
//        button.setFocusPainted(false);
//        button.setBorder(null);
////        button.setMargin(new Insets(0,50,0,0));
////        button.setBorder(BorderFactory.createCompoundBorder(
////                BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 0),  // Transparent color
////                BorderFactory.createEmptyBorder(0, 15, 0, 0)));
//
//        button.setContentAreaFilled(false);
//        button.setForeground(Color.WHITE);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
//
//        // Create a RoundBorder with a specific radius (adjust as needed)
//
//
//        return button;
//        }
//
//        }