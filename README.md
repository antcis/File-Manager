# File-Manager
MultiThreading Client-Server


## Getting Started
This Project allows a client machine to communicate with a storage server.
Requirements of network programming in this project:
 * client/server communications using the sockets API
 * multithreaded application, dealing with multiple clients
 
## Running the tests
* Execute the client ClientTCP.java in a console and the server ServerTCP.java in another one.
* Follow the instruction of the client. The following commands are available:
  * **ls**: display folders and files in the current server folder.
  * **cd Folder_Name** : allows to enter a parent or child directory in the server and thus modify the current server folder.
  (example : cd folder, cd ../folder)
  * **mkdir Folder_Name** : allows to create a directory in the current server folder.
  * **upload File_Name** : allows to upload a file from the local client folder to the the current server folder.
  * **download File_Name** : allows to download a file from the current server folder to the local client folder.
  * **exit** : disconnect the client.

## Authors
* **Sandratra RASENDRASOA** (2018)
* **Antonia FRANCIS** (2018)
