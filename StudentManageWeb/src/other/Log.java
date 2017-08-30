package other;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	 public Log(String actionFormation) {
			try {
				BufferedWriter buffereWriter = new BufferedWriter(new FileWriter("f:\\eclipse_workspace\\School_JavaWeb\\WebContent\\manage\\log.txt",true));
				buffereWriter.write(new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(new Date())+ actionFormation);
				buffereWriter.newLine();
				buffereWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	 public static void main(String[] args){
		 new Log(null);
	 }
}
