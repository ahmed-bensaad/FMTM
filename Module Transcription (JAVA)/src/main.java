import org.apache.commons.*;
import java.io.* ; 
import javax.sound.sampled.* ; 


public class main {

	public static void main(String[] args) {
		
		byte[][] data = null ; 
	
		try {
			File file = new File("sin1000.wav") ; 
			AudioInputStream audio = AudioSystem.getAudioInputStream(file) ; 
			AudioFormat format = audio.getFormat() ; 
			System.out.println(format) ; 
			System.out.println(audio.getFrameLength() + " " + format.getFrameSize()) ; 
			data = new byte[(int)audio.getFrameLength()][format.getFrameSize()] ; 
			for (int i = 0 ; i < (int)audio.getFrameLength() ; i++){
				audio.read(data[i]) ;
			}
			for (int i = 0 ; i < (int)audio.getFrameLength() ; i++){
				for(int j = 0 ; j < format.getFrameSize() ; j ++){
					//System.out.print(data[i][j] + " ") ; 
				}
				//System.out.println("") ; 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
				
		
		

	}

}
