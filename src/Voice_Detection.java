import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.sound.sampled.*;

public class Voice_Detection  {

    TargetDataLine targetDataLine;
    AudioFormat audioFormat;
    double max;
    double p0 = 20e-6;

    public Voice_Detection(){
        System.out.println("STARTING RECORDER");
        try{
             audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16,
                    2, 4, 44100, false);
            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            if (!AudioSystem.isLineSupported(dataInfo)){
                System.out.println("Not supported");
            }
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataInfo);
            targetDataLine.open();
            targetDataLine.start();
            max = Math.pow(2, audioFormat.getSampleSizeInBits() - 1);
            System.out.println(targetDataLine.getLevel());
             Thread audioRecorderThread = new Thread(){
              @Override
              public void run() {
                  System.out.println("test");
                  AudioInputStream recordingStream = new AudioInputStream(targetDataLine);


                  System.out.println("Stopped Recording!");
              }
            };
            audioRecorderThread.start();
        }

        catch (Exception e){
            System.out.println(e);
        }


    }

    public double measure() {
        int numBytes = 1024;
        byte[] bytes = new byte[numBytes];
        int bytesRead = targetDataLine.read(bytes, 0, numBytes);

        double[] samples = new double[bytesRead / 2];
        for (int i = 0, s = 0; i < bytesRead;) {
            int sample = 0;
            sample |= bytes[i++] & 0xFF;
            sample |= bytes[i++] << 8;

            double normalized = sample / max;

            samples[s++] = normalized;
        }

        // calculate the RMS of the samples
        double sum = 0;
        for (double sample : samples) {
            sum += sample * sample;
        }
        double rms = Math.sqrt(sum / samples.length);

        // calculate the SPL
        double spl = 20 * Math.log10(rms / p0);

        // return the SPL
        return spl;
    }



}
