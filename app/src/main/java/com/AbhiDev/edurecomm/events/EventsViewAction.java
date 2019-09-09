package com.AbhiDev.edurecomm.events;


import java.util.List;

public interface EventsViewAction extends BaseViewAction {
    public void setEventsRecyclerView(List<Event> events);
}
