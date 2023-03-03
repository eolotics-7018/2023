package frc.robot.subsystems;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import static frc.robot.Constants.Arduino.*;

public class Arduino {
	// Se define el canal de comunicación por I2C con el Arduino
	private static I2C Wire = new I2C(Port.kOnboard, kArduinoSlaveId);
	
	// Función que manda datos al arduino en forma de bytes
	public static void write(String input){
        char[] CharArray = input.toCharArray();
        byte[] WriteData = new byte[CharArray.length];
        for (int i = 0; i < CharArray.length; i++) {
            WriteData[i] = (byte) CharArray[i];
        }
        Wire.writeBulk(WriteData, WriteData.length);//sends each byte to arduino		
	}

	// Función que recibe datos del arduino
	public static String read(){
		byte[] data = new byte[kMaxBytes];
		Wire.read(kArduinoSlaveId, kMaxBytes, data);
		String output = new String(data);
		int pt = output.indexOf((char)255);
		return (String) output.subSequence(0, pt < 0 ? 0 : pt);
	}
	
}