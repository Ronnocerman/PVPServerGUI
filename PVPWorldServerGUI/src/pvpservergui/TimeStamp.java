package pvpservergui;

public class TimeStamp implements Comparable<TimeStamp>{
	private byte year;
	private byte month;
	private byte day;
	private byte hour;
	private byte minute;
	private byte second;
	
	public TimeStamp(byte year, byte month, byte day, byte hour, byte minute, byte second)throws IllegalArgumentException{
		if(year < 11){
			throw new IllegalArgumentException("year cannot be less than 11");
		}
		if(month < 0){
			throw new IllegalArgumentException("month cannot be less than 0");
		}
		if(month > 11){
			throw new IllegalArgumentException("month cannot be greater than 11");
		}
		if(day > 31){
			throw new IllegalArgumentException("day cannot be greater than 31");
		}
		if(day < 1){
			throw new IllegalArgumentException("day cannot be less than 1");
		}
		if(hour < 0){
			throw new IllegalArgumentException("hour cannot be less than 0");
		}
		if(hour > 23){
			throw new IllegalArgumentException("hour cannot be greater than 23");
		}
		if(minute < 0){
			throw new IllegalArgumentException("minute cannot be less than 0");
		}
		if(minute > 59){
			throw new IllegalArgumentException("minute cannot be greater than 59");
		}
		if(second < 0){
			throw new IllegalArgumentException("second cannot be less than 0");
		}
		if(second > 59){
			throw new IllegalArgumentException("second cannot be greater than 59");
		}
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public byte getYear(){
		return year;
	}
	
	public byte getMonth(){
		return month;
	}
	
	public byte getDay(){
		return day;
	}
	
	public byte getHour(){
		return hour;
	}
	
	public byte getMinute(){
		return minute;
	}
	
	public byte getSecond(){
		return second;
	}
	
	public byte[] values(){
		return new byte[]{year, month, day, hour, minute, second};
	}
	
	public short fullYear(){
		return (short) (2000 + year);
	}
	
	public int compareTo(TimeStamp o){
		return (year > o.year) ? 1 : (year < o.year) ? -1 : (month > o.month) ? 1 : (month < o.month) ? -1 : (day > o.day) ? 1 : (day < o.day) ? -1 : (hour > o.hour) ? 1 : (hour < o.hour) ? -1 : (minute > o.minute) ? 1 : (minute < o.minute) ? -1 : (second > o.second) ? 1 : (second < o.second) ? -1 : 0;
	}
	
	private String yearToString(){
		String base = "2000";
		String temp = year+"";
		return base.substring(0, base.length() - temp.length()) + temp;
	}
	
	public String toString(){
		return "(" + fullYear() + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second + ")";
	}
}
