
import java.util.Calendar;

import java.util.ArrayList; 
@SuppressWarnings("all")
public class Flight{	
	private String arrivalTime; 
	private String airlines;
	private String aircraftModel;
	private String departure;
	public ArrayList<String> weekdays;
	private String flightNO;	
	private float timeTaken; // minutes
	public Capital from, to;
	public boolean isAvailable;
	private int delay;
	private float remains;
	private boolean delayed;
	
	
	public Flight(String arrivalTime,String airlines,String aircraftModel,String departure, 
				  ArrayList<String> weekdays, String flightNO,float timetaken,Capital from,Capital to) {
		this.arrivalTime = arrivalTime;
		this.airlines = airlines;
		this.aircraftModel = aircraftModel;
		this.departure = departure;
		this.weekdays = new ArrayList<String>();
		this.flightNO = flightNO;
		this.timeTaken = timetaken;
		this.from = from;
		this.to = to;
		this.isAvailable = true;
		this.delay = 0;
		this.remains = timetaken;
		this.delayed = false;
	}
	
	public Capital getFrom() {
		return from;
	}
	public Capital getTo() {
		return to;
	}
	public float getTimeTaken() {
		return timeTaken;
	}
	public String getFlightNO() {
		return flightNO;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public String getDeparture() {
		return departure;
	}
	public String getAirlines() {
		return airlines;
	}
	public String getAircraftModel() {
		return aircraftModel;
	}
	public float getRemains() {
		return remains;
	}
	public boolean getDelayed() {
		return delayed;
	}
	public int getDelayTime() {
		return delay;
	}
	public void setDelay(int a) {
		this.delay = a;
	}
	public void setRemains(float a) {
		this.remains=a;
	}
	
	public String getWeekdays() {
		String result = "";
		for(String i: this.weekdays) {
			result = result + String.valueOf(i.charAt(0)) + String.valueOf(i.charAt(1))+", ";
		}
		return result;
	}
	
	public void setAirlines(String a) {
		this.airlines = a;
	}
	
	
	
	
	
	
	
	
	public void setTo(Capital to) {
		this.to = to;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public void setTimeTaken(float timeTaken) {
		this.timeTaken = timeTaken;
	}

	public void setFrom(Capital from) {
		this.from = from;
	}

	public boolean getStatus() {
		return this.isAvailable;
	}
	public void cancel() {
		if(from.getControltw().cancelFlight() == false) {
			this.isAvailable = false;
		}
		else if(to.getControltw().cancelFlight() == false) {
			this.isAvailable = false;
		}
		else {
			System.out.println("The control tower has not canceled the flight.");
		}
	}
	public void addWeekday(String newDay) {
		this.weekdays.add(newDay);
	}
	public void deleteWeekday(String deleteDay) {
		this.weekdays.remove(deleteDay);
	}
	
	
	//CALENDAR CONVERTIONS
	
	
	public Calendar departureToCalendar() {
		
		Calendar dep = Calendar.getInstance();
		String dr[] = this.departure.split(":");
		dep.set(Calendar.HOUR, Integer.parseInt(dr[0]));
		
		dep.set(Calendar.MINUTE, Integer.parseInt(dr[1]));
		
		
		return dep;	
	}
	
	public Calendar arrivalToCalendar() {
		
		Calendar arr = Calendar.getInstance();
		String ar[] = this.arrivalTime.split(":");
		arr.set(Calendar.HOUR, Integer.parseInt(ar[0]));
		
		
		arr.set(Calendar.MINUTE, Integer.parseInt(ar[1]));
		
		return arr;	
	}
	
	public void delay(int a) {
		this.delay = this.to.delaySignal(a);
		
		Calendar dep = departureToCalendar();
		Calendar arr = arrivalToCalendar();
		dep.add(Calendar.MINUTE,this.delay);
		arr.add(Calendar.MINUTE,this.delay);
		
		this.arrivalTime = String.valueOf(arr.get(Calendar.HOUR_OF_DAY)) +":"+  String.valueOf(arr.get(Calendar.MINUTE));
		this.departure = String.valueOf(dep.get(Calendar.HOUR_OF_DAY)) +":"+String.valueOf(dep.get(Calendar.MINUTE)) ;
		this.delayed = true;
	}
}
