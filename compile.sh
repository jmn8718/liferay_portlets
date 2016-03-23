rm apimarket-portlets-portlet.war
mvn clean package -P liferay6.2.1
cp ./target/apimarket-portlets-1.1.0-SNAPSHOT.war apimarket-portlets-portlet.war
