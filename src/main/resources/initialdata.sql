INSERT INTO teeth (teethID, Description, teethName) VALUES (1, "Upper Right Central", "URCe");
INSERT INTO teeth (teethID, Description, teethName) VALUES (2, "Upper Right Lateral", "URL");
INSERT INTO teeth (teethID, Description, teethName) VALUES (3, "Upper Right Cuspid", "URCu");
INSERT INTO teeth (teethID, Description, teethName) VALUES (4, "Upper Right 1st Bicuspid", "UR1B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (5, "Upper Right 2nd Bicuspid", "UR2B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (6, "Upper Right 1st Molar", "UR1M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (7, "Upper Right 2nd Molar", "UR2M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (8, "Upper Right 3rd Molar (Wisdom)", "UR3M(W)");
INSERT INTO teeth (teethID, Description, teethName) VALUES (9, "Lower Right 3rd Molar (Wisdom)", "LR3M(W)");
INSERT INTO teeth (teethID, Description, teethName) VALUES (10, "Lower Right 2nd Molar", "LR2M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (11, "Lower Right 1st Molar", "LR1M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (12, "Lower Right 2nd Bicuspid", "LR2B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (13, "Lower Right 1st Bicuspid", "LR1B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (14, "Lower Right Cuspid", "LRCu");
INSERT INTO teeth (teethID, Description, teethName) VALUES (15, "Lower Right Lateral", "LRL");
INSERT INTO teeth (teethID, Description, teethName) VALUES (16, "Lower Right Central", "LRCe");
INSERT INTO teeth (teethID, Description, teethName) VALUES (17, "Upper Left Central", "ULCe");
INSERT INTO teeth (teethID, Description, teethName) VALUES (18, "Upper Left Lateral", "ULL");
INSERT INTO teeth (teethID, Description, teethName) VALUES (19, "Upper Left Cuspid", "ULCu");
INSERT INTO teeth (teethID, Description, teethName) VALUES (20, "Upper Left 1st Bicuspid", "UL1B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (21, "Upper Left 2nd Bicuspid", "UL2B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (22, "Upper Left 1st Molar", "UL1M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (23, "Upper Left 2nd Molar", "UL2M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (24, "Upper Left 3rd Molar (Wisdom)", "UL3M(W)");
INSERT INTO teeth (teethID, Description, teethName) VALUES (25, "Lower Left 3rd Molar (Wisdom)", "LL3M(W)");
INSERT INTO teeth (teethID, Description, teethName) VALUES (26, "Lower Left 2nd Molar", "LL2M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (27, "Lower Left 1st Molar", "LL1M");
INSERT INTO teeth (teethID, Description, teethName) VALUES (28, "Lower Left 2nd Bicuspid", "LL2B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (29, "Lower Left 1st Bicuspid", "LL1B");
INSERT INTO teeth (teethID, Description, teethName) VALUES (30, "Lower Left Cuspid", "LLCu");
INSERT INTO teeth (teethID, Description, teethName) VALUES (31, "Lower Left Lateral", "LLL");
INSERT INTO teeth (teethID, Description, teethName) VALUES (32, "Lower Left Central", "LLCe");



INSERT INTO user_auth (userID,accountStatus,creationTime,lastLoginTime,userEmail,userIp,userPwd,userRole,verifyKey)  VALUES (1,"ACTIVE","2016-03-24 00:41:33","2016-03-24 00:41:33","srikanthvarma.vadapalli@gmail.com","0:0:0:0:0:0:0:1","0KR85HFJNh4eq5AVapYzNRQvL9QgerMrvdy97CdvrBk=","ROLE_USER","i/Pz2YCzpUvLhOFmxztRAJmQ/ckp2mkqsXEr4DNU0BUHPWjZwOuQa+FefLZ7QvbkuZ7u91qDslSWLVSuQL+onTUeKVG+vJSfm8IOIEixI5NbhS4emCnmXHNNwZssO9XfrFMcktFyEcBvjToYOsc30Zb9NLVXaPYeS6uaYhSS1L4=") ;
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