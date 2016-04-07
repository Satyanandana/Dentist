INSERT INTO user_auth (userID,accountStatus,creationTime,lastLoginTime,userEmail,userIp,userPwd,userRole,verifyKey)  VALUES (1,"ACTIVE","2016-03-24 00:41:33","2016-03-24 00:41:33","srikanthvarma.vadapalli@gmail.com","0:0:0:0:0:0:0:1","922DA5CDBB4974B30FD2ACEA8A8AB7ECE546AF560E23AC5F53871430BE686388","ROLE_USER","5771097CF379F9CA0FCCD69F71C011E0DBB1BD6D5738301EE7513A366E90A189A06F49D808D8C09DDEDB2E7393DED1B71B8B16252F9F0C269F3D66D2E5698A6A390903A165FC63393DB806033805B352E4A86F860035E2646C19B7E0F398786B8AADEA460EA117E545A3825E53A385CDF319862C33162CDD238514362A7416375182235C95E6E486F504C79BC7D5E258186EC6E4A3EF3AA0E560D03194B373C1D1074689BFEDF6E12608D2F4FA86294B87D9D1D843E82207695F130CE8A32382") ;
INSERT INTO patient_details (userID, emergencyContactName, emergencyContactNumber, emergencyContactRelation, dateOfBirth, email, firstName, address1, address2, city, state, zipcode, lastName, middleName, phoneNumber) VALUES ("1", "Hima Sindhu", "6174895892", "Sister", "1989-03-01", "srikanthvarma.vadapalli@gmail.com", "Satyanandana", "30 Franklin street", "unit 228", "Malden", "MA", "01248", "Vadapalli", "Srikanthvarma", "6178491980");

INSERT INTO recieved_messages (msg, receivedTime, receiverID) VALUES ("First message", "2016-02-10 00:00:00", "1");
INSERT INTO recieved_messages (msg, receivedTime, receiverID) VALUES ("Second message", "2016-03-01 00:00:00", "1");

INSERT INTO sent_messages (msg, sentTime, senderID) VALUES ("First message", "2016-02-10 00:00:00", "1");
INSERT INTO sent_messages (msg, sentTime, senderID) VALUES ("Second message", "2016-03-01 00:00:00", "1");

INSERT INTO appointmentrequests (appointmentRequestID, appointmentStartTime, note, requestInsertedTime, status, appointmentID, patientID) VALUES (NULL, "2016-03-01 00:00:00", "cleaning teeth", "2016-02-25 00:00:00", "WAITING_FOR_APPROVAL", NULL, "1");
INSERT INTO appointmentrequests (appointmentRequestID, appointmentStartTime, note, requestInsertedTime, status, appointmentID, patientID) VALUES (NULL, "2016-03-17 00:00:00", "Extract teeth", "2016-03-01 00:00:00", "ACCEPTED", NULL, "1");

INSERT INTO appointments (appointmentID, actualCalEventID, amountPaid, appointmentInsertedTime, appointmentStartTime, expectedAmount, fakeCalEventID, note, status, appointmentRequestID, patientID) VALUES (NULL, "fht789w76", NULL, "2016-03-02 00:00:00", "2016-03-01 00:00:00", NULL, "yr73988", "", "CONFIRMED", "1", "1");
INSERT INTO appointments (appointmentID, actualCalEventID, amountPaid, appointmentInsertedTime, appointmentStartTime, expectedAmount, fakeCalEventID, note, status, appointmentRequestID, patientID) VALUES (NULL, "fhtuyiw76", NULL, "2016-03-18 00:00:00", "2016-03-17 00:00:00", NULL, "yr73988", "", "CONFIRMED", "2", "1");

INSERT INTO insurances (insuranceID, dateOfBirth, insertedDate, insuranceProviderID, insuranceProviderName, status, subscriberFullName, subscriberID, patientID) VALUES (NULL, NULL, "2015-03-01 00:00:00", "5864858467", "Aetna", "EXPIRED", "SATYANANDANA SRIKANTHVARMA", "5486665487", "1");
INSERT INTO insurances (insuranceID, dateOfBirth, insertedDate, insuranceProviderID, insuranceProviderName, status, subscriberFullName, subscriberID, patientID) VALUES (NULL, NULL, "2016-03-01 00:00:00", "5864855783", "Blue Cross", "ACTIVE", "SATYANANDANA SRIKANTHVARMA", "5486685739", "1");

INSERT INTO patientteethstatus (lastModified, teethStatus, patientID, teethID) VALUES ("2016-03-01 00:00:00", "NORMAL", "1", "1");
INSERT INTO patientteethstatus (lastModified, teethStatus, patientID, teethID) VALUES ("2016-03-01 00:00:00", "EXTRACTED", "1", "2");

INSERT INTO treatments (treatmentID, amountExpected, amountPaid, note, status, treatmentDoneTime, treatmentExpectedTime, treatmentInsertedTime, patientID, teethID) VALUES (NULL, "180", "200", "somethig done to teeth 1", "COMPLETED", "2016-03-15", "2016-03-15", "2016-03-02 00:00:00", "1", "1");
INSERT INTO treatments (treatmentID, amountExpected, amountPaid, note, status, treatmentDoneTime, treatmentExpectedTime, treatmentInsertedTime, patientID, teethID) VALUES (NULL, "100", NULL, "somethig to be done to teeth 1", "PENDING", NULL, "2016-03-24", "2016-03-22 00:00:00", "1", "1");
INSERT INTO treatments (treatmentID, amountExpected, amountPaid, note, status, treatmentDoneTime, treatmentExpectedTime, treatmentInsertedTime, patientID, teethID) VALUES (NULL, "180", "200", "somethig done to teeth 2", "COMPLETED", "2016-03-15", "2016-03-15", "2016-03-02 00:00:00", "1", "2");
INSERT INTO treatments (treatmentID, amountExpected, amountPaid, note, status, treatmentDoneTime, treatmentExpectedTime, treatmentInsertedTime, patientID, teethID) VALUES (NULL, "100", NULL, "somethig to be done to teeth 2", "PENDING", NULL, "2016-03-24", "2016-03-22 00:00:00", "1", "2");