<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.main.VendorPojo" %>
 
 
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vendor Management</title>
    
<style>
    /* Modal styles */
 
    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.4);
        padding-top: 60px;
    }
 
    .modal-content {
    background-color: #fff;
    border-radius: 8px;
    padding: 20px;
    max-width: 365px;
    max-height: 450px;
    margin: 0 auto;
    position: relative;
    /* Add the following styles */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
 
 
/* Adjust the input field styles within the update dialog box */
#updateVendorDialog input[type="text"] {
    width: calc(100% - 20px); /* Adjusted input width */
    padding: 8px;
    border-radius: 6px;
    border: 1px solid #ccc;
    font-size: 16px;
}
 
/* Remove border color for vendorID input box */
#update_vendorID {
    border-color: transparent; /* Make border color transparent */
}
 
/* Remove border color on focus for vendorID input box */
#update_vendorID:focus {
    border-color: transparent; /* Make border color transparent */
    outline: none; /* Remove the default outline */
}
 
 
    .close {
    position: absolute;
    top: 10px;
    right: 10px; /* Adjust the right position as needed */
    font-size: 20px;
    cursor: pointer;
    color: #007bff;
    z-index: 1; /* Ensure it's above other content */
}
 
 
    .close:hover,
    .close:focus {
        color: gray;
        text-decoration: none;
        cursor: pointer;
    }
    
    .modal-content h2 {
    color: #333;
    margin-bottom: 20px;
}
 
.form-group {
    margin-bottom: 20px;
    width: 100%;
}
 
label {
    font-weight: bold;
    margin-bottom: 5px;
}
 
/* Input styles */
input[type="text"] {
    width: calc(100% - 20px); /* Adjusted input width */
    padding: 8px;
    border-radius: 6px;
    border: 2px solid #ccc;
    font-size: 16px;
}
 
input[type="text"],select,input[type="submit"] {
	border: 2px solid #ccc;
}
 
 
input#vendorID {
    border-color: transparent; /* Make border color transparent */
}
 
input#vendorID:focus {
    border-color: transparent; /* Make border color transparent */
    outline: none; /* Remove the default outline */
}
 
 
input#vendorID_update {
    border-color: transparent; /* Make border color transparent */
}
 
input#vendorID_update:focus {
    border-color: transparent; /* Make border color transparent */
    outline: none; /* Remove the default outline */
}
    
    /* used from their style*/
    
 
 
    h1 {
        color: #fff;
        text-align: center;
        margin-top: 20px;
    }
 
    h2 {
        color: #666;
        margin-top: 30px;
    }
 
    table {
        width: 85%;
        margin: 20px auto;
        border-collapse: collapse;
        border: 2px solid #ddd;
    }
 
    th, td {
        padding: 14px;
        text-align: center;
        border: 2px solid #ddd;
    }
 
    th {
        background-color: #C1C3BE;
        color:#545B5C;
    }
    
    table tr:nth-child(even) {
        background-color: #FFF; /* Set the background color for even rows */
    }
 
    .btn {
    padding: 8px 8px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}
 
.btn-add {
    background-color: #007bff;
    color: #fff;
}
 
.btn-add:hover {
    background-color:#0056b3;
}
 
.btn1 {
    padding: 12px 25px;
    border: none;
    border-radius: 15px;
    cursor: pointer;
    font-size: 16px;
}
 
    .btn-edit {
        background-color: #4CAF50;
        color: white;
    }
    .btn-edit:hover {
        background-color: #357938;
    }
 
    .btn-delete {
        background-color: #f44336;
        color: white;
    }
    
    .btn-delete:hover {
        background-color: #ba160a;
    }
    
 
 
.btn1-add {
    background-color: #b1b14e;
    color: black;
}
 
.btn1-add:hover {
    background-color: #8e8e3e;
}
 
.btn-edit,
.btn-delete {
    text-decoration: none; /* Remove the underline */
}
 
