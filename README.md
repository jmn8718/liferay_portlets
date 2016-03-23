#API_MARKET PORTLETS
##version
0.7.2 - 2016/01/13

##description
This portlet for Liferay Portal contains 2 portlets.
*User entity
*Get Api Form 

##User entity - Portlet
###description
This portlet evaluate the entities of the user's apps, then indicate to wich entity the user should be redirected.
The screen while the portlet evaluates shows a spinner loader and it takes a seconds to redirect to the especified page.
If the user doesn't have any app, then the portlet indicate "api_market".
If the user only have one app, then the portlet indicate the entity of the app.
If the user have more than one app, then it will evaluate the entity, it will choose the first entity in alphabetical order.
The admin have to set the parameters for access the API
-App Id
-App Key
-Endpoint
-Delay

##Get Api Form - Portlet
###description
This portlet shows a form for the Get Api functionality of the API_Market
The admin have to set the parameters for access the API
-armadilloEndpoint;
-armadilloAuthorization;
-armadilloCipherEndpoint;
-salesforceEndpoint;
-salesforceClientId;
-salesforceClientSecret;
-salesforceContactKey;
-salesforceSubscriberKey;
-salesforceSubscriberKeyUserTemplate;
-salesforceEventDefinitionKey;
-apisAvailables

##compile project
```
mvn clean package liferay:deploy -P liferay6.2.1
```
or
```
sh compile.sh
```