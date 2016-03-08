# Demo Application for WSO2 App Factory
```
+----+   +------------------------------+                +------------------+
|    +-->|                              |                |                  |
| DB |   |  Sales Data Application/API  |                |  Statistics API  |
|    |<--+                              |                |                  |
+----+   +-------------+----------------+                +----------+-------+
                       |                                            |
                       |           +-------------------------+      |
                       |           |                         |      |
                       +---------> |  Dashboard Application  | <----+
                                   |                         |
                                   +-------------------------+
```

As in the above diagram this is consists with 3 applications. Dashboard application will get the data from both Statistics API(Which is hosted in external environment instead of the App Factory Setup) and the Sales Data API(Which is in the WSO2 API Manager).

To deploy these applications, please follow below steps.

1. Clone the repository 

2. Statistics API(External API) Creation.

	For this demo application, we are going use separate tenant for the Statistics API creation, such that we can use
	 it in the Dashboard application as an External API.
  1. Create a tenant. (Lets say external.com)

  2. Log in to that tenant and create a Uploadable Jaggery Application by providing the AFDemo/statsapi/statsapi.zip file as the uploadable file

  3. Wait until it is successfully deployed and once successfully deployed, mark down the Launch URL.
     
    ex: [https://asproduction.appfactory.private.wso2.com/t/samtest.com/jaggeryapps/statsapi-1.0.0/]
 (https://asproduction.appfactory.private.wso2.com/t/samtest.com/jaggeryapps/statsapi-1.0.0/)

  4. Log into API publisher

    [https://apimanager.appfactory.private.wso2.com:9449/publisher](https://apimanager.appfactory.private.wso2.com:9449/publisher)

    Then create and publish an API providing above url as the Production and Sandbox endpoint. (Please refer [1](https://docs.wso2.com/display/AM190/WSO2+API+Manager+Documentation) and [2](https://docs.wso2.com/display/AM190/Create+and+Publish+an+API) for more information). 

  5. Log into API store

    [https://apimanager.appfactory.private.wso2.com:9449/store](https://apimanager.appfactory.private.wso2.com:9449/publisher)

    Then subscribe to above published API, generate the keys and mark down the 

       1. Published API URL

       2. Consumer Key

       3. Consumer Secret

       4. Access Token

  6. Test the API with below curl command by replacing ACCESS_TOKEN with above access token and  PUBLISHED_API_URL with above published url

		`curl -X GET -v -k -i  -H "Authorization: Bearer ACCESS_TOKEN" PUBLISHED_API_URL/stats`

    You will get an sample response object like in *AFDemo/sampleoutputs/statsapi_output.json*	

3. Go back to App Factory 
  
  [https://apps.appfactory.private.wso2.com:9443/appmgt](https://apps.appfactory.private.wso2.com:9443/appmgt)

  Logout and create a new tenant and login is that tenant

4. Create 4 members with roles as below

    <table>
      <tr>
        <th>USER</th>
        <th>ROLE</th>
      </tr>
      <tr>
        <td>devuser</td>
        <td>Developer</td>
      </tr>
      <tr>
        <td>qauser</td>
        <td>QA</td>
      </tr>
      <tr>
        <td>devops</td>
        <td>Dev Ops</td>
      </tr>
      <tr>
        <td>owner</td>
        <td>Application Owner</td>
      </tr>
    </table>


5. Create Sales Data API

    1. Login as "owner"(User with Application Owner role) create a java application.Wait until it is successfully deployed and once it is successfully deployed

    2. Go to the Team page and invite dev , qa and devops users that you have created above, to this application. Then logout

    3. Login as the devuser. Go to the above created app and create a version **1.0.0**

    4. As in the diagram, Sales Data API will require a database to store and read the sales data. For that create a database and note down the DB Url, DB Username  and the Password.

    5. Execute AFDemo/salesdata/src/main/resources/salesdata.sql file against the above created database

      `mysql -h DB_HOST_NAME -uUSERNAME -pPASSWORD DB_NAME < AFDemo/salesdata/src/main/resources/salesdata.sql`

    6. Create a datasource named "**salesdata_ds**" with above created DB information

    7. Go the repository page and copy the repository URL

    8. Clone the repository and **checkout the 1.0.0 branch.**

    9. Open the pom.xml and markdown the artifactId element and name element

    10. Replace the src folder with AFDemo/salesdata/src/ folder and replace the pom.xml with AFDemo/salesdata/pom.xml

    11. Open the pom.xml and change the artifactId to the artifactId, and name to name that you have marked down at step ix.

    12. Build, Commit and push the changes.

    13. Go to the Overview page and wait until version 1.0.0 is deployed with latest changes. For the first commit it will take some time since jenkins needs to download all the required artifacts. (See build logs page)

    14. Once version 1.0.0 is successfully deployed go to the launch url and you will see the updated application.

    15. Copy the development stage launch URL(This will be needed in a later step)

    16. You can see the rest API exposed by this application, by going to the url <LAUNCH_URL>/services/sales/regionalData 

6. Promote the Sales Data API upto the Production stage.

    1. Promoting to the Testing Stage

        1. Login as the devuser and promote it to the Testing Stage.

        2. Logout and Login as the qauser user and click "accept and deploy" button on the overview page. This will deploy the version 1.0.0 on the Testing stage. Now if you go to the Datasources page you will see the same datasource that we have created above, has copied to the Testing stage.

        3. Since the datasource in the Testing stage still pointing to the DB that created in the Development Stage, we have to create a new database for the Testing stage.Please follow above 5.IV - 5.V steps to create the DB and generate the required table structure. Please use different db name, db user for the Testing Stage DB

        4. Update the Datasource in the Testing stage with above created DB details.

        5. Go to the launch URL. 

    2. Promoting to the Production Stage.

        1. Login as the qauser and promote it to the Production stage.

        2. Logout and login as the devops user and click "accept and deploy" button on the overview page. This will deploy the version 1.0.0 on the Production stage. Now if you go to the Datasources page you will see the same datasource that we have created above, has copied to the Production stage. 
        
        3. Create a new database and user for the Production stage, create the db structure and update the Datasource with the created database as in 6.i.c - 6.i.d
        
        4. Go to the launch URL 
        
        5. Copy the Production stage launch URL(This will be needed in a later step)
        
7. Publish API for Sales Data API 

    1. Log into API publisher as the tenant admin

      [https://apimanager.appfactory.private.wso2.com:9449/publisher](https://apimanager.appfactory.private.wso2.com:9449/publisher)

      Then create and publish an API providing the Development launch URL that you have copied in the 5.IX step as the Sandbox endpoint and Production launch URL that you have copied in the 6.B.V as the Production Endpoint . (Please refer [1](https://docs.wso2.com/display/AM190/WSO2+API+Manager+Documentation) and [2](https://docs.wso2.com/display/AM190/Create+and+Publish+an+API) for more information). 

1. Create Sales dashboard.

    1. Login as the App Owner and create new Java Application

    2. Subscribe to the Sales Data API

        1. Go to the API store and subscribe to the above Sales Data API.

          [https://apimanager.appfactory.private.wso2.com:9449/store](https://apimanager.appfactory.private.wso2.com:9449/publisher)

          Test the API with below curl command by replacing ACCESS_TOKEN with above access token and PUBLISHED_API_URL with above published url

		      `curl -X GET -v -k -i  -H "Authorization: Bearer ACCESS_TOKEN" PUBLISHED_API_URL/services/sales/regionalData`

        2. Go the Resource/apis page and click show keys and sync keys. This will sync the API keys generated by the above step.

        3. Go the the properties section and create a property with the name "**tokenUrl**" and value as        
        
          https://gateway.apimanager.appfactory.private.wso2.com:8249/token

        4. Create a another property with the name "**salesDataApi**" and put the Sales Data API url that you have subscribed in above steps

    3. Add Statistics API as an **External API **

        5. Go the Resources/apis page and add external API using external APIs section. Use URL and keys that you have copied in step 2.V. When you creating the API remember to use "**statsdataapi**" as the API name.

    4. Now you have configured the all resources required for the Dashboard application. Now invite all members to the Dashboard application as in step 5.II.

    5. Logout and login as the devuser and create branch 1.0.0. Then copy the repository URL.

    6. Clone the repository and **checkout the 1.0.0 branch.**

    7. Open the pom.xml and markdown the artifactId element and name element

    8. Replace the src folder with AFDemo/salesdashboard/src/ folder and replace the pom.xml with AFDemo/salesdashboard/pom.xml

    9. Open the pom.xml and change the artifactId to the artifactId, and name to name that you have marked down at step vii.

    10. Open the src/main/resources/config.properties file and Change the value of the "ApplicationKey" element to the artifactId that you have marked down at step vii.

    11. Build, Commit and push the changes.

    12. Go to the Overview page and wait until version 1.0.0 is deployed with latest changes. For the first commit it will take some time since jenkins needs to download all the required artifacts. (See build logs page)

    13. Once version 1.0.0 is successfully deployed go to the launch url and you will see the Nice Dashboard application.

**References**

[1] https://docs.wso2.com/display/AM190/WSO2+API+Manager+Documentation

[2] https://docs.wso2.com/display/AM190/Create+and+Publish+an+API
