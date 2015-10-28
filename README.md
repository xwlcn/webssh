Web ssh shell & sftp
====================
SSH shell Web & SFTP is a SSH based web remote management system, due to the limited time, the current version of the shell SSH terminal only supports password authentication, if you have better suggestions, please send to the mailbox: 331319769@qq.com.

Administrators can operate like the real shell, such as the installation of software, modify the file, and so on, and these operations are able to real-time feedback to the console terminal, in order to achieve this, I use the websocket spring for remote interaction, I only configured websocket spring, for not supporting websocket browser, you need to manually configure the sockJs, of course, it is very simple.

For users not familiar with the shell commands, we added the SFTP feature, use it to carry out visual operations, of course, it is difficult to completely simulate the effect of the software, only provides a number of simple functions, such as: File visualization, new folder, modify file permissions properties, upload the local file, download remote files, delete files, only this. If you have better suggestions, please send to the mailbox: 331319769@qq.com.

The reason for this project is only because of interest, it may be that I am lazy, so I do not have to do a number of security measures, only to the user's password encryption processing, taking into account security issues, users can own their own extensions, such as adding keys SSH authentication, and HTTPS protocol.
Prerequisites
-------------
* Java JDK 1.7 or greater
http://www.oracle.com/technetwork/java/javase/overview/index.html

* Tomcat server 7.0 or greater
https://tomcat.apache.org

* Browser with Web Socket support
http://caniuse.com/websockets

Screenshots
-----------
![Regist](https://github.com/xwlcn/webssh/raw/master/screenshots/regist.png)

![Login](https://github.com/xwlcn/webssh/raw/master/screenshots/login.png)

![Home](https://github.com/xwlcn/webssh/raw/master/screenshots/home.png)

![Manage Systems](https://github.com/xwlcn/webssh/raw/master/screenshots/login.png)

![User Center](https://github.com/xwlcn/webssh/raw/master/screenshots/usercenter.png)

![Open Machine](https://github.com/xwlcn/webssh/raw/master/screenshots/openmachine.png)

![Shell](https://github.com/xwlcn/webssh/raw/master/screenshots/shell.png)

![Delete Machine](https://github.com/xwlcn/webssh/raw/master/screenshots/deletemachine.png)

![Modify Permissions](https://github.com/xwlcn/webssh/raw/master/screenshots/modifypermissions.png)

![Delete File](https://github.com/xwlcn/webssh/raw/master/screenshots/deletefile.png)

![Upload File](https://github.com/xwlcn/webssh/raw/master/screenshots/uploadfile.png)

![Change Directory](https://github.com/xwlcn/webssh/raw/master/screenshots/changedir.png)