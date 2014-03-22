/**
 * @author AmeyaCJoshi
 * @description This javascript file helps in validation of Display.jsp
 */


function checkError() {
	var block = document.getElementById("errorMsg");
	var fileName = document.getElementById("file1");
	var flag = false;

	//var pattern = new RegExp ("/^.*\.(ecore|ECORE)$/");
	//flag = pattern.test(fileName);
	var ext = "";

	var a = fileName.value.split(".");
	if (a.length === 1 || (a[0] === "" && a.length === 2)) {
		ext = "";
	}
	ext = a.pop();

	if (ext == "ecore" || ext == "ECORE" || ext == "xmi" || ext == "XMI")
		flag = true;

	if (!flag) {
		alert("File type must be either .ecore or .xmi!");
	}
}

function checkFields() {
	if (type == "downloadButton") {
		if ($(".myCheckBox:checked").length == 1) {
			return true;
		}
		alert("Please select 1 diagram for download!");
		return false;
	}

	if (type == "compareButton") {
		var check = document.getElementsByName("check");
		var checked = [];
		for (var i = 0; i < check.length; i++) {
			if (check[i].checked) {
				checked.push(check[i]);
			}
		}
		// Check for 2 Ecore; XMI not supported++
		var bothValid = true;
		if (checked.length == 2) {
			if (checked[0].id == "sequence" || checked[1].id == "sequence") {
				bothValid = false;
			}
			if (checked[0].id == "class" || checked[1].id == "class") {
				bothValid = false;
			}
		}
		if (checked.length == 2 && bothValid) {
			// DisplayDiagram looks for ID numbers in the checked.value fields
			return true;
		}
		alert("Please select 2 Ecore diagrams to compare");
		return false;
	}

	if (type == "displayButton") {
		if ($(".myCheckBox:checked").length == 1) {
			return true;
		}
		alert("Please select 1 diagram to display");
		return false;
	}

	// merge function
	if (type == "mergeButton") {
		var req = document.getElementById("req");
		var form = document.getElementById("requestForm");
		var check = document.getElementsByName("check");
		var checked = [];
		for (var i = 0; i < check.length; i++) {
			if (check[i].checked) {
				checked.push(check[i]);
			}
		}
		// Check for 2 XMI class; Ecore or sequence not supported
		var bothValid = true;
		if (checked.length == 2) {
			if (checked[0].id == "sequence" || checked[1].id == "sequence") {
				bothValid = false;
			}
			if (checked[0].id == "Ecore" || checked[1].id == "Ecore") {
				bothValid = false;
			}
		}
		if (checked.length == 2 && bothValid) {
			var reqO = {};
			reqO.Request = "Refresh";
			reqO.Diagram1 = checked[0].value;
			reqO.Diagram2 = checked[1].value;
			req.value = JSON.stringify(reqO);

			form.submit();
			return false;
		}
		alert("Please select 2 XMI Class diagrams to merge.");
		return false;
	}
	// end
}

function displayClassDiagramFields(element) {
	var option = element.options[element.selectedIndex].text;

	var selectLabel = document.getElementById("DiagramSelectLabel");
	var fileinput2 = document.getElementById("file2");
	var fileinput3 = document.getElementById("file3");
	if (option == "ECORE") {
		fileinput2.style.display = "none";
		fileinput3.style.display = "none";
		selectLabel.innerHTML = "Class Diagram Format: (.ecore)";
	} else if (option == "XMI") {
		fileinput2.style.display = "block";
		fileinput3.style.display = "block";
		selectLabel.innerHTML = "Class Diagram Format: (.di, .notation, .uml)";
	}
}

function toggleChecked(status) {
	$(".myCheckBox").each(function() {
		$(this).attr("checked", status);
	})
}