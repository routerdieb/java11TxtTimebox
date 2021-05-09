import java.time.*;
import java.time.format.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Locale;

class TimeboxCreator{
	LocalTime start;
	LocalTime end;

	public TimeboxCreator(String startTime, String endTime){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
      		this.start = LocalTime.parse(startTime, dateTimeFormatter);
		this.end = LocalTime.parse(endTime, dateTimeFormatter);
	}

	public void createStringTimebox() throws Exception{
		File file = new File(".\\timebox.txt");
		FileWriter fw = new FileWriter(file);

		fw.write("Timebox \n");
		Locale l = Locale.getDefault();		
		String country = l.getDisplayCountry();
		fw.write(country + "          " + LocalDate.now() + "\n");
		fw.write("-----------------------------------------\n");		

		
		LocalTime tmp = start;
		for (int position = 0;tmp.isBefore(end);position++){
			fw.write(tmp +" - " + tmp.plusMinutes(25) + " || \n");
			if (position%4 == 3){
				tmp = tmp.plusMinutes(60);
				fw.write("-----------------------------------------\n");
			}else{
				tmp = tmp.plusMinutes(30);
			}
		}
		fw.close();
				
	}

	public static void main(String[] args) throws Exception{
		String start, end;
		if (args == null || args.length == 0){
			start = "08:00";
			end = "16:00";		
		}else{
			start = fill(args[0]);
			end = fill(args[1]);
		}
		TimeboxCreator tc = new TimeboxCreator(start, end);
		tc.createStringTimebox();
	}
	private static String fill(String s){
		if(!s.contains(":")){
			s += ":00";
		}
		while(s.length() < 5){
			s = "0"+s;
		}
		return s;
	}



}