.btn-edit:hover,
.btn-delete:hover {
    text-decoration: none; /* Remove the underline on hover */
}
 
 
/* Styles for search input */
        #searchInput {
            padding: 15px;
            margin-bottom: 10px;
            width: 320px;
            border-radius: 100px;
            border: 2px solid black;
            font-size: 16px;
        }
        
        #searchInput:focus {
    background-color: #E9E4E5;
}
        
        
        
        
        .search-container {
    position: relative;
    display: inline-block; /* Ensure the container doesn't occupy full width */
    margin-right: 10px; /* Adjust as needed */
    text-align: right;
    margin-bottom: 10px;
    float:right;
    
    
 
    
}
 
/* CSS for the search input */
.search-container input[type="text"] {
    width: 300px; /* Adjust as needed */
    padding-right: 30px; /* Leave space for the icon */
}
 
/* CSS for the search icon */
.search-container .search-icon {
    position: absolute;
    right: 28%; /* Adjust as needed */
    top: 40%;
    transform: translateY(-50%);
    cursor: pointer;
    width: 20px; /* Adjust width as needed */
    height: 20px; /* Adjust height as needed */
}
 
        /* Styles for filter dropdowns */
        .btn-filter {
    padding: 14px 30px;
    border: none;
    cursor: pointer;
    border-radius: 15px;
    background-color: #007bff; /* Blue color */
    color: white;
    margin-left: 10px;
}
 
	.btn-filter:hover{
	
		background-color: #0056b3;
	}
/* Style for the filter options */
	#filterOptions {
	    display: none;
	    position: absolute;
	    background-color: white;
	    padding: 10px;
	    border: 1px solid #ccc;
	    border-radius: 15px;
	    z-index: 1;
	    right: 0;
	    margin-top: 58px;
	}
 
