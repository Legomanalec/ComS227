/**
 * @authorAlecMeyer
 * 
 */

package hw1;

import java.lang.Math;

public class WirelessPrinter {

	
	//Constants
	public static final double NEW_CARTRIDGE_INK_LEVEL = 1.0;
	
	public static final int PAGES_PER_CARTRIDGE = 1000;
	
	public static final int TRAY_CAPACITY = 500;
	
	
	
	/**
	 *private variables used throughout the class 
	 */
	private boolean printerConnected;
	
	private boolean printerOn;
	
	private double inkLevel;
	
	private int paperLevelExact;
	
	private int paperLevelInitial;
	
	private double inkLevelInitial;
	
	private int pagesPrinted;
	
	private double inkToUse;
	
	private int paperLevelAfterPrint;

	
	public WirelessPrinter(double ink, int paper) {
		/**
		 * @param 'double int' and 'int paper', are inputs in the WirelesPrinter constructor
		 * 
		 * The variables in this method set the initial values inputed through the constructor
		 *  
		 */
				
		inkLevel = ink;
		paperLevelExact = paper;
		
		paperLevelInitial = paper;
		inkLevelInitial = ink;
		paperLevelAfterPrint = paper;
	
	}
	
	public WirelessPrinter() {
		//Default value of ink for a constructor with no parameters
		inkLevel = .5;

	}
	//Setter methods
	public void connect() { 
		//Connect the printer to a network
		printerConnected = true;
	}
	
	public void disconnect() {
		//Disconnect the printer from a network
		printerConnected = false;
	}
	
	public void loadPaper(int pages) {
		/**
		 * Load the pages into the printer
		 * @param is the number of pages inputed to print
		 */

		
		 //this statement is used to prevent putting more than 
		 //TRAY_CAPACITY of paper into the printer, the value will always be 
		 //equal to, or less than TRAY_CAPACITY
		 
		paperLevelExact = Math.min(paperLevelExact + pages, TRAY_CAPACITY);
	}
	
	public void print(int pages) {
		//Print number of pages
		if (!isConnected())
			return;
				
				
		
		 //this statement prevents the paperLevelExact to go negative by getting the 
		 //max value of itself and '0' so that the value is always positive
		 
		paperLevelExact = Math.max(paperLevelExact - pages, 0);
		
		inkToUse = (paperLevelAfterPrint - paperLevelExact) * 0.001;
	
		paperLevelAfterPrint = paperLevelExact;
		
		inkLevel = inkLevel - inkToUse;
		
		
		 //Statement used to find the number of pages printed with ink
		 //(inkLevel - inkLevelInitial)*1000 finds the total number of possible pages printed with ink
		 //(paperLevelInitial - paperLevelExact) is used so that if the above function causes an impossible
		 //amount of paper it will us the minimum value of paper used
		 
		 
		pagesPrinted = (int) Math.min((paperLevelInitial - paperLevelExact), (inkLevelInitial - inkLevel)*1000);
		
		 
	}
	
	public void replaceCartridge() {
		//replaces the cartridge
		inkLevel = NEW_CARTRIDGE_INK_LEVEL;
	}
	
	public void turnOff() {
		//Set the printer status to 'off'
		printerOn = false;
		disconnect();
	}
	
	public void turnOn() {
		//Set the Printer status to 'on'
		printerOn = true;
		connect();
	}
	
	
	
	//Getter methods
	public boolean isConnected() {
		/**
		 * @return the valuse 'true' or 'false' to check if the printer is connected
		 */
		return printerConnected;
	}
	
	public boolean isOn() {
		/**
		 * @return the value 'true' or 'false' to check if printer is on
		 */
		return printerOn;
	}
	
	public int getPaperLevel() {
		/**
		 * @return percentage of paper left
		 */
		int paperLevel = (int) Math.round((paperLevelExact*100 / 500));
		return paperLevel;
	}
	
	public double getInkLevel() {
		/**
		 * @return ink value between 0.0 and 1.0
		 * round method is used because of the way java handles doubles
		 */
		return Math.max(Math.round(inkLevel * 100.0) / 100.0, 0.0);
	}
	
	public int getPaperLevelExact() {
		/**
		 * @return number of pages left
		 */
		return Math.max(paperLevelExact,  0);
	}
	
	public int getTotalPagesPrinted() {
		/**
		 * @return total number of pages printed with full ink
		 */
		return pagesPrinted;
	}
	
	public int getTotalPaperUsed() {
		/**
		 * @return the number of pages used
		 */
		return paperLevelInitial - paperLevelExact;
	}
}
