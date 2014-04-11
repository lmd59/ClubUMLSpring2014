/*Initial database creation*/
CREATE DATABASE  IF NOT EXISTS `clubuml`;
USE `clubuml`;

/*Cleanup of any existing tables*/
DROP TABLE IF EXISTS `comment`;/*legacy*/

/*Reverse dependency order to drop child tables first*/
DROP TABLE IF EXISTS `classes`;
DROP TABLE IF EXISTS `attributes`;
DROP TABLE IF EXISTS `DiagramMetricsScore`;
DROP TABLE IF EXISTS `metric`;
DROP TABLE IF EXISTS `metricType`;
DROP TABLE IF EXISTS `decisionRationale`;
DROP TABLE IF EXISTS `decision`;
DROP TABLE IF EXISTS `rationale`;
DROP TABLE IF EXISTS `compare`;
DROP TABLE IF EXISTS `DiagramPolicyScore`;
DROP TABLE IF EXISTS `report`;
DROP TABLE IF EXISTS `userproject`;
DROP TABLE IF EXISTS `diagram`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `diagramContext`;
DROP TABLE IF EXISTS `policy`;
DROP TABLE IF EXISTS `project`;


/*Table Creation*/

-- Table project
CREATE TABLE project
(
    projectId Int(11) NOT NULL AUTO_INCREMENT,
    projectName Varchar(45) NOT NULL UNIQUE,
    startDate Timestamp NOT NULL,
    description Varchar(255),
    enabled BOOLEAN NOT NULL DEFAULT true,
    disabledDate Timestamp NULL,
    PRIMARY KEY (projectId)
);

-- Table user
CREATE TABLE user
(
    userId Int(11) NOT NULL AUTO_INCREMENT,
    userName Varchar(45) NOT NULL UNIQUE,
    email Varchar(45) NOT NULL,
    password Varchar(45) NOT NULL,
    securityQ Varchar(60),
    securityA Varchar(60),
    userType char(2) NOT NULL DEFAULT "U",
  PRIMARY KEY (userId)
);

-- Table userproject
CREATE TABLE userproject (
userId int(11) NOT NULL,
projectId int(11) NOT NULL,
PRIMARY KEY (userId, projectId)
);

-- Table diagram
CREATE TABLE diagram
(
    diagramId Int(11) NOT NULL AUTO_INCREMENT,
    projectId Int(11) NOT NULL,
    userId Int(11) NOT NULL,
    contextId int(11) NOT NULL,
    diagramType Varchar(45) NOT NULL,
  diagramName Varchar(45) NOT NULL,
    createTime Timestamp NOT NULL,
    filePath Varchar(45) NOT NULL,
    fileType Varchar(20) NOT NULL,
   merged Tinyint NOT NULL DEFAULT 0,
   notationFileName Varchar(45),
   notationFilePath Varchar(45),
   diFileName Varchar(45),
  diFilePath Varchar(45),
  diagramRealPath Varchar(75),
  conPath Varchar(75),
 PRIMARY KEY (diagramId)
);

/**check for NOT NULL/ Default problems in testing*/

/*Note: filepath includes filename*/
-- Table report
CREATE TABLE report
(
    reportId Int(11) NOT NULL AUTO_INCREMENT,
    diagramA Int(11) NOT NULL,
   diagramB Int(11) NOT NULL,
   mergedDiagram Int(11),
   time Timestamp NOT NULL,
   reportFilePath Varchar(200) NOT NULL,
  PRIMARY KEY (reportId)
);

-- Table rationale (3/4/2014)
CREATE TABLE rationale
(
    rationaleId int(11) NOT NULL AUTO_INCREMENT,
  compareId int(11) NOT NULL,
  userId int(11) NOT NULL,
  rationaleTime timestamp NOT NULL,
  promotedDiagramId int(11) NOT NULL,
  alternativeDiagramId int(11) NOT NULL,
  summary varchar(255) NOT NULL,
  issue varchar(75),
  issueRelationship varchar(255),
  criteria varchar(75),
  criteriaRelationship varchar(255),
  PRIMARY KEY (rationaleId)
);

