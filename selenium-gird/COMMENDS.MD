## Command to start Hub
````
java -jar selenium-server-4.1.2.jar hub
````
## Command to start Node

This node will register:
* Chrome
* Firefox
* Safari
  
  Note: IF you are on Windows OS, please remove part related to safari and add for edge

````
  java -jar -Dwebdriver.<type>.<name>s path/to/selenium/server.jar node --config path/to/nodeConfig.json
  java -jar -Dwebdriver.msedge.driver=msedgedriver.exe -Dwebdriver.chrome.driver=chromedriver.exe selenium-server-4.1.2.jar node --config node_config.json
  
````
