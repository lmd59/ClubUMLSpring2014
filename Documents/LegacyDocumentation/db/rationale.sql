Use clubuml;
DROP TABLE comment;

From there, the developer can create the rationale entity using the following table creation script:

-- Table rationale
Use clubuml;
CREATE TABLE rationale
(
    rationaleId int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  compareId int(11) NOT NULL,
  userId int(11) NOT NULL,
  rationaleTime timestamp NOT NULL,
  promotedDiagramId int(11) NOT NULL,
  alternativeDiagramId int(11) NOT NULL,
  summary varchar(255) NOT NULL,
  issue varchar(75) NOT NULL,
  issueRelationship varchar(255) NOT NULL,
  criteria varchar(75) NOT NULL,
  criteriaRelationship varchar(255) NOT NULL
);

ALTER TABLE rationale ADD CONSTRAINT rationaleHaveCompareId FOREIGN KEY (compareId) REFERENCES compare (compareId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE rationale ADD CONSTRAINT rationaleHaveUserId FOREIGN KEY (userId) REFERENCES user (userId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE rationale ADD CONSTRAINT rationaleHavePromotedDiagramId FOREIGN KEY (promotedDiagramId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE rationale ADD CONSTRAINT rationaleHaveAlternativeDiagramId FOREIGN KEY (alternativeDiagramId) REFERENCES diagram (diagramId) ON DELETE NO ACTION ON UPDATE NO ACTION;
