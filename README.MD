# MyScheduler 

---

Design Document  

- Elijah Klopfstein	
- Binod Dahal
- Sri Lakshmi Sai Surapaneni
- Rahul Shakya

## Introduction 

Need to make plans for friends, family, coworkers? Having trouble find time to meet? MyScheduler can help you:  

-	Record your weekly/reoccurring events.
-	Record other days and/or times you are unavailable. 
-	Plan events based on your free time.

Use your Android device to create your own personal availability and create events that have a certain amount of time needed.  And find days that you have that amount of time available. Receive alerts about upcoming events that you and your groups planned.  

## Storyboard

[MyScheduler Storyboard](https://projects.invisionapp.com/prototype/cklpu6okn004apc01g570a32w/play)

![schedule](https://user-images.githubusercontent.com/43417850/109439848-06f05580-79fe-11eb-892b-c61a1c2b9dd6.PNG)

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

### Requirement 101: Calendar is color-coded for availability

#### Scenario

As a user with set availability.  

#### Dependencies

The users have set their personal availability.

#### Examples  

2.1  
**Given** the user has set their availability to be unavailable on Mondays. 

**When** I look at the first Monday of the month.

**Then** I will see a red box behind the day. 

2.2  
**Given** the user has set their availability to be available on Wednesdays.

**When** I look at the first Wednesday of the month.

**Then** I will see a green box behind the day. 

2.3  
**Given** the user has set their availability to be unavailable on Tuesdays 9 am to 4 pm. 

**When** I look at the first Tuesday of the month.

**Then** I will see a yellow box behind the day. 

### Requirement 102: Add an Event to be planned

#### Scenario

As a user trying to create an event.

#### Dependencies

The users have created an account.
The user has made their schedule.

#### Examples
3.1  

**Given** I play a ttrpg for 4 hours.

**When** I create an event named “ttrpg” that lasts 4 hours.

**Then** I should see a new event for “ttrpg” that lasts 4 hours.

3.2  
**Given** I have dinner for 2 hours.

**When** I create an event named “dinner” that lasts 2 hours.

**Then** I should see a new event for “dinner” that lasts 2 hours.

3.3  
**Given** I try to plan my dinner for longer than a day.

**When** I create an event named “dinner” that lasts 25 hours.

**Then** I should get an error for I cannot create events that last longer than a day and the event will not be created.

3.3  
**Given** I try to plan my dinner for no time.

**When** I create an event named “dinner” that lasts 0 hours.

**Then** I should get an error for I cannot create events that takes no time and the event will not be created.

### Requirement 103: Plan an Event

#### Scenario

As a user trying to plan an event.

#### Dependencies

The users have created an account.
The user has added events to be planned.

#### Assumptions

The user has an event named “ttrpg” and one named “dinner”

#### Examples
4.1  

**Given** The user is available at 12pm for at least 2 hours on 2/4/21.

**When** I plan the dinner event for 2/4/21, at 12 pm, at 1 West Corry Street Cincinnati, Ohio.

**Then** I should see the zip code autocomplete to 45219 and will be able to plan the event.

4.2  

**Given** The user is available at 7pm for at least 4 hours on 1/2/21.

**When** I plan the ttrpg event for 1/2/21, at 5 pm, at 1 West Corry Street Cincinnati, Ohio.

**Then** I should see the zip code autocomplete to 45219 and will be able to plan the event.

4.3  

**Given** The user is available at 7pm for at least 4 hours on 1/2/21.

**When** I plan the ttrpg event for 1/2/21, at 5 pm, at 324 dfsafsd Street Cincinnati, Ohio.

**Then** I should get an error for the address not existing and the event should not be planned.

### Requirement 104: Delete an Event

#### Scenario

As a user trying to delete an event.

#### Dependencies

The users have created an account.
The user has added events to be planned.
The user has planned events.

#### Assumptions

The user has an event named “ttrpg” and one named “dinner”
The user has planned one of each

#### Examples
5.1  

**Given** The user has planned “dinner” at 12pm on 2/4/21.

**When** I hit delete on that planned event.

**Then** I should see the event is no longer planned.

5.2  

**Given** The user has planned “ttrpg” at 7pm on 1/2/21.

**When** I try to delete the “ttrpg” event type.

**Then** I should get an error because you must delete all instances of the event before deleting the type.

5.3  

**Given** The user has planned “ttrpg” at 7pm on 1/2/21.

**When** I hit delete on the planned event.

**Then** I should see the event is no longer planned.

## Class Diagram

![MySchedulerUML](https://user-images.githubusercontent.com/43417850/109440184-49fef880-79ff-11eb-8fb6-1be184b724b0.jpg)

## Scrum Roles

- DevOps/Product Owner/Scrum Master: Elijah Klopfstein  
- Frontend Developer: Sri Lakshmi Sai Surapaneni
- Integration Developer 1: Binod Dahal
- Integration Developer 2: Rahul Shakya

## Weekly Meeting

Tuesday and Thursday at 8 PM on Discord link emailed to those who need it.

