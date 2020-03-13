# start karaf
<KARAF_HOME>/bin/karaf

<TAB> # displays all commands
Ctrl+D # stops karaf
start <bundle_id>
stop <bundle_id>
uninstall <bundle_id>
log:display


-----------------------------------
Installing custom service
# inside project root
cd ~/person-service
mvn install

# inside karaf 
# install mvn:<groupId>/<artifactId>/<version>
install mvn:org.edutilos/PersonService/1.0.0                                                                        

# output
Bundle ID: 49


cd ~/person-client
export JAVA_HOME=<path/to/java/home/with/javafx/jars/(jfxrt.jar)>
mvn install

# inside karaf
install mvn:org.edutilos/PersonClient/1.0.0

# output 
Bundle ID: 51

# list services inside karaf 
 list
START LEVEL 100 , List Threshold: 50
ID │ State     │ Lvl │ Version │ Name
───┼───────────┼─────┼─────────┼──────────────────────────────────────────────────────────────────────────────────────────────────────────────
22 │ Active    │  80 │ 4.2.8   │ Apache Karaf :: OSGi Services :: Event
49 │ Installed │  80 │ 1.0.0   │ PersonService
51 │ Installed │  80 │ 1.0.0   │ PersonClient


