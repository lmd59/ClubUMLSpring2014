package controller.comparer.xmi;

import java.util.List;

import controller.compare.ComparerIntf;
import controller.upload.FileInfo;
import controller.upload.UploadProcessorFactory;

public class XmiClassDiagramComparer implements ComparerIntf {

	private XmiClassDiagramParser ClassDiagram1;
	private XmiClassDiagramParser ClassDiagram2;

	/**
	 * Constructor
	 * 
	 * @param XmiFiles1
	 * @param XmiFiles2
	 */
	public XmiClassDiagramComparer(List<FileInfo> XmiFiles1,
			List<FileInfo> XmiFiles2) {

		// Process the first file
		FileInfo classDiagram1Notation = getFile(
				UploadProcessorFactory.NOTATION_EXTENSION, XmiFiles1);
		FileInfo classDiagram1Uml = getFile(
				UploadProcessorFactory.UML_EXTENSION, XmiFiles1);

		ClassDiagram1 = new XmiClassDiagramParser(
				classDiagram1Uml.getDestFilePath()
						+ classDiagram1Uml.getFileName(),
				classDiagram1Notation.getDestFilePath()
						+ classDiagram1Notation.getFileName());

		// Process the second file
		FileInfo classDiagram2Notation = getFile(
				UploadProcessorFactory.NOTATION_EXTENSION, XmiFiles2);
		FileInfo classDiagram2Uml = getFile(
				UploadProcessorFactory.UML_EXTENSION, XmiFiles2);

		ClassDiagram2 = new XmiClassDiagramParser(
				classDiagram2Uml.getDestFilePath()
						+ classDiagram2Uml.getFileName(),
				classDiagram2Notation.getDestFilePath()
						+ classDiagram2Notation.getFileName());
	}

	// Do not use this method yet. We need to change the interface to accept a
	// list of FileInfo, since Xmi requires more than 1 file per diagram
	@Override
	public List<Object> compare(List<FileInfo> file1, List<FileInfo> file2,
			String compareLayer) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Refactor this method since it can be used in other sources (ex:
	 * UmlUploadProcessors)
	 * 
	 * @param extension
	 * @param fileList
	 * @return
	 */
	private FileInfo getFile(String extension, List<FileInfo> fileList) {
		FileInfo info = null;
		for (int i = 0; i < fileList.size(); i++) {
			String extn = fileList
					.get(i)
					.getFileName()
					.substring(
							fileList.get(i).getFileName().lastIndexOf(".") + 1,
							fileList.get(i).getFileName().length());
			if (extn.equals(extension)) {
				info = fileList.get(i);
			}
		}
		return info;
	}

	// *************************************************************************
	// Implement and change these stubs to however you like
	// *************************************************************************

	// Compare names
	public String compareClassNames() {
		List<XmiClassElement> list1 = ClassDiagram1.getClassElements();
		List<XmiClassElement> list2 = ClassDiagram2.getClassElements();

		return "";
	}

	// Compare attributes
	public String compareProperties() {
		List<XmiClassElement> list1 = ClassDiagram1.getClassElements();
		List<XmiClassElement> list2 = ClassDiagram2.getClassElements();

		return "";
	}

	// Compare operations
	public String compareOperations() {
		List<XmiClassElement> list1 = ClassDiagram1.getClassElements();
		List<XmiClassElement> list2 = ClassDiagram2.getClassElements();

		return "";
	}
	
	// Compare Generalization
	public String compareGeneralizations() {
		List<XmiClassElement> list1 = ClassDiagram1.getClassElements();
		List<XmiClassElement> list2 = ClassDiagram2.getClassElements();

		return "";
	}

}
