

//Rikhil K. && Ronith K.
//Period 2-3
//DEFINITVE EDITION

import javax.swing.JButton; //import necessary components
import javax.swing.JTextField;
               
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.ImageIcon;

import javax.swing.JFrame;    
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;        
import java.awt.Graphics;

import java.awt.BorderLayout;    //import layouts
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.Image;
import java.awt.Font;
import javax.swing.Timer;

import java.awt.event.ActionListener;    
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;    //to get user input from mouse and keyboard
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;    
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;

public class FR2 extends TuningInput
{    
    private JButton start;    //import JButtons that are used to switch between panels, start button is used to switch to the playing panel from the start panel
    private JButton help;    //used to switch to the helping panel from the start panel
    private JButton back;    //used to switch to start panel from the playing panel
    private JButton back2;    //used to switch to the start panel from the helping panel
    private JButton back5;
    private JButton extreme;
    private JButton race;
    private JButton replay;
    private JButton playNew;
    
    private JButton car1;
    private JButton car2;
    private JButton car3;
    
    
    private JLabel label1;
    private JLabel label2;
    private JLabel label3; 
    private JLabel label4;
    
    private JTextField rearSprings;
    private JTextField frontSprings;
    private JTextField rollBar;
    private JTextField rearDown;
   
    private CardPanel cp;        //declare necessary variables for card layout
    private CardLayout cards;
    private JFrame formula;    //JFrame used for the entire program
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private double lapTime, targetTime, userFSpring, userRSpring, userDown, userRRoll;
    private boolean finished;
    
    private int front;
    private int rear;
    
    public FR2()
    {
        formula = new JFrame ("Formula Racer");    //instantiate  components and construct JFrame
        cp = new CardPanel();
        formula.getContentPane().add(cp);    //add CardLayout
        formula.setVisible(true);
        
        lapTime = 0;
        targetTime = 0;
        
        cards = new CardLayout();
        cp.setLayout(cards);
        
        StartPanel yay = new StartPanel();        //add panels into the CardLayout
        cp.add(yay, "Starting");
        
        Playing play = new Playing();        //panel in which user is given the weights of the car and uses them to get the correct suspension for the car
        cp.add(play, "Playing");
        
        RacePanel race = new RacePanel();        //panel in which user makes races their car to get faster times
        cp.add(race,"Racing");
        
        Helping thanks = new Helping();            //panel in which user gets directions on what how to play the game
        cp.add(thanks, "Helping");
        
        Equating bruh = new Equating();
        cp.add(bruh, "Equating");
        
        EndPanel end = new EndPanel();
        cp.add(end, "Ending");
        
        cards.first(cp);
    }
    
    public static void main(String[]args)
    {
        
        FR2 racer1 = new FR2();
        racer1.run();
    }
    
    class CardPanel extends JPanel
    {
        public CardPanel()
        {        
        }
    }
    class Playing extends JPanel implements ActionListener
    {

