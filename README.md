
#### TransmissionTradeStoreProject <H6>
Project of Transmission Trade

#### Steps to run the code <H6>
  - Downlaod the repo.
  - Import the Java Project
  - Right click on project->properties->java Build Path->libraries->Add libraries -> Select JUnit 4-> Select Junit4.

##### Steps to execute the code <H6>
  - Execute TestTradeStore.java with Junit Test.


###### PROBLEM STATEMENT <h6> 
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order
TradeId	|Version |	Counter-Party Id |	Book-Id	| Maturity Date |	Created Date |	Expired
Tl      | 1      | CP-1              | B1       | 20/05/2020    | <today date> | N
T2      | 2      | CP-2              | B1       | 20/05/2021    | <today date> | N
T2      | 1      | CP-1              | B1       | 20/05/2021    | 20/05/2021   | N
T2      | 3      | CP-3              | B2       | 20/05/2014    | <today date> | Y

###### There are couples of validation, we need to provide in the above assignment <h6> 
  
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update expire flag if in a store the trade crosses the maturity date.


  
##### Built information <h6>
- Java Version Used:-JDK 1.8,JRE 1.8
- Junt Version Used- JUnit 4

 ##### Built information <h6>
1. Check if 1st Trade is added.
2. Check if Version of the particualr trade id is high the list will be updated.
3. Check if Version of the particualr trade id is same the list will be updated.
4. Check if Version of the particualr trade id is low the trade will throw exception.
5. Check if maturity Date is greater than todays date the trade is added.
6. Check if maturity Date is lower than todays date the Trade will not be added.
7. Check If Maturity Date is Expired it will update the Expired Flag
8. Check Expired T3	3	CP-3	B2	20/05/2014	<today date>	Y
  
  
###### Test Cases Output <H6>
- All test Case Passed.


