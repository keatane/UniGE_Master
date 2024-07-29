# Gherkin test cases - Author: S4944382

## Feature: __SearchOwner__  
__Scenario__: A veterinarian searches an existing Owner
* __Given__ I am on the Home page of PetClinic
* __When__ I Click "Find Owners"
* __And__ I Insert the Owner's last name "Black"
* __And__ I Click the button "Find Owner"
* __Then__ I should see the Black Owner's info displayed

__Scenario__: A veterinarian searches a non-existing Owner
* __Given__ I am on the Home page of PetClinic
* __When__ I Click "Find Owners"
* __And__ I Insert the Owner's last name "ciao"
* __And__ I Click the button "Find Owner"
* __Then__ I should see a pop-up message showing "has not been found"

## Feature: __AddOwner__  
__Scenario__: A veterinarian wants to add an Owner
* __Given__ I am on the Home page of PetClinic
* __When__ I Click "Find Owners"
* __And__ I Click "Add Owner" 
* __And__ I complete the fields with [First Name], [Last Name], [Address], [City], [Telephone]
* __But__ No fields is empty nor a space has been inserted
* __And__ I click "Add Owner"
* __Then__ I should see a pop-up message in a new page showing the infos of the new Owner, with a green message "Owner created"

Examples:  
| First Name &nbsp;&nbsp;| Last Name &nbsp;&nbsp;| Address &nbsp;&nbsp;| City &nbsp;&nbsp;| Telephone &nbsp;&nbsp;|  
| Kevin &nbsp;&nbsp;| Cattaneo &nbsp;&nbsp;| aaa &nbsp;&nbsp;| aaa &nbsp;&nbsp;| 3335553334 &nbsp;&nbsp;|  
| Lucia &nbsp;&nbsp;| Andreis &nbsp;&nbsp;| bbb &nbsp;&nbsp;| bbb &nbsp;&nbsp;| 3335543331 &nbsp;&nbsp;|


## Feature: __ListVeterinarians__  
__Scenario__: An Owner wants to see the list of veterinarians  
* __Given__ I am on the Home page of PetClinic
* __When__ I Click "Veterinarians"
* __Then__ I should see the list of veterinarians, grouped by five rows each, with the first column as the name and the second as the specialties

## Feature: __ErrorPage__
__Scenario__: A web user search for a non-existing page in PetClinic website
* __Given__ I am on the Home page of PetClinic
* __When__ I search for "localhost:8080/ciao"
* __Then__ The error page loads and I should see an image of two pets and a message saying "Something happened"