-- Table decision (3/30/2014)
CREATE TABLE decision
(
	decisionId int(11) NOT NULL AUTO_INCREMENT,
	decisionName varchar(75) NOT NULL,
	projectId int(11) NOT NULL,
	decisionTime Timestamp NOT NULL,
	userId int(11) NOT NULL,
	diagramId int(11), /*should be changed to not null when RM2 functionality is completed -laurend*/
	PRIMARY KEY (decisionId)
);

-- Table decisionRationale many-to-many (3/30/2014)
CREATE TABLE decisionRationale (
decisionId int(11) NOT NULL,
rationaleId int(11) NOT NULL,
PRIMARY KEY (decisionId, rationaleId)
);

-- 2013/10/22 Create Table Policy --
CREATE TABLE policy
(
  policyId INT(11) NOT NULL AUTO_INCREMENT,
  policyName VARCHAR(45) NOT NULL UNIQUE,
  policyDescription VARCHAR(255), 
  policyLevel INT(11) NOT NULL,
  PRIMARY KEY (policyId)
);

-- 2013/10/22 Create Table DiagramContext --
CREATE TABLE diagramContext
(
  diagramContextId Int(11) NOT NULL AUTO_INCREMENT,
  description VARCHAR(255),    
      name VARCHAR(45) NOT NULL,
     policyId INT(11) NOT NULL,
      projectId INT(11) NOT NULL,
      enabled BOOLEAN NOT NULL DEFAULT true,
      disabledDate timestamp,
      PRIMARY KEY (diagramContextId)
);

-- 2013/11/12 Create Table compare --
CREATE TABLE compare
(
  compareId int(11) NOT NULL AUTO_INCREMENT,
  diagramAId int(11) NOT NULL,
  diagramBId int(11) NOT NULL,
  reportId int(11) NOT NULL,
  promoteCountA int(11) DEFAULT 0,
      promoteCountB int(11) DEFAULT 0,
  PRIMARY KEY (compareId)
);

/***************Tables for policy scoring funcitonality- No front end?****************/
CREATE TABLE diagramPolicyScore
(
  diagramId INT(11) NOT NULL,
  justification VARCHAR(100),
  policyId INT(11) NOT NULL,
  score INT(11) DEFAULT 0,
  PRIMARY KEY (diagramId, policyId)
);

CREATE TABLE metric
(
    metricId int(11) NOT NULL AUTO_INCREMENT,
    policyId int(11),
    metricTypeId int(2) NOT NULL,
    metricsWeight int(11) NOT NULL,
    PRIMARY KEY (metricId)
);

CREATE TABLE metricType
(
   metricTypeId int(11) NOT NULL AUTO_INCREMENT,
    description varchar(255),
    metricTypeName varchar(30) NOT NULL,
    PRIMARY KEY (metricTypeId)
);

CREATE TABLE diagramMetricsScore
(
    diagramId int(11) NOT NULL,
    metricId int(11) NOT NULL,
    score int(11) NOT NULL,
   PRIMARY KEY (diagramId,metricId)
);

/*metricID extension table*/
CREATE TABLE attributes
(
    metricId int(11) NOT NULL,
   idealNoOfAttributes int(11),
   maxNoOfAttributes int(11),
   minNoOfAttributes int(11)
);

/*metricID extension table*/
CREATE TABLE classes
(
    metricId int(11) NOT NULL,
    idealNoOfClasses int(11),
    maxNoOfClasses int(11),
    minNoOfClasses int(11)
);

/*Create Relationships*/

