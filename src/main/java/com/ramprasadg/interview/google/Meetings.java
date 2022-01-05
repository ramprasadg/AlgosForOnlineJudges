package src.main.java.com.ramprasadg.interview.google;

import java.util.PriorityQueue;

/**
 * Definition for an interval. public class Interval { int start; int end;
 * Interval() { start = 0; end = 0; } Interval(int s, int e) { start = s; end =
 * e; } }
 */
public class Meetings {
    static final int START = 1;
    static final int END = 0;

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    private class Event {
        public int time;
        public int type; // end event is 0; start event is 1

        public Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }

    public int minMeetingRooms(Interval[] intervals) {
        PriorityQueue<Event> events = new PriorityQueue<Event>((a, b) -> (a.time != b.time) ? a.time - b.time : a.type - b.type);

        for (int i = 0; i < intervals.length; i++) {
            Interval interval = intervals[i];
            events.add(new Event(interval.start, START));
            events.add(new Event(interval.end, END));
        }

        int res = 0;
        int rooms = 0;

        while (!events.isEmpty()) {
            Event event = events.poll();
            if (event.type == START) {
                rooms++;
                res = Math.max(res, rooms);
            } else {
                rooms--;
            }
        }

        return res;
    }
}
