package uc.edu.klopfsea.groupscheduler.ui.main

class CustomCalendar {

    List<EventDay> events = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    events.add(new EventDay(calendar, R.drawable.sample_icon));
    CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
    calendarView.setEvents(events);

}