/* Style for the filter options dropdown */
.filterDropdown {
    padding: 8px;
    margin-right: 10px;
    border-radius: 10px;
    border: 1px solid #ccc;
    font-size: 16px;
    width: 200px;
    margin-bottom: 5px;
}
 
 
nav ul {
            list-style-type: none;
            padding: 0;
        }
 
            nav ul li {
                display: inline;
                margin-right: 20px;
            }
 
                nav ul li a {
                    color: #fff;
                    text-decoration: none;
                }
                
                
        
        
        
        body {
            font-family: Arial, sans-serif;
            background-color: #E2E6E8;
            margin: 0;
            padding: 0;
        }
 
        /* Header Styles */
        header {
            background-color: #0C4160;
            color: #fff;
            padding: 25px 0;
            text-align: center;
        }
 
        header h1 {
            margin: 0;
        }
 
        /* Navigation Styles */
        .nav1 {
            background-color: #C1C3BE;
            padding: 18px;
           /* box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);*/
        }
 
        .nav1 ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }
 
        .nav1 ul li {
            display: inline;
            margin-right: 20px;
        }
 
        .nav1 ul li a {
            color: #545B5C;
            text-decoration: none;
            font-weight: bold;
        }
 
        .nav1 ul li a:hover {
            color: #AEA885;
        }
 
 
    </style>

    <script>
    
    function filterTable() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("vendorTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            var nameColumn = tr[i].getElementsByTagName("td")[1]; // Index of the "Name" column
            var serviceTypeColumn = tr[i].getElementsByTagName("td")[3]; // Index of the "Service Type" column
            var SLAColumn = tr[i].getElementsByTagName("td")[4]; // Index of the "SLA" column
            if (nameColumn && serviceTypeColumn && SLAColumn) {
                var nameValue = nameColumn.textContent || nameColumn.innerText;
                var serviceTypeValue = serviceTypeColumn.textContent || serviceTypeColumn.innerText;
                var SLAValue = SLAColumn.textContent || SLAColumn.innerText;
                if (nameValue.toUpperCase().indexOf(filter) > -1 || serviceTypeValue.toUpperCase().indexOf(filter) > -1 || SLAValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
 
    
    
    
 
 
 
    // Add an event listener to update the table when the search input changes
    //document.getElementById("searchInput").addEventListener("input", filterTable);
 
 
    function toggleFilter() {
        var filterOptions = document.getElementById("filterOptions");
        if (filterOptions.style.display === "none") {
            filterOptions.style.display = "block";
        } else {
            filterOptions.style.display = "none";
        }
    }
 
    // Add an event listener to update the table when the search input changes
    document.getElementById("searchInput").addEventListener("input", filterTable);
 
    
    
    
 
    
 
        function deleteVendor(vendorID) {
            if (confirm("Are you sure you want to delete this Vendor?")) {
                window.location.href = "DeleteVendorServlet?vendorID=" + vendorID;
            }
        }
 
        // Function to display add vendor dialog
        function displayAddVendorDialog() {
            // Display the add vendor form as a dialog
            document.getElementById("addVendorDialog").style.display = "block";
        }
 
        // Function to close the add vendor dialog
        function closeAddVendorDialog() {
            // Hide the add vendor dialog
            document.getElementById("addVendorDialog").style.display = "none";
        }
        
     // Function to display update vendor dialog
        function displayUpdateVendorDialog(vendorID, name, contact, serviceType, SLA) {
            console.log("Vendor ID:", vendorID);
            console.log("Name:", name);
            console.log("Contact:", contact);
            console.log("Service Type:", serviceType);
            console.log("SLA:", SLA);
 
            // Set the values in the update dialog fields
            document.getElementById("vendorID_update").value = vendorID;
            document.getElementById("name_update").value = name;
            document.getElementById("contact_update").value = contact;
            document.getElementById("serviceType_update").value = serviceType;
            document.getElementById("SLA_update").value = SLA;
 
            // Display the update dialog
            document.getElementById("updateVendorDialog").style.display = "block";
        }
 
 
 
 
        function submitUpdateForm() {
            document.getElementById("updateForm").submit();
        }
 
        function closeUpdateVendorDialog() {
            document.getElementById("updateVendorDialog").style.display = "none";
        }
        
        
     // Function to filter the table by service type and SLA
        function filterByServiceTypeAndSLA() {
    var serviceTypeInput = document.getElementById("serviceTypeFilter").value.trim().toUpperCase();
    var SLAInput = document.getElementById("SLAFilter").value.trim().toUpperCase();
    var table = document.getElementById("vendorTable");
    var tr = table.getElementsByTagName("tr");
    for (var i = 0; i < tr.length; i++) {
        var serviceTypeColumn = tr[i].getElementsByTagName("td")[3]; // Index of the "Service Type" column
        var SLAColumn = tr[i].getElementsByTagName("td")[4]; // Index of the "SLA" column
        if (serviceTypeColumn && SLAColumn) {
            var serviceTypeValue = serviceTypeColumn.textContent || serviceTypeColumn.innerText;
            var SLAValue = SLAColumn.textContent || SLAColumn.innerText;
            if ((serviceTypeInput === '' || serviceTypeValue.toUpperCase().indexOf(serviceTypeInput) > -1) &&
                (SLAInput === '' || SLAValue.toUpperCase().includes(SLAInput) || isWithinRange(SLAValue, SLAInput))) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
 
function isWithinRange(SLAValue, SLAInput) {
    var rangeValues = SLAInput.split('-').map(function(item) {
        return parseInt(item.trim());
    });
    var minRange = rangeValues[0];
    var maxRange = rangeValues[1];
    var SLAPercentage = parseFloat(SLAValue.replace('%', ''));
    return !isNaN(SLAPercentage) && SLAPercentage >= minRange && SLAPercentage <= maxRange;
}
 
 
 
 
 
    </script>
</head>
<body>
	<header>
        <h1>Manage Vendor</h1>
    </header>
    <div class="nav1">
        <ul class="nav1 ul">
            <li class="nav1 ul li"><a class="nav1 ul li a" href="Mangageuser.html">Manage Users</a></li>
            <li class="nav1 ul li"><a class="nav1 ul li a" href="VendorMainPage.jsp">Manage Vendors</a></li>
            <li class="nav1 ul li"><a class="nav1 ul li a" href="ViewServletAD">Manage Inventory</a></li>  
            <li class="nav1 ul li"><a class="nav1 ul li a" href="update">Manage Fault</a></li>
            <li class="nav1 ul li"><a class="nav1 ul li a" href="index.html">Logout</a></li>
        </ul>
    </div>
    <br/><br/>
 
    
    <!-- HTML -->
<div class="search-container">
        <input type="text" id="searchInput" placeholder="Search for vendors by name..." onkeyup="filterTable()">
        <img src="images/searchIcon.jpg" class="search-icon" alt="Search">
        <button class="btn btn-filter" onclick="toggleFilter()">Filter</button>
    </div>
 
    
    
    
    <!-- Filter Dropdowns -->
    <div id="filterOptions" style="display: none;">
        <select id="serviceTypeFilter" class="filterDropdown" onchange="filterByServiceTypeAndSLA()">
            <option value="">Filter by Service Type</option>
            <%
                try {
                    // Establish database connection
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/telecom", "root", "root");
 
                    // Prepare SQL query
                    PreparedStatement st = con.prepareStatement("SELECT DISTINCT serviceType FROM vendors");
 
                    // Execute query
                    ResultSet rs = st.executeQuery();
 
                    // Populate Service Type dropdown with data from ResultSet
                    while (rs.next()) {
                        String serviceType = rs.getString("serviceType");
            %>
            <option value="<%= serviceType %>"><%= serviceType %></option>
            <%
                    }
 
                    // Close resources
                    rs.close();
                    st.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Error occurred while retrieving data.");
                }
            %>
        </select>
        <select id="SLAFilter" class="filterDropdown" onchange="filterByServiceTypeAndSLA()">
    <option value="">Filter by SLA</option>
    <option value="0-30%">Less than 30%</option>
    <option value="30-50%">30% - 50%</option>
    <option value="50-70%">50% - 70%</option>
    <option value="70-90%">70% - 90%</option>
    <option value="90-95%">90% - 95%</option>
    <option value="95-98%">95% - 98%</option>
    <option value="98-100%">98% and above</option>
</select>
 
    </div>
 
 
    <button class="btn1 btn1-add" style="float: left; margin-left: 20px;" onclick="displayAddVendorDialog()">Add Vendor</button>
 
    <!-- Add Vendor Dialog -->
<div id="addVendorDialog" class="modal">
    <div class="modal-content">
        <form action="VenAdd" method="post" class="vendor-form">
            <span class="close" onclick="closeAddVendorDialog()">&times;</span>
            <h2 style="text-align:center;">Add New Vendor</h2>
            <%
            try {
                // Establish database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/telecom", "root", "root");
 
                // Prepare SQL query to fetch the maximum vendor ID
                PreparedStatement st = con.prepareStatement("SELECT CONCAT('V', MAX(CAST(SUBSTRING(vendorID, 2) AS UNSIGNED))) AS maxID FROM vendors");
 
                // Execute query
                ResultSet rs = st.executeQuery();
                String nextVendorID = "V101"; // Default value
                if (rs.next()) {
                    String maxID = rs.getString("maxID");
                    if (maxID != null) {
                        // Increment the maximum vendor ID for the next one
                        int nextID = Integer.parseInt(maxID.substring(1)) + 1;
                        nextVendorID = "V" + nextID;
                    }
                }
 
                // Close resources
                rs.close();
                st.close();
                con.close();
            %>
            <div class="form-group">
                <label for="vendorID">Vendor ID</label>
                <input style="background-color:#E9E4E5;" type="text" id="vendorID" name="vendorID" value="<%= nextVendorID %>" readonly>
            </div>
            <%
            } catch (Exception e) {
                e.printStackTrace();
            %>
            <div class="form-group">
                <label for="vendorID">Vendor ID</label>
                <input style="background-color:#E9E4E5;" type="text" id="vendorID" name="vendorID" value="Error fetching ID" readonly>
            </div>
            <% } %>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Enter the Vendor Name" required>
            </div>
            <div class="form-group">
                <label for="contact">Contact</label>
                <input type="text" id="contact" name="contact" placeholder="Enter the Contact ID" required>
            </div>
            <div class="form-group">
                <label for="serviceType">Service Type</label>
                <input type="text" id="serviceType" name="serviceType" placeholder="Enter the Service Type" required>
            </div>
            <div class="form-group">
                <label for="SLA">SLA</label>
                <input type="text" id="SLA" name="SLA" placeholder="Enter Your SLA" required>
            </div>
            <div class="form-group" style="text-align:center;">
                <button  type="submit" class="btn btn-add">Add</button>
            </div>
        </form>
    </div>
</div>
 
 
<br/><br/><br/>
    <h2 style="text-align:center;">Vendor Details</h2>
    
    
    <div id="updateVendorDialog" class="modal" style="display:none;">
        <div class="modal-content">
            <span class="close" onclick="closeUpdateVendorDialog()">&times;</span>
            <h2>Update Vendor Details</h2>
            <br/>
            <form id="updateForm" action="UpdateVendorDetailsServlet" method="post">
            <div class="form-group">
                <label for="vendorID_update">Vendor ID:</label>
                <input style="background-color:#E9E4E5;" type="text" id="vendorID_update" name="vendorID_update" readonly>
            </div>
            <div class="form-group">
                <label for="name_update">Name:</label>
                <input type="text" id="name_update" name="name_update">
            </div>
            <div class="form-group">
                <label for="contact_update">Contact:</label>
                <input type="text" id="contact_update" name="contact_update">
            </div>
            <div class="form-group">
                <label for="serviceType_update">Service Type:</label>
                <input type="text" id="serviceType_update" name="serviceType_update">
            </div>
            <div class="form-group">
                <label for="SLA_update">SLA:</label>
                <input type="text" id="SLA_update" name="SLA_update">
            </div>
            <div class="form-group" style="text-align:center;">
                <!-- Add similar labels and inputs for other fields -->
                <button class="btn btn-edit" type="button" onclick="submitUpdateForm()">Edit & Save</button>
            </div>
            </form>
        </div>
    </div>
    
    <table id="vendorTable" border="1">
    <thead>
        <tr>
            <th>Vendor ID</th>
            <th>Name</th>
            <th>Contact</th>
            <th>Service Type</th>
            <th>SLA</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                // Establish database connection
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/telecom", "root", "root");
 
                // Prepare SQL query
                PreparedStatement st = con.prepareStatement("select * from vendors");
 
                // Execute query
                ResultSet rs = st.executeQuery();
 
                // Create a list to store user objects
                ArrayList<VendorPojo> VendorList = new ArrayList<VendorPojo>();
 
                // Populate VendorList with data from ResultSet
                while (rs.next()) {
                    String vendorID = rs.getString("vendorID");
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    String serviceType = rs.getString("serviceType");
                    String SLA = rs.getString("SLA");
 
                    VendorPojo ven = new VendorPojo(vendorID, name, contact, serviceType, SLA);
                    VendorList.add(ven);
                }
 
                // Debugging: Check VendorList size after populating
                System.out.println("VendorList size: " + VendorList.size());
 
                // Display vendor data in HTML table
                for (VendorPojo ven : VendorList) {
        %>
        <tr>
            <td><%= ven.getVendorID() %></td>
            <td><%= ven.getName() %></td>
            <td><%= ven.getContact() %></td>
            <td><%= ven.getServiceType() %></td>
            <td><%= ven.getSLA() %></td>
            <td>
   				<a class="btn btn-edit" href="#" onclick="displayUpdateVendorDialog('<%= ven.getVendorID() %>', '<%= ven.getName() %>', '<%= ven.getContact() %>', '<%= ven.getServiceType() %>', '<%= ven.getSLA() %>')">Update</a>
 
   				
    			<a class="btn btn-delete" href="#" onclick="deleteVendor('<%= ven.getVendorID() %>')">Delete</a>
			</td>
 
        </tr>
        <%
                }
 
                // Close resources
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error occurred while retrieving data.");
            }
        %>
        </tbody>
    </table>
</body>
</html>
 
 
 