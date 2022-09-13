
//Rikhil Konduru
//Period 2
//4-6-18 (Day 1)
//This program will teach users how to use formulas to make a car faster around a track than its competitors.

import java.util.Scanner;
public class TuningInput
{
    public double percentFront;
    public int totalWeight;
    public double massFront;
    public double massRear;
    public double frontDamping;
    public double rearDamping;
    public double time;
    public double bumpSplit;
    public double frontBump;
    public double rearBump;
    public double frontRebound;
    public double rearRebound;
    public double frontMass;
    public double rearMass;
    public double frontLimit;
    public int rearDownforce;
    public int frontRoll;
    public double rearPercentSquared;
    public double frontPercentSquared;
    public int frontSprings1;
    public double percentRear;
    public double rearRoll;
    public int rearChoice;
    public int rearSprings1;
    public Scanner keyboard;
    
    public TuningInput()
    {    
        totalWeight = 0;
        massFront = 0;
        massRear = 0;
        frontMass = 0;
        frontSprings1 = 0;
        rearMass = 0;
        frontLimit = 0;
        percentRear = 0;
        rearDownforce = 0;
        frontRoll = 0;
        rearRoll = 0;
        rearChoice = 0;
        rearPercentSquared = 0;
        frontPercentSquared = 0;
        keyboard = new Scanner(System.in);
        //Declare values for the front weight distribution
        //Declare values for the rear weight distribution
		//Declare Tuning Values
    }
    public void getDamp()
    {
        totalWeight = (int)(Math.random()*1400+2300);            //Range of the variable car weights
        System.out.println(totalWeight);
        percentFront = (int)(Math.random()*27+38);                //Range of the front percentage
        percentRear = 100 - percentFront;
        System.out.println("\n" + percentFront);
        System.out.println("\n" + percentRear);
        massFront = totalWeight*percentFront/6400;
        System.out.println("\n" + massFront);
        time = 1.5;
        bumpSplit = 1.6667;
        frontDamping = (0.4054651081)*(totalWeight)*((percentFront/100)/32/time);		//Equation for damping
        rearDamping = (0.4054651081)*(totalWeight)*(((100-percentFront)/100)/32/time);	//Equation for damping
        frontRebound = frontDamping/bumpSplit;
        frontBump = frontDamping/bumpSplit/(bumpSplit-1);
        rearBump = rearDamping/bumpSplit/(bumpSplit-1);
        rearRebound = rearDamping/bumpSplit;
    }
    public void getSpringRates()
    {
        frontMass = (totalWeight*percentFront/6400);
        frontSprings1 = (int)(frontMass*31.193);
        rearMass = (totalWeight*percentRear/6400);
        rearSprings1 = (int)(rearMass*31.193);
        System.out.println("\n\nSprings:");
        System.out.println("Your front springs should be at " + frontSprings1 + " pounds");
        System.out.println("Your rear springs should be at " + rearSprings1 + " pounds");
    }
    public void getRollBar()
    {
        System.out.println("\n\nRoll Bars:");
        rearChoice = (int)(Math.random()*15+25);
        if(percentFront < 50)
        {
            frontRoll = (int)((percentFront)/(percentRear)*(rearChoice));
            System.out.println("Your front roll bar value would be " + frontRoll);
        }
        else if (percentFront > 50)
        {
            frontPercentSquared = (Math.pow(percentFront, 2));
            rearPercentSquared = (Math.pow(percentRear, 2));
            frontRoll = (int)((rearChoice)*(rearPercentSquared)/(frontPercentSquared));
            System.out.println("Your front roll bar value would be " + frontRoll);
        }
        else
        {
            frontRoll = rearChoice;
            System.out.println("Your front roll bar value would be " + frontRoll);
        }
    }
    public void getDownForce()
    {
        percentRear = 100 - percentFront;
        System.out.println("\n\nDownforce:");
        System.out.println("To calculate balanced downforce values, enter the maximum" +
        " front downforce you can possibly get.");
        frontLimit = Math.random()*125+100;
        rearDownforce = (int)(percentRear*(frontLimit)/percentFront);
        System.out.println("Your rear downforce should be: " + rearDownforce);
    }
    public static void main(String[] args)
    {
        TuningInput ti = new TuningInput();
        ti.runTuning();
    }
    public void runTuning()
    {
        getDamp();
        getSpringRates();
        getRollBar();
        getDownForce();
        
    }
}