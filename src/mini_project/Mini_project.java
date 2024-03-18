package mini_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.LineBorder;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.*;

class Bar {
    private String label;
    private int value;

    public Bar(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public double getValue() {
        return value;
    }
}

class ColorIcon implements Icon {   
    private int iconId;
    private int width;
    private int height;
    private Color color;
    private int shadow = 3;
    public ColorIcon( int id,Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.iconId=id;
    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
    public int getIconId() {
        return iconId;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        //draw the column
        g.fillRect(x, y, width - shadow, height);
        g.setColor(Color.GRAY);
        //draw a shadow behind each column 
        g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
    }
}

class Weather {

    public String state;
    public double[] rain;
    public int leng;  
    public Weather(String state, double[] rain, int leng) {
        this.state = state;
        this.rain = rain;
        this.leng = leng;
    }

    public String state() {
        return state;
    }

    public double[] rain() {
        return rain;
    }
    public double avrage() {
        double avg = 0;
        for (int i = 0; i < leng; i++) {
            avg += rain[i];
        }
        avg = avg / leng;
        return avg;
    }
    public String toString() {
        return (state + "     " + this.avrage());
    }
}

public class Mini_project extends JFrame implements MouseListener, ActionListener {
    //The arrayList Year will contain the years which are common among the states
    public static ArrayList<Integer> year = new ArrayList<Integer>();
    //arrayList of the Class Weather to be used in reading from file And drawing
    private ArrayList<Weather> wheather = new ArrayList<Weather>();
    //list of the class bar to save the data about each column and to be used in MouseMovedListener 
    private List<Bar> bars = new ArrayList<Bar>();
   // private double[] graphSource;
    //string choice to be readen from user input in data HTextField
    private String Choice = "all";
    //define a variable to determine how many coloms to be drawn according to the choice taken 
    private int numOfColoms = 0;
    //Initializing JPanels
    private JPanel all,left, right, input, drawing, options;
    private Button read, draw;
    private JTextField opt;
    private JLabel maxim, title, warning;
    private JTextArea data;
    Dimension screenSize;
    private JPanel barPanel;
    private JPanel labelPanel;
    private int barWidth = 50;
    private double barHeight;
    private int barGap = 10;
    //a boolean helps to know which buttom (read file,draw) is pressed first
    private boolean readPressedFirst = false;
    private JCheckBox checkbox;
    //Setting up GUI
    public Mini_project() {

        //Setting up the Title of the Window
        super("Rain in Syria");
        
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Set Size of the Window (WIDTH, HEIGHT)
        setSize(1000, 1000);

        //Exit Property of the Window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //to make the window open maximized by default
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Constructing JPanel 1 and 2 with GridLayout of 1 row and 1 column
        left = new JPanel();
        left.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        left.setLayout(new GridLayout(2, 1, 10, 10));

        right = new JPanel();
        right.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));//Constructing JPanel 3 and 4
        input = new JPanel();
        input.setBorder(BorderFactory.createTitledBorder("input"));

        drawing = new JPanel();
        drawing.setBorder(BorderFactory.createTitledBorder("Drawing"));
        drawing.setLayout(new GridLayout(1, 1, 10, 10));
        options = new JPanel();
        options.setBorder(BorderFactory.createTitledBorder("options"));

        drawing.add(options);
        //Adding JPanel 3 to JPanel 1 which means JPanel 3 is inside JPanel 1
        left.add(input);
        left.add(drawing);
        read = new Button("Read File");
        read.setPreferredSize(new Dimension(110, 20));
        read.addActionListener(this);
        input.add(read);

        title = new JLabel("    ");
        title.setBorder(new LineBorder(Color.black, 1));
        //title.setLocation(10, 70);
        input.add(title);
        title.setPreferredSize(new Dimension(250, 20));

        data = new JTextArea(15, 20);
        data.setLineWrap(true);
        data.setEditable(false);
        data.setBorder(new LineBorder(Color.black, 1));
        data.setCaretPosition(0);
        input.add(data);

        opt = new JTextField("all", 25);
        //  opt.addActionListener(this);
        options.add(opt);

        maxim = new JLabel("Column information  ");
        maxim.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        maxim.setPreferredSize(new Dimension(200, 20));
        options.add(maxim);

