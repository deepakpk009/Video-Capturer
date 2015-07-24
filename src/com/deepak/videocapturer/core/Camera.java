/*
This file is part of VideoCapturer

VideoCapturer is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

VideoCapturer is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with VideoCapturer.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.deepak.videocapturer.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.*;
import javax.media.protocol.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

/**
 *
 * @author deepak
 */

/*
 * the camera class which provides the methods for video capturing
 */
public class Camera {

    private MediaLocator mediaLocator = null;
    private DataSource dataSource = null;
    private Player player = null;
    private FrameGrabbingControl framGrabbingControl;
    private Buffer buffer;
    private Image image;
    private BufferToImage bufferToImage;

    /*
     * class contructor
     */
    public Camera() throws IOException, NoDataSourceException, NoPlayerException, CannotRealizeException, InterruptedException {
        // get the default web cam
        mediaLocator = new MediaLocator("vfw://0");
        // create data source
        dataSource = Manager.createDataSource(mediaLocator);
        // create player
        player = Manager.createRealizedPlayer(dataSource);
    }

    /*
     * start method to start the capturing device
     */
    public void start() {
        // starting the player using thread
        new Thread() {

            @Override
            @SuppressWarnings("static-access")
            public void run() {
                try {
                    // start player
                    player.start();
                    // wait for 3 seconds
                    this.sleep(3000);
                    // get the frame grabbing controller
                    framGrabbingControl = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
                    // grab a frame for getting the format
                    buffer = framGrabbingControl.grabFrame();
                    // initialise buffer to image object for converting it to an image
                    bufferToImage = new BufferToImage((VideoFormat) buffer.getFormat());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Camera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // start the thread
        }.start();
    }

    /*
     * method to stop the player
     */
    public void stop() {
        player.stop();
    }

    /*
     * method to get the captured frame image
     */
    public BufferedImage getPicture() {
        // if frame grabber is present then
        if (framGrabbingControl != null && bufferToImage != null) {
            // grab a frame
            buffer = framGrabbingControl.grabFrame();
            // UPDATE:
            // bufferToImage initilization is moved to start() method 
            // convert it to image
            image = bufferToImage.createImage(buffer);
            return (BufferedImage) image;
        } else {
            return null;
        }
    }
}
