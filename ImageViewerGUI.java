import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;

public class ImageViewerGUI extends JFrame implements ActionListener{

    public static void main(String[] args){
        new ImageViewerGUI();
    }
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "/home/...";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    float brightenFactor = 1;
    boolean firstTimeEnteringMain = true; // sorry, but I had to define this boolean. I couldn't think of another way.

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setResizable(true);
        mainPanel();
        this.setVisible(true);
    }

    public void mainPanel(){

        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.lightGray);

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(50,90,600,120);
        buttonsPanel.setLayout(new GridLayout(3, 2,10,5));
        buttonsPanel.setBackground(Color.lightGray);

        if(firstTimeEnteringMain){  // I had a problem with action listener and my buttons, this is my way of fixing it.
            firstTimeEnteringMain = false;

            // Construct buttons
            selectFileButton = new JButton("Choose Image");
            showImageButton = new JButton("Show Image");
            brightnessButton = new JButton("Brightness");
            grayscaleButton = new JButton("Gray Scale");
            resizeButton = new JButton("Resize");
            closeButton = new JButton("Exit");
            backButton = new JButton("Back");
            showResizeButton = new JButton("Result");
            showBrightnessButton = new JButton("Result");

            // Without this the action listener doesn't work
            selectFileButton.addActionListener(this);
            showImageButton.addActionListener(this);
            brightnessButton.addActionListener(this);
            grayscaleButton.addActionListener(this);
            resizeButton.addActionListener(this);
            closeButton.addActionListener(this);
            backButton.addActionListener(this);
            showResizeButton.addActionListener(this);
            showBrightnessButton.addActionListener(this);

            // Recolor buttons
            selectFileButton.setBackground(Color.white);
            showImageButton.setBackground(Color.white);
            brightnessButton.setBackground(Color.white);
            grayscaleButton.setBackground(Color.white);
            resizeButton.setBackground(Color.white);
            closeButton.setBackground(Color.white);
            backButton.setBackground(Color.white);
            showResizeButton.setBackground(Color.white);
            showBrightnessButton.setBackground(Color.white);

            // Set bounds for the ones that are not in our grid panel
            backButton.setBounds(150,220,80,30);
            showResizeButton.setBounds(470,220,80,30);
            showBrightnessButton.setBounds(470,220,80,30);

        }
        // Add all buttons to Grid panel
        buttonsPanel.add(selectFileButton);
        buttonsPanel.add(showImageButton);
        buttonsPanel.add(brightnessButton);
        buttonsPanel.add(grayscaleButton);
        buttonsPanel.add(resizeButton);
        buttonsPanel.add(closeButton);

        // Add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // Add main panel to our frame
        this.add(mainPanel);

    }

    public void resizePanel(){
        // Create resize panel
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        resizePanel.setBackground(Color.lightGray);

        // Create the labels and the text fields, setting their bounds, recoloring them and adding them to the panel
        JLabel resizeTempLabel1 = new JLabel("Resize section",SwingConstants.CENTER);
        JLabel resizeTempLabel2 = new JLabel("Width:",SwingConstants.CENTER);
        JLabel resizeTempLabel3 = new JLabel("Height:",SwingConstants.CENTER);
        widthTextField = new JTextField("");
        heightTextField = new JTextField("");

        resizeTempLabel1.setBounds(250,60,200,30);
        resizeTempLabel2.setBounds(125,100,150,30);
        resizeTempLabel3.setBounds(125,130,150,30);
        widthTextField.setBounds(425,100,150,30);
        heightTextField.setBounds(425,130,150,30);

        resizeTempLabel1.setBackground(Color.white);
        resizeTempLabel2.setBackground(Color.white);
        resizeTempLabel3.setBackground(Color.white);
        widthTextField.setBackground(Color.white);
        heightTextField.setBackground(Color.white);

        resizePanel.add(resizeTempLabel1);
        resizePanel.add(resizeTempLabel2);
        resizePanel.add(resizeTempLabel3);
        resizePanel.add(widthTextField);
        resizePanel.add(heightTextField);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);

        // Add panel to the main frame
        this.add(resizePanel);
    }
    public void brightnessPanel(){
        // Create brightness panel
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        brightnessPanel.setBackground(Color.lightGray);

        // Create the labels and the text field, set their bounds, recolor them and add them to the panel
        JLabel brightnessTempLabel1 = new JLabel("Enter Brightness Factor",SwingConstants.CENTER);
        JLabel brightnessTempLabel2 = new JLabel("(must be between 0 and 1)",SwingConstants.CENTER);
        brightnessTextField = new JTextField("");

        brightnessTempLabel1.setBounds(125,100,150,30);
        brightnessTempLabel2.setBounds(125,130,150,30);
        brightnessTextField.setBounds(425,115,150,30);

        brightnessTempLabel1.setBackground(Color.white);
        brightnessTempLabel2.setBackground(Color.white);
        brightnessTextField.setBackground(Color.white);

        brightnessPanel.add(brightnessTempLabel1);
        brightnessPanel.add(brightnessTempLabel2);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(backButton);
        brightnessPanel.add(showBrightnessButton);

        // Add panel to the main frame
        this.add(brightnessPanel);
    }

    public void chooseFileImage(){
        // a simple file chooser
        fileChooser.setAcceptAllFileFilterUsed(false);
        int tmp = fileChooser.showSaveDialog(null);
        if(tmp == JFileChooser.APPROVE_OPTION) filePath = fileChooser.getSelectedFile().getAbsolutePath();
        else System.out.println("You cancelled the operation.");
    }
    public void showOriginalImage(){
        try {
            // Create label that contains the image from filepath
            this.file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(0, 0, 1800, 1000);

            // Create display frame for the image
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempPanel.add(imageLabel);
            tempFrame.add(tempPanel);
        }
        catch(Exception ex){
            System.out.println("Something went wrong!");
        }
    }

    public void grayScaleImage(){
        try {
            // Create label that contains the gray scaled image from filepath
            this.file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            BufferedImage grayScaledBufferedImage = op.filter(bufferedImage, null);
            ImageIcon imageIcon = new ImageIcon(grayScaledBufferedImage);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(0, 0, 1800, 1000);

            // Create display frame for the image
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempPanel.add(imageLabel);
            tempFrame.add(tempPanel);
        }
        catch(Exception ex){
            System.out.println("Something went wrong!");
        }
    }
    public void showResizeImage(int w, int h){
        try {
            // Create label that contains the resized image from filepath
            this.file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            Image resizedImage = imageIcon.getImage().getScaledInstance(w, h,Image.SCALE_DEFAULT);
            ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedImageIcon);
            imageLabel.setBounds(0, 0, 1800, 1000);

            // Create display frame for the image
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempPanel.add(imageLabel);
            tempFrame.add(tempPanel);
        }
        catch(Exception ex){
            System.out.println("Something went wrong!");
        }
    }
    public void showBrightnessImage(float brightenFactor){
        try {
            // Create label that contains the brightened image from filepath
            this.file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            RescaleOp op = new RescaleOp(brightenFactor, 0, null);
            bufferedImage = op.filter(bufferedImage, bufferedImage);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(0, 0, 1800, 1000);

            // Create display frame for the image
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempPanel.add(imageLabel);
            tempFrame.add(tempPanel);
        }
        catch(Exception ex){
            System.out.println("Something went wrong!");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            this.getContentPane().removeAll();
            this.resizePanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showImageButton){
            showOriginalImage();

        }else if(e.getSource()==grayscaleButton){
            grayScaleImage();

        }else if(e.getSource()== showResizeButton){
            String widthTextFieldText = widthTextField.getText();
            String heightTextFieldText = heightTextField.getText();
            try{
                int width = Integer.parseInt(widthTextFieldText);
                int height = Integer.parseInt(heightTextFieldText);
                if(width < 0 || height <0) System.out.println("Invalid input!");
                else showResizeImage(width,height);
            }
            catch(Exception ex){
                System.out.println("Invalid input!");
            }

        }else if(e.getSource()==brightnessButton){
            this.getContentPane().removeAll();
            this.brightnessPanel();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()== showBrightnessButton){
            try{
                brightenFactor = Float.parseFloat(brightnessTextField.getText());
                if(brightenFactor < 0 || brightenFactor > 1) System.out.println("Invalid input!");
                else showBrightnessImage(brightenFactor);
            }
            catch(Exception ex) {
                System.out.println("Invalid input!");
            }

        }else if(e.getSource()== selectFileButton){
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}

// Nemidoonam chera, vali bazi image ha az frame mizanan biroon va nemidoonam ke bayad fixesh konam ya na.