        draw = new Button("Draw");
        draw.setPreferredSize(new Dimension(110, 20));
        draw.addActionListener(this);
        options.add(draw);
        //this label to print a message only if draw buttom was pressed before read file buttom
        //or other errors from what we validate occured
        warning = new JLabel();
        warning.setEnabled(false);
        options.add(warning);
        
        
        checkbox = new JCheckBox("Enable lines");
        options.add(checkbox);
        // set stateJPanel
        all=new JPanel();
        all=(JPanel)getContentPane();
        setContentPane(all);
        all.setLayout(new BoxLayout(all, BoxLayout.X_AXIS));
        all.setAlignmentX(Component.LEFT_ALIGNMENT);
        left.setPreferredSize(new Dimension(300, (int) (screenSize.getHeight())));
        right.setPreferredSize(new Dimension((int) (screenSize.getWidth() - 310), (int) (screenSize.getHeight())));
        right.setOpaque(true);
        all.add(left, BorderLayout.WEST);
        all.add(right, BorderLayout.EAST);
        setVisible(true);
    }

    //Main Method
    public static void main(String[] args) {
        Mini_project jp = new Mini_project();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel mouseBar = (JLabel)e.getComponent();
        ColorIcon mouseIcon=(ColorIcon)( mouseBar.getIcon());
        int mIconId = mouseIcon.getIconId();
        maxim.setText(bars.get(mIconId).getLabel() + "  " + bars.get(mIconId).getValue());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        maxim.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == read) {
            //a boolean =true means we pressed readfile buttom first
            readPressedFirst = true;
            //clear the warning jlabel
            warning.setText("");
            //warning.setEnabled(false);  
            //call length() method
            int length = length();
            //call years() method to read the years out of the file
            years();
            if (warning.getText().equalsIgnoreCase(""))   //There would be no errors in reading file in Years Method
            {   
            String[] res = {" "};
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("D:\\rain.txt"));
                String reads = br.readLine(); //reading lines
                //the first line is the title we need to show it on the label
                title.setText(reads);
                //Temporary String to save the state name
                String state;
                int numOfState = 1;
                //if the user pressed read buttom more than one the data will be saved once 
                //because wheather.clear() clear the list of weather
                wheather.clear();
                //read the second line , which will contain the first state's name  
                reads = br.readLine();
                boolean ok =true;
                while (reads != null) {
                    //store the rainfall rate temporary for each state
                    double[] rains = new double[length];
                    state = reads;
                    //read the rainfall rates for each state - which are length rate
                    for (int i = 0; i < length; i++)
                    {
                        //read the row, split it & save the second part which is the rainfall
                        try {
                            reads = br.readLine();}
                        catch (NullPointerException nulex) {
                            System.out.print("NullPointerException Caught");
                        } 
                       if (!reads.matches("(\\d{4}, \\d*)||(\\d{4},\\d*)"))
                       {
                           warning.setText("Error in File in line "+(3+i+wheather.size()*(length+1)));
                           warning.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
                           ok=false;
                           break;
                       }
                       else
                        {
                            res = reads.split(",", 2);
                            rains[i] = Double.parseDouble(res[1]);
                        } 
                    }
                    if(! ok) break;
                    //here we add the info about a state to our array of Weather
                    wheather.add(new Weather(state, rains, length));
                    //numOfState++;
                     try {
                            reads = br.readLine();
                             if (!reads.matches("[a-zA-Z]*"))
                              {
                             warning.setText("Error in File in line "+(wheather.size()*(length+1)+2));
                             warning.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
                             ok=false;
                            break;
                              }
                     }
                        catch (NullPointerException nulex) {
                            System.out.print("NullPointerException Caught");
                        } 
                    
                
                }
                br.close();
                if (ok){
                //if the user press read buttom more than once the data will be written once on the text area
                //because data.setText("") make the text area empty each time
                data.setText("");
                //printing each state with the average rainfall rate into the textarea : data
                for (int i = 0; i < wheather.size(); i++) {
                    data.append(numOfState+"  "+wheather.get(i) + "\n");
                    numOfState++;
                }
            }} catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        }  
        else if (e.getSource() == draw) {
            //when the user press the draw buttom then the histogramme must be shown according to the choice
            if (!readPressedFirst) // = if draw was pressed before read file,print a message : 
            {
                warning.setText(" you should press read buttom first ");
                warning.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
                warning.setHorizontalAlignment(JLabel.CENTER);
                warning.setVerticalAlignment(JLabel.CENTER);
                warning.setPreferredSize(new Dimension(options.getWidth(), 20));
            } 
            else if (warning.getText().equalsIgnoreCase(""))   //There would be no errors in reading file
            {   
                boolean mat;
                warning.setText("");
                Choice = opt.getText();
                mat = Choice.matches("^(([1][0])|[1-9])(\\,(([1][0])|[1-9]))*||all||ALL||All");
                if (!mat || (mat && Choice.length() > 2 * wheather.size() - 1)
                        ||(mat && Choice.length()==1 &&Integer.parseInt(Choice)>wheather.size()))
                {
                    warning.setText("Invalid Choice");
                    warning.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
                    opt.setText("");
                }     
                else {
                    double[] graphSource;
                    //the info list array contains the data should be shown under the coloms in the histograme
                    List<String> info = new ArrayList<String>(); //it is a list so we can declare it without size since it changes according to the case
                    //the first case : we want to draw a histograme for all states with avg rainfall rate
                    if (Choice.equalsIgnoreCase("all"))
                    {
                        //In this case where gonna draw a colom for each state 
                        numOfColoms = wheather.size();
                        //the graphSource array contains the values to be drawn
                        graphSource = new double[numOfColoms];
                        for (int i = 0; i < numOfColoms; i++) {
                            //we're gonna draw the average for a state
                            graphSource[i] = wheather.get(i).avrage();
                            //we're gonna type the number of the state under each colom
                            info.add(wheather.get(i).state());
                        }

                    } //the second case : we want to draw a histograme for some states with avg rainfall rate 
                    else if (Choice.contains(",")) {
                        String[] seperated = Choice.split(",");
                        numOfColoms = seperated.length;
                        graphSource = new double[numOfColoms];
                        for (int i = 0; i < numOfColoms; i++) {
                            //we're gonna draw the average for a state
                            graphSource[i] = wheather.get((Integer.parseInt(seperated[i])) - 1).avrage();
                            //we're gonna type the number of the state under each colom
                            info.add(wheather.get((Integer.parseInt(seperated[i])) - 1).state());
                        }
                    } //The last case : the string here must be one number < 10 .. we want to draw a histograme for one state among years
                    else { //Draw the rains for one state
                        numOfColoms = wheather.get((Integer.parseInt(Choice) - 1)).rain.length;
                        graphSource = new double[numOfColoms];
                        graphSource = wheather.get((Integer.parseInt(Choice) - 1)).rain;
                        for (int i = 0; i < numOfColoms; i++) {
                            //graphSource[i] = wheather.get((Integer.parseInt(seperated[i]))-1).avrage();
                            info.add(Integer.toString(year.get(i)));
                        }
                    }
                    //to repaint each time the buttom is pressed
                    right.revalidate();
                    right.removeAll();
                    try {
                        barPanel.revalidate();
                        barPanel.removeAll();
                        labelPanel.revalidate();
                        labelPanel.removeAll();
                    } catch (NullPointerException nuexp) {
                        System.out.println("NullPointerException Caught ");
                    }
                    //whatever was the case we are going to call the drawHistogrme method with parameters suitthe case
                    drawHistograme(graphSource, info);
                }
            }
        }
    
    
    }
    // A function that will compute the number of the rains for a state - which is fixed for all states
    public static int length() {
        int leng = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\rain.txt"));
            String reads = br.readLine(); //reading lines-first Line : Title
            reads = br.readLine();          //reading lines-second Line : state name
            reads = br.readLine();          //reading lines-Third Line : year,rainfall rate
            //Checking .. While the line contains a number, add one to the counter (=leng) 
            //till it reads a line with no number (A state name)     
            while (reads != null && reads.matches(".*\\d+.*")) {
                leng++;
                reads = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leng;
    }

    //The years() method read the years from the file for one state 'cause they're fixed for all states
    public void years() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\rain.txt"));
            String reads = br.readLine();       //reading lines-first Line : Title                
            reads = br.readLine();                //reading lines-second Line : state name
            
            if (!reads.matches("[a-zA-Z]*"))
            {
                warning.setText("Error in File in line 2");
                warning.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
            }
            else
            {
                 for (int i = 0; i < length(); i++) {
                reads = br.readLine();           //reading lines: year,rainfall rate.
                if (!reads.matches("(\\d{4}, \\d*)||(\\d{4},\\d*)"))
                { warning.setText("Error in File in line "+(3+i));
                warning.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
                }
                else
                {
                //split the row & save the first part which is the year
                String temp[] = reads.split(",", 2);
                year.add(Integer.parseInt(temp[0]));
            
                }
            }
            }
           
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //a method that would draw the histograme in order to the option chosen
    private void drawHistograme(double[] graphSource, List info) {
        bars.clear();
        //create bars List
        for (int i = 0; i < numOfColoms; i++) {
            Bar bar;
            int help = (int) Math.round((graphSource[i]));
            bar = new Bar((String) info.get(i), help);
            bars.add(bar);
        }
        //create the panels to Draw on them
        right.setBorder(new EmptyBorder(10, 10, 10, 10));
        right.setLayout(new BorderLayout());
        //if checkBox is selected then draw horizantal Lines and points horizantal lines
        if (checkbox.isSelected()) {
            barPanel = new JPanel(new GridLayout(1, bars.size(), barGap, 0)) {
            protected void paintComponent(Graphics g) {
                //draw the vertical lines 
                super.paintComponent(g);
                for (int i = 20; i < screenSize.getHeight(); i = i + 40) {   //draw the horizantal lines 
                    g.drawLine(10, i, (int) screenSize.getWidth() - 350, i);
                    //draw the points horizantal lines
                    for (int j = i + 10; j <= i + 50; j = j + 10) {
                        for (int k = 10; k < screenSize.getWidth() - 350; k = k + 3) {
                            g.drawLine(k, j, k, j);
                        }
                    }
                }
            }
        };
        } else {//   barPanel = new JPanel(new GridLayout(1, bars.size(), barGap, 0))
          barPanel = new JPanel(new GridLayout(1, bars.size(), barGap, 0)) {
            protected void paintComponent(Graphics g) {
                //draw the vertical lines 
                super.paintComponent(g);
                for (int i = 20; i < screenSize.getHeight(); i = i + 40) {   //draw the vertical lines 
                    g.drawLine(10, i, (int) screenSize.getWidth() - 350, i);
                }
            }
        };
                  
        }
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder(compound);

        labelPanel = new JPanel(new GridLayout(1, 0, barGap, 0));
        labelPanel.setBorder(new EmptyBorder(5, 10, 0, 10));
        this.layoutHistogram();

        right.add(barPanel, BorderLayout.CENTER);
        right.add(labelPanel, BorderLayout.PAGE_END);
        barPanel.setVisible(true);
        labelPanel.setVisible(true);
        right.setVisible(true);
    }

    public void layoutHistogram() {
        Color color = null;
        double maxValue = 0;
        for (Bar bar : bars) {
            maxValue = Math.max(maxValue, bar.getValue());
        }
        
        if(maxValue<500)
        maxValue= maxValue+(maxValue*0.1);
        else
            maxValue= maxValue+(maxValue*0.2);
        for (Bar bar : bars)
        {
            int first = (int) (Math.random() * 256);
            int second = (int) (Math.random() * 256);
            int third = (int) (Math.random() * 256);
            color = new Color(first, second, third);
            JLabel label = new JLabel(bar.getValue() + "");
            label.setForeground(Color.white);                  //The color of the text
            label.setHorizontalTextPosition(JLabel.CENTER);  //ubove the colom
            label.setHorizontalAlignment(JLabel.CENTER);  //the x's of the cloumns * 
            label.setVerticalAlignment(JLabel.BOTTOM);   //the y's of the colomn
            barHeight = (bar.getValue() * screenSize.getHeight()) / maxValue;
            Icon icon = new ColorIcon( bars.indexOf(bar),color, barWidth , (int) barHeight);
            
            label.setIcon(icon);//to show only the rectangle 
            label.addMouseListener(this);
            barPanel.add(label);
            JLabel barLabel = new JLabel(bar.getLabel());
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add(barLabel);
        }    
   }
}