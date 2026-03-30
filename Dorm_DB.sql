-- Create the new database
CREATE DATABASE Dorm_DB;

-- Select the database for use
USE Dorm_DB;

---
-- 2. ROOM TABLE CREATION AND DATA INSERTION
---

-- Table structure matching the Room Management interface
CREATE TABLE ROOM (
    RoomNumber    VARCHAR(10) PRIMARY KEY,     -- e.g., A101
    RoomStatus    VARCHAR(20) NOT NULL,        -- e.g., YES, NO
    BedCapacity   INT NOT NULL                 -- e.g., 4, 2
);

-- Inserting Arbitrary (Sample) Room Data
INSERT INTO ROOM (RoomNumber, RoomStatus, BedCapacity) VALUES
('101', 'YES', 4),  -- Available
('102', 'NO', 2),   -- Not Available (Full)
('201', 'YES', 3),  -- Available
('202', 'YES', 4),  -- Available
('301', 'NO', 1);   -- Not Available (Maintenance)

---

-- 3. STUDENT TABLE CREATION AND DATA INSERTION
---

-- Table structure matching the Manage Students interface, linked to ROOM table
CREATE TABLE STUDENT (
    StudentID     VARCHAR(20) PRIMARY KEY,     -- e.g., S001
    StudentName   VARCHAR(100) NOT NULL,       -- e.g., Beki, Yordanos
    Sex            VARCHAR(20),
    RoomNo        VARCHAR(10),                 -- FK: Must exist in ROOM table
    MobileNo      VARCHAR(20),                 
    Address       VARCHAR(255),                
    LivingStatus  VARCHAR(20) NOT NULL,        -- e.g., Living, Leaved
    
    FOREIGN KEY (RoomNo) REFERENCES ROOM(RoomNumber)
);
-- Inserting Arbitrary (Sample) Student Data
INSERT INTO STUDENT (StudentID, StudentName,Sex, RoomNo, MobileNo, Address, LivingStatus) VALUES
('S001', 'Yordanos A.','male', '101', '0912345678', 'Addis Ababa', 'Living'),
('S002', 'Beki T.','male', '102', 'A987654321', 'Hawassa City', 'Living'),
('S003', 'Abebe D.', 'female','201', '0922334455', 'Gondar', 'Living'),
('S004', 'Lensa H.', 'male','101', '0955667788', 'Dire Dawa', 'Living'),
('S005', 'Tigist K.','female', '301', '0990909090', 'Mekelle', 'Leaved'); -- Example of a student who has left

---
CREATE TABLE  owner (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    userRole VARCHAR(20) NOT NULL -- 'Administrator', 'Proctor', or 'Student'
);

-- Inserting the three required users for testing
INSERT INTO owner (username, password, userRole) VALUES 
('admin', 'admin', 'Administrator'),
('proctor1', 'proctor1', 'Proctor'),
('student1', 'student1', 'Student');
CREATE TABLE  permission (
    permissionID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    studentID VARCHAR(15) NOT NULL,
    proctorID VARCHAR(15), 
    reason VARCHAR(255) NOT NULL,
    departureDate DATE NOT NULL,
    departureTime TIME NOT NULL,
    expectedReturnDate DATE,
    expectedReturnTime TIME,
    status VARCHAR(20) DEFAULT 'Pending Return', -- Records if the student is still out
    FOREIGN KEY (studentID) REFERENCES student(studentID)
);
-- 4. OPTIONAL: Verification Queries (Run these to see the data)
SELECT * FROM owner;
SELECT *
FROM STUDENT 
WHERE studentID= 'S001';
USE Dorm_DB;
show tables;