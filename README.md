music-store
===========

a oracle / java based database project

# Phase 3

Oracle Music - Main Menu

1. Music Applications
2. Ordering Applications
3. Sales Applications
4. Administrative Applications -- Access restricted to Managers ONLY
5. Exit

## Music Applications - Menu

For item 1 (Music Applications) in the Main Menu, another menu would appear as shown below,
which allows a sales clerk to add a music title to the inventory, change information about any title
(based on ISBN) and query the music title information.

1. Add A Music Title
2. Query Music Information
3. Return to Main Menu

Select Item:

Each of these items would require further information about the music title to be entered from the
user. Adding music requires all parameters of the title to be entered into the database. *Note:
Changing music information stored in the database is not included for this project. To query
music information, the system must prompt the user to enter the desired search criteria and then
return the results in a tabular format.

##￼Order Applications - Menu

For item 2 (Ordering Applications) in the Main Menu, another menu would appear as shown
below, which allows a sales clerk to query the order data (based on order number, customer, or
vendor), or place a new order.

1. Query Order Information
2. Place New Order
3. Return to Main Menu

Select Item:

Each of these items would require further information about the order from the user. To query
an order, the user must enter search criteria, and results of the query must be returned in a tabular
format. To place an order, the order information must be prompted for and stored into the
database; if the order is for a customer, then customer information must be stored also.

## Sales Applications - Menu

For item 3 (Sales Applications) in the Main Menu, another menu would appear as shown below.
The first option allows a clerk to record the information in the database that a given music title(s)
has been sold. Appropriate customer information is also stored. The second option produces six
sales reports. Note that only managers are allowed to produce sales reports.

1. Sell A Music Title(s)
2. Return to Main Menu

Select Item:

The first choice is continuously used at the cash registers. No customer information is required
for a sale. A sale should allow for multiple music titles to be sold during one transaction.

## Administrative Applications - Menu

For item 4 (Administrative Applications) in the Main Menu, another menu would appear as
shown below. This menu allows the manager to add/change employee information.

1. Employee Information -- Access restricted to Managers ONLY
2. Return to Main Menu

Select Item:

The first item would require further information. The system should ask whether the user wants to
add new information or change existing information. To add information, the system should ask
for input and store it appropriately. To change information, the system should first display the
current information in the database for a particular record, then prompt the user to change the
appropriate information, and finally, store the changes.