
/*Add MonteScreenRecorder.jar as pom dependency run the following command into you command prompt

mvn install:install-file -Dfile=path/to/MonteScreenRecorder.jar -DgroupId=org.monte -DartifactId=monte-screen-recorder -Dversion=0.7.5 -Dpackaging=jar

Now add the following dependency tag to pom.xml

<dependency>
<groupId>org.monte</groupId>
<artifactId>monte-screen-recorder</artifactId>
<version>0.7.5</version>
<scope>test</scope>
</dependency>*/


package com.poc.testframwork.util;

import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

 
import java.awt.*;
 
public class TestRecording {
 
private ScreenRecorder screenRecorder;
 
public void startRecording() throws Exception {
// Create an instance of GraphicsConfiguration to get the Graphics configuration of the Screen.
// This is needed for ScreenRecorder class.
GraphicsConfiguration gc = GraphicsEnvironment
.getLocalGraphicsEnvironment().getDefaultScreenDevice()
.getDefaultConfiguration();
// Create a instance of ScreenRecorder with the required configurations
screenRecorder = new ScreenRecorder(gc, new Format(MediaTypeKey,MediaType.FILE, MimeTypeKey, MIME_QUICKTIME),
new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,ENCODING_QUICKTIME_JPEG, CompressorNameKey,
ENCODING_QUICKTIME_JPEG, DepthKey, (int) 24,FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
KeyFrameIntervalKey, (int) (15 * 60)),
new Format(MediaTypeKey,MediaType.VIDEO, EncodingKey, "black", FrameRateKey,Rational.valueOf(30)),
null);
screenRecorder.start();
}
 


public void stopRecording() throws Exception {
// Call the stop method of ScreenRecorder to end the recording
screenRecorder.stop();
}
}