        public Playing()
        {
            setBackground(Color.GREEN);
            setLayout(new GridLayout(5,2));  
            
            back = new JButton("Back");                    // create JButtons to switch to previous panels in the card layout
            back.setPreferredSize(new Dimension(300,100));    //enlarge to button to make it user friendly and better to look at
            back.addActionListener(this);
            add(back);
            
            race = new JButton("Race");                    //this button allows you to go from play panel to race panel
            race.setPreferredSize(new Dimension(300,100));    //enlarge to button to make it user friendly and better to look at
            race.addActionListener(this);
            add(race);
            
            label1 = new JLabel("Enter the front spring pressure: Front Mass*31.193 (input an integer)  ", JLabel.CENTER);
            label1.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            label1.setForeground(Color.CYAN);
            add(label1);
            label2 = new JLabel("Enter the rear spring pressure: Rear Mass*31.193 (input an integer)  ", JLabel.CENTER);
            label2.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            label2.setForeground(Color.CYAN);
            add(label2);
            
            
            frontSprings = new JTextField("", 10);        //text field for user inputted answer for front suspension
            frontSprings.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            frontSprings.addActionListener(this);
            add(frontSprings);
            
            rearSprings = new JTextField("", 10);        //text field for user inputted answer for rear suspension
            rearSprings.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            rearSprings.addActionListener(this);
            add(rearSprings);
            
            label3 = new JLabel("Enter the front roll bar value: front percentage of weight/rear percentage of weight * rear roll bar  ", JLabel.CENTER);
            label3.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            label3.setForeground(Color.CYAN);
            add(label3); 
            label4 = new JLabel("Enter the rear down force", JLabel.CENTER);
            label4.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            label4.setForeground(Color.CYAN);
            add(label4);
            
            rollBar = new JTextField("", 10);        //text field for user inputted answer for front suspension
            rollBar.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            rollBar.addActionListener(this);
            add(rollBar);
            
            rearDown = new JTextField("", 10);        //text field for user inputted answer for front suspension
            rearDown.setFont(new Font("Comic Sans", Font.ITALIC, 20));
            rearDown.addActionListener(this);
            add(rearDown);
            
            repaint();
        }
        public void actionPerformed(ActionEvent e)
        {
            

            try{
                front = Integer.parseInt(frontSprings.getText());        //get user inputed answers in the playing panel form the text fields and store into front and rear var
                
            }catch(Exception fronte){
                front = 0;
            }
            try{
                rear = Integer.parseInt(rearSprings.getText());
            
            }catch(Exception reare){
                rear = 0;
            }
            
            try{
                userDown = Double.parseDouble(rearDown.getText());
            }catch(Exception userDowne){
                userDown = 0;
            }
            
            try{
                userRRoll = Double.parseDouble(rollBar.getText());
            }catch(Exception userRRolle){
                userRRoll = 0;
            }
            
            if((userDown > rearDownforce -3) && (userDown < rearDownforce +3))
            {
                lapTime--;
            }
            
            
            System.out.println(rear);
            
            if(e.getActionCommand().equals("Back"))        //when the back button is pressed it should switch to the start panel
            {
                cards.show(cp ,"Starting");                //card layout with start panel
            }
            else if(e.getActionCommand().equals("Race"))        //when the race button is pressed it should switch to the race panel
            {
                
                cards.show(cp, "Racing");                //card layout with race panel
            }
            
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            //g.drawString("Select your values", 100,100); //Import an image of a car here  
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Speedometer.jpg"));    //Image for the start panel
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);
            g.setColor(Color.CYAN);
            Font font1 = new Font ("Comic Sans", Font.ITALIC,20);    //get readable font
            g.setFont(font1);  
			g.drawString("rear percentage of weight* maximum front down force/percentage of front weight", 1020, 900);
            //g.drawString("Enter rear spring pressure", 980, 400);
            //g.drawString("Enter front spring pressure", 480, 400);
            
        }
    }
        
    class Helping extends JPanel implements ActionListener
    {
        public Helping()
        {
            setBackground(Color.YELLOW);
            
            back2 = new JButton("Back");        //same as previous back button but in the helping class
            back2.setPreferredSize(new Dimension(300,100));
            back2.addActionListener(this);
            add(back2);
            
            
            repaint();
        }
        
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Back"))
            {
                cards.show(cp ,"Starting");
            }
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Night.jpg"));
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);
            
            g.setColor(Color.GREEN);
            Font font2 = new Font ("Comic Sans", Font.PLAIN,50);
            g.setFont(font2);
            
            g.drawString("You must click the mouse to start the car engine once on the track. ", 100,300);
            
            g.drawString("In the Play panel you will be given the weights of various sections of the car.", 100,400);
            
            g.drawString("You must use these weights and suspension formulas in order to get the", 100,475);
            g.drawString("maximum speed from your car.",100,525);
            
            g.drawString("Below are the formulas necessary to attain maximum speed from your car:",100,600);
            //g.drawString("formulas",100,650);
            
            g.drawString("The formula for the front mass of the car is: totalWeight*percentFront/6400 " , 100, 700);
            g.drawString("The formula for the rear mass of the car is: totalWeight*percentRear/6400 " , 100, 750);
            g.drawString("The formula for the front spring stiffness is: frontMass*31.193 " , 100, 800);
            g.drawString("The formula for the rear spring stiffness is: rearMass*31.193 " , 100, 850);
            g.drawString("The formula for the rear downforce given the front is: ", 100, 900);
            g.drawString("(percentRear*(frontLimit)/percentFront) ", 100, 950);
			g.drawString("Use the arrow keys to move the car", 100, 1000);
            
            Font font1 = new Font ("Comic Sans", Font.PLAIN,50);          //Readable font
            g.setFont(font1);
            g.drawString("Directions", 850, 175);    
        }
    }
    public void run()
    {
        formula.setSize(1920, 1200);                
        formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formula.setResizable(true);
        formula.setVisible(true);
    }    
    class StartPanel extends JPanel
    {
    
        public StartPanel()
        {
            setBackground(Color.BLUE);    
            repaint();
                    
        start = new JButton("Start");    
        help = new JButton("Help");
        
        
        PlayHandler playHandler = new PlayHandler();
        HelpHandler helpHandler = new HelpHandler();
        start.setPreferredSize(new Dimension(300,100));
        start.addActionListener(playHandler);
        help.setPreferredSize(new Dimension(300,100));        //large buttons more user friendly
        help.addActionListener(helpHandler);
        
        add(start);
        add(help);
        repaint();
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("FormulaR.jpg"));        //Background for the title screen
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);

            g.setColor(Color.ORANGE);
            Font font1 = new Font ("Comic Sans", Font.ITALIC,50);
            g.setFont(font1);
            g.drawString("Welcome to Formula Racer!", 680,250);        //Title Screen caption
        }
        class PlayHandler implements ActionListener        
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getActionCommand().equals("Start"))    //If the start panel is pressed then the user will get relocated to a panel with the formulas
                {
                    cards.show(cp ,"Equating");
                }
            }
        }
    
        class HelpHandler implements ActionListener        
        {
            public void actionPerformed(ActionEvent e)
            {
                String helpingCommand = e.getActionCommand();
                if(helpingCommand.equals("Help"))        //If the start panel is pressed then the user will get relocated to a panel with the directions
                {
                    cards.show(cp,"Helping");
                }
            }
        }
    }
    
    class EndPanel extends JPanel implements ActionListener
    {
        public EndPanel()
        {
            replay = new JButton("Replay Race");
            replay.setPreferredSize(new Dimension(300,100));
            replay.addActionListener(this);
            add(replay);
            
            playNew = new JButton("Play New Game");
            playNew.setPreferredSize(new Dimension(300,100));
            playNew.addActionListener(this);
            add(playNew);
            
            
            
            repaint();
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            //getSpringRates();
            //getRollBar();
            //getDownForce();
            
            
            
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Formula.jpg"));        //Background for the title screen
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);


            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans", Font.ITALIC, 30));
            g.drawString("Results", 940, 300);
            g.drawString("Front Springs   = " + frontSprings1, 500,400);
            g.drawString("Rear Springs    = " + rearSprings1, 500,500);
            g.drawString("Rear Down Force = " + rearDownforce, 500,600);
            g.drawString("Front Roll      = " + frontRoll, 500,700);
            if(front == frontSprings1) {
                g.drawString("Your input   of " + front + " is correct. You received a time reduction.", 900,400);
                lapTime--;
            }
			else if(front == 0)
			{
				g.drawString("You did not answer the question. There is no penalty.", 900, 400);
			}
			else if(front != 0 && front != frontSprings1){
                g.drawString("Your input   of " + front + " is incorrect. You have received a time penalty.", 900,400);
                lapTime++;
            }
            if(rear == rearSprings1) 
			{
                g.drawString("Your input   of " + rear + " is correct. You received a time reduction.", 900,500);
                lapTime--;
            }
			else if(rear == 0)
			{
				g.drawString("You did not answer the question. There is no penalty.", 900, 500);
			}
			else if(rear != rearSprings1 && rear != 0)
			{
                g.drawString("Your input   of " + rear + " is incorrect. You have received a time penalty.", 900,500);
                lapTime++;
            }
            if(userDown == rearDownforce) 
			{
                g.drawString("Your input   of " + userDown + " is correct. You received a time reduction.", 900,600);
                lapTime--;
            }
			else if(userDown == 0)
			{
				g.drawString("You did not answer the question. There is no penalty.", 900, 600);
			}
			else if(userDown != 0 && userDown != rearDownforce)
			{
                g.drawString("Your input   of " + userDown + " is incorrect. You have received a time penalty.", 900,600);
                lapTime++;
            }      
            if(userRRoll == frontRoll) 
			{
                g.drawString("Your input   = " + userRRoll + " is correct. You received a time reduction.", 900,700);
                lapTime--;
            }
			else if(userRRoll == 0)
			{
				g.drawString("You did not answer the question. There is no penalty.", 900, 700);
			}
			else if(userRRoll != 0 && userRRoll != frontRoll)
			{
                g.drawString("Your input   = " + userRRoll + " is incorrect. You have received a time penalty.", 900,700);
                lapTime++;
            }
            g.drawString("Lap Time        = " + lapTime, 500,300);
            
        }    
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Replay Race"))
            {
                cards.show(cp ,"Racing");
            }
            if(e.getActionCommand().equals("Play New Game"))
            {
                
                cards.show(cp, "Starting");
            }
        }
        
    }
    class Equating extends JPanel implements ActionListener
    {
        public Equating()
        {
            setBackground(Color.YELLOW);
            
            back5 = new JButton("Back");        //same as previous back button but in the helping class
            back5.setPreferredSize(new Dimension(300,100));
            back5.addActionListener(this);
            add(back5);
            extreme = new JButton("Race Setup");        //same as previous back button but in the helping class
            extreme.setPreferredSize(new Dimension(300,100));
            extreme.addActionListener(this);
            add(extreme);  
            repaint();
            //percentFront = (int)(Math.random()*27+38);
            //frontLimit = (int)(Math.random()*125+100);
            //rearChoice = (int)Math.random()*15+25;
            //totalWeight = (int)(Math.random()*1400+2300);
            runTuning();
        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Back"))
            {
                cards.show(cp ,"Starting");
            }
            else if(e.getActionCommand().equals("Race Setup"))
            {
                cards.show(cp ,"Playing");
            }
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Dyno.jpg"));
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);
            
            g.setColor(Color.BLACK);
            Font font6 = new Font ("Comic Sans", Font.PLAIN,60);
            g.setFont(font6);
            g.drawString("Car Statistics", 780,200);
			
			Font font2 = new Font ("Comic Sans", Font.PLAIN,25);
            g.setFont(font2);
			g.drawString("*The help panel has more formulas", 600, 1050);
            
            g.drawString("Your car's weight is : " + totalWeight, 600, 830);
            g.drawString("Your front weight distribution is: " + percentFront, 600, 880);
            g.drawString("Your maximum front downforce is: " + frontLimit, 600, 930);
            g.drawString("The rear roll bar stiffness is: " + rearChoice, 600, 980);
   
            
           /* g.drawString("You must click the mouse to start the car engine once on the track. ", 100,300);
            
            g.drawString("In the Play panel you will be given the weights of various sections of the car.", 100,400);
            
            g.drawString("You must use these weights and suspension formulas in order to get the", 100,475);
            g.drawString("maximum speed from your car.",100,500);
            
            g.drawString("Below are the formulas necessary to attain maximum speed from your car:",100,600);
            g.drawString("formulas",100,600);
            
            Font font1 = new Font ("Comic Sans", Font.PLAIN,50);          //Readable font
            g.setFont(font1);
            g.drawString("Directions", 850, 175);   */
        }
    }
    class RacePanel extends JPanel implements ActionListener
    {
    private int x, y, count, rotationAngle;
    private boolean left, up;
    private Timer timer;
    private boolean startTimer;
    private Timer lap;
    private boolean stopRace;
   
        public RacePanel()
        {
            TimerHandler tHandler = new TimerHandler();
            setBackground(Color.GREEN);
            x = 860; y = 1050; count = 0;
            // initial track location of the car
            left = up = false;        // initialize the movement of the car
            Move move = new Move();
            timer = new Timer(0, move);
            timer.start();    
            
            lap = new Timer(10, tHandler);
            lap.start();
            
            back = new JButton("Back");
            back.setPreferredSize(new Dimension(150,50));    //Sets the size of the button
            back.addActionListener(this);
            add(back);
            repaint();
            
            stopRace = false;
        }
        
        class TimerHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                
                lapTime+= 0.1;
            }
        }
                
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Back"))        //Allows for the button to function
            {
                stopRace = true;
                cards.show(cp ,"Starting");                //Implements StartPanel on the card layout
            }
            /*if(e.getActionCommand().equals("Race") && !finished)
            {
                lapTime++;
            }
            repaint();*/
            
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if(finished || stopRace)
            {
            setBackground(Color.GREEN);
            
            x = 860;
            y = 1050; count = 0;
            
            Color c = g.getColor();
            System.out.println("the color = " + c);
            lapTime = 0.0;
            rotationAngle = 0;
            
            // initial track location of the car
            left = up = false;        // initialize the movement of the car  
            
            stopRace = false;
            finished = false;
            repaint();
            }
            
                        
            //System.out.println("the color = " + g.getColor());
            formula.setSize(1920, 1200);
            formula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            formula.setResizable(true);
            formula.setVisible(true);
            BufferedImage image1 = null;
            try
            {
            image1 = ImageIO.read(new File("Track1.jpg"));           //This is the track the user will be racing on
            }
            catch(IOException e)
            {
                System.out.println("Error: "+e);
            }
            g.drawImage(image1, 0,0, 1920, 1200, null);        //draw images in the background to make the game look visually good
            g.setColor(Color.YELLOW);
            g.drawLine(850,965,850,1160);
            g.setColor(Color.BLACK);
            Font timerFont = new Font("Serif",Font.PLAIN,30);
            g.drawString("" + lapTime, 100,100);
            BufferedImage image6 = null;
            try
            {
            image6 = ImageIO.read(new File("LapEnd.png"));           //This is the track the user will be racing on
            }
            catch(IOException e)
            {
                //System.out.println("Error: "+e);
            }
            g.drawImage(image6, 850,975, 100, 200, null);
            g.setColor(Color.RED);
            BufferedImage image3 = null;
            try
            {
            image1 = ImageIO.read(new File("Car4.png"));        //Imported the car image for the user input on the track
            }
            catch(IOException e)                                //Catches the exceptions
            {
                System.out.println("Error: "+e);
            }
            AffineTransform at = new AffineTransform();
            at.setToTranslation(x,y);
            at.scale(0.1,0.1);
            at.rotate(Math.toRadians(rotationAngle),image1.getWidth()/2, image1.getHeight()/2);    //used to rotate the car
            
            Graphics2D d2d = (Graphics2D) g;
            d2d.drawImage(image1, at,null);
            //d2d.drawImage(image1, x,y,100, 50, null);
            g.setColor(Color.BLACK);
            //g.drawRect(341,370,1256,72);
            //g.drawRect(350,890,1222,69);
            
            /*if(rotationAngle <= 90)
            {
                x -= 5;
            }
            if(rotationAngle <= 180)
            {
                x += 5;
            }
            if(rotationAngle <= 270)
            {
                x -= 5;
            }
            if(rotationAngle <= 360)
            {
                x += 5;
            }
            psychology*/    
            //System.out.println("The value of x = " + x + " The rotation angle is " + rotationAngle + " The value of y is " + y);
            if(count > 0)
            {
            if(rotationAngle == 0 || (rotationAngle == -360))
            {
                x -= 12;
            }
            if(rotationAngle == 15 || (rotationAngle == 375) || (rotationAngle == -345))    //used to turn the car
            {
                x -= 10;
                y -= 2;
            }
            if(rotationAngle == 30 || (rotationAngle == 390) || (rotationAngle == -330))
            {
                x -= 6;
                y -= 4;
            }
            if(rotationAngle == 45 || (rotationAngle == 405) || (rotationAngle == -315))
            {
                x -= 4;
                y -= 6;
            }
            if(rotationAngle == 60 || (rotationAngle == 420) || (rotationAngle == -300))
            {
                x -= 2;
                y -= 10;
            }
            if(rotationAngle == 75 || (rotationAngle == 435) || (rotationAngle == -285))
            {
                x -= 2;
                y -= 10;
            }
            if(rotationAngle == 90 || (rotationAngle == 450) || (rotationAngle == -270))
            {
                x -= 0;
                y -= 12;
            }
            
            if(rotationAngle == 105 || (rotationAngle == 465) || (rotationAngle == -255))
            {
                y -= 10;
                x += 2;
            }
            if(rotationAngle == 120 || (rotationAngle == 480) || (rotationAngle == -240))
            {
                y -= 6;
                x += 4;
            }
            if(rotationAngle == 135 || (rotationAngle == 495) || (rotationAngle == -225))
            {
                y -= 4;
                x += 6;
            }
            if(rotationAngle == 150 || (rotationAngle == 510) || (rotationAngle == -210))
            {
                y -= 2;
                x += 10;
            }
            if(rotationAngle == 165 || (rotationAngle == 525) || (rotationAngle == -195))
            {
                y -= 2;
                x += 10;
            }
            if(rotationAngle == 180 || (rotationAngle == 540) || (rotationAngle == -180))
            {
                y -= 0;
                x += 12;
            }
            if(rotationAngle == 195 || (rotationAngle == 555) || (rotationAngle ==  -165))
            {
                x += 10;
                y += 2;
            }
            if(rotationAngle == 210 || (rotationAngle == 570)|| (rotationAngle == -150))
            {
                x += 6;
                y += 4;
            }
            if(rotationAngle == 225 || (rotationAngle == 585)|| (rotationAngle == -135))
            {
                x += 4;
                y += 6;
            }
            if(rotationAngle == 240 || (rotationAngle == 600)|| (rotationAngle == -120))
            {
                x += 2;
                y += 10;
            }
            if(rotationAngle == 255 || (rotationAngle == 615)|| (rotationAngle == -105))
            {
                x += 2;
                y += 10;
            }
            if(rotationAngle == 270 || (rotationAngle == 630)|| (rotationAngle == -90))
            {
                x += 0;
                y += 12;
            }
            if(rotationAngle == 285 || (rotationAngle == 645)|| (rotationAngle == -75))
            {
                y += 10;
                x -= 2;
            }
            if(rotationAngle == 300 || (rotationAngle == 660)|| (rotationAngle == -60))
            {
                y += 6;
                x -= 4;
            }
            if(rotationAngle == 315 || (rotationAngle == 675)|| (rotationAngle == -45))
            {
                y += 4;
                x -= 3;
            }
            if(rotationAngle == 330 || (rotationAngle == 690)|| (rotationAngle == -30))
            {
                y += 2;
                x -= 10;
            }
            if(rotationAngle == 345 || (rotationAngle == 705)|| (rotationAngle == -15))
            {
                y += 2;
                x -= 10;
            }
            if(rotationAngle == 360 || (rotationAngle == 720))
            {
                y += 0;
                x -= 12;
            }
            }
            
            if((x>=341 && x<= 1577) && (y>=310 && y<=382))
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
                count = 0;
                
                x-=15; y-=2; 
            }
            if((x>=350 && x<= 1572) && (y>=890 && y<=959))
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
                count = 0;
                //x-=15; y-=2;
            }
            if((x>= 0 && x<= 1046) && (y>= 633 && y<= 694))
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
                count = 0;
                //x-=15; y-=2;
            }
            if((x>=1341 && x<= 1596) && (y>=446 && y<=865))
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
                count = 0;
                //x-=5; y-=5;
            }
            if(y >= 1150 || x <= 70 || x>= 1850)
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
                count = 0;
                //x+=15; 
            }
            if(x <= 660 && y <= 180)
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
                count = 0;
                            }
            if(x >= 1260 && y <= 180)
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
                count = 0;
            }
            if((x >= 660 && x <= 1260) && (y <= 60))
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
               count = 0;
                //x-=15; y+=5;
            }
            if((x <= 0 || x >= 1920) || (y <= 0 || y >= 1200))
            {
                x = 850;
                y = 995;
                rotationAngle = 0;
            }
            if((x <=935 && x>= 900)&&(y >=965 && y <= 1160))
            {
                finished = true;
                cards.show(cp , "Ending");        //An ending page is shown once the user completes a lap around the track
            }
            repaint();
    }
    class Move implements ActionListener, MouseListener, KeyListener
    {
        public Move()        //Allows for movement of the car
        {
            addMouseListener(this);
            addKeyListener(this);
        }
        public void actionPerformed(ActionEvent e)
        {
            repaint();

        }
        public void mousePressed(MouseEvent e)
        {
            requestFocusInWindow();
            count++;
            if(count%2 == 1)
                timer.setDelay(60);        //The user has to click the mouse to start the car for a race start
            else
            {
                timer.setDelay(0);
            }
            System.out.println("Count = " + count);
        }
        public void mouseEntered(MouseEvent evt)
        {
            
        }
        public void mouseClicked(MouseEvent evt)
        {
            System.out.println(evt.getX());    //This gets the x value when the mouse is clicked on the race panel
            System.out.println(evt.getY()); //This gets the y value when the mouse is clicked on the race panel
        }
        public void mouseReleased(MouseEvent evt)
        {
            
        }
        public void mouseExited(MouseEvent evt)
        {
            
        }
        public void moveCar(int xinc, int yinc)
        {
                for(int i = 0; i<15 ; i++)
                {
                    
                    x= x+1;
                    y = y+1;
                    repaint();   
                }
        }
        public void keyPressed(KeyEvent e)        //User uses the arrow keys to move the car
        {

            int code = e.getKeyCode();        
            if(code == KeyEvent.VK_DOWN)
            {
                down = true;
            }
            else if(code == KeyEvent.VK_UP)    //The upwards movement is restricted to a slower speed and is affected by the user's performance
            {
                up = true;
            }
            else if(code == KeyEvent.VK_RIGHT) //This is the right movement of the car
            {
                right = true;
            }
            else if(code == KeyEvent.VK_LEFT)
            {
                left = true;
            }
            if(left && count > 0)
            {
                rotationAngle -= 15;
            }else
            if(right && count > 0)
            {
                rotationAngle += 15;
            }

            if(rotationAngle >= 360)
            {
                rotationAngle = rotationAngle - 360;
            }else
            if(rotationAngle <= -360)
            {
                rotationAngle = rotationAngle + 360;
            }
            System.out.println(rotationAngle);
        }
        public void keyReleased(KeyEvent e)
        {
            int code = e.getKeyCode();    
            if(code == KeyEvent.VK_DOWN)
            {
                down = false;
            }
            else if(code == KeyEvent.VK_UP)    //The upwards movement is restricted to a slower speed and is affected by the user's performance
            {
                up = false;
            }
            else if(code == KeyEvent.VK_RIGHT) //This is the right movement of the car
            {
                right = false;
            }
            else if(code == KeyEvent.VK_LEFT)
            {
                left = false;
            }
        }
        public void keyReturned(KeyEvent e){}
        public void keyTyped(KeyEvent e){}
        }
    }
    //If the user goes off the track, the car will reduce to the base speed
    //Variable used for checkpoints the user needs to pass for the laps to count
    //Text fields used for getting using input for the suspension values
    //Check whether the user input roughly matches with the correct calculated values
    //Judge how fast the car should go in the turns based on the inputted values
    //Use flow layout for various text boxes inside the play panel
    //If 'q' is pressed then a pop up will come and this will allow to user to go back to the start screen
}