ALTER TABLE diagram ADD CONSTRAINT diagramHaveOwnerId FOREIGN KEY (userId) REFERENCES user (userId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagram ADD CONSTRAINT diagramHaveProjectId FOREIGN KEY (projectId) REFERENCES project (projectId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE report ADD CONSTRAINT reportHaveDiagramA FOREIGN KEY (diagramA) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE report ADD CONSTRAINT reportHaveDiagramB FOREIGN KEY (diagramB) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE report ADD CONSTRAINT reportHaveMergedDiagram FOREIGN KEY (mergedDiagram) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE rationale ADD CONSTRAINT rationaleHaveCompareId FOREIGN KEY (compareId) REFERENCES compare (compareId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE rationale ADD CONSTRAINT rationaleHaveUserId FOREIGN KEY (userId) REFERENCES user (userId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE rationale ADD CONSTRAINT rationaleHavePromotedDiagramId FOREIGN KEY (promotedDiagramId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE rationale ADD CONSTRAINT rationaleHaveAlternativeDiagramId FOREIGN KEY (alternativeDiagramId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE decision ADD CONSTRAINT decisionHaveDiagramId FOREIGN KEY (diagramId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE decision ADD CONSTRAINT decisionHaveProjectId FOREIGN KEY (projectId) REFERENCES project (projectId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE decision ADD CONSTRAINT decisionHaveUserId FOREIGN KEY (userId) REFERENCES user (userId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE decisionRationale ADD CONSTRAINT decisionRationaleHaveDecisionId FOREIGN KEY (decisionId) REFERENCES decision (decisionId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE decisionRationale ADD CONSTRAINT decisionRationaleHaveRationaleId FOREIGN KEY (rationaleId) REFERENCES rationale (rationaleId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE userproject ADD CONSTRAINT userprojectHaveUserId FOREIGN KEY (userId) REFERENCES user (userId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE userproject ADD CONSTRAINT userprojectHaveProjectId FOREIGN KEY (projectId) REFERENCES project (projectId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE compare ADD CONSTRAINT compareHaveReportId FOREIGN KEY (reportId) REFERENCES report (reportId) ON DELETE NO ACTION ON UPDATE NO ACTION; 
ALTER TABLE compare ADD CONSTRAINT compareHaveDiagramAId FOREIGN KEY (diagramAId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE compare ADD CONSTRAINT compareHaveDiagramBId FOREIGN KEY (diagramBId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagramPolicyScore ADD CONSTRAINT diagramPolicyScoreHavePolicyId FOREIGN KEY (policyId) REFERENCES policy (policyId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagramPolicyScore ADD CONSTRAINT diagramPolicyScoreHaveDiagramId FOREIGN KEY (diagramId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagramContext ADD CONSTRAINT diagramContextHavePolicyId FOREIGN KEY (policyId) REFERENCES policy (policyId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagramContext ADD CONSTRAINT diagramContextHaveProjectId FOREIGN KEY (projectId) REFERENCES project (projectId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagram ADD CONSTRAINT diagramHaveDiagramContextId FOREIGN KEY (contextId) REFERENCES diagramContext (diagramcontextId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE metric ADD CONSTRAINT metricHavePolicyId FOREIGN KEY (policyId) REFERENCES policy (policyId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE metric ADD CONSTRAINT metricHaveMetricTypeId FOREIGN KEY (metricTypeId) REFERENCES metricType (metricTypeId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagramMetricsScore ADD CONSTRAINT diagramMetricsScoreHaveDiagramId FOREIGN KEY (diagramId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE diagramMetricsScore ADD CONSTRAINT diagramMetricsScoreHaveMetricId FOREIGN KEY (metricId) REFERENCES metric (metricId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE attributes ADD CONSTRAINT attributesHaveMetricId FOREIGN KEY (metricId) REFERENCES metric (metricId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE classes ADD CONSTRAINT classesHaveMetricId FOREIGN KEY (metricId) REFERENCES metric (metricId) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*insert sample data*/
insert into policy (policyName,policyDescription,policyLevel) values ("policy 1","policy 1 description",2);
INSERT INTO policy (policyName, policyDescription, policyLevel) VALUES ('Policy2', 'Policy Description', 2);
INSERT INTO metricType (metricTypeId, description, metricTypeName) VALUES (1, 'ASSOCIATIONS', 'ASSOCIATIONS');
INSERT INTO metricType (metricTypeId, description, metricTypeName) VALUES (2, 'MULTIPLICITIES', 'MULTIPLICITIES');
INSERT INTO metricType (metricTypeId, description, metricTypeName) VALUES (3, 'ATTRIBUTES', 'ATTRIBUTES');
INSERT INTO metricType (metricTypeId, description, metricTypeName) VALUES (4, 'CLASSES', 'CLASSES');
INSERT INTO metric (metricId, policyId, metricTypeID, metricsWeight) VALUES (1, 2, 4, 10);
INSERT INTO metric (metricId, policyId, metricTypeID, metricsWeight) VALUES (2, 2, 3, 10);
INSERT INTO classes (metricId, idealNoOfClasses, maxNoOfClasses, minNOOfClasses) VALUES (1, 4, 5, 2);
INSERT INTO attributes (metricId, idealNoOfAttributes, maxNoOfAttributes, minNOOfAttributes) VALUES (2, 4, 5, 1);




