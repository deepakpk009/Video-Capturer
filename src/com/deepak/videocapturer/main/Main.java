/*
 * -----------------------------------
VideoCapturer
-----------------------------------
a simple video capturer module using JMF
-----------------------------------

Developed By : deepak pk
Email : deepakpk009@yahoo.in
-------------------------------------
PREREQUESTE :

Inorder to run this project you should
hava JMF (Java Media Framework)installed
and the video capturer device detected by the JMF
-------------------------------------
This Project is Licensed under LGPL
-------------------------------------

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.deepak.videocapturer.main;

import com.deepak.videocapturer.gui.MainWindow;

/**
 *
 * @author deepak
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}
