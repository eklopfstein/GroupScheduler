# GroupScheduler 

---

Design Document  

Elijah Klopfstein	
Binod Dahal
Sri Lakshmi Sai Surapaneni

## Introduction 

Need to make plans for friends, family, coworkers? Having trouble find time to meet? GroupScheduler can help you:  

-	Record your weekly/reoccurring events.
-	Record other days and/or times you are unavailable. 
-	Join groups with your friends, family, or coworkers.
-	See the whole groups availability and find your mutual free time!  

Use your Android device to create your own personal availability.  Join groups and create events that have a certain amount of time needed.  And find days that you all have that amount of time available.   Receive alerts about upcoming events that you and your groups planned.  

## Storyboard

[GroupScheduler Storyboard]( https://projects.invisionapp.com/freehand/document/BG7G4TDMJ)

## Functional Requirements

### Requirement 100.0: Set Personal Availability

#### Scenario

As a user trying to make plans with groups, I need to set my own availability, both reoccurring weekly availability and special availability for certain days.

#### Examples
1.1  

**Given** I work on weekdays all day.

**When** I add availability event being the weekdays and have it last all day.

**Then** I should see an event for M,T,W,R,F with time of all day show up in my events
 
1.2  
**Given** I have the 12th of January 2021 off work.

**When** I add availability event being 1/12/2021 and have it before 4:30pm.

**Then** I should see an event for 12th in the month of January with time of before 4:30pm show up in my events.

1.3  
**Given** I have the 28th of January 2021 off work.

**When** I add availability event being 1/28/2021 and have it before 12am.

**Then** I should get an error because there would be no time before 12am on the same day and have no new event created.

1.4  
**Given** I have the 28th of January 2021 off work.

**When** I add availability event being 1/28/2021 and have it after 12pm.

**Then** I should get an error because there would be no time after 12pm on the same day and have no new event created.

### Requirement 101: Create a Group

#### Scenario

As a user trying to create groups to share our availability. 

#### Dependencies

The users have set their personal availability.
The users have created an account.

#### Examples  

2.1  
**Given** the user has created an account. 

**When** I try to create a group name “DnD” with the DnD logo as the group picture.

**Then** I will see a group created called “DnD” and my picture will be the group picture.

2.2  
**Given** the user has created an account.  

**When** I try to create a group name “Bland Group” with no group picture.

**Then** I will see a group created called “Bland Group” with no group picture.

2.3  
**Given** the user has created an account.  

**When** I try to create a group with no name or picture.

**Then** I will fail to create a group because names are required.

### Requirement 102: Add users and friends to a group.

#### Scenario

As a user in a group trying to add friends and other users to the group.

#### Dependencies

The users have created an account.
The user has created the group.

#### Assumptions

The user has friends in the app.

#### Examples
3.1  

**Given** A group called “DnD” has been made.

**When** I add my friend from the friends tab to the “DnD” group.

**Then** I should the friend I added in the list of members for the “DnD” group.
 
3.2  
**Given** A group called “DnD” has been made.

**When** I add realemail@outlook.com to the “DnD” group.

**Then** I should the user with realemail@outlook.com as their email added in the list of members for the “DnD” group.

3.3  
**Given** A group called “DnD” has been made.

**When** I add emailatoutlook.com to the “DnD” group.

**Then** I should get an error because this is not a real email address and there is no user with this as their name.

### Requirement 103: Add an Event to a Group

#### Scenario

As a user in a group trying to an event to a group.

#### Dependencies

The users have created an account.
The user has created the group.

#### Examples
4.1  

**Given** In the group we play a ttrpg for 4 hours.

**When** I create an event named “ttrpg” that lasts 4 hours.

**Then** I should see a new group event for “ttrpg” that lasts 4 hours.

4.2  
**Given** In the group we have dinner for 2 hours.

**When** I create an event named “dinner” that lasts 2 hours.

**Then** I should see a new group event for “dinner” that lasts 2 hours.

4.3  
**Given** I am in a group.

**When** I create an event named “dinner” that lasts 25 hours.

**Then** I should get an error for I cannot create events that last longer than a day and the event will not be created.

4.3  
**Given** I am in a group.

**When** I create an event named “dinner” that lasts 0 hours.

**Then** I should get an error for I cannot create events that takes no time and the event will not be created.

### Requirement 104: Plan an Event in a Group

#### Scenario

As a user in a group trying to plan an event.

#### Dependencies

The users have created an account.
The user has created the group.
The group has added events to the group.

#### Assumptions

The group has an event named “ttrpg” and one named “dinner”

#### Examples
5.1  

**Given** Everyone is available at 12pm for at least 2 hours on 2/4/21.

**When** I plan the dinner event for 2/4/21, at 12 pm, at 1 West Corry Street Cincinnati, Ohio.

**Then** I should see the zip code autocomplete to 45219 and will be able to plan the event.

5.2  

**Given** Everyone is available at 7pm for at least 4 hours on 1/2/21.

**When** I plan the ttrpg event for 1/2/21, at 5 pm, at 1 West Corry Street Cincinnati, Ohio.

**Then** I should see the zip code autocomplete to 45219 and will be able to plan the event.

5.3  

**Given** Everyone is available at 7pm for at least 4 hours on 1/2/21.

**When** I plan the ttrpg event for 1/2/21, at 5 pm, at 324 dfsafsd Street Cincinnati, Ohio.

**Then** I should get an error for the address not existing and the event should not be planned.

## Class Diagram



## Scrum Roles

- DevOps/Product Owner/Scrum Master: Elijah Klopfstein  
- Frontend Developer: Sri Lakshmi Sai Surapaneni
- Integration Developer 1: Binod Dahal
- Integration Developer 2: Rahul Shakya

## Weekly Meeting

Tuesday and Thursday at 8 PM on Discord link emailed to those who need it.

